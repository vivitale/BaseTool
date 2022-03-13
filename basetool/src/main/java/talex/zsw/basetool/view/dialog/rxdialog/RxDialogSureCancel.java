package talex.zsw.basetool.view.dialog.rxdialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import talex.zsw.basetool.R;

/**
 * 作用：确认 取消 Dialog
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RxDialogSureCancel extends RxDialog {

    private ImageView mIvLogo;
    private TextView mTvContent;
    private TextView mTvSure;
    private TextView mTvCancel;
    private TextView mTvTitle;

    public RxDialogSureCancel(Context context, int themeResId) {
        super(context, themeResId);
        initView();
    }

    public RxDialogSureCancel(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView();
    }

    public RxDialogSureCancel(Context context) {
        super(context);
        initView();
    }

    public RxDialogSureCancel(Activity context) {
        super(context);
        initView();
    }

    public RxDialogSureCancel(Context context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView();
    }

    private void initView() {
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sure_false, null);
        mIvLogo = (ImageView) dialogView.findViewById(R.id.iv_logo);
        mTvSure = (TextView) dialogView.findViewById(R.id.tv_sure);
        mTvCancel = (TextView) dialogView.findViewById(R.id.tv_cancel);
        mTvContent = (TextView) dialogView.findViewById(R.id.tv_content);
        mTvContent.setTextIsSelectable(true);
        mTvTitle = (TextView) dialogView.findViewById(R.id.tv_title);
        setContentView(dialogView);
    }

    // --------------- 数据 ---------------

    public void setTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setContent(String content) {
        this.mTvContent.setText(content);
    }

    public void setSureText(String strSure) {
        this.mTvSure.setText(strSure);
    }

    public void setSureListener(View.OnClickListener sureListener) {
        mTvSure.setOnClickListener(sureListener);
    }

    public void setCancelText(String strCancel) {
        this.mTvCancel.setText(strCancel);
    }

    // --------------- View ---------------

    public ImageView getLogoView() {
        return mIvLogo;
    }

    public TextView getTitleView() {
        return mTvTitle;
    }

    public TextView getContentView() {
        return mTvContent;
    }

    public TextView getSureView() {
        return mTvSure;
    }

    public TextView getCancelView() {
        return mTvCancel;
    }

    private View.OnClickListener cancelListener;
    public View.OnClickListener getCancelListener()
    {
        return cancelListener;
    }

    public void setCancelListener(View.OnClickListener cancelListener)
    {
        this.cancelListener = cancelListener;
        if(cancelListener!=null && mTvCancel!=null)
        {
            mTvCancel.setOnClickListener(cancelListener);
        }
    }
}
