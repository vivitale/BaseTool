package talex.zsw.basetool.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 作用：数据库工具
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class DBTool
{

	/** 读文件buffer. */
	private static final int FILE_BUFFER = 1024;

	/**
	 * 数据库导出到sdcard.
	 *
	 * @param context 上下文
	 * @param dbName  数据库名字 例如 xx.db
	 */
	public static void exportDb2Sdcard(Context context, String dbName)
	{
		String filePath = context.getDatabasePath(dbName).getAbsolutePath();
		byte[] buffer = new byte[FILE_BUFFER];
		int length;
		OutputStream output;
		InputStream input;
		try
		{
			input = new FileInputStream(new File((filePath)));
			output = new FileOutputStream(context.getExternalCacheDir()+File.separator+dbName);
			while((length = input.read(buffer)) > 0)
			{
				output.write(buffer, 0, length);
			}
			output.flush();
			output.close();
			input.close();
			Log.i("DBTool", "mv success!");
		}
		catch(IOException e)
		{
			Log.e("DBTool", e.toString());
		}
	}
}
