package talex.zsw.sample.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.umeng.analytics.MobclickAgent;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import talex.zsw.basetool.util.Tool;

/**
 * 作用：基本的Application,项目的Application继承自该类,调用setImg(int res)方法来设置基本图片
 * 作者：tale email:vvtale@gmail.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("Registered")
public class BaseApplication extends MultiDexApplication
{
	private static Context mApplicationContext;

	@Override public void onCreate()
	{
		mApplicationContext = this;

		initPhotoError();
		super.onCreate();
	}

	/**
	 * 解决7.0系统拍照崩溃问题
	 */
	private void initPhotoError()
	{
		StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
		StrictMode.setVmPolicy(builder.build());
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
		{
			builder.detectFileUriExposure();
		}
	}

	public boolean isShow()
	{
		if(android.os.Build.VERSION.SDK_INT >= 15)
		{
			return true;
		}
		return false;
	}

	@Override protected void attachBaseContext(Context base)
	{
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	public static Context getContext()
	{
		return mApplicationContext;
	}

	public void exit()
	{
		Tool.uninstall();
		android.os.Process.killProcess(android.os.Process.myPid());
		MobclickAgent.onKillProcess(mApplicationContext);
	}

}
