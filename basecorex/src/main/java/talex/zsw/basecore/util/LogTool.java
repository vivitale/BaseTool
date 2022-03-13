package talex.zsw.basecore.util;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import androidx.annotation.IntDef;
import androidx.annotation.IntRange;

/**
 * 作用：日志工具
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/15
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public final class LogTool
{

	public static final int V = Log.VERBOSE;
	public static final int D = Log.DEBUG;
	public static final int I = Log.INFO;
	public static final int W = Log.WARN;
	public static final int E = Log.ERROR;
	public static final int A = Log.ASSERT;

	@IntDef({V, D, I, W, E, A})
	@Retention(RetentionPolicy.SOURCE)
	public @interface TYPE
	{}

	private static final char[] T = new char[]{'V', 'D', 'I', 'W', 'E', 'A'};

	private static final int FILE = 0x10;
	private static final int JSON = 0x20;
	private static final int XML = 0x30;

	private static final String FILE_SEP = System.getProperty("file.separator");
	private static final String LINE_SEP = System.getProperty("line.separator");
	private static final String TOP_CORNER = "┌";
	private static final String MIDDLE_CORNER = "├";
	private static final String LEFT_BORDER = "│ ";
	private static final String BOTTOM_CORNER = "└";
	private static final String SIDE_DIVIDER = "────────────────────────────────────────────────────────";
	private static final String MIDDLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
	private static final String TOP_BORDER = TOP_CORNER+SIDE_DIVIDER+SIDE_DIVIDER;
	private static final String MIDDLE_BORDER = MIDDLE_CORNER+MIDDLE_DIVIDER+MIDDLE_DIVIDER;
	private static final String BOTTOM_BORDER = BOTTOM_CORNER+SIDE_DIVIDER+SIDE_DIVIDER;
	private static final int MAX_LEN = 3000;
	@SuppressLint("SimpleDateFormat") private static final Format FORMAT = new SimpleDateFormat("MM-dd HH:mm:ss.SSS ");
	private static final String NOTHING = "log nothing";
	private static final String NULL = "null";
	private static final String ARGS = "args";
	private static final String PLACEHOLDER = " ";
	private static final Config CONFIG = new Config();
	private static ExecutorService sExecutor;

	private LogTool()
	{
		throw new UnsupportedOperationException("u can't instantiate me...");
	}

	public static Config getConfig()
	{
		return CONFIG;
	}

	public static void v(final Object... contents)
	{
		log(V, CONFIG.mGlobalTag, contents);
	}

	public static void v(final String contents)
	{
		log(V, CONFIG.mGlobalTag, contents);
	}

	public static void v(final String tag, final Object... contents)
	{
		log(V, tag, contents);
	}

	public static void pv(final Object... contents)
	{
		plog(V, CONFIG.mGlobalTag, contents);
	}

	public static void d(final Object... contents)
	{
		log(D, CONFIG.mGlobalTag, contents);
	}

	public static void d(final String contents)
	{
		log(D, CONFIG.mGlobalTag, contents);
	}

	public static void d(final String tag, final Object... contents)
	{
		log(D, tag, contents);
	}

	public static void pd(final Object... contents)
	{
		plog(D, CONFIG.mGlobalTag, contents);
	}

	public static void i(final Object... contents)
	{
		log(I, CONFIG.mGlobalTag, contents);
	}

	public static void i(final String contents)
	{
		log(I, CONFIG.mGlobalTag, contents);
	}

	public static void i(final String tag, final Object... contents)
	{
		log(I, tag, contents);
	}

	public static void pi(final Object... contents)
	{
		plog(I, CONFIG.mGlobalTag, contents);
	}

	public static void w(final Object... contents)
	{
		log(W, CONFIG.mGlobalTag, contents);
	}

	public static void w(final String contents)
	{
		log(W, CONFIG.mGlobalTag, contents);
	}

	public static void w(final String tag, final Object... contents)
	{
		log(W, tag, contents);
	}

	public static void pw(final Object... contents)
	{
		plog(W, CONFIG.mGlobalTag, contents);
	}

	public static void e(final Object... contents)
	{
		log(E, CONFIG.mGlobalTag, contents);
	}

	public static void e(final String contents)
	{
		log(E, CONFIG.mGlobalTag, contents);
	}

	public static void e(final String tag, final Object... contents)
	{
		log(E, tag, contents);
	}

	public static void pe(final Object... contents)
	{
		plog(E, CONFIG.mGlobalTag, contents);
	}

	public static void a(final Object... contents)
	{
		log(A, CONFIG.mGlobalTag, contents);
	}

	public static void a(final String contents)
	{
		log(A, CONFIG.mGlobalTag, contents);
	}

	public static void a(final String tag, final Object... contents)
	{
		log(A, tag, contents);
	}

	public static void pa(final Object... contents)
	{
		plog(A, CONFIG.mGlobalTag, contents);
	}

	public static void file(final Object content)
	{
		log(FILE | D, CONFIG.mGlobalTag, content);
	}

	public static void file(@TYPE final int type, final Object content)
	{
		log(FILE | type, CONFIG.mGlobalTag, content);
	}

	public static void file(final String tag, final Object content)
	{
		log(FILE | D, tag, content);
	}

	public static void file(@TYPE final int type, final String tag, final Object content)
	{
		log(FILE | type, tag, content);
	}

	public static void pfile(final Object content)
	{
		plog(FILE | D, CONFIG.mGlobalTag, content);
	}

	public static void json(final String content)
	{
		log(JSON | D, CONFIG.mGlobalTag, content);
	}

	public static void json(final Object content)
	{
		json(JsonTool.getJsonString(content));
	}

	public static void json(@TYPE final int type, final String content)
	{
		log(JSON | type, CONFIG.mGlobalTag, content);
	}

	public static void json(final String tag, final String content)
	{
		log(JSON | D, tag, content);
	}

	public static void json(@TYPE final int type, final String tag, final String content)
	{
		log(JSON | type, tag, content);
	}

	public static void pjson(final String content)
	{
		plog(JSON | D, CONFIG.mGlobalTag, content);
	}

	public static void xml(final String content)
	{
		log(XML | D, CONFIG.mGlobalTag, content);
	}

	public static void xml(@TYPE final int type, final String content)
	{
		log(XML | type, CONFIG.mGlobalTag, content);
	}

	public static void xml(final String tag, final String content)
	{
		log(XML | D, tag, content);
	}

	public static void xml(@TYPE final int type, final String tag, final String content)
	{
		log(XML | type, tag, content);
	}

	public static void pxml(final String content)
	{
		plog(XML | D, CONFIG.mGlobalTag, content);
	}

	public static void nv(String contents)
	{
		Log.v(CONFIG.mGlobalTag, DataTool.getNotNull(contents));
	}

	public static void ni(String contents)
	{
		Log.i(CONFIG.mGlobalTag, DataTool.getNotNull(contents));
	}

	public static void nd(String contents)
	{
		Log.d(CONFIG.mGlobalTag, DataTool.getNotNull(contents));
	}

	public static void nw(String contents)
	{
		Log.w(CONFIG.mGlobalTag, DataTool.getNotNull(contents));
	}

	public static void ne(String contents)
	{
		Log.e(CONFIG.mGlobalTag, DataTool.getNotNull(contents));
	}

	public static void nv(String tag, String contents)
	{
		Log.v(tag, DataTool.getNotNull(contents));
	}

	public static void ni(String tag, String contents)
	{
		Log.i(tag, DataTool.getNotNull(contents));
	}

	public static void nd(String tag, String contents)
	{
		Log.d(tag, DataTool.getNotNull(contents));
	}

	public static void nw(String tag, String contents)
	{
		Log.w(tag, DataTool.getNotNull(contents));
	}

	public static void ne(String tag, String contents)
	{
		Log.e(tag, DataTool.getNotNull(contents));
	}

	public static void log(final int type, final String tag, final Object... contents)
	{
		if(!CONFIG.mLogSwitch || (!CONFIG.mLog2ConsoleSwitch && !CONFIG.mLog2FileSwitch))
		{
			return;
		}
		int type_low = type & 0x0f, type_high = type & 0xf0;
		if(type_low < CONFIG.mConsoleFilter && type_low < CONFIG.mFileFilter)
		{
			return;
		}
		final TagHead tagHead = processTagAndHead(tag);
		String body = processBody(type_high, contents);
		if(CONFIG.mLog2ConsoleSwitch && type_low >= CONFIG.mConsoleFilter && type_high != FILE)
		{
			print2Console(type_low, tagHead.tag, tagHead.consoleHead, body);
		}
		if((CONFIG.mLog2FileSwitch || type_high == FILE) && type_low >= CONFIG.mFileFilter)
		{
			print2File(type_low, tagHead.tag, tagHead.fileHead+body);
		}
	}

	public static void plog(final int type, final String tag, final Object... contents)
	{
		int type_low = type & 0x0f, type_high = type & 0xf0;
		if(type_low < CONFIG.mConsoleFilter && type_low < CONFIG.mFileFilter)
		{
			return;
		}
		final TagHead tagHead = processTagAndHead(tag);
		String body = processBody(type_high, contents);
		print2File(type_low, tagHead.tag, tagHead.fileHead+body);
	}

	private static TagHead processTagAndHead(String tag)
	{
		if(!CONFIG.mTagIsSpace && !CONFIG.mLogHeadSwitch)
		{
			tag = CONFIG.mGlobalTag;
		}
		else
		{
			final StackTraceElement[] stackTrace = new Throwable().getStackTrace();
			final int stackIndex = 3+CONFIG.mStackOffset;
			if(stackIndex >= stackTrace.length)
			{
				StackTraceElement targetElement = stackTrace[3];
				final String fileName = getFileName(targetElement);
				if(CONFIG.mTagIsSpace && isSpace(tag))
				{
					int index = fileName.indexOf('.');// Use proguard may not find '.'.
					tag = index == -1 ? fileName : fileName.substring(0, index);
				}
				return new TagHead(tag, null, ": ");
			}
			StackTraceElement targetElement = stackTrace[stackIndex];
			final String fileName = getFileName(targetElement);
			if(CONFIG.mTagIsSpace && isSpace(tag))
			{
				int index = fileName.indexOf('.');// Use proguard may not find '.'.
				tag = index == -1 ? fileName : fileName.substring(0, index);
			}
			if(CONFIG.mLogHeadSwitch)
			{
				String tName = Thread.currentThread().getName();
				final String head = new Formatter()
					.format("%s, %s.%s(%s:%d)", tName, targetElement.getClassName(), targetElement.getMethodName(), fileName, targetElement
						.getLineNumber())
					.toString();
				final String fileHead = " ["+head+"]: ";
				if(CONFIG.mStackDeep <= 1)
				{
					return new TagHead(tag, new String[]{head}, fileHead);
				}
				else
				{
					final String[] consoleHead = new String[Math.min(CONFIG.mStackDeep, stackTrace.length-stackIndex)];
					consoleHead[0] = head;
					int spaceLen = tName.length()+2;
					String space = new Formatter().format("%"+spaceLen+"s", "").toString();
					for(int i = 1, len = consoleHead.length; i < len; ++i)
					{
						targetElement = stackTrace[i+stackIndex];
						consoleHead[i] = new Formatter()
							.format("%s%s.%s(%s:%d)", space, targetElement.getClassName(), targetElement.getMethodName(), getFileName(targetElement), targetElement
								.getLineNumber())
							.toString();
					}
					return new TagHead(tag, consoleHead, fileHead);
				}
			}
		}
		return new TagHead(tag, null, ": ");
	}

	private static String getFileName(final StackTraceElement targetElement)
	{
		String fileName = targetElement.getFileName();
		if(fileName != null)
		{
			return fileName;
		}
		// If name of file is null, should add
		// "-keepattributes SourceFile,LineNumberTable" in proguard file.
		String className = targetElement.getClassName();
		String[] classNameInfo = className.split("\\.");
		if(classNameInfo.length > 0)
		{
			className = classNameInfo[classNameInfo.length-1];
		}
		int index = className.indexOf('$');
		if(index != -1)
		{
			className = className.substring(0, index);
		}
		return className+".java";
	}

	private static String processBody(final int type, final Object... contents)
	{
		String body = NULL;
		if(contents != null)
		{
			if(contents.length == 1)
			{
				Object object = contents[0];
				if(object != null)
				{
					body = object.toString();
				}
				if(type == JSON)
				{
					body = formatJson(body);
				}
				else if(type == XML)
				{
					body = formatXml(body);
				}
			}
			else
			{
				StringBuilder sb = new StringBuilder();
				for(int i = 0, len = contents.length; i < len; ++i)
				{
					Object content = contents[i];
					sb
						.append(ARGS)
						.append("[")
						.append(i)
						.append("]")
						.append(" = ")
						.append(content == null ? NULL : content.toString())
						.append(LINE_SEP);
				}
				body = sb.toString();
			}
		}
		return body.length() == 0 ? NOTHING : body;
	}

	public static String formatJson(Object jsonObj)
	{
		return formatJson(JsonTool.getJsonString(jsonObj));
	}

	public static String formatJson(String json)
	{
		try
		{
			if(json.startsWith("{"))
			{
				json = new JSONObject(json).toString(4);
			}
			else if(json.startsWith("["))
			{
				json = new JSONArray(json).toString(4);
			}
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return json;
	}

	public static String formatXml(String xml)
	{
		try
		{
			Source xmlInput = new StreamSource(new StringReader(xml));
			StreamResult xmlOutput = new StreamResult(new StringWriter());
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(xmlInput, xmlOutput);
			xml = xmlOutput.getWriter().toString().replaceFirst(">", ">"+LINE_SEP);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return xml;
	}

	private static void print2Console(final int type, final String tag, final String[] head, final String msg)
	{
		if(CONFIG.mSingleTagSwitch)
		{
			StringBuilder sb = new StringBuilder();
			sb.append(PLACEHOLDER).append(LINE_SEP);
			if(CONFIG.mLogBorderSwitch)
			{
				sb.append(TOP_BORDER).append(LINE_SEP);
				if(head != null)
				{
					for(String aHead : head)
					{
						sb.append(LEFT_BORDER).append(aHead).append(LINE_SEP);
					}
					sb.append(MIDDLE_BORDER).append(LINE_SEP);
				}
				for(String line : msg.split(LINE_SEP))
				{
					sb.append(LEFT_BORDER).append(line).append(LINE_SEP);
				}
				sb.append(BOTTOM_BORDER);
			}
			else
			{
				if(head != null)
				{
					for(String aHead : head)
					{
						sb.append(aHead).append(LINE_SEP);
					}
				}
				sb.append(msg);
			}
			printMsgSingleTag(type, tag, sb.toString());
		}
		else
		{
			printBorder(type, tag, true);
			printHead(type, tag, head);
			printMsg(type, tag, msg);
			printBorder(type, tag, false);
		}
	}

	private static void printBorder(final int type, final String tag, boolean isTop)
	{
		if(CONFIG.mLogBorderSwitch)
		{
			Log.println(type, tag, isTop ? TOP_BORDER : BOTTOM_BORDER);
		}
	}

	private static void printHead(final int type, final String tag, final String[] head)
	{
		if(head != null)
		{
			for(String aHead : head)
			{
				Log.println(type, tag, CONFIG.mLogBorderSwitch ? LEFT_BORDER+aHead : aHead);
			}
			if(CONFIG.mLogBorderSwitch)
			{
				Log.println(type, tag, MIDDLE_BORDER);
			}
		}
	}

	private static void printMsg(final int type, final String tag, final String msg)
	{
		int len = msg.length();
		int countOfSub = len/MAX_LEN;
		if(countOfSub > 0)
		{
			int index = 0;
			for(int i = 0; i < countOfSub; i++)
			{
				printSubMsg(type, tag, msg.substring(index, index+MAX_LEN));
				index += MAX_LEN;
			}
			if(index != len)
			{
				printSubMsg(type, tag, msg.substring(index, len));
			}
		}
		else
		{
			printSubMsg(type, tag, msg);
		}
	}

	private static void printMsgSingleTag(final int type, final String tag, final String msg)
	{
		int len = msg.length();
		int countOfSub = len/MAX_LEN;
		if(countOfSub > 0)
		{
			if(CONFIG.mLogBorderSwitch)
			{
				Log.println(type, tag, msg.substring(0, MAX_LEN)+LINE_SEP+BOTTOM_BORDER);
				int index = MAX_LEN;
				for(int i = 1; i < countOfSub; i++)
				{
					Log.println(type, tag, PLACEHOLDER+LINE_SEP+TOP_BORDER+LINE_SEP+LEFT_BORDER+
						msg.substring(index, index+MAX_LEN)+LINE_SEP+BOTTOM_BORDER);
					index += MAX_LEN;
				}
				if(index != len)
				{
					Log.println(type, tag,
					            PLACEHOLDER+LINE_SEP+TOP_BORDER+LINE_SEP+LEFT_BORDER+msg.substring(index, len));
				}
			}
			else
			{
				int index = 0;
				for(int i = 0; i < countOfSub; i++)
				{
					Log.println(type, tag, msg.substring(index, index+MAX_LEN));
					index += MAX_LEN;
				}
				if(index != len)
				{
					Log.println(type, tag, msg.substring(index, len));
				}
			}
		}
		else
		{
			Log.println(type, tag, msg);
		}
	}

	private static void printSubMsg(final int type, final String tag, final String msg)
	{
		if(!CONFIG.mLogBorderSwitch)
		{
			Log.println(type, tag, msg);
			return;
		}
		StringBuilder sb = new StringBuilder();
		String[] lines = msg.split(LINE_SEP);
		for(String line : lines)
		{
			Log.println(type, tag, LEFT_BORDER+line);
		}
	}

	private static void printSubMsg1(final int type, final String tag, final String msg)
	{
		if(!CONFIG.mLogBorderSwitch)
		{

			return;
		}
		StringBuilder sb = new StringBuilder();
		String[] lines = msg.split(LINE_SEP);
		for(String line : lines)
		{
			Log.println(type, tag, LEFT_BORDER+line);
		}
	}

	private static void print2File(final int type, final String tag, final String msg)
	{
		Date now = new Date(System.currentTimeMillis());
		String format = FORMAT.format(now);
		String date = format.substring(0, 5);
		String time = format.substring(6);
		final String fullPath = (CONFIG.mDir == null ? CONFIG.mDefaultDir : CONFIG.mDir)+CONFIG.mFilePrefix+"-"+date+
			".txt";
		if(!createOrExistsFile(fullPath))
		{
			Log.e("LogUtils", "create "+fullPath+" failed!");
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(time).append(T[type-V]).append("/").append(tag).append(msg).append(LINE_SEP);
		final String content = sb.toString();
		input2File(content, fullPath);
	}

	private static boolean createOrExistsFile(final String filePath)
	{
		File file = new File(filePath);
		if(file.exists())
		{
			return file.isFile();
		}
		if(!createOrExistsDir(file.getParentFile()))
		{
			return false;
		}
		try
		{
			boolean isCreate = file.createNewFile();
			if(isCreate)
			{
				printDeviceInfo(filePath);
			}
			return isCreate;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	private static void printDeviceInfo(final String filePath)
	{
		String versionName = "";
		int versionCode = 0;
		try
		{
			PackageInfo pi = Tool
				.getContext()
				.getPackageManager()
				.getPackageInfo(Tool.getContext().getPackageName(), 0);
			if(pi != null)
			{
				versionName = pi.versionName;
				versionCode = pi.versionCode;
			}
		}
		catch(PackageManager.NameNotFoundException e)
		{
			e.printStackTrace();
		}
		String time = filePath.substring(filePath.length()-9, filePath.length()-4);
		final String head = "************* Log Head ****************"+"\nDate of Log        : "+time+
			"\nDevice Manufacturer: "+Build.MANUFACTURER+"\nDevice Model       : "+Build.MODEL+
			"\nAndroid Version    : "+Build.VERSION.RELEASE+"\nAndroid SDK        : "+Build.VERSION.SDK_INT+
			"\nApp VersionName    : "+versionName+"\nApp VersionCode    : "+versionCode+
			"\n************* Log Head ****************\n\n";
		input2File(head, filePath);
	}

	private static boolean createOrExistsDir(final File file)
	{
		return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
	}

	private static boolean isSpace(final String s)
	{
		if(s == null)
		{
			return true;
		}
		for(int i = 0, len = s.length(); i < len; ++i)
		{
			if(!Character.isWhitespace(s.charAt(i)))
			{
				return false;
			}
		}
		return true;
	}

	private static void input2File(final String input, final String filePath)
	{
		if(sExecutor == null)
		{
			sExecutor = Executors.newSingleThreadExecutor();
		}
		Future<Boolean> submit = sExecutor.submit(new Callable<Boolean>()
		{
			@Override public Boolean call() throws Exception
			{
				BufferedWriter bw = null;
				try
				{
					bw = new BufferedWriter(new FileWriter(filePath, true));
					bw.write(input);
					return true;
				}
				catch(IOException e)
				{
					e.printStackTrace();
					return false;
				}
				finally
				{
					try
					{
						if(bw != null)
						{
							bw.close();
						}
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		try
		{
			if(submit.get())
			{
				return;
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		catch(ExecutionException e)
		{
			e.printStackTrace();
		}
		Log.e("LogUtils", "log to "+filePath+" failed!");
	}

	public static class Config
	{
		private String mDefaultDir;// log 默认文件存储目录
		private String mDir;       // log 文件存储目录
		private String mFilePrefix = "util";// log 文件前缀
		private boolean mLogSwitch = true;  // log 总开关
		private boolean mLog2ConsoleSwitch = true;  // log 控制台开关
		private String mGlobalTag = "TALE";  // log 全局 tag
		private boolean mTagIsSpace = true;  // The global tag is space.
		private boolean mLogHeadSwitch = true;  // log 头部信息开关
		private boolean mLog2FileSwitch = false; // log 文件开关
		private boolean mLogBorderSwitch = true;  // log 边框开关
		private boolean mSingleTagSwitch = true;  // log 单一 tag 开关（为美化 AS 3.1 的 Logcat）
		private int mConsoleFilter = V;     // log 控制台过滤器
		private int mFileFilter = V;     // log 文件过滤器
		private int mStackDeep = 1;     // log 栈深度
		private int mStackOffset = 0;     // log 栈偏移

		private Config()
		{
			if(mDefaultDir != null)
			{
				return;
			}
			if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) &&
				Tool.getContext().getExternalCacheDir() != null)
			{
				mDefaultDir = Tool.getContext().getExternalCacheDir()+FILE_SEP+"log"+FILE_SEP;
			}
			else
			{
				mDefaultDir = Tool.getContext().getCacheDir()+FILE_SEP+"log"+FILE_SEP;
			}
		}

		public Config setLogSwitch(final boolean logSwitch)
		{
			mLogSwitch = logSwitch;
			return this;
		}

		public Config setConsoleSwitch(final boolean consoleSwitch)
		{
			mLog2ConsoleSwitch = consoleSwitch;
			return this;
		}

		public Config setGlobalTag(final String tag)
		{
			if(isSpace(tag))
			{
				mGlobalTag = "";
				mTagIsSpace = true;
			}
			else
			{
				mGlobalTag = tag;
				mTagIsSpace = false;
			}
			return this;
		}

		public Config setLogHeadSwitch(final boolean logHeadSwitch)
		{
			mLogHeadSwitch = logHeadSwitch;
			return this;
		}

		public Config setLog2FileSwitch(final boolean log2FileSwitch)
		{
			mLog2FileSwitch = log2FileSwitch;
			return this;
		}

		public Config setDir(final String dir)
		{
			if(isSpace(dir))
			{
				mDir = null;
			}
			else
			{
				mDir = dir.endsWith(FILE_SEP) ? dir : dir+FILE_SEP;
			}
			return this;
		}

		public Config setDir(final File dir)
		{
			mDir = dir == null ? null : dir.getAbsolutePath()+FILE_SEP;
			return this;
		}

		public Config setFilePrefix(final String filePrefix)
		{
			if(isSpace(filePrefix))
			{
				mFilePrefix = "util";
			}
			else
			{
				mFilePrefix = filePrefix;
			}
			return this;
		}

		public Config setBorderSwitch(final boolean borderSwitch)
		{
			mLogBorderSwitch = borderSwitch;
			return this;
		}

		public Config setSingleTagSwitch(final boolean singleTagSwitch)
		{
			mSingleTagSwitch = singleTagSwitch;
			return this;
		}

		public Config setConsoleFilter(@TYPE final int consoleFilter)
		{
			mConsoleFilter = consoleFilter;
			return this;
		}

		public Config setFileFilter(@TYPE final int fileFilter)
		{
			mFileFilter = fileFilter;
			return this;
		}

		public Config setStackDeep(@IntRange(from = 1) final int stackDeep)
		{
			mStackDeep = stackDeep;
			return this;
		}

		public Config setStackOffset(@IntRange(from = 0) final int stackOffset)
		{
			mStackOffset = stackOffset;
			return this;
		}

		@Override public String toString()
		{
			return "switch: "+mLogSwitch+LINE_SEP+"console: "+mLog2ConsoleSwitch+LINE_SEP+"tag: "+
				(mTagIsSpace ? "null" : mGlobalTag)+LINE_SEP+"head: "+mLogHeadSwitch+LINE_SEP+"file: "+mLog2FileSwitch+
				LINE_SEP+"dir: "+(mDir == null ? mDefaultDir : mDir)+LINE_SEP+"filePrefix: "+mFilePrefix+LINE_SEP+
				"border: "+mLogBorderSwitch+LINE_SEP+"singleTag: "+mSingleTagSwitch+LINE_SEP+"consoleFilter: "+
				T[mConsoleFilter-V]+LINE_SEP+"fileFilter: "+T[mFileFilter-V]+LINE_SEP+"stackDeep: "+mStackDeep+LINE_SEP+
				"mStackOffset: "+mStackOffset;
		}
	}

	private static class TagHead
	{
		String tag;
		String[] consoleHead;
		String fileHead;

		TagHead(String tag, String[] consoleHead, String fileHead)
		{
			this.tag = tag;
			this.consoleHead = consoleHead;
			this.fileHead = fileHead;
		}
	}
}
