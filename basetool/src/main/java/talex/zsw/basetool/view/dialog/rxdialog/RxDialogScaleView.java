package talex.zsw.basetool.view.dialog.rxdialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import talex.zsw.basetool.R;
import talex.zsw.basetool.util.BitmapTool;
import talex.zsw.basetool.view.imageview.scaleimage.ImageSource;
import talex.zsw.basetool.view.imageview.scaleimage.ScaleImageView;

/**
 * 作用：查看图片并支持手势缩放
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RxDialogScaleView extends RxDialog
{
	private ScaleImageView mScaleImageView;
	private String filePath;
	private Uri fileUri;
	private String fileAssetName;
	private String imageUrl;
	private Bitmap fileBitmap;
	private ImageView ivClose;
	private View.OnClickListener cancelListener;
	private int resId;
	private int maxScale = 100;

	public RxDialogScaleView(Context context)
	{
		super(context);
		initView();
	}

	public RxDialogScaleView(Activity context)
	{
		super(context);
		initView();
	}


	public RxDialogScaleView(Context context, String filePath, boolean isAssets)
	{
		super(context);
		initView();
		setImage(filePath, isAssets);
	}

	public RxDialogScaleView(Context context, Uri uri)
	{
		super(context);
		initView();
		setImage(uri);
	}

	public RxDialogScaleView(Context context, int resId, boolean isResId)
	{
		super(context);
		initView();
		if(isResId)
		{
			setImage(resId);
		}
	}

	public RxDialogScaleView(Context context, Bitmap bitmap)
	{
		super(context);
		initView();
		setImage(bitmap);
	}

	public RxDialogScaleView(Context context, String imageUrl)
	{
		super(context);
		initView();
		setImage(imageUrl);
	}

	public RxDialogScaleView(Context context, int themeResId)
	{
		super(context, themeResId);
		initView();
	}

	public RxDialogScaleView(Context context, boolean cancelable, OnCancelListener cancelListener)
	{
		super(context, cancelable, cancelListener);
		initView();
	}


	public RxDialogScaleView(Context context, float alpha, int gravity)
	{
		super(context, alpha, gravity);
		initView();
	}

	private void initView()
	{
		View dialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_scaleview, null);
		mScaleImageView = dialogView.findViewById(R.id.rx_scale_view);
		mScaleImageView.setMaxScale(maxScale);
		ivClose = dialogView.findViewById(R.id.iv_close);
		if(cancelListener!=null)
		{
			ivClose.setOnClickListener(cancelListener);
		}
		else
		{
			ivClose.setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View view)
				{
					cancel();
				}
			});
		}
		setFullScreen();
		setContentView(dialogView);
	}

	// --------------- 数据 ---------------

	public void setImage(String filePath, boolean isAssets)
	{
		if(isAssets)
		{
			this.fileAssetName = filePath;
			mScaleImageView.setImage(ImageSource.asset(filePath));
		}
		else
		{
			this.filePath = filePath;
			mScaleImageView.setImage(ImageSource.uri(filePath));
		}
	}

	@SuppressLint("CheckResult") public void setImage(String imageUrl)
	{
		this.imageUrl = imageUrl;
		Observable.just(imageUrl)
		          .subscribeOn(Schedulers.io())
		          .map(url -> BitmapTool.getBitmapByUrl(url))
		          .observeOn(AndroidSchedulers.mainThread())
		          .subscribe(bitmap -> {
			          if(bitmap!=null)
		            {
			            mScaleImageView.setImage(ImageSource.bitmap(bitmap));
		            }
		          });
	}

	public void setImage(Uri uri)
	{
		this.fileUri = uri;
		mScaleImageView.setImage(ImageSource.uri(uri));
	}

	public void setImage(int resId)
	{
		this.resId = resId;
		mScaleImageView.setImage(ImageSource.resource(resId));
	}

	public void setImage(Bitmap bitmap)
	{
		this.fileBitmap = bitmap;
		mScaleImageView.setImage(ImageSource.bitmap(fileBitmap));
	}

	public int getMaxScale()
	{
		return maxScale;
	}

	public void setMaxScale(int maxScale)
	{
		this.maxScale = maxScale;
		initView();
	}

	public String getFilePath()
	{
		return filePath;
	}

	public Uri getFileUri()
	{
		return fileUri;
	}

	public String getFileAssetName()
	{
		return fileAssetName;
	}

	public Bitmap getFileBitmap()
	{
		return fileBitmap;
	}

	public int getResId()
	{
		return resId;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public View.OnClickListener getCancelListener()
	{
		return cancelListener;
	}

	public void setCancelListener(View.OnClickListener cancelListener)
	{
		this.cancelListener = cancelListener;
		if(cancelListener!=null && ivClose!=null)
		{
			ivClose.setOnClickListener(cancelListener);
		}
	}

	// --------------- View ---------------

	public ScaleImageView getScaleImageView()
	{
		return mScaleImageView;
	}

}
