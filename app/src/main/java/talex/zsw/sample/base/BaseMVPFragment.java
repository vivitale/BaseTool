package talex.zsw.sample.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Field;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import talex.zsw.basetool.util.KeyboardTool;
import talex.zsw.basetool.util.RegTool;
import talex.zsw.basetool.view.dialog.sweetalertdialog.SweetAlertDialog;
import talex.zsw.sample.mvp._Presenter;
import talex.zsw.sample.mvp._View;

/**
 * 作用：基于MVP架构的Fragment基类
 * 作者：tale email:vvtale@gmail.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseMVPFragment<T extends _Presenter> extends Fragment implements _View
{
	// 将代理类通用行为抽出来
	protected T mPresenter;

	public SweetAlertDialog mSweetAlertDialog;
	private LayoutInflater inflater;
	private InputMethodManager mInputMethodManager;
	private ViewGroup container;
	public View mView;
	private Handler mainHandler;

	protected abstract void initArgs(Bundle bundle);

	protected abstract void initView(Bundle bundle);

	protected abstract void initData();

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.inflater = inflater;
		this.container = container;

		mInputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		mSweetAlertDialog = new SweetAlertDialog(getActivity());
		mSweetAlertDialog.setCancelable(false);
		KeyboardTool.hideSoftInput(getActivity());

		try
		{
			initArgs(getArguments());
			initView(savedInstanceState);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mView;
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		initData();
		isPrepared = true;
		if(getUserVisibleHint())
		{
			isVisible = true;
			onVisible();
		}
		super.onViewCreated(view, savedInstanceState);
	}

	protected void setContentView(int layout)
	{
		mView = inflater.inflate(layout, container, false);
		ButterKnife.bind(this, mView);
	}

	/**
	 * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
	 *
	 * @param isVisibleToUser 是否显示出来了
	 */
	@Override public void setUserVisibleHint(boolean isVisibleToUser)
	{
		super.setUserVisibleHint(isVisibleToUser);
		if(!isPrepared)
		{
			return;
		}
		if(getUserVisibleHint())
		{
			isVisible = true;
			onVisible();
		}
		else
		{
			isVisible = false;
			onInvisible();
		}
	}

	/**
	 * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
	 * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
	 *
	 * @param hidden hidden True if the fragment is now hidden, false if it is not
	 *               visible.
	 */
	@Override public void onHiddenChanged(boolean hidden)
	{
		super.onHiddenChanged(hidden);
		if(!isPrepared)
		{
			return;
		}
		if(!hidden)
		{
			isVisible = true;
			onVisible();
		}
		else
		{
			isVisible = false;
			onInvisible();
		}
	}

	@Override public void onVisible()
	{

	}

	@Override public void onInvisible()
	{

	}

	@Override public void onStart()
	{
		super.onStart();
		if(mPresenter != null)
		{
			mPresenter.onStart();
		}
	}

	@Override public void onResume()
	{
		super.onResume();
		if(mPresenter != null)
		{
			mPresenter.onResume();
		}
	}

	@Override public void onPause()
	{
		if(mPresenter != null)
		{
			mPresenter.onPause();
		}
		super.onPause();
	}

	@Override public void onStop()
	{
		super.onStop();
		if(mPresenter != null)
		{
			mPresenter.onStop();
		}
	}

	@Override public void onDestroyView()
	{
		if(mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
		{
			mSweetAlertDialog.dismiss();
			mSweetAlertDialog = null;
		}

		super.onDestroyView();
		ViewGroup parent = (ViewGroup) mView.getParent();
		if(null != parent)
		{
			parent.removeView(mView);
		}
	}

	@Override public void onDestroy()
	{
		super.onDestroy();
		if(mPresenter != null)
		{
			mPresenter.onDestroy();
		}
	}

	@TargetApi(Build.VERSION_CODES.KITKAT) @Override public void onDetach()
	{
		try
		{
			Field childFragmentManager
				= Fragment.class.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		}
		catch(NoSuchFieldException | IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		super.onDetach();
	}

	@Override public void getData(boolean silence)
	{
	}

	@Override public void disLoading()
	{
	}

	/**
	 * @return 获取屏幕宽度
	 */
	public int getScrnWeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		return mDisplayMetrics.widthPixels;
	}

	/**
	 * @return 获取屏幕高度
	 */
	public int getScrnHeight()
	{
		DisplayMetrics mDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
		return mDisplayMetrics.heightPixels;
	}

	@Override public Handler getHandler()
	{
		if(mainHandler == null)
		{
			mainHandler = new Handler();
		}
		return mainHandler;
	}

	@Override public Context getCont()
	{
		return getActivity();
	}

	@Override public void showToast(String msg)
	{
		if(!RegTool.isNullString(msg))
		{
			Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
		}
	}

	public void showSnackbar(String msg)
	{
		Snackbar.make(mView, msg, Snackbar.LENGTH_SHORT).show();
	}

	protected void showSnackbar(int id)
	{

		Snackbar.make(mView, id, Snackbar.LENGTH_SHORT).show();
	}

	private static long mLastClickTime;
	public static final int MIN_CLICK_DELAY_TIME = 400;

	@Override public boolean isFastClick()
	{
		// 当前时间
		long currentTime = System.currentTimeMillis();
		// 两次点击的时间差
		long time = currentTime-mLastClickTime;
		if(0 < time && time < MIN_CLICK_DELAY_TIME)
		{
			return true;
		}
		mLastClickTime = currentTime;
		return false;
	}

	public SweetAlertDialog.OnSweetClickListener finishListener = new SweetAlertDialog.OnSweetClickListener()
	{
		@Override public void onClick(SweetAlertDialog sweetAlertDialog)
		{
			sweetAlertDialog.dismissWithAnimation();
			getActivity().finish();
		}
	};

	@Override public SweetAlertDialog.OnSweetClickListener getFinishListener()
	{
		return finishListener;
	}

	@Override public void disDialog()
	{
		mSweetAlertDialog.dismiss();
	}

	@Override public void showDialog()
	{
		if(mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
		{
			mSweetAlertDialog.setTitleText("正在加载数据");
			mSweetAlertDialog.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
		}
		else
		{
			mSweetAlertDialog
				= new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE).setTitleText("正在加载数据");
			mSweetAlertDialog.setCancelable(false);
			mSweetAlertDialog.show();
		}
	}

	@Override public void showDialog(int type, String title, String content)
	{
		showDialog(type, title, content, "确定", null, new SweetAlertDialog.OnSweetClickListener()
		{
			@Override public void onClick(SweetAlertDialog sweetAlertDialog)
			{
				sweetAlertDialog.dismiss();
			}
		}, null);
	}

	@Override
	public void showDialog(int type, String title, String content, String confirmText, SweetAlertDialog.OnSweetClickListener confirmListener)
	{
		showDialog(type, title, content, confirmText, null, confirmListener, null);
	}

	@Override
	public void showDialog(int type, String title, String content, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener confirmListener, SweetAlertDialog.OnSweetClickListener cancelListener)
	{
		if(mSweetAlertDialog != null && mSweetAlertDialog.isShowing())
		{
			mSweetAlertDialog.changeAlertType(type);
		}
		else
		{
			mSweetAlertDialog = new SweetAlertDialog(getActivity(), type);
			mSweetAlertDialog.setCancelable(false);
		}
		// Title
		if(!RegTool.isNullString(title))
		{
			mSweetAlertDialog.setTitleText(title);
		}
		else
		{
			mSweetAlertDialog.setTitleText("");
		}
		// content
		if(!RegTool.isNullString(content))
		{
			mSweetAlertDialog.setContentText(content);
		}
		else
		{
			mSweetAlertDialog.showContentText(false);
		}
		// confirmText
		if(!RegTool.isNullString(confirmText))
		{
			mSweetAlertDialog.setConfirmText(confirmText);
		}
		// cancelText
		if(!RegTool.isNullString(cancelText))
		{
			mSweetAlertDialog.setCancelText(cancelText);
		}
		else
		{
			mSweetAlertDialog.showCancelButton(false);
		}
		// confirmListener
		if(confirmListener != null)
		{
			mSweetAlertDialog.setConfirmClickListener(confirmListener);
		}
		else
		{
			mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener()
			{
				@Override public void onClick(SweetAlertDialog sweetAlertDialog)
				{
					sweetAlertDialog.dismiss();
				}
			});
		}
		// confirmListener
		if(confirmListener != null)
		{
			mSweetAlertDialog.setCancelClickListener(cancelListener);
		}
		else
		{
			mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener()
			{
				@Override public void onClick(SweetAlertDialog sweetAlertDialog)
				{
					sweetAlertDialog.dismiss();
				}
			});
		}
		mSweetAlertDialog.show();
	}

	public int getStatusBarHeight()
	{
		try
		{
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			return getResources().getDimensionPixelSize(x);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public void start(Class<?> cls)
	{
		getActivity().startActivity(new Intent(getActivity(), cls));
	}

	public void start(Intent intent)
	{
		getActivity().startActivity(intent);
	}

	// --------------- Fragment是否可见监听事件 --------------
	public boolean isVisible;
	public boolean isPrepared;
}
