# BaseCore
[![](https://img.shields.io/badge/platform-android-brightgreen.svg)](https://developer.android.com/index.html)  [![API](https://img.shields.io/badge/API-16%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=14)  [![](https://jitpack.io/v/vivitale/BaseCoreX.svg)](https://jitpack.io/#vivitale/BaseCoreX) [![Twitter](https://img.shields.io/badge/Gradle-3.2.1-brightgreen.svg)](https://github.com/vivitale/BaseCore)

[TOC]
 
## 使用方法
> 第一步 在 build.gradle(Project:XXXX) 的 repositories 添加::

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
> 第二步 在 build.gradle(Module:app) 的 dependencies 添加:

	dependencies {
	        implementation 'com.github.vivitale:BaseCore:latest.release'
	}

> 第三步 使用方法,在Application中初始化:
 
    Tool.init(this, BuildConfig.DEBUG);

## 库
```
	// 已经整合进入BaseCore中的库
	api 'com.android.support:support-v4:28.0.0'
	api 'com.android.support:appcompat-v7:28.0.0'
	api 'com.android.support:design:28.0.0'
	api 'com.android.support:recyclerview-v7:28.0.0'
	api 'com.android.support:cardview-v7:28.0.0'
	api 'com.android.support:percent:28.0.0'
	api 'com.android.support.constraint:constraint-layout:1.1.3'
	// json相关
	api 'com.google.code.gson:gson:2.8.5'
	// Knife
	api 'com.jakewharton:butterknife:8.8.1'
	annotationProcessor 'com.jakewharton:butterknife-compiler:8.8.1' //Java 的butterknife注解处理器
	// RxJava
	api 'io.reactivex.rxjava2:rxjava:2.1.14'
	api 'io.reactivex.rxjava2:rxandroid:2.0.2'
	api 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
	api 'com.trello.rxlifecycle2:rxlifecycle-components:2.1.0' // 包含android库和基础库
	api 'com.trello.rxlifecycle2:rxlifecycle-kotlin:2.1.0' // 支持Kotlin语法的RxLifecycle基础库
	api 'com.trello.rxlifecycle2:rxlifecycle-android-lifecycle-kotlin:2.1.0' //支持Kotlin语法的Android库
	// EventBus
	api 'org.greenrobot:eventbus:3.1.1'
	//沉浸式
	api 'com.readystatesoftware.systembartint:systembartint:1.0.4'
	// 动画
	api 'com.nineoldandroids:library:2.4.0'
	api 'com.github.florent37:viewanimator:1.0.5'
	//腾讯MMKV 取代sp
	api 'com.tencent:mmkv:1.0.11'

	// 依据项目情况需自行整合的库
	// 网络请求
	provided 'com.lzy.net:okgo:3.0.4' //可以单独使用，不需要依赖下方的扩展包
	provided 'com.lzy.net:okrx2:2.0.2' //RxJava扩展支持，根据需要添加
	provided 'com.lzy.net:okserver:2.0.5' //版本号使用 + 可以自动引用最新版
	provided 'com.squareup.okhttp3:okhttp:3.8.1'
	provided 'com.squareup.okio:okio:1.13.0'
	// 图片工具glide
	provided 'com.github.bumptech.glide:glide:4.7.1'
	annotationProcessor 'com.github.bumptech.glide:compiler:4.7.1'
	provided 'com.github.bumptech.glide:okhttp3-integration:4.7.1'
	// RecyclerView适配器工具
	provided 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.40'
	provided 'cn.bingoogolapple:bga-baseadapter:1.2.9@aar'
	// 轮播图
	provided 'cn.bingoogolapple:bga-banner:2.2.4@aar'
	// 二维码,一维码工具
	provided 'cn.bingoogolapple:bga-qrcode-zxing:1.2.1'
	provided 'cn.bingoogolapple:bga-qrcode-zbar:1.2.1'
	// 图片选择、预览、九宫格图片控件、拖拽排序九宫格图片控件,需要同时选择 bga-baseadapter
	provided 'cn.bingoogolapple:bga-photopicker:1.2.8@aar'
	// 日历控件
	// provided 'com.squareup:android-times-square:1.6.5@aar'
```
### 常用工具库
库名 | 简单说明 | GitHub | 使用方法
--------- | ------------- | ------------- | -------------
Okgo | 网络请求框架 |[Github](https://github.com/jeasonlzy/okhttp-OkGo) | [使用方法](https://github.com/jeasonlzy/okhttp-OkGo/wiki)
ViewAnimator  | 给View添加动画效果 |[Github](https://github.com/florent37)  | [使用方法](https://github.com/florent37/ViewAnimator/blob/master/README.md)
BaseRecyclerViewAdapterHelper  | RecyclerView适配器简单化库 |[Github](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)  | [使用方法](https://www.jianshu.com/p/b343fcff51b0)
BGABaseAdapter  | AdapterView 和 RecyclerView 中通用的 Adapter 和 ViewHolder |[Github](https://github.com/bingoogolapple/BGABaseAdapter-Android)  | 
BGABanner  | 轮播图 |[Github](https://github.com/bingoogolapple/BGABanner-Android)  | 
BGAQRCode  | 条码工具库 |[Github](https://github.com/bingoogolapple/BGAQRCode-Android)  | 
BGAPhotoPicker  | 图片选择、预览等 |[Github](https://github.com/bingoogolapple/BGAPhotoPicker-Android)
android-times-square  | 日历工具 |[Github](https://github.com/square/android-times-square)


## MVP框架介绍
* [ ] TODO 编辑MVP框架使用方法

## Util
### ActivityTool  -> Activity相关工具
方法名 | 说明
--------- | -------------
addActivity                 | 添加Activity 到栈
removeActivity              | Activity退栈
currentActivity             | 获取当前的Activity（堆栈中最后一个压入的)
finishActivity              | 结束当前Activity（堆栈中最后一个压入的）
finishAllActivity           | 结束所有的Activity
AppExit                     | 退出当前APP
getActivityStack            | 获取Activity栈
isExistActivity             | 判断是否存在指定Activity
isActivityExistsInStack     | 打开的栈中是否存在指定Activity
launchActivity              | 打开指定的Activity
skipActivity                | 跳转到指定Activity
skipActivityAndFinish       | 跳转到指定Activity并关闭当前Activity
skipActivityAndFinishAll    | 跳转后Finish之前所有的Activity
skipActivityForResult       | activityForResult封装
getLauncherActivity         | 获取launcher activity

### AnimationTool -> 简单动画工具
方法名 | 说明
--------- | -------------
animationColorGradient      | 颜色渐变动画
cardFilpAnimation           | 卡片翻转动画
zoomIn                      | 缩小动画
zoomOut                     | 放大动画
ScaleUpDowm                 | 缩小放大动画
animateHeight               | 高度变化动画
popup                       | 透明,大小,从0-1
popout                      | 透明,大小,从1-0
setAnimationListener        | 设置动画结束监听事件

### AppTool -> 应用操作工具
方法名 | 说明
--------- | -------------
InstallAPK                  | 安装APK
installApp                  | 安装App（支持7.0）
installAppSilent            | 静默安装App
uninstallApp                | 卸载App
uninstallAppSilent          | 静默卸载App
isAppRoot                   | 判断App是否有root权限
launchApp                   | 打开App
getAppPackageName           | 获取App包名
getAppDetailsSettings       | 获取App具体设置
getAppName                  | 获取App名称
getAppIcon                  | 获取App图标
getAppPath                  | 获取App路径
getAppVersionName           | 获取App版本号
getAppVersionCode           | 获取App版本码
isSystemApp                 | 判断App是否是系统应用
isAppDebug                  | 判断App是否是Debug版本
getAppSignature             | 获取App签名
getAppSignatureSHA1         | 获取应用签名的的SHA1值
isInstallApp                | 判断App是否安装
getAppInfo                  | 获取当前App信息
getBean                     | 得到AppInfo的Bean
getAllAppsInfo              | 获取所有已安装App信息
isAppBackground             | 判断当前App处于前台还是后台
relaunchApp                 | 重启APP


### BarTool -> 状态栏相关
方法名 | 说明
--------- | -------------
setTransparentStatusBar     | 设置透明状态栏(>=19)
hideStatusBar               | 隐藏状态栏
noTitle                     | 隐藏Title
FLAG_FULLSCREEN             | 设置全屏
getStatusBarHeight          | 获取状态栏高度
isStatusBarExists           | 判断状态栏是否存在
getActionBarHeight          | 获取ActionBar高度
showNotificationBar         | 显示通知栏
hideNotificationBar         | 隐藏通知栏
invokePanels                | 反射唤醒通知栏
transparencyBar             | 修改状态栏为全透明(>=19)
setStatusBarColor           | 修改状态栏颜色(>=19)
StatusBarLightMode          | 设置状态栏黑色字体图标，适配4.4以上版本MIUIV、Flyme和6.0以上版本其他Android
StatusBarDarkMode           | 清除MIUI或flyme或6.0以上版本状态栏黑色字体
FlymeSetStatusBarLightMode  | 设置状态栏图标为深色和魅族特定的文字风格
MIUISetStatusBarLightMode   | 设置状态栏字体图标为深色，需要MIUIV6以上


### BitmapTool -> 图像工具类
方法名 | 说明
--------- | -------------
**图片转换**|
bitmap2Bytes                | bitmap转byteArr
bytes2Bitmap                | byteArr转bitmap
drawable2Bitmap             | drawable转bitmap
bitmap2Drawable             | bitmap转drawable
drawable2Bytes              | drawable转byteArr
bytes2Drawable              | byteArr转drawable
bitmap2Base64               | Bitmap转Base64
base642Bitmap               | Base64转Bitmap
view2Bitmap                 | View 转 Bitmap
**图片处理**|
getBitmap                   | 获取bitmap
scale                       | 缩放图片
clip                        | 裁剪图片
skew                        | 倾斜图片
rotate                      | 旋转图片
fillet                      | 指定图片的切边，对图片进行圆角处理
getRotateDegree             | 获取图片旋转角度
toRound                     | 转为圆形图片
toRoundCorner               | 转为圆角图片
fastBlur                    | 快速模糊
renderScriptBlur            | renderScript模糊图片(API大于17)
stackBlur                   | stack模糊图片
addFrame                    | 添加颜色边框
addReflection               | 添加倒影
addTextWatermark            | 添加文字水印
addImageWatermark           | 添加图片水印
toAlpha                     | 转为alpha位图
toGray                      | 转为灰度图片
getThumb                    | 获取缩略图
zoomImage                   | 缩放图片
copyCenterBitmap            | 按指定宽高截取中间一段图片
resourceIdToUri             | 将资源ID转为Uri
resizeBitmap                | 重新计算图片的最大尺寸
**图片保存**|
save                        | 保存图片
saveBitmap2FileZIP          | 保存图片
saveBitmap2File             | 保存图片
**图片压缩有关**|
compressByScale             | 按缩放压缩
compressByQuality           | 按质量压缩
compressBySampleSize        | 按采样大小压缩
compressImage               | 压缩图片
**其他**|
GetLocalOrNetBitmap         | 得到本地或者网络上的bitmap
getBitmapByUrl              | 下载图片
calculateInSampleSize       | 计算采样大小
isImage                     | 根据文件名判断文件是否为图片
getImageType                | 获取图片类型


### BrightnessTool -> 亮度相关工具类
方法名 | 说明
--------- | -------------
isAutoBrightnessEnabled     | 判断是否开启自动调节亮度
setAutoBrightnessEnabled    | 设置是否开启自动调节亮度
getBrightness               | 获取屏幕亮度
setBrightness               | 设置屏幕亮度
setWindowBrightness         | 设置窗口亮度
getWindowBrightness         | 获取窗口亮度


### BroadcastTool -> 广播工具
方法名 | 说明
--------- | -------------
initRegisterReceiverNetWork | 注册监听网络状态的广播


### CameraTool -> 相机工具
方法名 | 说明
--------- | -------------
openFlashLight              | 打开闪光灯
closeFlashLight             | 关闭闪光灯


### ClipboardTool -> 剪切板工具
方法名 | 说明
--------- | -------------
copyText                    | 复制文本到剪贴板
getText                     | 获取剪贴板的文本
copyUri                     | 复制uri到剪贴板
getUri                      | 获取剪贴板的uri
copyIntent                  | 复制意图到剪贴板
getIntent                   | 获取剪贴板的意图


### Cockroach -> 降低Android非必要crash
项目地址:[https://github.com/android-notes/Cockroach](https://github.com/android-notes/Cockroach)


### ColorTool -> 颜色工具
方法名 | 说明
--------- | -------------
getColorById                | 通过color的资源id获取Color的int
getColorByInt               | int转Color的int
changeColorAlpha            | 修改颜色透明度
getAlphaPercent             | 获取透明度 0.0 ~ 1.0
adjustAlpha                 | 调整颜色的透明度
colorAtLightness            | 获取更加明亮的颜色
lightnessOfColor            | 获取更加明亮的颜色
getHexString                | 16进制的颜色值


### ConstTool -> 常量相关
方法名 | 说明
--------- | -------------
**存储相关常量**                  |
BYTE                        | Byte与Byte的倍数
KB                          | KB与Byte的倍数
MB                          | MB与Byte的倍数
GB                          | GB与Byte的倍数
**时间相关常量**                  |
MSEC                        | 毫秒与毫秒的倍数
SEC                         | 秒与毫秒的倍数
MIN                         | 分与毫秒的倍数
HOUR                        | 时与毫秒的倍数
DAY                         | 天与毫秒的倍数
**正则相关常量**                  |
REGEX_MOBILE_SIMPLE         | 手机号（简单）
REGEX_MOBILE_EXACT          | 手机号（精确）
REGEX_TEL                   | 电话号码
REGEX_IDCARD15              | 身份证号码15位
REGEX_IDCARD18              | 身份证号码18位
REGEX_EMAIL                 | 邮箱
REGEX_URL                   | URL
REGEX_CHZ                   | 汉字
REGEX_USERNAME              | 用户名，取值范围为a-z,A-Z,0-9,"_",汉字，不能以"_"结尾,用户名必须是6-20位
REGEX_DATE                  | yyyy-MM-dd格式的日期校验，已考虑平闰年
REGEX_IP                    | IP地址


### CrashTool -> 异常处理工具
程序发生异常,会将异常信息保存到本地文件夹


### DataCleanTool -> 应用数据清除管理器
方法名 | 说明
--------- | -------------
cleanInternalCache          | 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
cleanDatabases              | 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
cleanSharedPreference       | 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
cleanDatabaseByName         | 按名字清除本应用数据库
cleanFiles                  | 清除/data/data/com.xxx.xxx/files下的内容
cleanExternalCache          | 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
cleanCustomCache            |  清除自定义路径下的文件，使用需小心，请不要误删。
cleanApplicationData        | 清除本应用所有的数据
clearWebViewCache           | 清理Webview缓存数据库
getTotalCacheSize           | 获取缓存大小
clearAllCache               | 清除缓存
getFolderSize               | 获取文件大小
getFormatSize               | 格式化单位


### DataTool -> 数据处理相关
方法名 | 说明
--------- | -------------
**数据转换**|
string2Int                  | 字符串 转 整数 -转换失败将会 return 0;
string2Ints                 | 字符串 转 整型数组
string2Long                 | 字符串 转 long -转换失败将会 return 0;
string2Double               | 字符串 转 double -转换失败将会 return 0;
string2Float                | 字符串 转 float -转换失败将会 return 0;
string2InputStream          | 字符串 转 InputStream
input2OutputStream          | inputStream 转 outputStream
output2InputStream          | outputStream 转 inputStream
inputStream2Bytes           | inputStream 转 byteArr
bytes2InputStream           | byteArr 转 inputStream
outputStream2Bytes          | outputStream 转 byteArr
bytes2OutputStream          | outputStream 转 byteArr
inputStream2String          | inputStream 转 string按编码
string2InputStream          | string 转 inputStream按编码
outputStream2String         | outputStream 转 string按编码
string2OutputStream         | string 转 outputStream按编码
bytes2HexString             | byteArr 转 hexString
bytes2HexStringWithBlank    | byteArr 转 hexString
hexString2Bytes             | hexString 转 byteArr
bytes2String                | byteArr 转 对应内容
bytes2Int                   | int 转 byte[]
int2Bytes                   | byte[] 转 int
hex2Dec                     | hexChar 转 int
chars2Bytes                 | charArr 转 byteArr
bytes2Chars                 | byteArr 转 charArr
resId2Uri                   | 资源id 转 Uri
byte2Size                   | 字节数 转 以unit为单位的size
size2Byte                   | 以unit为单位的size 转 字节数
byte2FitSize                | 字节数 转 合适大小
hexString2BitCode           | 将16进制转为区位码
string2Unicode              | 将汉字转Unicode(汉字 -> \u6c49\u5b57)
unicode2String              | 将Unicode转汉字(\u6c49\u5b57 -> 汉字)
string2URL                  | 字符串 转 URL字符串(汉字 -> %e6%b1%89%e5%ad%97)
URL2String                  | URL字符串 转 字符串(%e6%b1%89%e5%ad%97 -> 汉字)
toASCII                     | 将字符编码转换成US-ASCII码
toISO_8859_1                | 将字符编码转换成ISO-8859-1码
toUTF_8                     | 将字符编码转换成UTF-8码
toUTF_16BE                  | 将字符编码转换成UTF-16BE码
toUTF_16LE                  | 将字符编码转换成UTF-16LE码
toUTF_16                    | 将字符编码转换成UTF-16码
toGBK                       | 将字符编码转换成GBK码
changeCharset               | 字符串编码转换
toDBC                       | 转化为半角字符
toSBC                       | 转化为全角字符
oneCn2ASCII                 | 单个汉字转成ASCII码
oneCn2PY                    | 单个汉字转成拼音
**数据处理**|
getAstro                    | 根据日期判断获取对应星座
hideMobilePhone4            | 隐藏手机中间4位号码 130****0000
formatCard                  | 格式化银行卡 加*  3749 **** **** 330
formatCardEnd4              | 获取银行卡后四位
format2Decimals             | 将字符串格式化为带两位小数的字符串
upperFirstLetter            | 首字母大写
lowerFirstLetter            | 首字母小写
reverse                     | 反转字符串
getPYFirstLetter            | 获得第一个汉字首字母
cn2PY                       | 中文转拼音
getAmountValue              | 金额格式化  ###,###,###,##0.00
getPercentValue             | 获取百分比  new BigDecimal(0.21) - 21%
intsGetSum                  | 整型数组求和
getRoundUp                  | 四舍五入
getNotNull                  | 若输入为null，则返回空字符串；否则返回字符串自身
getOrDefault                | 如果为空则返回默认值
stringToString              | 字符串的转义(处理特殊字符)
deleteBlank                 | 去除字符串中的空格，回车制表 符等。。。\t \r \n
deleteHTMLTag               | 去除String中的HTML标签



### DBTool -> 数据库工具
方法名 | 说明
--------- | -------------
exportDb2Sdcard             | 数据库导出到sdcard.


### DeviceTool -> 设备工具
方法名 | 说明
--------- | -------------
**屏幕相关**|
getScreenHeight             | 得到屏幕的高
getScreenWidth              | 得到屏幕的宽
getScreenWidths             | 得到设备屏幕的宽度
getScreenHeights            | 得到设备屏幕的高度
getScreenDensity            | 得到设备的密度
setLandscape                | 设置屏幕为横屏
setPortrait                 | 设置屏幕为竖屏
isLandscape                 | 判断是否横屏
isPortrait                  | 判断是否竖屏
getScreenRotation           | 获取屏幕旋转角度
captureWithStatusBar        | 获取当前屏幕截图，包含状态栏
captureWithoutStatusBar     | 获取当前屏幕截图，不包含状态栏
getDisplayMetrics           | 获取DisplayMetrics对象
isScreenLock                | 判断是否锁屏
**硬件信息相关**|
getUniqueSerialNumber       | 获取手机唯一标识序列号
getIMEI                     | 获取设备的IMEI
getIMSI                     | 获取设备的IMSI
getDeviceSoftwareVersion    | 获取设备的软件版本号
getLine1Number              | 获取手机号
getNetworkCountryIso        | 获取ISO标准的国家码，即国际长途区号
getNetworkOperator          | 获取设备的 MCC + MNC
getNetworkOperatorName      | 获取(当前已注册的用户)的名字
getNetworkType              | 获取当前使用的网络类型
getPhoneType                | 获取手机类型
getSimCountryIso            | 获取SIM卡的国家码
getSimOperator              | 获取SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字
getSimOperatorName          | 获取服务商名称
getSimSerialNumber          | 获取SIM卡的序列号
getSimState                 | 获取SIM的状态信息
getSubscriberId             | 获取唯一的用户ID
getVoiceMailNumber          | 获取语音邮件号码
getAndroid_id               | 获取ANDROID ID
getBuildBrandModel          | 获取设备型号，如MI2SC
getBuildBrand               | 获取设备品牌名称
getBuildMANUFACTURER        | 获取设备厂商，如Xiaomi
getSerialNumber             | 获取序列号
checkPermission             | 检查权限
getDeviceInfo               | 获取设备信息
ThroughArray                | 遍历LOG输出HashMap
getMacAddress               | 获取设备MAC地址
getLocalIpAddress           | GPS获取IP
getIpAddressWifi            | wifi获取IP
getIpAddressOperator        | 3G/4g网络IP
getIpAddress                | 获取本机的ip地址（上面3种方法都包括）
**app相关信息**|
getAppInfo                  | 获取包信息
getAppVersionNo             | 获取App版本号
getAppVersionName           | 获取App版本名称
reboot                      | 重启
reboot2Recovery             | 重启到 recovery
reboot2Bootloader           | 重启到 bootloader
**手机操作相关**|
isPhone                     | 判断设备是否是手机
getPhoneStatus              | 获取手机状态信息
dial                        | 跳至填充好phoneNumber的拨号界面
callPhone                   | 拨打电话
sendSms                     | 发送短信
getAllContactInfo           | 获取手机联系人
getContantNum               | 打开手机联系人界面点击联系人后便获取该号码
getAllSMS                   | 获取手机短信并保存到xml中


### DimenTool -> 尺寸转换
方法名 | 说明
--------- | -------------
dp2px                       | dp转px
dip2px                      | dip转px
px2dp                       | px转dp
px2dip                      | px转dip
sp2px                       | sp转px
px2sp                       | px转sp


### Download -> 下载工具
```
// ----------- 下载相关 ----------- 
@SuppressLint("SdCardPath")
public static final String path = "/mnt/sdcard/Download";// 保存到SD卡的文件夹
private String localfile = "";// 文件下载之后的本地真实地址
public String host = "http://daodao.zhiyoubao.com";//下载服务器的地址
private String dowonloadUrl = "";// 文件的最终下载地址
private static final String threadcount = "4";// 设置下载线程数为4
private DownloaderService downloader;

private Handler mHandler = new Handler()
{
    public void handleMessage(Message msg)
    {
        if (msg.what == 5478)
        {
            String url = (String) msg.obj;
            int length = msg.arg1;
            // 设置进度条按读取的length长度更新
            mProgressBar.incrementProgressBy(length);
            mTVProgress.setText(getPercent(mProgressBar.getProgress(), mProgressBar.getMax()));
            if (mProgressBar.getProgress() == mProgressBar.getMax())
            {
                // 下载完成！
                // 下载完成后清除进度条并将map中的数据清空
                downloader.delete(url);
                downloader.reset();
                ...
            }
        }
    }
};
/** 响应开始下载按钮的点击事件 */
private void startDownload()
{
    dowonloadUrl = host + mUpdateAppInfo.getFilePath();
    String fileName = context.getPackageName() + mUpdateAppInfo.getVersion() + ".apk";
    localfile = path + fileName;
    DownloadTask downloadTask = new DownloadTask();
    downloadTask.execute(dowonloadUrl, localfile, threadcount);
}
/** 响应暂停下载按钮的点击事件 */
private void pauseDownload()
{
    if (downloader != null)
    {
        downloader.pause();
    }
}
/** 显示进度条 */
private void showProgress(DownloadInfoDetail downloadInfoDetail)
{
    mProgressBar.setMax(downloadInfoDetail.getFileSize());
    mProgressBar.setProgress(downloadInfoDetail.getComplete());
}
/** 获取当前进度的百分比 */
private String getPercent(int now, int max)
{
    float percent = (float) now / (float) max * 100;
    DecimalFormat df = new DecimalFormat("#0.00");
    return df.format(percent) + "%";
}
/** 下载任务  */
private class DownloadTask extends AsyncTask<String, Integer, DownloadInfoDetail>
{
    String urlstr = null;
    @Override
    protected void onPreExecute()
    {
    }
    @Override
    protected DownloadInfoDetail doInBackground(String... params)
    {
        urlstr = params[0];
        String localfile = params[1];
        int threadcount = Integer.parseInt(params[2]);
        // 初始化一个downloader下载器
        if (downloader == null)
        {
            downloader = new DownloaderService(urlstr, localfile,
                threadcount, context, mHandler);
        }
        if (downloader.isdownloading())
        {
            return null;
        }
        // 得到下载信息类的个数组成集合
        return downloader.getDownloaderInfors();
    }
    @Override
    protected void onPostExecute(DownloadInfoDetail downloadInfoDetail)
    {
        if (downloadInfoDetail != null)
        {
            // 显示进度条
            showProgress(downloadInfoDetail);
            // 调用方法开始下载
            downloader.download();
        }
    }
}
```


### EditTextUtil -> 输入框控制工具类
方法名 | 说明
--------- | -------------
setInhibitInputSpeChat      | 禁止EditText输入特殊字符
setEditShowPass             | 设置editText是否显示密码
lockEdit                    | 使editText不可编辑
limitInput                  | 限制输入为中英文
setEdTwoDecimal             | 限制输入为两位小数
setEdDecimal                | 设置最多几位小数
setEditNumberAuto           | 设置指定格式输入数字


### EncodeTool -> 编码解码相关工具
方法名 | 说明
--------- | -------------
urlEncode                   | URL编码
urlDecode                   | URL解码
base64Encode                | Base64编码
base64Encode2String         | Base64编码
base64Decode                | Base64解码
base64UrlSafeEncode         | Base64URL安全编码
htmlEncode                  | Html编码
htmlDecode                  | Html解码


### EncryptTool -> 加密解密相关的工具 
方法名 | 说明
--------- | -------------
**哈希加密相关**                  |
encryptMD2ToString          | MD2加密
encryptMD2                  | MD2加密
encryptMD5ToString          | MD5加密
encryptMD5                  | MD5加密
encryptMD5File2String       | MD5加密文件
encryptMD5File              | MD5加密文件
encryptSHA1ToString         | SHA1加密
encryptSHA1                 | SHA1加密
encryptSHA224ToString       | SHA224加密
encryptSHA224               | SHA224加密
encryptSHA256ToString       | SHA256加密
encryptSHA256               | SHA256加密
encryptSHA384ToString       | SHA384加密
encryptSHA384               | SHA384加密
encryptSHA512ToString       | SHA512加密
encryptSHA512               | SHA512加密
encryptAlgorithm            | 对data进行algorithm算法加密
**DES加密相关**                 |
DESTemplet                  | DES加密
encryptDES                  | DES加密
encryptDES2Base64           | DES加密后转为Base64编码
encryptDES2HexString        | DES加密后转为16进制
decryptBase64DES            | DES解密Base64编码密文
decryptHexStringDES         | DES解密16进制密文
decryptDES                  | DES解密
**3DES加密相关**                 |
encrypt3DES2Base64          | 3DES加密后转为Base64编码
encrypt3DES2HexString       | 3DES加密后转为16进制
encrypt3DES                 | 3DES加密
decryptBase64_3DES          | 3DES解密Base64编码密文
decryptHexString3DES        | 3DES解密16进制密文
decrypt3DES                 | 3DES解密
**AES加密相关**                  |
encryptAES2Base64           | AES加密后转为Base64编码
encryptAES2HexString        | AES加密后转为16进制
encryptAES                  | AES加密
decryptBase64AES            | AES解密Base64编码密文
decryptHexStringAES         | AES解密16进制密文
decryptAES                  | AES解密


### ExifTool -> 文件写入Exif信息
方法名 | 说明
--------- | -------------
writeLatLonIntoJpeg         | 将经纬度信息写入JPEG图片文件里


### FragmentTool -> Fragment工具
方法名 | 说明
--------- | -------------
showFragment                | 动态显示Fragment


### FileTool -> 文件操作相关
方法名 | 说明
--------- | -------------
**SD卡操作**|
getRootPath                 | 得到SD卡根目录
getCecheFolder              | 获取本应用图片缓存目录
isSDCardEnable              | 判断SD卡是否打开
getSDCardPath               | 获取SD卡路径
getDataPath                 | 获取SD卡Data路径
getFreeSpace                | 获取SD卡剩余空间
sdCardIsAvailable           | SD卡是否可用
**文件操作**|
fileExists                  | 文件或者文件夹是否存在
delAllFile                  | 删除指定文件夹下所有文件, 不保留文件夹.
copy                        | 文件复制(文件路径)
copyFile                    | 复制文件(文件/InputStream流)
copyFolder                  | 复制整个文件夹内
renameFile                  | 重命名文件
getSDCardAvailaleSize       | 获取磁盘可用空间
getDirSize                  | 获取某个目录可用大小
getFileAllSize              | 获取文件或者文件夹大小
initFile                    | 创建一个文件
initDirectory               | 创建一个文件夹
saveFile                    | 保存InputStream流到文件
saveFileUTF8                | 用UTF8保存一个文件
getFileUTF8                 | 用UTF8读取一个文件
getFileIntent               | 得到一个文件Intent
getDiskCacheDir             | 获取缓存目录
getDiskFileDir              | 获取缓存视频文件目录
mergeFiles                  | 多个文件拼接合并
getNativeM3u                | 将在线的m3u8替换成本地的m3u8
write                       | 将字符串 保存成 文件
TextToFile                  | 传入文件名以及字符串, 将字符串信息保存到文件中
GetAllFileName              | 获取 搜索的路径 下的 所有 后缀 的文件
readFileByLines             | 以行为单位读取文件，常用于读面向行的格式化文件
getFileByPath               | 根据文件路径获取文件
isFileExists                | 判断文件是否存在
isDir                       | 判断是否是目录
isFile                      | 判断是否是文件
createOrExistsDir           | 判断目录是否存在，不存在则判断是否创建成功
createOrExistsFile          | 判断文件是否存在，不存在则判断是否创建成功
createFileByDeleteOldFile   | 判断文件是否存在，存在则在创建之前删除
copyOrMoveDir               | 复制或移动目录
copyOrMoveFile              | 复制或移动文件
copyDir                     | 复制目录
copyFile                    | 复制文件
moveDir                     | 移动目录
moveFile                    | 移动文件
deleteDir                   | 删除目录
deleteFile                  | 删除文件
listFilesInDir              | 获取目录下所有文件
listFilesInDirWithFilter    | 获取目录下所有后缀名为suffix的文件
searchFileInDir             | 获取目录下指定文件名的文件包括子目录
writeFileFromIS             | 将输入流写入文件
writeFileFromString         | 将字符串写入文件
readFile2List               | 指定编码按行读取文件到List
readFile2String             | 指定编码按行读取文件到字符串中
readFile2Bytes              | 指定编码按行读取文件到字符数组中
getFileCharsetSimple        | 简单获取文件编码格式
getFileLines                | 获取文件行数
getFileSize                 | 获取文件大小
getFileMD5                  | 获取文件的MD5校验码
closeIO                     | 关闭IO
getDirName                  | 获取全路径中的最长目录
getFileName                 | 获取全路径中的文件名
getFileNameNoExtension      | 获取全路径中的不带拓展名的文件名
getFileExtension            | 获取全路径中的文件拓展名
**清除数据**|
cleanInternalCache          | 清除内部缓存
cleanInternalFiles          | 清除内部文件
cleanInternalDbs            | 清除内部数据库
cleanInternalDbByName       | 根据名称清除数据库
cleanInternalSP             | 清除内部SP
cleanExternalCache          | 清除外部缓存
cleanCustomCache            | 清除自定义目录下的文件


### GlideTool -> Glide下载图片工具类
方法名 | 说明
--------- | -------------
loadImg                     | 加载图片(设置占位图,调整图片宽高,自定义)
loadImgCenterCrop           | 缩放宽和高都到达View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能超过边界
loadImgCenterInside         | 如果宽和高都在View的边界内，那就不缩放，否则缩放宽和高都进入View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能在边界内
loadImgFitCenter            | 缩放宽和高都进入View的边界，有一个参数在边界上，另一个参数可能在边界上，也可能在边界内
loadImgCircleCrop           | 加载圆形图片
loadImgRoundedCorners       | 加载圆角图片
loadImgNoCache              | 加载图片并且不缓存
loadImageSimpleTarget       | 加载图片返回Bitmap
getBitmap                   | 获取图片,或者图片路径
clean                       | 清空图片缓存


### IntentTool -> Intent相关 
方法名 | 说明
--------- | -------------
getInstallAppIntent         | 获取安装App(支持7.0)的意图
getUninstallAppIntent       | 获取卸载App的意图
getLaunchAppItent           | 获取打开App的意图
getAppInfoIntent            | 获取App信息的意图,App设置页面
getShareInfoIntent          | 获取App信息分享的意图
getIntentByPackageName      | 根据包名获取意图
getComponentNameIntent      | 获取其他应用的Intent
getShareTextIntent          | 获取分享文字的意图
getShareImageIntent         | 获取分享图片的意图
getShutdownIntent           | 关机重启的意图
getDialIntent               | 拨号的意图
getCallIntent               | 打电话的意图
getSendSmsIntent            | 发送短信的意图
getCaptureIntent            | 调用系统相机的意图
getOpenCameraIntent         | 获取打开照程序界面的Intent
getWebIntent                | 获取打开网页的意图
getImagePickerIntent        | 获取[跳转至相册选择界面,并跳转至裁剪界面，可以指定是否缩放裁剪区域]的Intent
getCameraIntent             | 获取[跳转至相册选择界面,并跳转至裁剪界面，默认可缩放裁剪区域]的Intent
getCropImageIntent          | 获取[跳转至裁剪界面]的Intent
getChoosedImage             | 获得选中相册的图片
getChoosedImagePath         | 获得选中相册的图片路径
getTakePictureFile          | 获取拍照之后的照片文件（JPG格式）


### JsonTool -> Json解析,拼装类
方法名 | 说明
--------- | -------------
getObject                   | 将数据解析成指定泛型并返回
getJsonString               | 将指定类变成Json型数据返回
getMapFromJson              | 将json字符串解析成Map
getMapFromObj               | 将object解析成Map


### KeyboardTool -> 软键盘工具 
方法名 | 说明
--------- | -------------
hideSoftInput               | 动态隐藏软键盘
hideKeyboard                | 点击隐藏软键盘
showSoftInput               | 动态显示软键盘
toggleSoftInput             | 切换键盘显示与否状态
clickBlankArea2HideSoftInput| 点击屏幕空白区域隐藏软键盘（方法1）
clickBlankArea2HideSoftInput1| 点击屏幕空白区域隐藏软键盘（方法2）
isSoftInputVisible          | 判断软键盘是否可见
registerSoftInputChangedListener  | 注册软键盘改变监听器
unregisterSoftInputChangedListener| 注销软键盘改变监听器
fixSoftInputLeaks           | 修复软键盘内存泄漏


### LocationTool -> 定位相关工具
方法名 | 说明
--------- | -------------
isGpsEnabled                | 判断Gps是否可用
isLocationEnabled           | 判断定位是否可用
openGpsSettings             | 打开Gps设置界面
registerLocation            | 注册Location
unRegisterLocation          | 注销Location
getAddress                  | 根据经纬度获取地理位置
getCountryName              | 根据经纬度获取所在国家
getLocality                 | 根据经纬度获取所在地
getStreet                   | 根据经纬度获取所在街道
gpsToDegree                 | GPS坐标 转换成 角度(例如 113.202222 转换成 113°12′8″)
GPS84ToGCJ02                | 国际 GPS84 坐标系 转换成 [国测局坐标系] 火星坐标系 (GCJ-02)
GCJ02ToGPS84                | [国测局坐标系] 火星坐标系 (GCJ-02) 转换成 国际 GPS84 坐标系
GCJ02ToBD09                 | 火星坐标系 (GCJ-02) 转换成 百度坐标系 (BD-09)
BD09ToGCJ02                 | 百度坐标系 (BD-09) 转换成 火星坐标系 (GCJ-02)
BD09ToGPS84                 | 百度坐标系 (BD-09) 转换成 国际 GPS84 坐标系
outOfChina                  | 判断经纬度是否在中国范围内
getLastLocation             | 获取最后一个已知经纬度


### LogTool -> 日志工具
方法名 | 说明
--------- | -------------
getConfig                   | 获取 log 配置
Config.setLogSwitch         | 设置 log 总开关
Config.setConsoleSwitch     | 设置 log 控制台开关
Config.setGlobalTag         | 设置 log 全局 tag
Config.setLogHeadSwitch     | 设置 log 头部信息开关
Config.setLog2FileSwitch    | 设置 log 文件开关
Config.setDir               | 设置 log 文件存储目录
Config.setFilePrefix        | 设置 log 文件前缀
Config.setBorderSwitch      | 设置 log 边框开关
Config.setSingleTagSwitch   | 设置 log 单一 tag 开关（为美化 AS 3.1 的 Logcat）
Config.setConsoleFilter     | 设置 log 控制台过滤器
Config.setFileFilter        | 设置 log 文件过滤器
Config.setStackDeep         | 设置 log 栈深度
Config.setStackOffset       | 设置 log 栈偏移
log                         | 自定义 tag 的 type 日志
v                           | tag 为类名的 Verbose 日志
pv                          | 打印 Verbose 日志
d                           | tag 为类名的 Debug 日志
pd                          | 打印 Debug 日志
i                           | tag 为类名的 Info 日志
pi                          | 打印 Info 日志
w                           | tag 为类名的 Warn 日志
pw                          | 打印 Warn 日志
e                           | tag 为类名的 Error 日志
pe                          | 打印 Error 日志
a                           | tag 为类名的 Assert 日志
pa                          | 打印 Assert 日志
file                        | log 到文件
json                        | log 字符串之 json
xml                         | log 字符串之 xml


### MapTool -> 地图工具(google,百度,高德)
方法名 | 说明
--------- | -------------
gaodeNaviActivity           | 启动高德App进行导航
gaodeWeb                    | 启动高德地图Web
baiduNaviActivity           | 启动百度App进行导航
baiduMapActivity            | 启动百度App显示位置
baiduMapWeb                 | 启动百度Web
googleNaviActivity          | 启动谷歌地图App进行导航
googleMapActivity           | 启动谷歌地图App
googleNaviWeb               | 打开google Web地图导航
isInstallByRead             | 根据包名检测某个APP是否安装
isInstallGaode              | 是否安装高德地图
isInstallBaidu              | 是否安装百度地图
isInstallGoogle             | 是否安装谷歌地图


### MathTool -> 数学计算类
方法名 | 说明
--------- | -------------
add                         | 加 返回double
sub                         | 减 返回double
mul                         | 乘 返回double
div                         | 除 返回double
round                       | 小数位四舍五入 返回double
roundStr                    | 小数位四舍五入 返回String

### NetTool -> 不断的ping网址,返回延迟
方法名 | 说明
--------- | -------------
NetPingTool                 | 初始化方法,传入网址,端口和回调方法
startGetDelay               | 开始监听
release                     | 释放
setmDomain                  | 设置网址
setmPort                    | 设置端口号
setDuration                 | 设置延迟,多久发送一次ping

### NetTool -> 网络工具
方法名 | 说明
--------- | -------------
ping                        | 判断是否有外网连接
isWifiEnabled               | 判断WIFI是否打开
is3rd                       | 判断是否为3G网络
isWifi                      | 判断网络连接方式是否为WIFI
isNetworkAvailable          | 判断网络连接是否可用
isGpsEnabled                | GPS是否打开
getNetWork                  | 获取当前网络状态
openWirelessSettings        | 打开网络设置界面
getActiveNetworkInfo        | 获取活动网络信息
isAvailable                 | 判断网络是否可用
isConnected                 | 判断网络是否连接
is4G                        | 判断网络是否是4G
isWifiConnected             | 判断wifi是否连接状态
getNetworkOperatorName      | 获取移动网络运营商名称
getPhoneType                | 获取移动终端类型
getNetWorkType              | 获取当前的网络类型
getNetWorkTypeName          | 获取当前的网络类型名称


### NotificationTool -> 系统消息
方法名 | 说明
--------- | -------------
sendSysNotification         | 发送系统消息的消息
clearNotification           | 取消指定id的消息


### PermissionsTool -> 权限工具
方法名 | 说明
--------- | -------------
getPermissions              | 获取程序中使用的权限列表
isGranted                   | 返回某些权限是否已经获取
```
PermissionTool
    .permission(PermissionConstants.PHONE, PermissionConstants.STORAGE)
    .rationale(new PermissionTool.OnRationaleListener()
    {
        @Override public void rationale(final ShouldRequest shouldRequest)
        {
            PermissionHelper.showRationaleDialog(shouldRequest);
        }
    })
    .callback(new PermissionTool.FullCallback()
    {
        @Override public void onGranted(List<String> permissionsGranted)
        {
        }
        @Override public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied)
        {
            if(!permissionsDeniedForever.isEmpty())
            {
                PermissionHelper.showOpenAppSettingDialog();
            }
        }
    })
    .theme(new PermissionTool.ThemeCallback()
    {
        @Override public void onActivityCreate(Activity activity)
        {
            PermissionHelper.setFullScreen(activity);
        }
    })
    .request();
```

### PermissionsHelper -> 权限简易获取辅助类
方法名 | 说明
--------- | -------------
requestCamera               | 获取相机权限
requestStorage              | 获取内存卡权限
requestPhone                | 获取电话权限
requestSms                  | 获取短信权限
requestLocation             | 获取地理位置权限
requestCalendar             | 获取日历权限
requestContacts             | 获取联系人权限
requestMicrophone           | 获取麦克风权限
requestSensors              | 获取人体传感器权限
request                     | 获取权限
showOpenAppSettingDialog    | 显示一个打开APP设置来获取权限的Dialog
showRationaleDialog         | 显示一个获取权限的提示
launchAppDetailsSettings    | 打开App的设置界面
setFullScreen               | activity设置全屏
```
PermissionHelper.requestStorage(new PermissionTool.FullCallback()
{
    @Override public void onGranted(List<String> permissionsGranted)
    {   
    }
    @Override public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied)
    {
        if(!permissionsDeniedForever.isEmpty())
        {
            PermissionHelper.showOpenAppSettingDialog();
        }
    }
});
```

### PhotoTool -> 进程相关
方法名 | 说明
--------- | -------------
openCameraImage             | 调用系统相机
openLocalImage              | 调用系统相册
cropImage                   | 裁剪图片
createImagePathUri          | 创建一条图片地址uri,用于保存拍照后的照片
getRealFilePath             | 获取图片uri的真实文件地址(4.4以下)
getImageAbsolutePath        | 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换


### ProcessTool -> 进程相关
方法名 | 说明
--------- | -------------
getForegroundProcessName    | 获取前台线程包名
getAllBackgroundProcesses   | 获取后台服务进程
killAllBackgroundProcesses  | 杀死所有后台服务进程
killBackgroundProcesses     | 杀死后台服务进程


### RegTool -> 正则表达式
方法名 | 说明
--------- | -------------
isNullString                | 判断字符串是否为空 为空即true
isEmpty                     | 判断对象是否为空 为空即true
isNotEmpty                  | 判断对象是否不为空
isEquals                    | 是否相同
isInteger                   | 判断字符串是否是整数
isDouble                    | 判断字符串是否是浮点数
isNumber                    | 判断字符串是否是数字
isMobileSimple              | 验证手机号（简单）
isMobileExact               | 验证手机号（精确）
isTel                       | 验证电话号码
isBankCard                  | 验证银卡卡号
isIDCard                    | 15位和18位身份证号码的正则表达式 身份证验证
isIDCard15                  | 验证身份证号码15位
isIDCard18                  | 验证身份证号码18位
isEmail                     | 验证邮箱
isURL                       | 验证URL
isChz                       | 验证汉字
isUsername                  | 验证用户名
isDate                      | 验证yyyy-MM-dd格式的日期校验，已考虑平闰年
isIP                        | 验证IP地址
isMatch                     | string是否匹配regex
stringFormat                | String.Format方法的封装



### ServiceTool -> 服务工具 
方法名 | 说明
--------- | -------------
isServiceRunning            | 获取服务是否开启
getAllRunningServices       | 获取所有运行中的服务


### ShellTool -> Shell命令关 
方法名 | 说明
--------- | -------------
isRoot                      | 判断设备是否root
execCmd                     | 执行命令


### SoundPoolTool -> 声音工具 
方法名 | 说明
--------- | -------------
create                      | 播放语音文件
dismisSoundPool             | 释放播放池

### SpTool -> SharedPreferences工具 
方法名 | 说明
--------- | -------------
init                        | 初始化
getString                   | 获取String
saveString                  | 保存String
getObject                   | 获取Object
saveObject                  | 保存Object
getBoolean                  | 获取boolean
saveBoolean                 | 保存boolean
getInt                      | 获取int
saveInt                     | 保存int
getLong                     | 获取long
saveLong                    | 保存long
getFloat                    | 获取float
saveFloat                   | 保存float
getAll                      | 获取所有的键值对
contains                    | 是否包含某key
remove                      | 移除
clear                       | 清除所有数据


### TextTool -> 文本工具

```
TextTool.getBuilder("")
    .setBold().setAlign(Layout.Alignment.ALIGN_CENTER)
    .append("测试").append("Url\n").setUrl(URL_VONTOOLS)
    .append("列表项\n").setBullet(60, getResources().getColor(R.color.baby_blue))
    .append("  测试引用\n").setQuoteColor(getResources().getColor(R.color.baby_blue))
    .append("首行缩进\n").setLeadingMargin(30, 50)
    .append("测试").append("上标").setSuperscript().append("下标\n").setSubscript()
    .append("测试").append("点击事件\n").setClickSpan(clickableSpan)
    .append("测试").append("serif 字体\n").setFontFamily("serif")
    .append("测试").append("图片\n").setResourceId(R.drawable.logo)
    .append("测试").append("前景色").setForegroundColor(Color.GREEN).append("背景色\n").setBackgroundColor(getResources().getColor(R.color.baby_blue))
    .append("测试").append("删除线").setStrikethrough().append("下划线\n").setUnderline()
    .append("测试").append("sans-serif 字体\n").setFontFamily("sans-serif")
    .append("测试").append("2倍字体\n").setProportion(2)
    .append("测试").append("monospace字体\n").setFontFamily("monospace")
    .append("测试").append("普通模糊效果字体\n").setBlur(3, BlurMaskFilter.Blur.NORMAL)
    .append("测试").append(" 粗体 ").setBold().append(" 斜体 ").setItalic().append(" 粗斜体 \n").setBoldItalic()
    .append("测试").append("横向2倍字体\n").setXProportion(2)
    .append("\n测试正常对齐\n").setAlign(Layout.Alignment.ALIGN_NORMAL)
    .append("测试居中对齐\n").setAlign(Layout.Alignment.ALIGN_CENTER)
    .append("测试相反对齐\n").setAlign(Layout.Alignment.ALIGN_OPPOSITE)
    .into(mTvAboutSpannable);
```


### ThreadPoolTool -> 线程池相关工具
方法名 | 说明
--------- | -------------
ThreadPoolTool              | 初始化创建一个线程池
execute                     | 在未来某个时间执行给定的命令
shutDown                    | 待以前提交的任务执行完毕后关闭线程池
shutDownNow                 | 试图停止所有正在执行的活动任务
isShutDown                  | 判断线程池是否已关闭
isTerminated                | 关闭线程池后判断所有任务是否都已完成
awaitTermination            | 请求关闭、发生超时或者当前线程中断
submit                      | 提交一个Callable任务用于执行
invokeAll                   | 执行给定的任务
invokeAny                   | 执行给定的任务
schedule                    | 延迟执行Runnable命令scheduleWithFixedRate       | 延迟并循环执行命令


### TimeTool -> 时间相关工具
方法名 | 说明
--------- | -------------
milliseconds2String         | 将时间戳转为时间字符串
string2Milliseconds         | 将时间字符串转为时间戳
string2Date                 | 将时间字符串转为Date类型
date2String                 | 将Date类型转为时间字符串
date2Milliseconds           | 将Date类型转为时间戳
milliseconds2Date           | 将时间戳转为Date类型
milliseconds2Unit           | 毫秒时间戳单位转换
getIntervalTime             | 获取两个时间差
getCurTimeMills             | 获取当前时间戳
getCurTimeString            | 获取当前时间字符串
getCurTimeDate              | 获取当前时间
getIntervalByNow            | 获取与当前时间的差
isLeapYear                  | 判断闰年
getCurrentDateTime          | 获取当前日期时间
getYestoryDate              | 得到昨天的日期
formatTime                  | 视频时间 转换成 "mm:ss"
formatSeconds               | "mm:ss" 转换成 视频时间
getDaysByYearMonth          | 根据年,月 获取对应的月份天数
stringForWeek               | 判断当前日期是星期几
changeFormat                | 修改日期格式
getDateInt                  | 返回int型的日期 yyyyMMdd
getTimeInt                  | 返回int型的时间 HHmmss
nDaysAfter                  | 推迟N天的日期
isInDate                    | 判断时间是否在时间段内
changeDateHMS               | 修改日期的时间段格式
isAEarlier                  | A的时间是不是比B早
getDateStart                | 获取某日的开始时间
getDateEnd                  | 获取某日的结束时间


### Tool -> Tools的常用工具类
方法名 | 说明
--------- | -------------
init                        | 初始化工具类
uninstall                   | 结束工具类


### VibrateTool -> 震动帮助类
方法名 | 说明
--------- | -------------
vibrateOnce                 | 简单震动一次
vibrateComplicated          | 复杂的震动
vibrateStop                 | 停止震动

### WebViewTool -> WebView工具类
方法名 | 说明
--------- | -------------
setWebData                  | 简单震动一次


## View

### CheckBox
#### AnimateCheckBox -> 一个带有动画效果的CheckBox
原项目地址:[https://github.com/hanks-zyh/AnimateCheckBox](https://github.com/hanks-zyh/AnimateCheckBox)

方法名 | 说明
--------- | -------------
setCircleColor              | 设置圆的颜色
setLineColor                | 设置对号的颜色
setUnCheckColor             | 设置未选中时的颜色
setOnCheckedChangeListener  | 设置状态改变监听
isChecked                   | 返回当前选中状态
setChecked                  | 设置当前选中状态

### Dialog
#### SweetAlertDialog -> 几种Dialog直接的切换显示
原项目地址:[https://github.com/pedant/sweet-alert-dialog](https://github.com/pedant/sweet-alert-dialog)
```
public static final int NORMAL_TYPE = 0;        // 普通模式
public static final int ERROR_TYPE = 1;            // 显示错误框
public static final int SUCCESS_TYPE = 2;        // 显示成功框
public static final int WARNING_TYPE = 3;        // 显示警告
public static final int CUSTOM_IMAGE_TYPE = 4;    // 显示图片
public static final int PROGRESS_TYPE = 5;        // 显示圆形进度条
```

#### RxDialogChooseImage -> 用来显示选择获取图片方式的Dialog
```
RxDialogChooseImage dialogChooseImage = new RxDialogChooseImage(mContext, TITLE);
dialogChooseImage.show();


override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
{
    when (requestCode)
    {
        PhotoTool.GET_IMAGE_FROM_PHONE ->
        { //选择相册之后的处理
            if (resultCode == RESULT_OK)
            {
                PhotoTool.cropImage(this@VerifyActivity, data?.data, 16, 11, 320, 220) // 裁剪图片
            }
        }
        PhotoTool.GET_IMAGE_BY_CAMERA  ->
        { //选择相册之后的处理
            if (resultCode == RESULT_OK)
            {
                PhotoTool.cropImage(this@VerifyActivity, PhotoTool.imageUriFromCamera, 16, 11, 320, 220) // 裁剪图片
            }
        }
        PhotoTool.CROP_IMAGE           ->
        {
            Glide.with(this@VerifyActivity)
                    .load(PhotoTool.cropImageUri)
                    .into(mIvPicture)
        }
    }
    super.onActivityResult(requestCode, resultCode, data)
}
```


#### RxDialogEditSureCancel -> 带有确认取消按钮的 输入框Dialog
```
final RxDialogEditSureCancel rxDialogEditSureCancel = new RxDialogEditSureCancel(mContext);//提示弹窗
rxDialogEditSureCancel.getTitleView().setBackgroundResource(R.drawable.logo);
rxDialogEditSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rxDialogEditSureCancel.cancel();
    }
});
rxDialogEditSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rxDialogEditSureCancel.cancel();
    }
});
rxDialogEditSureCancel.show();
```


#### RxDialogScaleView -> 查看图片并支持手势缩放
```
RxDialogScaleView rxDialogScaleView = new RxDialogScaleView(mContext);
rxDialogScaleView.setImage(...);
rxDialogScaleView.show();
```

#### RxDialogSure -> 有确定按钮的弹出框,显示基础信息
```
RxDialogSure rxDialogSure = new RxDialogSure(mContext);
rxDialogSure.getLogoView().setImageResource(R.drawable.logo);
rxDialogSure.getSureView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rxDialogSure.cancel();
                    }
                });
rxDialogSure.show();
```

#### RxDialogSureCancel -> 有确定,取消 按钮的弹出框,显示基础信息
```
RxDialogSureCancel rxDialogSureCancel = new RxDialogSureCancel(mContext);
rxDialogSureCancel.getTitleView().setBackgroundResource(R.drawable.logo);
rxDialogSureCancel.getSureView().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rxDialogSureCancel.cancel();
    }
});
rxDialogSureCancel.getCancelView().setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        rxDialogSureCancel.cancel();
    }
});
rxDialogSureCancel.show();
```


### EditText
#### ClearEditText -> 带清理功能的EditText 
方法名 | 说明
--------- | -------------
shake                       | 抖动
setAfterChangedListener     | 设置文字变化以后的事件

### ImageView
#### DynamicHeightImageView -> 可设置宽高比的图片
方法名 | 说明
--------- | -------------
setHeightRatio              | 设置ImageView的宽高尺寸比例
getHeightRatio              | 获取设置的宽高比例


#### GifView -> 显示Gif图片
方法名 | 说明
--------- | -------------
setMovieResource            | 设置gif的id


#### ScaleImageView -> 显示一个支持手势缩放的ImageView
原项目地址:[https://github.com/davemorrissey/subsampling-scale-image-view](https://github.com/davemorrissey/subsampling-scale-image-view)

### PopupWindow
#### MyPopupWindow  -> 可以监听显示,消失事件的POP
方法名 | 说明
--------- | -------------
setPopListener              | 设置监听POP显示隐藏的事件


#### PopLayout  -> 简单弹出一个指定layout,不传则弹出简单TextView
```
// 显示一个简单文本的pop
PopLayout popLayout = PopLayout(MainActivity.this, "厉害了")
// 显示一个自定义View的pop
PopLayout popLayout = PopLayout(MainActivity.this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.layout.activity_main)
//pop显示
popLayout.show(mBtn1)
```
#### PopListView  -> 弹出一个列表pop
```
PopListView popListView = new PopListView(this,
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
popListView.addAction(new ActionItem("标清"));
popListView.addAction(new ActionItem("高清"));
popListView.addAction(new ActionItem("超清"));
popListView.setColorItemText(0);
// popListView.setmAdapter();//给listView设置adapter来实现列表项目格式自定义
popListView.setItemOnClickListener(new PopListView.OnItemOnClickListener(){
    @Override public void onItemClick(ActionItem item, int position)
    {
        
    }
});
popListView.show(view, 0);
```

### RecyleView
类名 | 说明
--------- | -------------
DividerGridItemDecoration   | 设置Grid下RecyleView的间隔
DividerItemDecoration       | 设置List下RecyleView的间隔
FullyGridLayoutManager      | 使RecyclerView使用GridLayoutManager嵌套在ScrollView中
FullyLinearLayoutManager    | 使RecyclerView使用LinearLayoutManager嵌套在ScrollView中
FullyStaggeredGridLayoutManager| 使RecyclerView使用瀑布流嵌套在ScrollView中
SampleFooter                | 简单实现的没有更多底部栏
SampleHeader                | 简单实现的没有数据栏

#### MarqueeRecyclerView -> 自动跑马灯的RecyclerView
#### MyGridView -> 计算高度为全部item的GridView
#### MyListView -> 计算高度为全部item的ListView

### TextView
#### Anticlockwise -> 倒计时器
方法名 | 说明
--------- | -------------
initTime                    | 初始化时间 秒
reStart                     | 重新启动计时 秒
onPause                     | 暂停计时
onResume                    | 继续计时
setTimeFormat               | 设置时间格式
setOnTimeCompleteListener   | 计时结束时的事件


#### AutofitTextView -> 自适应文字大小的TextView
原项目地址:[https://github.com/grantland/android-autofittextview](https://github.com/grantland/android-autofittextview)

自动修改文字大小以适应View的宽度


#### MyDigitalClock -> 时钟控件,以自定义格式显示当前时间
方法名 | 说明
--------- | -------------
setFormat                   | 设置时间格式


#### RichText  -> Android平台下的富文本解析器
原项目地址:[https://github.com/zzhoujay/RichText](https://github.com/zzhoujay/RichText)
在此项目中集成的是老版本的RichText,单一个类文件,如需扩展Markdown等新功能请前往Github集成最新项目

方法名 | 说明
--------- | -------------
setRichText                 | 设置富文本内容
setPlaceHolder              | 设置站位图片
setErrorImage               | 设置错误图片
setOnImageClickListener     | 设置图片点击事件

#### RunTextView  -> 无限自动跑马灯

#### RxTextViewVertical  -> 无限向上自动翻滚的TextView
```
ArrayList<String> titleList = new ArrayList<String>();
titleList.add("测试轮播消息1");
...
mRxTextViewVertical.setTextList(titleList);
mRxTextViewVertical.setText(26, 5, 0xff766156);//设置属性
mRxTextViewVertical.setTextStillTime(3000);//设置停留时长间隔
mRxTextViewVertical.setAnimTime(300);//设置进入和退出的时间间隔
mRxTextViewVertical.setOnItemClickListener(new RxTextViewVertical.OnItemClickListener() {
    @Override
    public void onItemClick(int position) {
        RxToast.success(mContext, "点击了 : " + titleList.get(position), Toast.LENGTH_SHORT, true).show();
    }
});
```

#### RxTextViewVerticalMore  -> 无限向上自动翻滚的自定义View
```
List<View> views = new ArrayList<>();
viewsviews.add(VIEW);
...
mRxTextViewVerticalMore.setViews(views);
```

#### StateButton  -> 省去为Button写drawable的麻烦
原项目地址:[https://github.com/niniloveyou/StateButton](https://github.com/niniloveyou/StateButton)

Customizable attributes
-----------------------

|        Attribute       |      default value     |           xml            |                 java                |
|------------------------|------------------------|--------------------------|-------------------------------------|
| normalTextColor        |   original text color  | normalTextColor          | setNormalTextColor(int color)       |
| pressedTextColor       |   original text color  | pressedTextColor          | setPressedTextColor(int color)       |
| unableTextColor        |   original text color  | unableTextColor          | setUnableTextColor(int color)       |
| strokeDashWidth        |   0  | strokeDashWidth          | setStrokeDash(int dashWidth, int dashGap)       |
| strokeDashGap        |   0  | strokeDashGap          | setStrokeDash(int dashWidth, int dashGap)      |
| normalStrokeWidth        |   0  | normalStrokeWidth          | setNormalStrokeWidth(int widht)       |
| pressedStrokeWidth        |   0  | pressedStrokeWidth          | setPressedStrokeWidth(int widht)        |
| unableStrokeWidth        |   0  | unableStrokeWidth          | setUnableStrokeWidth(int widht)        |
| normalStrokeColor        |   0  | normalStrokeColor          | setNormalStrokeColor(int color)       |
| pressedStrokeColor        |   0  | pressedStrokeColor          | setPressedStrokeColor(int color)       |
| unableStrokeColor        |   0  | unableStrokeColor          | setUnableStrokeColor(int color)       |
| normalBackgroundColor        |   0  | normalBackgroundColor          | setNormalBackgroundColor(int color)       |
| pressedBackgroundColor        |   0  | pressedBackgroundColor   | setPressedBackgroundColor(int color)       |
| unableBackgroundColor        |   0  | unableBackgroundColor          | setUnableBackgroundColor(int color)       |
| radius        |   0  | radius          | setRadius(int radius) / setRadius(float[] radii)      |
| round        |  false  | round          | setRound(boolean round)       |
| animationDuration        |   0ms  | animationDuration          | setAnimationDuration(int duration)       |


xml中定义了以下属性
-  Define `xmlns:app="http://schemas.android.com/apk/res-auto"` on root of your xml file

```
<talex.zsw.basecore.view.textview.StateButton
            android:id="@+id/stateButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_horizontal"
            android:text="StateButton"
            
            app:radius="5dp"
            app:round="true"
            app:animationDuration="200"
            
            app:strokeDashGap="2dp"
            app:strokeDashWidth="5dp"
            app:normalStrokeWidth="2dp"
            app:pressedStrokeWidth="2dp"
            app:unableStrokeWidth="2dp"
            
            app:normalStrokeColor="@android:color/white"
            app:pressedStrokeColor="@android:color/white"
            app:unableStrokeColor="@android:color/white"
            
            app:normalTextColor="@android:color/white"
            app:pressedTextColor="@android:color/white"
            app:unableTextColor="@android:color/white"
            
            app:normalBackgroundColor="@color/colorPrimaryDark"
            app:pressedBackgroundColor="@color/colorPrimaryDark"
            app:unableBackgroundColor="@color/colorPrimaryDark"/>
```

#### TimeSinceTextView -> 根据当前系统时间,设置某个时间的表达
```
    <string name="timespan_now">刚刚</string>
    <plurals name="timespan_years">
        <item quantity="other">%d年前</item>
    </plurals>
    <plurals name="timespan_months">
        <item quantity="other">%d个月前</item>
    </plurals>
    <plurals name="timespan_weeks">
        <item quantity="other">%d周前</item>
    </plurals>
    <plurals name="timespan_days">
        <item quantity="other">%d天前</item>
    </plurals>
    <plurals name="timespan_hours">
        <item quantity="other">%d小时前</item>
    </plurals>
    <plurals name="timespan_minutes">
        <item quantity="other">%d分钟前</item>
    </plurals>
    <plurals name="timespan_seconds">
        <item quantity="other">%d秒前</item>
    </plurals>
    <plurals name="timespan_years_abbr">
        <item quantity="other">%d年</item>
    </plurals>
    <plurals name="timespan_months_abbr">
        <item quantity="other">%d月</item>
    </plurals>
    <plurals name="timespan_weeks_abbr">
        <item quantity="other">%d周</item>
    </plurals>
    <plurals name="timespan_days_abbr">
        <item quantity="other">%d天</item>
    </plurals>
    <plurals name="timespan_hours_abbr">
        <item quantity="other">%d小时</item>
    </plurals>
    <plurals name="timespan_minutes_abbr">
        <item quantity="other">%d分钟</item>
    </plurals>
    <plurals name="timespan_seconds_abbr">
        <item quantity="other">%d秒</item>
    </plurals>
```

### ViewPager
#### CustomViewPager -> 自动适应高度的ViewPager
在Activity中处理的方法

```
mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
{
    @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {
    
    }

    @Override public void onPageSelected(int position)
    {
        mViewPager.resetHeight(position);
    }

    @Override public void onPageScrollStateChanged(int state)
    {
    }
});
mViewPager.resetHeight(0);


/** 获取当前选中的Tab */
public int getCurr()
{
    return mViewPager.getCurrentItem();
}

/** 设置ViewPsger的存放的View和对应的position,此方法必须等View绘制完毕以后调用,如果是Fragment,则在onViewCreated之后调用 */
public void setObjectForPosition(View view, int position)
{
    mViewPager.setObjectForPosition(view, position);
}
```
在Fragment中处理的方法

```
((CompanyDetailActivity) getActivity()).setObjectForPosition(mView, position);
```

方法名 | 说明
--------- | -------------
resetHeight                 | 在切换tab的时候，重置ViewPager的高度
setObjectForPosition        | 设置ViewPsger的存放的View和对应的position,此方法必须等View绘制完毕以后调用,如果是Fragment,则在onViewCreated之后调用


### WebView
#### MyWebView -> 高度为网页最大的WevView
高度会自行计算的WebView

### Other
#### BGABadge -> Badge控件
原项目地址:[https://github.com/bingoogolapple/BGABadgeView-Android](https://github.com/bingoogolapple/BGABadgeView-Android)

该项目的老版本控件,不需使用注入的方式,直接调用对应的控件即可
BGABadgeView
BGADragBadgeView
BGABadgeTextView
BGABadgeImageView
BGABadgeRadioButton
BGABadgeFrameLayout
BGABadgeLinearLayout
BGABadgeRelativeLayout


方法名 | 说明
--------- | -------------
showCirclePointBadge        | 显示圆点徽章
showTextBadge               | 显示文字徽章
hiddenBadge                 | 隐藏徽章
showDrawableBadge           | 显示图像徽章
setDragDismissDelegage      | 设置拖动删除徽章的代理
isShowBadge                 | 是否正在显示徽章
isDraggable                 | 是否可拖拽
isDragging                  | 是否正在拖动

属性名 | 说明 | 默认值
----------- | ----------- | -----------
badge_bgColor         | 徽章背景色        | Color.RED
badge_textColor         | 徽章文本的颜色        | Color.WHITE
badge_textSize         | 徽章文本字体大小        | 10sp
badge_verticalMargin         | 徽章背景与宿主控件上下边缘间距离        | 4dp
badge_horizontalMargin         | 徽章背景与宿主控件左右边缘间距离        | 4dp
badge_padding         | 徽章文本边缘与徽章背景边缘间的距离        | 4dp
badge_gravity         | 徽章在宿主控件中的位置        | BGABadgeImageView和BGABadgeRadioButton是右上方，其他控件是右边垂直居中
badge_draggable         | 是否开启拖拽删除徽章        | false
badge_isResumeTravel         | 拖拽徽章超出轨迹范围后，再次放回到轨迹范围时，是否恢复轨迹        | false
badge_borderWidth         | 徽章描边宽度        | 0dp
badge_borderColor         | 徽章描边颜色        | Color.WHITE
badge_dragExtra         | 触发开始拖拽徽章事件的扩展触摸距离        | 4dp

#### DropDownMenu  -> 下拉菜单
原项目地址:[https://github.com/dongjunkun/DropDownMenu](https://github.com/dongjunkun/DropDownMenu)

相比原项目增加了更多可自定义项目,使用方法基本一致

#### FlowLayout -> Android流式布局，支持单选、多选等
原项目地址:[https://github.com/hongyangAndroid/FlowLayout](https://github.com/hongyangAndroid/FlowLayout)

```
<talex.zsw.basecore.view.other.flowlayout.TagFlowLayout
    android:id="@+id/mFlowLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:FL_max_select="1"/>

// 设置适配器
mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
   {
       @Override
       public View getView(FlowLayout parent, int position, String s)
       {
           TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                   mFlowLayout, false);
           tv.setText(s);
           return tv;
       }
   });
   
// 点击事件
mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
{
  @Override
  public boolean onTagClick(View view, int position, FlowLayout parent)
  {
      Toast.makeText(getActivity(), mVals[position], Toast.LENGTH_SHORT).show();
      return true;
  }
});

// Tag选中的回调事件
mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener()
{
  @Override
  public void onSelected(Set<Integer> selectPosSet)
  {
      getActivity().setTitle("choose:" + selectPosSet.toString());
  }
});

mFlowLayout.setMaxSelectCount(1)// 设置最大选中数量
flowAdapter?.setSelectedList(1)// 设置选中的列表
```

属性名 | 说明 | 默认值
----------- | ----------- | -----------
FL_max_select | -1为不限制选择数量，>=1的数字为控制选择tag的数量 | -1


### IconText -> 带有TextView上方组合一个ImageView
属性 | 说明
--------- | -------------
android:text                | 设置TextView文本
android:textSize            | 设置TextView文字大小
IT_text_selected_color      | 设置TextView选中时的颜色
IT_text_unselected_color    | 设置TextView未选中时的颜色
IT_icon_selected            | 设置ImageView选中时的图片
IT_icon_unselected          | 设置ImageView未选中的图片
IT_icon_width               | 设置ImageView的宽度
IT_icon_height              | 设置ImageView的高度
IT_icon_wrap                | 设置ImageView的高度是否自适应,默认true
IT_selected_background      | 设置选中时的背景色
IT_unselected_background    | 设置未选中时的背景色
IT_padding                  | 设置控件上下的padding
IT_match                    | 设置控件的高度是否充满,默认false
IT_badge_text               | 设置badge的文本
IT_badge_text_show          | 设置badge的文本是否显示
IT_badge_icon_show          | 设置badge的icon是否显示
IT_selected                 | 设置控件是否为选中状态

#### NiceSpinner -> 简单的Spinner的实现
原项目地址:[https://github.com/arcadefire/nice-spinner](https://github.com/arcadefire/nice-spinner)

```
 NiceSpinner niceSpinner = (NiceSpinner) findViewById(R.id.nice_spinner);
 List<String> dataset = new LinkedList<>(Arrays.asList("One", "Two", "Three", "Four", "Five"));
 niceSpinner.attachDataSource(dataset);
```


#### PageControlView  -> 底部小圆圈
方法名 | 说明
--------- | -------------
setCount                    | 设置圆圈数量
generatePageControl         | 设置当前选中圆圈
setSelectRes                | 设置选中圆圈资源id
setUnSelectRes              | 设置未选中圆圈资源id


#### RxToast  -> Activity相关工具
方法名 | 说明
--------- | -------------
**Toast 替代方法 ：立即显示无需等待**|
normal                      | 普通提示(深灰)
warning                     | 警告提示(黄)
info                        | 信息提示(蓝)
success                     | 成功提示(绿)
error                       | 错误提示(红)
warning                     | 警告提示(黄)
**系统 Toast 替代方法**           |
showToast                   | 显示系统toast
showToastShort              | 显示系统toast(短)
showToastLong               | 显示系统toast(短)
doubleClickExit             | 点击两次退出


#### SlideDateTimePicker  -> 日期时间滚轮选择
原项目地址:[https://github.com/jjobes/SlideDateTimePicker](https://github.com/jjobes/SlideDateTimePicker)

相比原项目,增加了一些新的设置选项,优化了项目布局

```
SlideDateTimeListener startListener = new SlideDateTimeListener(){
    @Override public void onDateTimeSet(Date date)
    {
        
    }
};
// Fragment中则使用getChildFragmentManager()
new SlideDateTimePicker.Builder(getSupportFragmentManager())
        .setListener(startListener)
        .setInitialDate(startDate)
        .setMinDate(minDate)
        .setMaxDate(maxDate)
        .setIndicatorColor(Color.parseColor("#990000"))
        .setShowTime(false)
        .setThemeColor(Color.parseColor("#FFFF00"))
        .setTitleColor(Color.parseColor("#FF0000"))
        .build()
        .show()
```


#### SquareLayout -> 保持宽高比一致的布局 

#### SwipeToLoadLayout -> 上拉刷新,下拉加载
原项目地址:[https://github.com/Aspsine/SwipeToLoadLayout](https://github.com/Aspsine/SwipeToLoadLayout)

相比原项目功能增加了可设置固定头部的功能

```
<talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeToLoadLayout
    android:id="@+id/mSwipeToLoadLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    app:swipe_style="scale">

    <include
        android:id="@id/swipe_refresh_header"
        layout="@layout/layout_twitter_header"/>

    <LinearLayout
        android:layout_width="match_parent"
        android:id="@id/swipe_head"
        android:background="@color/aqua"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="测试测试"/>

    </LinearLayout>

    <android.support.v7.widget.RecyclerView
        android:id="@id/swipe_target"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:clipToPadding="false"
        android:clipToPadding="false"
        android:overScrollMode="never"
        android:scrollbars="none"/>

    <include
        android:id="@id/swipe_load_more_footer"
        layout="@layout/layout_classic_footer"/>
</talex.zsw.baselibrary.view.SwipeToLoadLayout.SwipeToLoadLayout>
```

    可用已定义的下拉刷新头部
    layout_gif_header
    layout_google_hook_header
    layout_google_header
    layout_jd_header
    layout_twitter_header
    layout_yalantis_header
    
    可用已定义的上拉加载底部
    layout_classic_footer
    layout_google_hook_footer
    layout_google_footer

## Service & Receiver
### 开机启动服务 -> 该功能需要手动设置开机启动才会生效
1.在清单文件中添加开机启动的服务和广播

```
<!-- 开机启动 -->
<service
    android:name="talex.zsw.basecore.service.BootService">
</service>

<receiver android:name="talex.zsw.basecore.receiver.BootReceiver">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED"/>
        <category android:name="android.intent.category.HOME" />
    </intent-filter>
</receiver>
```
2.在清单文件中添加开机启动的权限

```
<!--开机启动服务-->
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED"/>
```
3.在MyApplication中接收开机启动的事件

```
@Subscribe public void onEvent(BootEvent event)
{
    Intent intentMainActivity = new Intent(this, MainActivity.class);
    intentMainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intentMainActivity);
}
```

### LocationService -> 地理位置相关的服务
1.在清单文件中添加获取地理位置的服务

```
<!-- 地理位置 -->
<service
    android:name="talex.zsw.basecore.service.LocationService">
</service>
```
2.代码中启动服务

```
private boolean isBind = false;
private LocationService mLocationService = null;
private ServiceConnection mLocationServiceConnection = new ServiceConnection(){
    @Override public void onServiceConnected(ComponentName name, IBinder service)
    {
        isBind = true;
        mLocationService = ((LocationService.LocationBinder)service).getService();
        mLocationService.setOnGetLocationListener(new LocationService.OnGetLocationListener(){
            @Override
            public void getLocation(String lastLatitude, String lastLongitude, String latitude, String longitude, String country, String locality, String street)
            {
                runOnUiThread(new Runnable(){
                    @Override public void run()
                    {
                        
                    }
                });
            }
        });
    }

    @Override public void onServiceDisconnected(ComponentName name)
    {
        mLocationService = null;
    }
};

private void bindService()
{
    PermissionHelper.requestLocation(new PermissionTool.FullCallback()
    {
        @Override public void onGranted(List<String> permissionsGranted)
        {
            Intent intent = new Intent(BaseMVPActivity.this, LocationService.class);
            bindService(intent, mLocationServiceConnection, Context.BIND_AUTO_CREATE);
        }

        @Override public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied)
        {
            PermissionHelper.showOpenAppSettingDialog();
        }
    });
}

private void unBindService()
{
    isBind = false;
    unbindService(mLocationServiceConnection);
}
```
### PingService -> ping服务器获取网络延迟
1.在清单文件中添加获取地理位置的服务

```
<!-- ping服务器 -->
<service
    android:name="talex.zsw.basecore.service.PingService">
</service>
```
2.代码中启动服务
```
private PingService mPingService;

private ServiceConnection mPingServiceConnection = new ServiceConnection()
{
    @Override public void onServiceConnected(ComponentName name, IBinder service)
    {
        LogTool.nv("onServiceConnected");
        mPingService = ((PingService.PingBinder) service).getService();
        mPingService.startPing("www.baidu.com", new NetPingTool.IOnNetPingListener()
        {
            @Override public void ontDelay(long log)
            {
                LogTool.ni("延迟 "+log+" ms");
            }

            @Override public void onError()
            {
                LogTool.ne("错误，网络不通");
            }
        });
    }

    @Override public void onServiceDisconnected(ComponentName name)
    {
        LogTool.nv("onServiceDisconnected");
        mPingService = null;
    }
};

private void bindPingService()
{
    LogTool.nv("bindPingService");
    Intent intent = new Intent(this, PingService.class);
    bindService(intent, mPingServiceConnection, Context.BIND_AUTO_CREATE);
}

private void unBindPingService()
{
    unbindService(mPingServiceConnection);
i}
```

![未完待续](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1527567486795&di=19907f5d73242150201c8779411e5b61&imgtype=0&src=http%3A%2F%2Fcdnq.duitang.com%2Fuploads%2Fitem%2F201501%2F10%2F20150110163418_h4JFG.thumb.700_0.jpeg)



-------
# 特别感谢 

以下文章与项目给予的极大灵感和支持

https://www.jianshu.com/u/46702d5c6978

https://github.com/vondear/RxTools

https://github.com/Blankj/AndroidUtilCode

https://github.com/zzhoujay/RichText

https://github.com/florent37/ViewAnimator

https://github.com/pedant/sweet-alert-dialog

https://github.com/Aspsine/SwipeToLoadLayout

https://github.com/grantland/android-autofittextview

https://github.com/bingoogolapple

https://github.com/dongjunkun/DropDownMenu

https://github.com/jjobes/SlideDateTimePicker

https://github.com/davemorrissey/subsampling-scale-image-view

https://github.com/hanks-zyh/AnimateCheckBox

https://github.com/square/android-times-square

https://github.com/hongyangAndroid/FlowLayout

https://github.com/arcadefire/nice-spinner

