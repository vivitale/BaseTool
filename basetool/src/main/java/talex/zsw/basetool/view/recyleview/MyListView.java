package talex.zsw.basetool.view.recyleview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 作用：计算高度为全部item的ListView
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MyListView extends ListView
{
	public MyListView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public MyListView(Context context)
	{
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle)
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