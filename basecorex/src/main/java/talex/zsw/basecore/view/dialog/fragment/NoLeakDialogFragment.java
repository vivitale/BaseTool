package talex.zsw.basecore.view.dialog.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 作用：修复内存溢出的问题
 * 创建时间：2022/4/23
 */
public class NoLeakDialogFragment extends DialogFragment
{
	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		if(getDialog()!=null)
		{
			getDialog().setOnCancelListener(null);
			getDialog().setOnDismissListener(null);
		}
	}

	@NonNull @Override public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
	{
		return new NoLeakDialog(requireContext(), getTheme());
	}
}
