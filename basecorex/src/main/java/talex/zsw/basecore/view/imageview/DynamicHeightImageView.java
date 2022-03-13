package talex.zsw.basecore.view.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 作用：保持一定宽高比的图片
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("AppCompatCustomView")
public class DynamicHeightImageView extends ImageView
{

	private double mHeightRatio= 1;

	public DynamicHeightImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public DynamicHeightImageView(Context context)
	{
		super(context);
	}

	public DynamicHeightImageView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public void setHeightRatio(double ratio)
	{
		if (ratio != mHeightRatio)
		{
			mHeightRatio = ratio;
			requestLayout();
		}
	}

	public double getHeightRatio()
	{
		return mHeightRatio;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		if (mHeightRatio > 0.0)
		{
			// set the image views size
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = (int) (width * mHeightRatio);
			setMeasuredDimension(width, height);
		}
		else
		{
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void onDetachedFromWindow()
	{
		super.onDetachedFromWindow();
		setImageDrawable(null);
	}
}
