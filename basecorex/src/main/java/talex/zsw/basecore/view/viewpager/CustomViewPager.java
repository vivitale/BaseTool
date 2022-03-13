package talex.zsw.basecore.view.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.LinkedHashMap;

import androidx.viewpager.widget.ViewPager;

/**
 * 作用：自动适应高度的ViewPager
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CustomViewPager extends ViewPager
{
	private int current;
	private int height = 0;
	/**
	 * 保存position与对于的View
	 */
	private HashMap<Integer, View> mChildrenViews = new LinkedHashMap<Integer, View>();

	public CustomViewPager(Context context)
	{
		this(context, null);
	}

	public CustomViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		if(mChildrenViews.size() > current)
		{
			View child = mChildrenViews.get(current);
			child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			height = child.getMeasuredHeight();
		}
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 在切换tab的时候，重置ViewPager的高度
	 */
	public void resetHeight(int current)
	{
		this.current = current;
		if(mChildrenViews.size() > current)
		{

			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
			if(layoutParams == null)
			{
				layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
			}
			else
			{
				layoutParams.height = height;
			}
			setLayoutParams(layoutParams);
		}
	}

	/**
	 * 设置ViewPsger的存放的View和对应的position,此方法必须等View绘制完毕以后调用
	 * 如果是Fragment,则在onViewCreated之后调用
	 */
	public void setObjectForPosition(View view, int position)
	{
		mChildrenViews.put(position, view);
	}
}
