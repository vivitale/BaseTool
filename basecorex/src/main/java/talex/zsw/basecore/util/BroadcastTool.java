package talex.zsw.basecore.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

/**
 * 作用：广播工具
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BroadcastTool
{
	/**
	 * 注册监听网络状态的广播
	 *
	 * @param context 上下文
	 */
	public static BroadcastReceiverNetWork initRegisterReceiverNetWork(Context context)
	{
		// 注册监听网络状态的服务
		BroadcastReceiverNetWork mReceiverNetWork = new BroadcastReceiverNetWork();
		IntentFilter mFilter = new IntentFilter();
		mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		context.registerReceiver(mReceiverNetWork, mFilter);
		return mReceiverNetWork;
	}

	/**
	 * 网络状态改变广播
	 */
	public static class BroadcastReceiverNetWork extends BroadcastReceiver
	{
		@Override public void onReceive(Context context, Intent intent)
		{
			NetTool.getNetWorkType(context);
		}
	}
}
