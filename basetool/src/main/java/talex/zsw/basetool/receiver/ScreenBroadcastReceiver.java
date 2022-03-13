package talex.zsw.basetool.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import talex.zsw.basetool.util.LogTool;

/**
 * 作用：锁屏,解锁,开屏的广播监听
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ScreenBroadcastReceiver extends BroadcastReceiver
{
	@Override public void onReceive(Context context, Intent intent)
	{
		String action = intent.getAction();
		LogTool.nv("广播Action = "+action);
		if(action.equals(Intent.ACTION_SCREEN_OFF))
		{
			LogTool.nv("锁屏");
			// EventBus.getDefault().post(new ScreenEvent(Intent.ACTION_SCREEN_OFF));
		}
		else if(action.equals(Intent.ACTION_SCREEN_ON))
		{
			LogTool.nv("解锁");
			// EventBus.getDefault().post(new ScreenEvent(Intent.ACTION_SCREEN_ON));
		}
		else if(action.equals(Intent.ACTION_USER_PRESENT))
		{
			LogTool.nv("开屏");
			// EventBus.getDefault().post(new ScreenEvent(Intent.ACTION_USER_PRESENT));
		}
	}

	// --------------- 怎么用 ---------------

//	// --------------- 锁屏,解锁监听 ---------------
//	private var screenBroadcastReceiver: ScreenBroadcastReceiver? = null
//	private fun registerScreenReceiver()
//	{
//		if (screenBroadcastReceiver == null)
//		{
//			screenBroadcastReceiver = ScreenBroadcastReceiver()
//		}
//		val filter = IntentFilter()
//		filter.addAction(Intent.ACTION_SCREEN_OFF)
//		filter.addAction(Intent.ACTION_SCREEN_ON)
//		filter.addAction(Intent.ACTION_USER_PRESENT)
//		applicationContext.registerReceiver(screenBroadcastReceiver, filter)
//	}
//
//	private fun unRegisterScreenReceiver()
//	{
//		if (screenBroadcastReceiver != null)
//		{
//			applicationContext.unregisterReceiver(screenBroadcastReceiver)
//		}
//	}
//
//	@Subscribe
//	fun onEvent(event: ScreenEvent)
//	{
//		if (event.action == Intent.ACTION_SCREEN_OFF && ActivityTool.currentActivity() != this)
//		{ // 锁屏
//			val intent = Intent(this, MainActivity::class.java)
//			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//			start(intent)
//		}
//	}

}
