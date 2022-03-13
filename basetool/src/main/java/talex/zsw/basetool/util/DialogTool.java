package talex.zsw.basetool.util;

import android.app.Dialog;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * 作用：Dialog工具类
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DialogTool
{
	/**
	 * 结束一个Dialog
	 */
	public static void endDialog(Dialog dialog)
	{
		if(dialog!=null)
		{
			if(dialog.isShowing())
			{
				dialog.dismiss();
			}
			dialog.onDetachedFromWindow();
			dialog = null;
		}
	}

	/**
	 * 结束一个Dialog
	 */
	public static void endDialogEdit(Dialog dialog)
	{
		if(dialog!=null)
		{
			InputMethodManager inputmanger
				= (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(dialog.getWindow().peekDecorView().getWindowToken(), 0);

			if(dialog.isShowing())
			{
				dialog.dismiss();
			}
			dialog.onDetachedFromWindow();
			dialog = null;
		}
	}
}
