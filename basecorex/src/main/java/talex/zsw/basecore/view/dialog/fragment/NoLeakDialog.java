package talex.zsw.basecore.view.dialog.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Message;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 作用：修复内存溢出的问题
 * 创建时间：2022/4/23
 */
public class NoLeakDialog extends Dialog
{

	private WeakReference<DialogFragment> hostFragmentReference;

	public void setHostFragmentReference(DialogFragment hostFragment) {
		this.hostFragmentReference = new WeakReference<>(hostFragment);
	}

	public NoLeakDialog(@NonNull Context context, int themeResId) {
		super(context, themeResId);
	}


	@Override
	public void setOnCancelListener(@Nullable OnCancelListener listener) {
		// 空实现，不持有外部的引用
	}

	@Override
	public void setOnShowListener(@Nullable OnShowListener listener) {
		// 空实现，不持有外部的引用
	}

	@Override
	public void setOnDismissListener(@Nullable OnDismissListener listener) {
		// 空实现，不持有外部的引用
	}

	@Override
	public void setCancelMessage(@Nullable Message msg) {
		// 空实现，不持有外部的引用
	}

	@Override
	public void setDismissMessage(@Nullable Message msg) {
		// 空实现，不持有外部的引用
	}

	@Override
	public void dismiss() {
		super.dismiss();
		if (null != hostFragmentReference && null != hostFragmentReference.get()) {
			hostFragmentReference.get().dismissAllowingStateLoss();
		}
	}
}