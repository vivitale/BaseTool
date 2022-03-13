package talex.zsw.basecore.util.glide;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

import androidx.annotation.NonNull;

/**
 * 作用：圆形
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GlideRoundImage extends BitmapTransformation
{

	private static float radius = 0f;

	public GlideRoundImage()
	{
		this(4);
	}

	public GlideRoundImage(int dp)
	{
		super();
		radius = Resources.getSystem().getDisplayMetrics().density*dp;
	}

	@Override
	protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight)
	{
		return roundCrop(pool, toTransform);
		//Glide 4.0.0以上则使用下面的
		//Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
		//return roundCrop(pool, bitmap);
	}

	private static Bitmap roundCrop(BitmapPool pool, Bitmap source)
	{
		if(source == null)
		{
			return null;
		}

		Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
		if(result == null)
		{
			result
				= Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
		}

		Canvas canvas = new Canvas(result);
		Paint paint = new Paint();
		paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
		paint.setAntiAlias(true);
		RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
		canvas.drawRoundRect(rectF, radius, radius, paint);
		return result;
	}

	public String getId()
	{
		return getClass().getName()+Math.round(radius);
	}

	@Override public void updateDiskCacheKey(@NonNull MessageDigest messageDigest)
	{

	}
}