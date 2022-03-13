package talex.zsw.sample.mvp;

import com.lzy.okgo.OkGo;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import talex.zsw.basecore.util.LogTool;
import talex.zsw.basecore.view.dialog.sweetalertdialog.SweetAlertDialog;
import talex.zsw.sample.entitys.BaseResponse;

/**
 * 作用：基于MVP架构的Presenter 代理的基类的实现
 * 作者：赵小白 email:vvtale@gmail.com  
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class _PresenterImpl<T extends _View, V> implements _Presenter, RequestCallback<V>
{
	protected T mView;
	private _Model model = new _ModelImpl();
	private Disposable mDisposable;

	public _PresenterImpl(T view)
	{
		mView = view;
	}

	@Override public void onStart()
	{
	}

	@Override public void onResume()
	{
	}

	@Override public void onPause()
	{
	}

	@Override public void onStop()
	{
	}

	@Override public void onRestart()
	{
	}

	@Override public void onDestroy()
	{
		OkGo.getInstance().cancelTag(this);
		mView = null;
	}

	@Override public void getData(HttpDto http)
	{
		if(isViewExist())
		{
			http.setTag(this);
			model.getData((RequestCallback<BaseResponse>) http.getTag(), http);
		}
	}
	//================================== 接口请求 =======================================

	@Override public void beforeRequest()
	{
		if(isViewExist())
		{
			mView.showDialog();
		}
	}

	@Override public void requestError(String msg, final HttpDto httpDto)
	{
		if(isViewExist())
		{
			mView.disLoading();
			LogTool.e(msg);
			String title = "";
			String info = "";
			if(msg.contains("Timeout"))
			{
				title = "连接服务器超时";
				info = "数据加载失败，请重试！";
			}
			else if(msg.contains("504"))
			{
				title = "无网络服务";
				info = "请检查网络后重试！";
			}
			else if(msg.contains("Failed to connect"))
			{
				title = "服务器异常";
				info = "请稍后重试！";
			}
			else if(msg.contains("网络请求失败"))
			{
				title = "网络请求失败";
				info = "请稍后重试！";
			}
			else
			{
				title = "对不起，访问出错了";
				info = "请稍后重试！";
			}
			if(httpDto.isShowError())
			{
				if(httpDto.isTryAgain())
				{
					if(httpDto.isFinish())
					{
						mView.showDialog(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
						{
							@Override public void onClick(SweetAlertDialog sweetAlertDialog)
							{
								getData(httpDto);
							}
						}, mView.getFinishListener());
					}
					else
					{
						mView.showDialog(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
						{
							@Override public void onClick(SweetAlertDialog sweetAlertDialog)
							{
								getData(httpDto);
							}
						}, null);
					}
				}
				else if(httpDto.isFinish())
				{
					mView.showDialog(SweetAlertDialog.ERROR_TYPE, title, info, "确定", null, mView.getFinishListener(), null);
				}
				else
				{
					mView.showDialog(SweetAlertDialog.ERROR_TYPE, title, info);
				}
			}
			else
			{
				if(httpDto.isTryAgain())
				{
					if(httpDto.isFinish())
					{
						mView.showDialog(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
						{
							@Override public void onClick(SweetAlertDialog sweetAlertDialog)
							{
								getData(httpDto);
							}
						}, mView.getFinishListener());
					}
					else
					{
						mView.showDialog(SweetAlertDialog.ERROR_TYPE, title, info, "重试", "取消", new SweetAlertDialog.OnSweetClickListener()
						{
							@Override public void onClick(SweetAlertDialog sweetAlertDialog)
							{
								getData(httpDto);
							}
						}, null);
					}
				}
				else if(httpDto.isFinish())
				{
					mView.showDialog(SweetAlertDialog.ERROR_TYPE, title, info, "确定", null, mView.getFinishListener(), null);
				}
			}
		}
	}

	@Override public void requestComplete()
	{
	}

	@Override public void requestTryAgain(@NonNull HttpDto httpDto)
	{
		getData(httpDto);
	}

	@Override public boolean isViewExist()
	{
		return mView != null;
	}

	@Override public void requestSuccess(@NotNull V response, @NotNull HttpDto httpDto)
	{

	}

	@Override public void requestSuccess(@NotNull Observable<V> observable, @NotNull final HttpDto httpDto)
	{
		observable
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Observer<Object>()
			{
				@Override public void onSubscribe(Disposable disposable)
				{
					mDisposable = disposable;
				}

				@Override public void onNext(Object o)
				{
					requestSuccess((V) o, httpDto);
				}

				@Override public void onError(Throwable e)
				{
					requestError(e.getLocalizedMessage()+"\n"+e, httpDto);
				}

				@Override public void onComplete()
				{

				}
			});
	}

	@Override public void requestCancel()
	{
		mDisposable.dispose();
	}
}
