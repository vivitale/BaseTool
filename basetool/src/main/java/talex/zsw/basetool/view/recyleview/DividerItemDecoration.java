package talex.zsw.basetool.view.recyleview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import talex.zsw.basetool.util.ColorTool;

/**
 * 作用：简易的计算显示RecyclerView间隔
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration
{
	public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

	public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

	private Drawable mDivider;
	private int dividerHeight = 1;
	private int mOrientation;
	private boolean showLastLine = true;// 是否显示最后一条线

	/** 隐藏间隔 */
	public DividerItemDecoration(int orientation)
	{
		this.dividerHeight = 0;
		mDivider = new ColorDrawable(Color.TRANSPARENT);
		setOrientation(orientation);
	}

	/** 显示一个纯色的间隔符 */
	public DividerItemDecoration(int orientation, int resource)
	{
		mDivider = new ColorDrawable(ColorTool.getColorById(resource));
		setOrientation(orientation);
	}

	public DividerItemDecoration(int orientation, int resource, boolean showLastLine)
	{
		this.showLastLine = showLastLine;
		mDivider = new ColorDrawable(ColorTool.getColorById(resource));
		setOrientation(orientation);
	}

	public DividerItemDecoration(Context context, int orientation, int resource, int height, boolean isColor)
	{
		this.dividerHeight = height;
		Resources resources = context.getResources();
		if(isColor)
		{
			mDivider = new ColorDrawable(ColorTool.getColorById(resource));
		}
		else
		{
			mDivider = resources.getDrawable(resource);
		}
		setOrientation(orientation);
	}

	public DividerItemDecoration(Context context, int orientation, int resource, int height, boolean isColor, boolean showLastLine)
	{
		this.showLastLine = showLastLine;
		this.dividerHeight = height;
		Resources resources = context.getResources();
		if(isColor)
		{
			mDivider = new ColorDrawable(ColorTool.getColorById(resource));
		}
		else
		{
			mDivider = resources.getDrawable(resource);
		}
		setOrientation(orientation);
	}

	public void setOrientation(int orientation)
	{
		if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST)
		{
			throw new IllegalArgumentException("invalid orientation");
		}
		mOrientation = orientation;
	}

	@Override public void onDraw(Canvas c, RecyclerView parent)
	{
		if(mOrientation == VERTICAL_LIST)
		{
			drawVertical(c, parent);
		}
		else
		{
			drawHorizontal(c, parent);
		}
	}


	public void drawVertical(Canvas c, RecyclerView parent)
	{
		final int left = parent.getPaddingLeft();
		final int right = parent.getWidth()-parent.getPaddingRight();

		int childCount = parent.getChildCount();
		if(!showLastLine)
		{
			childCount = childCount-1;
		}
		for(int i = 0; i < childCount; i++)
		{
			final View child = parent.getChildAt(i);
			RecyclerView v = new RecyclerView(parent.getContext());
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			final int top = child.getBottom()+params.bottomMargin;
			final int bottom = top+dividerHeight;
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	public void drawHorizontal(Canvas c, RecyclerView parent)
	{
		final int top = parent.getPaddingTop();
		final int bottom = parent.getHeight()-parent.getPaddingBottom();

		int childCount = parent.getChildCount();
		if(!showLastLine)
		{
			childCount = childCount-1;
		}
		for(int i = 0; i < childCount; i++)
		{
			final View child = parent.getChildAt(i);
			RecyclerView v = new RecyclerView(parent.getContext());
			final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
			final int left = child.getRight()+params.rightMargin;
			final int right = left+dividerHeight;
			mDivider.setBounds(left, top, right, bottom);
			mDivider.draw(c);
		}
	}

	@Override public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent)
	{
		if(mOrientation == VERTICAL_LIST)
		{
			outRect.set(0, 0, 0, dividerHeight);
		}
		else
		{
			outRect.set(0, 0, dividerHeight, 0);
		}
	}
}
