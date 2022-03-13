package talex.zsw.basecore.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import talex.zsw.basecore.R;
import talex.zsw.basecore.crash.caoc.CaocConfig;
import talex.zsw.basecore.interfaces.OnSimpleListener;
import talex.zsw.basecore.crash.cockroach.Cockroach;

/**
 * 作用：Tool的常用工具类
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class Tool
{

	@SuppressLint("StaticFieldLeak") private static Context context;
	private boolean isDebug = false;

	public boolean isDebug()
	{
		return isDebug;
	}

	public void setDebug(boolean debug)
	{
		isDebug = debug;
	}

	/**
	 * 初始化工具类
	 *
	 * @param context 上下文
	 * @param isDebu
	 */
	public static void init(Context context, boolean isDebu)
	{
		init(context, isDebu, false);
	}

	/**
	 * 初始化工具类
	 *
	 * @param context        上下文
	 * @param isDebu
	 * @param isMulitProcess true 多进程共享数据  fasle 单进程存储数据
	 */
	public static void init(Context context, boolean isDebu, boolean isMulitProcess)
	{
		Tool.context = context.getApplicationContext();
		if(isMulitProcess)
		{
			SpTool.initMulitProcess(context);
		}
		else
		{
			SpTool.init(context);
		}
		if(!isDebu)
		{
			CrashTool.init(context);
			LogTool.getConfig().setConsoleSwitch(false);
		}
	}

	/**
	 * 永不崩溃的APP——Crash防护
	 */
	public static void initCockroah()
	{
		Cockroach.install(new Cockroach.ExceptionHandler()
		{
			@Override public void handlerException(final Thread thread, final Throwable throwable)
			{
				new Handler(Looper.getMainLooper()).post(new Runnable()
				{
					@Override public void run()
					{
						try
						{
							LogTool.e(thread+"\n"+throwable.toString());
							throwable.printStackTrace();
						}
						catch(Throwable ignored)
						{
						}
					}
				});
			}
		});
	}

	/**
	 * 避免闪退
	 *
	 * @param errorRes 错误提示图标,不传则显示一个 bug
	 * @param restartActivity      重启的Activity
	 */
	public static void initCaoc(int errorRes, Class<? extends Activity> restartActivity)
	{
		CaocConfig.Builder builder = CaocConfig.Builder.create();
		builder.backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
		       .enabled(true) //是否启动全局异常捕获
		       .showErrorDetails(true) //是否显示错误详细信息
		       .trackActivities(true) //是否跟踪Activity
		       .minTimeBetweenCrashesMs(2000); //崩溃的间隔时间(毫秒)
		if(errorRes != 0)
		{
			builder.errorDrawable(errorRes); //错误图标
		}
		if(restartActivity != null)
		{
			builder.showRestartButton(true) //是否显示重启按钮
			       .restartActivity(restartActivity); //重新启动后的activity
		}
		// builder.errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
		//         .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
		builder.apply();
	}

	/**
	 * 结束工具类
	 */
	public static void uninstall()
	{
		Cockroach.uninstall();
		ActivityTool.AppExit(context);
	}

	/**
	 * 在某种获取不到 Context 的情况下，即可以使用才方法获取 Context
	 * <p>
	 * 获取ApplicationContext
	 *
	 * @return ApplicationContext
	 */
	public static Context getContext()
	{
		if(context != null)
		{
			return context.getApplicationContext();
		}
		throw new NullPointerException("请先调用init()方法");
	}

	//---------------------------------------------------------------------------------------------- 其他工具类

	// ################################################################
	// ##                          延时任务                           ##
	// ################################################################

	/**
	 * 延时任务
	 *
	 * @param delayTime        延时时间
	 * @param onSimpleListener 延时后调用方法
	 */
	public static void delayToDo(long delayTime, final OnSimpleListener onSimpleListener)
	{
		try
		{
			new Handler().postDelayed(new Runnable()
			{
				@Override public void run()
				{
					//execute the task
					onSimpleListener.doSomething();
				}
			}, delayTime);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	// ################################################################
	// ##                           倒计时                            ##
	// ################################################################

	private static android.os.CountDownTimer timer;

	/**
	 * 倒计时
	 *
	 * @param textView 控件
	 * @param waitTime 倒计时总时长
	 * @param interval 倒计时的间隔时间
	 * @param hint     倒计时完毕时显示的文字
	 * @param listener 结束时调用的接口
	 */
	public static void countDown(final TextView textView, long waitTime, long interval, final String hint, OnSimpleListener listener)
	{
		String format = DataTool.getString(R.string.tool_count_down);
		countDown(textView, waitTime, interval, format, hint, listener);
	}

	/**
	 * 倒计时
	 *
	 * @param textView 控件
	 * @param waitTime 倒计时总时长
	 * @param interval 倒计时的间隔时间
	 * @param format   计时器的文本格式 (如 剩下 %d 秒)
	 * @param hint     倒计时完毕时显示的文字
	 * @param listener 结束时调用的接口
	 */
	public static void countDown(final TextView textView, long waitTime, long interval, final String format, final String hint, final OnSimpleListener listener)
	{
		try
		{
			textView.setEnabled(false);
			timer = new android.os.CountDownTimer(waitTime, interval)
			{

				@SuppressLint("DefaultLocale") @Override public void onTick(long millisUntilFinished)
				{
					String data = String.format(format, millisUntilFinished/1000);

					if(textView != null)
					{
						textView.setText(data);
					}
				}

				@Override public void onFinish()
				{
					if(textView != null)
					{
						textView.setEnabled(true);
						textView.setText(hint);
					}
					if(listener != null)
					{
						listener.doSomething();
					}
				}
			};
			timer.start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 取消倒计时
	 */
	public static void cancelCountDown()
	{
		if(timer != null)
		{
			timer.cancel();
			timer = null;
		}
	}

	// ################################################################
	// ##                            其他                             ##
	// ################################################################

	/**
	 * 手动计算出listView的高度，但是不再具有滚动效果
	 */
	public static void fixListViewHeight(ListView listView)
	{
		// 如果没有设置数据适配器，则ListView没有子项，返回。
		ListAdapter listAdapter = listView.getAdapter();
		if(listAdapter == null)
		{
			return;
		}

		int totalHeight = listView.getPaddingTop()+listView.getPaddingBottom();
		for(int i = 0; i < listAdapter.getCount(); i++)
		{
			View listItem = listAdapter.getView(i, null, listView);
			if(listItem instanceof ViewGroup)
			{
				listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			}
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight+(listView.getDividerHeight()*(listAdapter.getCount()-1));
		listView.setLayoutParams(params);
	}

	/**
	 * 根据资源名称获取资源 id
	 * <p>
	 * 不提倡使用这个方法获取资源,比其直接获取ID效率慢
	 * <p>
	 * 例如
	 * getResources().getIdentifier("ic_launcher", "drawable", getPackageName());
	 */
	public static int getResIdByName(String name, String defType)
	{
		return getContext().getResources().getIdentifier(name, defType, getContext().getPackageName());
	}

	private static long lastClickTime;
	public static boolean isFastClick(int millisecond)
	{
		long curClickTime = System.currentTimeMillis();
		long interval = (curClickTime-lastClickTime);

		if(0 < interval && interval < millisecond)
		{
			// 超过点击间隔后再将lastClickTime重置为当前点击时间
			return true;
		}
		lastClickTime = curClickTime;
		return false;
	}

	/**
	 * 获取后台Handler
	 */
	public static Handler getBackgroundHandler()
	{
		HandlerThread thread = new HandlerThread("background");
		thread.start();
		return new Handler(thread.getLooper());
	}
}
