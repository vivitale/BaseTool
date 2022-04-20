package talex.zsw.basecore.util;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

import java.util.Locale;

import androidx.annotation.RequiresApi;

/**
 * 作用：TTS工具
 * 创建时间：2022/4/15
 */
public class TTSTool
{
	private volatile static TTSTool instance;

	public static TTSTool getInstance()
	{
		if(instance == null)
		{
			synchronized(TTSTool.class)
			{//锁
				if(instance == null)
				{
					instance = new TTSTool(Tool.getContext());
				}
			}
		}
		return instance;
	}

	private TextToSpeech textToSpeech; // TTS对象

	public TTSTool(Context context) {
		textToSpeech = new TextToSpeech(context.getApplicationContext(), (int i)-> {
			if (i == TextToSpeech.SUCCESS) {
				textToSpeech.setLanguage(Locale.CHINA);
				textToSpeech.setPitch(1.0f);// 设置音调，值越大声音越尖（女生），值越小则变成男声,1.0是常规
				textToSpeech.setSpeechRate(1.0f);
			}
		});
		textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
			@Override
			public void onStart(String utteranceId) {
				// Log.e("TestBug","开始阅读");
			}

			@Override
			public void onDone(String utteranceId) {
				// Log.e("TestBug","阅读完毕11111");
			}

			@Override
			public void onError(String utteranceId) {
				// Log.e("TestBug","阅读出错");
			}
		});
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public void speakText(String text) {
		if (textToSpeech != null) {
			textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "UniqueID");
		}
	}

	public void ttsStop(){
		if (null != textToSpeech){
			textToSpeech.stop();
		}
	}

	public void ttsDestory(){
		if (null != textToSpeech){
			textToSpeech.stop();
			textToSpeech.shutdown();
		}
	}
}
