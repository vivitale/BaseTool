package talex.zsw.basetool.receiver;

/**
 * 作用：锁屏,解锁,开屏的广播监听
 * 作者：tale email:vvtale@gmail.com
 * 创建时间：2021/6/27
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class ScreenEvent
{
	private String action;

	public ScreenEvent(String action)
	{
		this.action = action;
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}
}
