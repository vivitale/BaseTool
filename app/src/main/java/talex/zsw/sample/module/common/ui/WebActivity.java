package talex.zsw.sample.module.common.ui;

import android.content.Intent;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import talex.zsw.basetool.util.WebViewTool;
import talex.zsw.basetool.view.other.RxToast;
import talex.zsw.basetool.view.other.TitleBar;
import talex.zsw.sample.R;
import talex.zsw.sample.base.BaseMVPActivity;
import talex.zsw.sample.entitys.BaseResponse;
import talex.zsw.sample.mvp.CommonPresenter;
import talex.zsw.sample.mvp.CommonView;
import talex.zsw.sample.mvp._Presenter;

/**
 * 作用：通用的Web页面来展示富文本等
 * 作者：tale email:vvtale@gmail.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class WebActivity extends BaseMVPActivity<_Presenter> implements CommonView.View
{
	@BindView(R.id.mTitleBar) TitleBar mTitleBar;
	@BindView(R.id.mProgressBar) ProgressBar mProgressBar;
	@BindView(R.id.mWebView) WebView mWebView;

	private String title, data;

	@Override protected void initArgs(Intent intent)
	{
		title = intent.getStringExtra("title");
		data = intent.getStringExtra("data");
	}

	@Override protected void initView()
	{
		setContentView(R.layout.activity_web);
		ButterKnife.bind(this);
		mPresenter = new CommonPresenter(this);

		RxToast.normal("111111111111111");
	}

	@Override protected void initData()
	{
		mTitleBar.setTitle(title);
		WebViewTool.setWebData(data, mWebView, mProgressBar);
	}

	@Override public void bindData(@NotNull BaseResponse response)
	{
	}
}
