package talex.zsw.basetool.view.other;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 作用：TabLayout+ViewPager撤消左右滑动切换功能
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class NoScrollViewPage extends ViewPager
{
	private boolean isCanScroll = false;

	public NoScrollViewPage(Context context)
	{
		super(context);
	}

	public NoScrollViewPage(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public void setScanScroll(boolean isCanScroll)
	{
		this.isCanScroll = isCanScroll;
	}

	@Override public void scrollTo(int x, int y)
	{
		super.scrollTo(x, y);
	}

	@Override public boolean onTouchEvent(MotionEvent arg0)
	{
		if(isCanScroll)
		{
			return super.onTouchEvent(arg0);
		}
		else
		{
			return false;
		}
	}

	@Override public void setCurrentItem(int item, boolean smoothScroll)
	{
		super.setCurrentItem(item, smoothScroll);
	}

	@Override public void setCurrentItem(int item)
	{
		super.setCurrentItem(item);
	}

	@Override public boolean onInterceptTouchEvent(MotionEvent arg0)
	{
		if(isCanScroll)
		{
			return super.onInterceptTouchEvent(arg0);
		}
		else
		{
			return false;
		}
	}
}
