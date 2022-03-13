package talex.zsw.basecore.util;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 作用：Fragment工具
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class FragmentTool
{

	//----------------------------------------------------------------------------------------------Fragment的静态使用 start
	//在布局文件中直接使用标签
    /*    <fragment
            android:layout_below="@id/id_fragment_title"
            android:id="@+id/id_fragment_content"
            android:name="com.zhy.zhy_fragments.ContentFragment"
            android:layout_width="fill_parent"
            android:layout_height="fill_parent" />*/

	//==============================================================================================Fragment的静态使用 end

	//----------------------------------------------------------------------------------------------Fragment的动态使用 start

	/**
	 * v4包下的使用
	 * 动态的使用Fragment
	 * <p>
	 * 在布局文件中使用 FrameLayout 标签
	 *
	 * @param fragmentActivity activity
	 * @param fragment         fragment
	 * @param id               <FrameLayout android:id="@+id/r_id_fragment"/>
	 */
	public static void showFragment(FragmentActivity fragmentActivity, Fragment fragment, int id)
	{
		FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(id, fragment);
		fragmentTransaction.commit();
	}

	/**
	 * android.app.Activity下的使用
	 * 动态的使用Fragment
	 * <p>
	 * 在布局文件中使用 FrameLayout 标签
	 *
	 * @param activity activity
	 * @param fragment fragment
	 * @param id       <FrameLayout android:id="@+id/r_id_fragment"/>
	 */
	public static void showFragment(Activity activity, android.app.Fragment fragment, int id)
	{
		android.app.FragmentManager fragmentManager = activity.getFragmentManager();
		android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(id, fragment);
		fragmentTransaction.commit();
	}

	//==============================================================================================Fragment的动态使用 start


}
