package talex.zsw.basecore.view.recyleview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import talex.zsw.basecore.R;

public class SampleFooter extends RelativeLayout
{
	private int layoutId = R.layout.sample_footer;

	public SampleFooter(Context context)
	{
		super(context);
		init(context);
	}

	public SampleFooter(Context context, int layoutId)
	{
		super(context);
		this.layoutId = layoutId;
		init(context);
	}

	public SampleFooter(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public SampleFooter(Context context, AttributeSet attrs, int layoutId)
	{
		super(context, attrs);
		this.layoutId = layoutId;
		init(context);
	}

	public void init(Context context)
	{
		inflate(context, layoutId, this);
	}
}