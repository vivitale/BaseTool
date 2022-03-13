/*
 * Copyright 2014-2017 Eduard Ereza Martínez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package talex.zsw.basetool.crash.caoc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/**
 * 作用：默认的错误注册器
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public final class CustomActivityOnCrash
{
	private final static String TAG = "TALE";
	// 传递给错误Activity的附加条件
	private static final String EXTRA_CONFIG = "cat.ereza.customactivityoncrash.EXTRA_CONFIG";
	private static final String EXTRA_STACK_TRACE
		= "cat.ereza.customactivityoncrash.EXTRA_STACK_TRACE";
	private static final String EXTRA_ACTIVITY_LOG
		= "cat.ereza.customactivityoncrash.EXTRA_ACTIVITY_LOG";

	// 一般常数
	private static final String INTENT_ACTION_ERROR_ACTIVITY
		= "cat.ereza.customactivityoncrash.ERROR";
	private static final String INTENT_ACTION_RESTART_ACTIVITY
		= "cat.ereza.customactivityoncrash.RESTART";
	private static final String CAOC_HANDLER_PACKAGE_NAME = "cat.ereza.customactivityoncrash";
	private static final String DEFAULT_HANDLER_PACKAGE_NAME = "com.android.internal.os";
	private static final int MAX_STACK_TRACE_SIZE = 131071; //128 KB - 1
	private static final int MAX_ACTIVITIES_IN_LOG = 50;

	// Shared preferences
	private static final String SHARED_PREFERENCES_FILE = "custom_activity_on_crash";
	private static final String SHARED_PREFERENCES_FIELD_TIMESTAMP = "last_crash_timestamp";

	// 内部变量
	@SuppressLint("StaticFieldLeak")
	// This is an application-wide component
	private static Application application;
	private static CaocConfig config = new CaocConfig();
	private static final Deque<String> activityLog = new ArrayDeque<>(MAX_ACTIVITIES_IN_LOG);
	private static WeakReference<Activity> lastActivityCreated = new WeakReference<>(null);
	private static boolean isInBackground = true;


	/**
	 * Installs CustomActivityOnCrash on the application using the default error activity.
	 * <p>
	 * 使用默认的错误Activity在应用程序上安装CustomActivityOnCrash。
	 *
	 * @param context Context to use for obtaining the ApplicationContext. Must not be null.
	 *                用于获取ApplicationContext的Context。不得为空。
	 */
	@RestrictTo(RestrictTo.Scope.LIBRARY) public static void install(
		@Nullable final Context context)
	{
		try
		{
			if(context == null)
			{
				Log.e(TAG, "初始化失败：Context为空!");
			}
			else
			{
				// INSTALL!
				final Thread.UncaughtExceptionHandler oldHandler
					= Thread.getDefaultUncaughtExceptionHandler();

				if(oldHandler != null &&
					oldHandler.getClass().getName().startsWith(CAOC_HANDLER_PACKAGE_NAME))
				{
					Log.e(TAG, "CustomActivityOnCrash 已经初始化，什么也没做!");
				}
				else
				{
					if(oldHandler != null &&
						!oldHandler.getClass().getName().startsWith(DEFAULT_HANDLER_PACKAGE_NAME))
					{
						Log.e(TAG, "IMPORTANT WARNING! You already have an UncaughtExceptionHandler, are you sure this is correct? If you use a custom UncaughtExceptionHandler, you must initialize it AFTER CustomActivityOnCrash! Installing anyway, but your original handler will not be called.");
						Log.e(TAG, "重要警告! 你已经有了一个UncaughtExceptionHandler，你确定这是对的吗？如果你使用一个自定义的UncaughtExceptionHandler，你必须在CustomActivityOnCrash之后初始化它。安装，但你原来的处理程序将不会被调用。");
					}

					application = (Application) context.getApplicationContext();

					// 我们定义了一个默认的异常处理程序，它可以做我们想要的事情，所以它可以从Crashlytics/ACRA中调用。
					Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler()
					{
						@Override public void uncaughtException(
							@NotNull Thread thread, @NotNull final Throwable throwable)
						{
							if(config.isEnabled())
							{
								Log.e(TAG, "应用程序已经崩溃，执行CustomActivityOnCrash的UncaughtExceptionHandler", throwable);

								if(hasCrashedInTheLastSeconds(application))
								{
									Log.e(TAG, "应用程序最近已经崩溃了，没有启动自定义错误Activity，因为我们可以进入一个重启循环。你确定你的应用程序不会在启动时直接崩溃吗？", throwable);
									if(oldHandler != null)
									{
										oldHandler.uncaughtException(thread, throwable);
										return;
									}
								}
								else
								{
									setLastCrashTimestamp(application, new Date().getTime());

									Class<? extends Activity> errorActivityClass
										= config.getErrorActivityClass();

									if(errorActivityClass == null)
									{
										errorActivityClass = guessErrorActivityClass(application);
									}

									if(isStackTraceLikelyConflictive(throwable, errorActivityClass))
									{
										Log.e(TAG, "你的应用程序类或你的错误Activity已经崩溃了，自定义Activity将不会被启动。");
										if(oldHandler != null)
										{
											oldHandler.uncaughtException(thread, throwable);
											return;
										}
									}
									else if(config.getBackgroundMode() ==
										CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM || !isInBackground)
									{

										final Intent intent
											= new Intent(application, errorActivityClass);
										StringWriter sw = new StringWriter();
										PrintWriter pw = new PrintWriter(sw);
										throwable.printStackTrace(pw);
										String stackTraceString = sw.toString();

										//Reduce data to 128KB so we don't get a TransactionTooLargeException when sending the intent.
										//The limit is 1MB on Android but some devices seem to have it lower.
										//将数据减少到128KB，这样我们在发送意图时就不会得到TransactionTooLargeException。
										//Android上的限制是1MB，但有些设备似乎更低。
										//See: http://developer.android.com/reference/android/os/TransactionTooLargeException.html
										//And: http://stackoverflow.com/questions/11451393/what-to-do-on-transactiontoolargeexception#comment46697371_12809171
										if(stackTraceString.length() > MAX_STACK_TRACE_SIZE)
										{
											String disclaimer = " [stack trace too large]";
											stackTraceString = stackTraceString.substring(0,
											                                              MAX_STACK_TRACE_SIZE-
												                                              disclaimer
													                                              .length())+
												disclaimer;
										}
										intent.putExtra(EXTRA_STACK_TRACE, stackTraceString);

										if(config.isTrackActivities())
										{
											StringBuilder activityLogString = new StringBuilder();
											while(!activityLog.isEmpty())
											{
												activityLogString.append(activityLog.poll());
											}
											intent.putExtra(EXTRA_ACTIVITY_LOG, activityLogString.toString());
										}

										if(config.isShowRestartButton() &&
											config.getRestartActivityClass() == null)
										{
											//我们可以设置restartActivityClass，因为应用程序现在就会终止。
											//并且在重新启动时，默认情况下将再次为空。
											config.setRestartActivityClass(guessRestartActivityClass(application));
										}

										intent.putExtra(EXTRA_CONFIG, config);
										intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
											                Intent.FLAG_ACTIVITY_CLEAR_TASK);
										if(config.getEventListener() != null)
										{
											config.getEventListener().onLaunchErrorActivity();
										}
										application.startActivity(intent);
									}
									else if(config.getBackgroundMode() ==
										CaocConfig.BACKGROUND_MODE_CRASH)
									{
										if(oldHandler != null)
										{
											oldHandler.uncaughtException(thread, throwable);
											return;
										}
										// If it is null (should not be), we let it continue and kill the process or it will be stuck
										// 如果它是空的（不应该是空的），我们让它继续，并杀死进程，否则它将被卡住。
									}
									//Else (BACKGROUND_MODE_SILENT): do nothing and let the following code kill the process
									//Else (BACKGROUND_MODE_SILENT): 什么都不做，让下面的代码杀死进程。
								}
								final Activity lastActivity = lastActivityCreated.get();
								if(lastActivity != null)
								{
									//We finish the activity, this solves a bug which causes infinite recursion.
									//我们关闭这个Activity，这解决了一个导致无限递归的错误。
									//See: https://github.com/ACRA/acra/issues/42
									lastActivity.finish();
									lastActivityCreated.clear();
								}
								killCurrentProcess();
							}
							else if(oldHandler != null)
							{
								oldHandler.uncaughtException(thread, throwable);
							}
						}
					});
					application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
					{
						int currentlyStartedActivities = 0;
						final DateFormat dateFormat
							= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

						@Override
						public void onActivityCreated(Activity activity, Bundle savedInstanceState)
						{
							if(activity.getClass() != config.getErrorActivityClass())
							{
								// Copied from ACRA:
								// Ignore activityClass because we want the last
								// application Activity that was started so that we can
								// explicitly kill it off.
								// 从ACRA中复制。
								// 忽略activityClass，因为我们要的是最后的
								// 忽略activityClass，因为我们需要最后一个被启动的应用程序Activity，这样我们就可以
								// 明确地将其关闭。
								lastActivityCreated = new WeakReference<>(activity);
							}
							if(config.isTrackActivities())
							{
								activityLog.add(dateFormat.format(new Date())+": "+
									                activity.getClass().getSimpleName()+
									                " created\n");
							}
						}

						@Override public void onActivityStarted(Activity activity)
						{
							currentlyStartedActivities++;
							isInBackground = (currentlyStartedActivities == 0);
							//Do nothing
						}

						@Override public void onActivityResumed(Activity activity)
						{
							if(config.isTrackActivities())
							{
								activityLog.add(dateFormat.format(new Date())+": "+
									                activity.getClass().getSimpleName()+
									                " resumed\n");
							}
						}

						@Override public void onActivityPaused(Activity activity)
						{
							if(config.isTrackActivities())
							{
								activityLog.add(dateFormat.format(new Date())+": "+
									                activity.getClass().getSimpleName()+
									                " paused\n");
							}
						}

						@Override public void onActivityStopped(Activity activity)
						{
							//Do nothing
							currentlyStartedActivities--;
							isInBackground = (currentlyStartedActivities == 0);
						}

						@Override
						public void onActivitySaveInstanceState(Activity activity, Bundle outState)
						{
							//Do nothing
						}

						@Override public void onActivityDestroyed(Activity activity)
						{
							if(config.isTrackActivities())
							{
								activityLog.add(dateFormat.format(new Date())+": "+
									                activity.getClass().getSimpleName()+
									                " destroyed\n");
							}
						}
					});
				}
				Log.i(TAG,"CustomActivityOnCrash已被安装。");
			}
		}
		catch(Throwable t)
		{
			Log.e(TAG, "在安装CustomActivityOnCrash时发生了一个未知的错误，它可能没有被正确初始化。如果需要，请将此作为一个错误报告。", t);
		}
	}

	/**
	 * Given an Intent, returns the stack trace extra from it.
	 * <p>
	 * 给定一个Intent，返回它的堆栈跟踪。
	 *
	 * @param intent The Intent. Must not be null.
	 *               intent 不得为空。
	 * @return The stacktrace, or null if not provided.
	 * 堆栈跟踪，如果没有提供，则为空。
	 */
	@NonNull public static String getStackTraceFromIntent(@NonNull Intent intent)
	{
		return intent.getStringExtra(CustomActivityOnCrash.EXTRA_STACK_TRACE);
	}

	/**
	 * Given an Intent, returns the config extra from it.
	 * <p>
	 * 给定一个Intent，返回它的配置外。
	 *
	 * @param intent The Intent. Must not be null.
	 *               intent 不得为空。
	 * @return The config, or null if not provided.
	 * 配置，如果没有提供，则为空。
	 */
	@NonNull public static CaocConfig getConfigFromIntent(@NonNull Intent intent)
	{
		return (CaocConfig) intent.getSerializableExtra(CustomActivityOnCrash.EXTRA_CONFIG);
	}

	/**
	 * Given an Intent, returns the activity log extra from it.
	 * <p>
	 * 给定一个Intent，返回它的Activity日志外。
	 *
	 * @param intent The Intent. Must not be null.
	 *               intent 不得为空。
	 * @return The activity log, or null if not provided.
	 * Activity日志，如果没有提供，则为空。
	 */
	@Nullable public static String getActivityLogFromIntent(@NonNull Intent intent)
	{
		return intent.getStringExtra(CustomActivityOnCrash.EXTRA_ACTIVITY_LOG);
	}

	/**
	 * Given an Intent, returns several error details including the stack trace extra from the intent.
	 * <p>
	 * 给定一个Intent，返回若干错误细节，包括从Intent中获得的堆栈跟踪。
	 *
	 * @param context A valid context. Must not be null.
	 *                context 不得为空。
	 * @param intent  The Intent. Must not be null.
	 *                intent 不得为空。
	 * @return The full error details.
	 * 完整的错误细节。
	 */
	@NonNull public static String getAllErrorDetailsFromIntent(
		@NonNull Context context, @NonNull Intent intent)
	{
		// I don't think that this needs localization because it's a development string...
		// 我不认为这需要本地化，因为它是一个开发字符串...

		Date currentDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

		//Get build date
		String buildDateAsString = getBuildDateAsString(context, dateFormat);

		//Get app version
		String versionName = getVersionName(context);

		String errorDetails = "";

		errorDetails += "Build version: "+versionName+" \n";
		if(buildDateAsString != null)
		{
			errorDetails += "Build date: "+buildDateAsString+" \n";
		}
		errorDetails += "Current date: "+dateFormat.format(currentDate)+" \n";
		//Added a space between line feeds to fix #18.
		//Ideally, we should not use this method at all... It is only formatted this way because of coupling with the default error activity.
		//We should move it to a method that returns a bean, and let anyone format it as they wish.
		//在换行之间添加了一个空格，以修复#18。
		//事实上，我们根本就不应该使用这个方法... 它之所以被这样格式化，只是因为它与默认的错误Activity有耦合关系。
		//我们应该把它移到一个返回Bean的方法中，让任何人按自己的意愿来格式化它。
		errorDetails += "Device: "+getDeviceModelName()+" \n \n";
		errorDetails += "Stack trace:  \n";
		errorDetails += getStackTraceFromIntent(intent);

		String activityLog = getActivityLogFromIntent(intent);

		if(activityLog != null)
		{
			errorDetails += "\nUser actions: \n";
			errorDetails += activityLog;
		}
		return errorDetails;
	}

	/**
	 * Given an Intent, restarts the app and launches a startActivity to that intent.
	 * The flags NEW_TASK and CLEAR_TASK are set if the Intent does not have them, to ensure
	 * the app stack is fully cleared.
	 * If an event listener is provided, the restart app event is invoked.
	 * Must only be used from your error activity.
	 * <p>
	 * 给定一个Intent，重新启动应用程序，并对该Intent启动一个startActivity。
	 * 如果该Intent没有NEW_TASK和CLEAR_TASK标志，则设置这两个标志，以确保
	 * 应用程序堆栈被完全清除。
	 * 如果提供了一个事件监听器，重启应用程序事件就会被调用。
	 * 必须只从你的错误Activity中使用。
	 *
	 * @param activity The current error activity. Must not be null.
	 *                 当前的错误Activity。不得为空。
	 * @param intent   The Intent. Must not be null.
	 *                 intent 不得为空。
	 * @param config   The config object as obtained by calling getConfigFromIntent.
	 *                 通过调用getConfigFromIntent获得的配置对象。
	 */
	public static void restartApplicationWithIntent(
		@NonNull Activity activity, @NonNull Intent intent, @NonNull CaocConfig config)
	{
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |
			                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		if(intent.getComponent() != null)
		{
			// If the class name has been set, we force it to simulate a Launcher launch.
			// If we don't do this, if you restart from the error activity, then press home,
			// and then launch the activity from the launcher, the main activity appears twice on the backstack.
			// This will most likely not have any detrimental effect because if you set the Intent component,
			// if will always be launched regardless of the actions specified here.
			// 如果类的名字已经设置好了，我们强制它模拟Launcher的启动。
			// 如果我们不这样做，如果你从错误的Activity中重新启动，然后按主页。
			// 然后再从启动器中启动Activity，主Activity就会在后台出现两次。
			// 这很可能不会有任何不利的影响，因为如果你设置了Intent组件。
			// if将总是被启动，而不考虑这里指定的动作。
			intent.setAction(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
		}
		if(config.getEventListener() != null)
		{
			config.getEventListener().onRestartAppFromErrorActivity();
		}
		activity.finish();
		activity.startActivity(intent);
		killCurrentProcess();
	}

	public static void restartApplication(@NonNull Activity activity, @NonNull CaocConfig config)
	{
		Intent intent = new Intent(activity, config.getRestartActivityClass());
		restartApplicationWithIntent(activity, intent, config);
	}

	/**
	 * Closes the app.
	 * If an event listener is provided, the close app event is invoked.
	 * Must only be used from your error activity.
	 * <p>
	 * 关闭应用程序。
	 * 如果提供了一个事件监听器，关闭应用程序的事件会被调用。
	 * 必须只在你的错误Activity中使用。
	 *
	 * @param activity The current error activity. Must not be null.
	 *                 当前的错误Activity。不得为空。
	 * @param config   The config object as obtained by calling getConfigFromIntent.
	 *                 通过调用getConfigFromIntent获得的配置对象。
	 */
	public static void closeApplication(@NonNull Activity activity, @NonNull CaocConfig config)
	{
		if(config.getEventListener() != null)
		{
			config.getEventListener().onCloseAppFromErrorActivity();
		}
		activity.finish();
		killCurrentProcess();
	}

	/// INTERNAL METHODS NOT TO BE USED BY THIRD PARTIES

	/**
	 * INTERNAL method that returns the current configuration of the library.
	 * If you want to check the config, use CaocConfig.Builder.get();
	 * <p>
	 * INTERNAL方法，返回库的当前配置。
	 * 如果你想检查配置，请使用CaocConfig.Builder.get()。
	 *
	 * @return the current configuration
	 * 当前配置
	 */
	@RestrictTo(RestrictTo.Scope.LIBRARY) @NonNull public static CaocConfig getConfig()
	{
		return config;
	}

	/**
	 * INTERNAL method that sets the configuration of the library.
	 * You must not use this, use CaocConfig.Builder.apply()
	 * <p>
	 * INTERNAL方法，设置库的配置。
	 * 你必须不使用这个，使用CaocConfig.Builder.apply()
	 *
	 * @param config the configuration to use
	 *               使用的配置
	 */
	@RestrictTo(RestrictTo.Scope.LIBRARY) public static void setConfig(@NonNull CaocConfig config)
	{
		CustomActivityOnCrash.config = config;
	}

	/**
	 * INTERNAL method that checks if the stack trace that just crashed is conflictive. This is true in the following scenarios:
	 * - The application has crashed while initializing (handleBindApplication is in the stack)
	 * - The error activity has crashed (activityClass is in the stack)
	 * <p>
	 * INTERNAL方法，检查刚刚崩溃的堆栈跟踪是否有冲突。这在以下情况下是真的。
	 * - 应用程序在初始化时崩溃（handleBindApplication在堆栈中）。
	 * - 错误的Activity已经崩溃（activityClass在堆栈中）。
	 *
	 * @param throwable     The throwable from which the stack trace will be checked
	 *                      将检查堆栈跟踪的可抛物。
	 * @param activityClass The activity class to launch when the app crashes
	 *                      当应用程序崩溃时要启动的Activity类
	 * @return true if this stack trace is conflictive and the activity must not be launched, false otherwise
	 * 如果这个堆栈跟踪是冲突的，Activity必须不被启动，则为true，否则为false。
	 */
	private static boolean isStackTraceLikelyConflictive(
		@NonNull Throwable throwable, @NonNull Class<? extends Activity> activityClass)
	{
		do
		{
			StackTraceElement[] stackTrace = throwable.getStackTrace();
			for(StackTraceElement element : stackTrace)
			{
				if((element.getClassName().equals("android.app.ActivityThread") &&
					element.getMethodName().equals("handleBindApplication")) ||
					element.getClassName().equals(activityClass.getName()))
				{
					return true;
				}
			}
		}
		while((throwable = throwable.getCause()) != null);
		return false;
	}

	/**
	 * INTERNAL method that returns the build date of the current APK as a string, or null if unable to determine it.
	 * <p>
	 * 内部方法，以字符串形式返回当前APK的构建日期，如果无法确定则为空。
	 *
	 * @param context    A valid context. Must not be null.
	 *                   一个有效的context。不得为空。
	 * @param dateFormat DateFormat to use to convert from Date to String
	 *                   用来将日期转换为字符串的DateFormat
	 * @return The formatted date, or "Unknown" if unable to determine it.
	 * 格式化的日期，如果无法确定，则为 "未知"。
	 */
	@Nullable private static String getBuildDateAsString(
		@NonNull Context context, @NonNull DateFormat dateFormat)
	{
		long buildDate;
		try
		{
			ApplicationInfo ai = context
				.getPackageManager()
				.getApplicationInfo(context.getPackageName(), 0);
			ZipFile zf = new ZipFile(ai.sourceDir);

			//If this failed, try with the old zip method
			ZipEntry ze = zf.getEntry("classes.dex");
			buildDate = ze.getTime();


			zf.close();
		}
		catch(Exception e)
		{
			buildDate = 0;
		}

		if(buildDate > 312764400000L)
		{
			return dateFormat.format(new Date(buildDate));
		}
		else
		{
			return null;
		}
	}

	/**
	 * INTERNAL method that returns the version name of the current app, or null if unable to determine it.
	 * <p>
	 * 内部方法，返回当前应用程序的版本名称，如果无法确定则为空。
	 *
	 * @param context A valid context. Must not be null.
	 *                一个有效的Context。不得为空。
	 * @return The version name, or "Unknown if unable to determine it.
	 * 版本名称，如果无法确定，则为 "未知"。
	 */
	@NonNull private static String getVersionName(Context context)
	{
		try
		{
			PackageInfo packageInfo = context
				.getPackageManager()
				.getPackageInfo(context.getPackageName(), 0);
			return packageInfo.versionName;
		}
		catch(Exception e)
		{
			return "Unknown";
		}
	}

	/**
	 * INTERNAL method that returns the device model name with correct capitalization.
	 * Taken from: http://stackoverflow.com/a/12707479/1254846
	 * <p>
	 * INTERNAL方法，返回具有正确大写字母的设备型号名称。
	 *
	 * @return The device model name (i.e., "LGE Nexus 5")
	 * 设备型号名称（例如，"LGE Nexus 5"）。
	 */
	@NonNull private static String getDeviceModelName()
	{
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		if(model.startsWith(manufacturer))
		{
			return capitalize(model);
		}
		else
		{
			return capitalize(manufacturer)+" "+model;
		}
	}

	/**
	 * INTERNAL method that capitalizes the first character of a string
	 * <p>
	 * 内部方法，将一个字符串的第一个字符大写。
	 *
	 * @param s The string to capitalize
	 * @return The capitalized string
	 */
	@NonNull private static String capitalize(@Nullable String s)
	{
		if(s == null || s.length() == 0)
		{
			return "";
		}
		char first = s.charAt(0);
		if(Character.isUpperCase(first))
		{
			return s;
		}
		else
		{
			return Character.toUpperCase(first)+s.substring(1);
		}
	}

	/**
	 * INTERNAL method used to guess which activity must be called from the error activity to restart the app.
	 * It will first get activities from the AndroidManifest with intent filter <action android:name="cat.ereza.customactivityoncrash.RESTART" />,
	 * if it cannot find them, then it will get the default launcher.
	 * If there is no default launcher, this returns null.
	 * <p>
	 * INTERNAL方法用于猜测必须从错误Activity中调用哪个Activity来重新启动应用程序。
	 * 它将首先从AndroidManifest中获取带有意图过滤器 <action android:name="cat.ereza.customactivityoncrash.RESTART" /> 的Activity。
	 * 如果找不到它们，它就会得到默认的启动器。
	 * 如果没有默认的启动器，这将返回null。
	 *
	 * @param context A valid context. Must not be null.
	 *                一个有效的Context。不得为空。
	 * @return The guessed restart activity class, or null if no suitable one is found
	 * 猜测的重启Activity类，如果没有找到合适的，则为空。
	 */
	@Nullable private static Class<? extends Activity> guessRestartActivityClass(
		@NonNull Context context)
	{
		Class<? extends Activity> resolvedActivityClass;

		// 如果定义了行动，则使用该
		resolvedActivityClass = getRestartActivityClassWithIntentFilter(context);

		// 否则，获得默认的启动器Activity
		if(resolvedActivityClass == null)
		{
			resolvedActivityClass = getLauncherActivity(context);
		}

		return resolvedActivityClass;
	}

	/**
	 * INTERNAL method used to get the first activity with an intent-filter <action android:name="cat.ereza.customactivityoncrash.RESTART" />,
	 * If there is no activity with that intent filter, this returns null.
	 * <p>
	 * INTERNAL方法用于获取具有意图过滤器的第一个Activity <action android:name="cat.ereza.customactivityoncrash.RESTART" />。
	 * 如果没有具有该意图过滤器的Activity，则返回null。
	 *
	 * @param context A valid context. Must not be null.
	 *                一个有效的Context。不得为空。
	 * @return A valid activity class, or null if no suitable one is found
	 * 一个有效的Activity类别，如果没有找到合适的，则为空。
	 */
	@SuppressWarnings("unchecked") @Nullable
	private static Class<? extends Activity> getRestartActivityClassWithIntentFilter(
		@NonNull Context context)
	{
		Intent searchedIntent = new Intent()
			.setAction(INTENT_ACTION_RESTART_ACTIVITY)
			.setPackage(context.getPackageName());
		@SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> resolveInfos = context
			.getPackageManager()
			.queryIntentActivities(searchedIntent, PackageManager.GET_RESOLVED_FILTER);

		if(resolveInfos != null && resolveInfos.size() > 0)
		{
			ResolveInfo resolveInfo = resolveInfos.get(0);
			try
			{
				return (Class<? extends Activity>) Class.forName(resolveInfo.activityInfo.name);
			}
			catch(ClassNotFoundException e)
			{
				// 不应该发生，把它打印到日志中去!
				Log.e(TAG, "通过意图过滤器解析重启Activity类时失败了，堆栈跟踪如下", e);
			}
		}

		return null;
	}

	/**
	 * INTERNAL method used to get the default launcher activity for the app.
	 * If there is no launchable activity, this returns null.
	 * <p>
	 * 用于获取应用程序的默认启动器Activity的内部方法。
	 * 如果没有可启动的Activity，这个方法返回null。
	 *
	 * @param context A valid context. Must not be null.
	 *                一个有效的Context。不得为空。
	 * @return A valid activity class, or null if no suitable one is found
	 * 一个有效的Activity类别，如果没有找到合适的，则为空。
	 */
	@SuppressWarnings("unchecked") @Nullable
	private static Class<? extends Activity> getLauncherActivity(@NonNull Context context)
	{
		Intent intent = context
			.getPackageManager()
			.getLaunchIntentForPackage(context.getPackageName());
		if(intent != null)
		{
			try
			{
				return (Class<? extends Activity>) Class.forName(intent
					                                                 .getComponent()
					                                                 .getClassName());
			}
			catch(ClassNotFoundException e)
			{
				//Should not happen, print it to the log!
				Log.e(TAG, "在通过getLaunchIntentForPackage解析重启Activity类时失败了，堆栈跟踪如下!", e);
			}
		}

		return null;
	}

	/**
	 * INTERNAL method used to guess which error activity must be called when the app crashes.
	 * It will first get activities from the AndroidManifest with intent filter <action android:name="cat.ereza.customactivityoncrash.ERROR" />,
	 * if it cannot find them, then it will use the default error activity.
	 * <p>
	 * INTERNAL方法用于猜测应用程序崩溃时必须调用哪个错误Activity。
	 * 它将首先从AndroidManifest中获取带有意图过滤器 <action android:name="cat.ereza.customactivityoncrash.ERROR" /> 的Activity。
	 * 如果它找不到这些Activity，那么它将使用默认的错误Activity。
	 *
	 * @param context A valid context. Must not be null.
	 *                一个有效的Context。不得为空。
	 * @return The guessed error activity class, or the default error activity if not found
	 * 猜测的错误Activity类，如果没有找到，则是默认的错误Activity。
	 */
	@NonNull private static Class<? extends Activity> guessErrorActivityClass(
		@NonNull Context context)
	{
		Class<? extends Activity> resolvedActivityClass;

		//If action is defined, use that
		resolvedActivityClass = getErrorActivityClassWithIntentFilter(context);

		//Else, get the default error activity
		if(resolvedActivityClass == null)
		{
			resolvedActivityClass = DefaultErrorActivity.class;
		}

		return resolvedActivityClass;
	}

	/**
	 * INTERNAL method used to get the first activity with an intent-filter <action android:name="cat.ereza.customactivityoncrash.ERROR" />,
	 * If there is no activity with that intent filter, this returns null.
	 * <p>
	 * INTERNAL方法用于获取具有意图过滤器的第一个Activity <action android:name="cat.ereza.customactivityoncrash.ERROR" />。
	 * 如果没有具有该意图过滤器的Activity，则返回null。
	 *
	 * @param context A valid context. Must not be null.
	 *                一个有效的Context。不得为空。
	 * @return A valid activity class, or null if no suitable one is found
	 * 一个有效的Activity类别，如果没有找到合适的，则为空。
	 */
	@SuppressWarnings("unchecked") @Nullable
	private static Class<? extends Activity> getErrorActivityClassWithIntentFilter(
		@NonNull Context context)
	{
		Intent searchedIntent = new Intent()
			.setAction(INTENT_ACTION_ERROR_ACTIVITY)
			.setPackage(context.getPackageName());
		@SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> resolveInfos = context
			.getPackageManager()
			.queryIntentActivities(searchedIntent, PackageManager.GET_RESOLVED_FILTER);

		if(resolveInfos != null && resolveInfos.size() > 0)
		{
			ResolveInfo resolveInfo = resolveInfos.get(0);
			try
			{
				return (Class<? extends Activity>) Class.forName(resolveInfo.activityInfo.name);
			}
			catch(ClassNotFoundException e)
			{
				//Should not happen, print it to the log!
				Log.e(TAG, "通过意图过滤器解析错误Activity类时失败了，堆栈跟踪如下", e);
			}
		}

		return null;
	}

	/**
	 * INTERNAL method that kills the current process.
	 * It is used after restarting or killing the app.
	 * <p>
	 * 杀死当前进程的内部方法。
	 * 它是在重启或杀死应用程序后使用的。
	 */
	private static void killCurrentProcess()
	{
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(10);
	}

	/**
	 * INTERNAL method that stores the last crash timestamp
	 * <p>
	 * 内部方法，存储最后一次崩溃的时间戳。
	 *
	 * @param timestamp The current timestamp.
	 *                  当前的时间戳。
	 */
	@SuppressLint("ApplySharedPref")
	//This must be done immediately since we are killing the app
	private static void setLastCrashTimestamp(@NonNull Context context, long timestamp)
	{
		context
			.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
			.edit()
			.putLong(SHARED_PREFERENCES_FIELD_TIMESTAMP, timestamp)
			.commit();
	}

	/**
	 * INTERNAL method that gets the last crash timestamp
	 * <p>
	 * 获得最后一次崩溃时间戳的内部方法
	 *
	 * @return The last crash timestamp, or -1 if not set.
	 * 最后一次崩溃的时间戳，如果没有设置，则为-1。
	 */
	private static long getLastCrashTimestamp(@NonNull Context context)
	{
		return context
			.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE)
			.getLong(SHARED_PREFERENCES_FIELD_TIMESTAMP, -1);
	}

	/**
	 * INTERNAL method that tells if the app has crashed in the last seconds.
	 * This is used to avoid restart loops.
	 * <p>
	 * INTERNAL方法，告诉人们应用程序在过去的几秒钟内是否崩溃了。
	 * 这是用来避免重启循环的。
	 *
	 * @return true if the app has crashed in the last seconds, false otherwise.
	 * 如果应用程序在过去几秒钟内崩溃，则为true，否则为false。
	 */
	private static boolean hasCrashedInTheLastSeconds(@NonNull Context context)
	{
		long lastTimestamp = getLastCrashTimestamp(context);
		long currentTimestamp = new Date().getTime();

		return (lastTimestamp <= currentTimestamp &&
			currentTimestamp-lastTimestamp < config.getMinTimeBetweenCrashesMs());
	}

	/**
	 * Interface to be called when events occur, so they can be reported
	 * by the app as, for example, Google Analytics events.
	 * <p>
	 * 当事件发生时要调用的接口，因此它们可以被报告给
	 * 例如，由应用程序作为谷歌分析事件报告。
	 */
	public interface EventListener extends Serializable
	{
		void onLaunchErrorActivity();

		void onRestartAppFromErrorActivity();

		void onCloseAppFromErrorActivity();
	}
}
