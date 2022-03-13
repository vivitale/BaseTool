package talex.zsw.sample.mvp;

import android.content.Context;
import android.os.Handler;

import talex.zsw.basetool.view.dialog.sweetalertdialog.SweetAlertDialog;

/**
 * 作用：基于MVP架构的View 视图基类
 * 作者：tale email:vvtale@gmail.com
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface _View
{
	void showToast(String msg);

	void showDialog();

	void showDialog(int type, String title, String content, String confirmText, String cancelText, SweetAlertDialog.OnSweetClickListener confirmListener, SweetAlertDialog.OnSweetClickListener cancelListener);

	void showDialog(int type, String title, String content, String confirmText, SweetAlertDialog.OnSweetClickListener confirmListener);

	void showDialog(int type, String title, String content);

	void disDialog();

	void getData(boolean silence);

	Handler getHandler();

	Context getCont();

	void disLoading();

	boolean isFastClick();

	SweetAlertDialog.OnSweetClickListener getFinishListener();

	void onVisible();

	void onInvisible();
}
