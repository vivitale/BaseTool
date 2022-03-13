package talex.zsw.basetool.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 作用：声音工具类
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SoundPoolTool
{
	// private Context mContext;
	private static SoundPool mSoundPool;

	/**
	 * 播放语音文件
	 *
	 * @param context 上下文
	 * @param resId   资源ID
	 * @return SoundPool 返回类型
	 */
	public static SoundPool create(Context context, int resId)
	{
		if (mSoundPool == null)
		{
			mSoundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		}
		final int soundId = mSoundPool.load(context, resId, 1);
		mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener()
		{
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
									   int status)
			{
				soundPool.play(soundId, 1.0f, 0.5f, 1, 0, 1.0f);
			}
		});
		return mSoundPool;
	}

	/**
	 * 释放播放池
	 */
	public static void dismisSoundPool()
	{
		mSoundPool.release();
		mSoundPool = null;
	}
}

