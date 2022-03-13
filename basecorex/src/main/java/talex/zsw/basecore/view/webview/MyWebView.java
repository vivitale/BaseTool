package talex.zsw.basecore.view.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 作用：高度为网页最大的WevView
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyWebView extends WebView
{

	public MyWebView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public MyWebView(Context context)
	{
		super(context);
	}

	public MyWebView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
		                                             MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}