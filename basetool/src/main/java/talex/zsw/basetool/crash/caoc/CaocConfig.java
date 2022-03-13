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

import android.app.Activity;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Modifier;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作用：错误信息收集
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CaocConfig implements Serializable
{

	@IntDef({BACKGROUND_MODE_CRASH, BACKGROUND_MODE_SHOW_CUSTOM, BACKGROUND_MODE_SILENT})
	@Retention(RetentionPolicy.SOURCE)
	private @interface BackgroundMode
	{}

	public static final int BACKGROUND_MODE_SILENT = 0;
	public static final int BACKGROUND_MODE_SHOW_CUSTOM = 1;
	public static final int BACKGROUND_MODE_CRASH = 2;

	private int backgroundMode = BACKGROUND_MODE_SHOW_CUSTOM;
	private boolean enabled = true;
	private boolean showErrorDetails = true;
	private boolean showRestartButton = true;
	private boolean trackActivities = false;
	private int minTimeBetweenCrashesMs = 3000;
	private Integer errorDrawable = null;
	private Class<? extends Activity> errorActivityClass = null;
	private Class<? extends Activity> restartActivityClass = null;
	private CustomActivityOnCrash.EventListener eventListener = null;

	@BackgroundMode public int getBackgroundMode()
	{
		return backgroundMode;
	}

	public void setBackgroundMode(@BackgroundMode int backgroundMode)
	{
		this.backgroundMode = backgroundMode;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}

	public boolean isShowErrorDetails()
	{
		return showErrorDetails;
	}

	public void setShowErrorDetails(boolean showErrorDetails)
	{
		this.showErrorDetails = showErrorDetails;
	}

	public boolean isShowRestartButton()
	{
		return showRestartButton;
	}

	public void setShowRestartButton(boolean showRestartButton)
	{
		this.showRestartButton = showRestartButton;
	}

	public boolean isTrackActivities()
	{
		return trackActivities;
	}

	public void setTrackActivities(boolean trackActivities)
	{
		this.trackActivities = trackActivities;
	}

	public int getMinTimeBetweenCrashesMs()
	{
		return minTimeBetweenCrashesMs;
	}

	public void setMinTimeBetweenCrashesMs(int minTimeBetweenCrashesMs)
	{
		this.minTimeBetweenCrashesMs = minTimeBetweenCrashesMs;
	}

	@Nullable @DrawableRes public Integer getErrorDrawable()
	{
		return errorDrawable;
	}

	public void setErrorDrawable(@Nullable @DrawableRes Integer errorDrawable)
	{
		this.errorDrawable = errorDrawable;
	}

	@Nullable public Class<? extends Activity> getErrorActivityClass()
	{
		return errorActivityClass;
	}

	public void setErrorActivityClass(@Nullable Class<? extends Activity> errorActivityClass)
	{
		this.errorActivityClass = errorActivityClass;
	}

	@Nullable public Class<? extends Activity> getRestartActivityClass()
	{
		return restartActivityClass;
	}

	public void setRestartActivityClass(@Nullable Class<? extends Activity> restartActivityClass)
	{
		this.restartActivityClass = restartActivityClass;
	}

	@Nullable public CustomActivityOnCrash.EventListener getEventListener()
	{
		return eventListener;
	}

	public void setEventListener(@Nullable CustomActivityOnCrash.EventListener eventListener)
	{
		this.eventListener = eventListener;
	}

	public static class Builder
	{
		private CaocConfig config;

		@NonNull public static Builder create()
		{
			Builder builder = new Builder();
			CaocConfig currentConfig = CustomActivityOnCrash.getConfig();

			CaocConfig config = new CaocConfig();
			config.backgroundMode = currentConfig.backgroundMode;
			config.enabled = currentConfig.enabled;
			config.showErrorDetails = currentConfig.showErrorDetails;
			config.showRestartButton = currentConfig.showRestartButton;
			config.trackActivities = currentConfig.trackActivities;
			config.minTimeBetweenCrashesMs = currentConfig.minTimeBetweenCrashesMs;
			config.errorDrawable = currentConfig.errorDrawable;
			config.errorActivityClass = currentConfig.errorActivityClass;
			config.restartActivityClass = currentConfig.restartActivityClass;
			config.eventListener = currentConfig.eventListener;

			builder.config = config;

			return builder;
		}

		/**
		 * Defines if the error activity must be launched when the app is on background.
		 * BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: launch the error activity when the app is in background,
		 * BackgroundMode.BACKGROUND_MODE_CRASH: launch the default system error when the app is in background,
		 * BackgroundMode.BACKGROUND_MODE_SILENT: crash silently when the app is in background,
		 * The default is BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM (the app will be brought to front when a crash occurs).
		 * <p>
		 * 定义错误活动是否必须在应用程序处于后台时启动。
		 * BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: 当应用程序处于后台时，启动错误Activity。
		 * BackgroundMode.BACKGROUND_MODE_CRASH: 当应用程序在后台时，启动默认的系统错误。
		 * BackgroundMode.BACKGROUND_MODE_SILENT: 当应用程序处于后台时，会无声地崩溃。
		 * 默认模式 BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM
		 */
		@NonNull public Builder backgroundMode(@BackgroundMode int backgroundMode)
		{
			config.backgroundMode = backgroundMode;
			return this;
		}

		/**
		 * Defines if CustomActivityOnCrash crash interception mechanism is enabled.
		 * Set it to true if you want CustomActivityOnCrash to intercept crashes,
		 * false if you want them to be treated as if the library was not installed.
		 * The default is true.
		 * <p>
		 * 定义是否启用CustomActivityOnCrash崩溃拦截机制。
		 * 如果你想让CustomActivityOnCrash拦截崩溃，将其设置为true。
		 * 如果你想让它们被当作没有安装库的情况来处理，则设置为false。
		 * 默认为true。
		 */
		@NonNull public Builder enabled(boolean enabled)
		{
			config.enabled = enabled;
			return this;
		}

		/**
		 * Defines if the error activity must shown the error details button.
		 * Set it to true if you want to show the full stack trace and device info,
		 * false if you want it to be hidden.
		 * The default is true.
		 * <p>
		 * 定义错误活动是否必须显示错误细节按钮。
		 * 如果你想显示完整的堆栈跟踪和设备信息，把它设置为true。
		 * 如果你希望它被隐藏，则设置为false。
		 * 默认是true。
		 */
		@NonNull public Builder showErrorDetails(boolean showErrorDetails)
		{
			config.showErrorDetails = showErrorDetails;
			return this;
		}

		/**
		 * Defines if the error activity should show a restart button.
		 * Set it to true if you want to show a restart button,
		 * false if you want to show a close button.
		 * Note that even if restart is enabled but you app does not have any launcher activities,
		 * a close button will still be used by the default error activity.
		 * The default is true.
		 * <p>
		 * 定义错误活动是否应该显示一个重启按钮。
		 * 如果你想显示一个重启按钮，把它设置为true。
		 * 如果你想显示一个关闭按钮，则设置为false。
		 * 注意，即使重启被启用，但你的应用程序没有任何启动器活动。
		 * 关闭按钮仍然会被默认的错误活动所使用。
		 * 默认是true。
		 */
		@NonNull public Builder showRestartButton(boolean showRestartButton)
		{
			config.showRestartButton = showRestartButton;
			return this;
		}

		/**
		 * Defines if the activities visited by the user should be tracked
		 * so they are reported when an error occurs.
		 * The default is false.
		 * <p>
		 * 定义是否应该跟踪用户访问的Activities
		 * 以便在发生错误时报告它们。
		 * 默认为false。
		 */
		@NonNull public Builder trackActivities(boolean trackActivities)
		{
			config.trackActivities = trackActivities;
			return this;
		}

		/**
		 * Defines the time that must pass between app crashes to determine that we are not
		 * in a crash loop. If a crash has occurred less that this time ago,
		 * the error activity will not be launched and the system crash screen will be invoked.
		 * The default is 3000.
		 * <p>
		 * 定义了应用程序崩溃之间必须经过的时间，以确定我们不在
		 * 在一个崩溃循环中。如果在这段时间内发生了崩溃。
		 * 错误活动将不会被启动，系统崩溃屏幕将被调用。
		 * 默认值是3000。
		 */
		@NonNull public Builder minTimeBetweenCrashesMs(int minTimeBetweenCrashesMs)
		{
			config.minTimeBetweenCrashesMs = minTimeBetweenCrashesMs;
			return this;
		}

		/**
		 * Defines which drawable to use in the default error activity image.
		 * Set this if you want to use an image other than the default one.
		 * The default is R.drawable.customactivityoncrash_error_image (a cute upside-down bug).
		 * <p>
		 * 定义在默认错误活动图像中使用的可画性。
		 * 如果你想使用默认图片以外的图片，请设置这个。
		 * 默认是R.drawable.customactivityoncrash_error_image。
		 */
		@NonNull public Builder errorDrawable(@Nullable @DrawableRes Integer errorDrawable)
		{
			config.errorDrawable = errorDrawable;
			return this;
		}

		/**
		 * Sets the error activity class to launch when a crash occurs.
		 * If null, the default error activity will be used.
		 * <p>
		 * 设置当崩溃发生时要启动的错误活动类。
		 * 如果为空，将使用默认的错误活动。
		 */
		@NonNull public Builder errorActivity(
			@Nullable Class<? extends Activity> errorActivityClass)
		{
			config.errorActivityClass = errorActivityClass;
			return this;
		}

		/**
		 * Sets the main activity class that the error activity must launch when a crash occurs.
		 * If not set or set to null, the default launch activity will be used.
		 * If your app has no launch activities and this is not set, the default error activity will close instead.
		 * <p>
		 * 设置错误Activity在发生崩溃时可以启动的主Activity类。
		 * 如果没有设置或设置为空，将使用默认的启动活动。
		 * 如果你的应用程序没有启动活动并且没有设置，那么默认的错误活动将被关闭。
		 */
		@NonNull public Builder restartActivity(
			@Nullable Class<? extends Activity> restartActivityClass)
		{
			config.restartActivityClass = restartActivityClass;
			return this;
		}

		/**
		 * Sets an event listener to be called when events occur, so they can be reported
		 * by the app as, for example, Google Analytics events.
		 * If not set or set to null, no events will be reported.
		 * <p>
		 * 设置一个事件监听器，当事件发生时被调用，因此它们可以被报告给
		 * 例如，由应用程序报告的Google Analytics事件。
		 * 如果不设置或设置为空，则不会报告任何事件。
		 *
		 * @param eventListener The event listener.
		 *                      事件监听器。
		 * @throws IllegalArgumentException if the eventListener is an inner or anonymous class
		 *                                  如果eventListener是一个内部或匿名的类
		 */
		@NonNull public Builder eventListener(
			@Nullable CustomActivityOnCrash.EventListener eventListener)
		{
			if(eventListener != null && eventListener.getClass().getEnclosingClass() != null &&
				!Modifier.isStatic(eventListener.getClass().getModifiers()))
			{
				throw new IllegalArgumentException("The event listener cannot be an inner or anonymous class, because it will need to be serialized. Change it to a class of its own, or make it a static inner class.");
			}
			else
			{
				config.eventListener = eventListener;
			}
			return this;
		}

		@NonNull public CaocConfig get()
		{
			return config;
		}

		public void apply()
		{
			CustomActivityOnCrash.setConfig(config);
		}
	}
}
