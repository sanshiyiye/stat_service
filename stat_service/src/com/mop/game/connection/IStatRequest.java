package com.mop.game.connection;

import java.util.Iterator;
import java.util.Map.Entry;

public class IStatRequest
{
	/** 持有访问Session对象*/
	private IStatSession session;
	
	private IStatNetContext context;
	
	public IStatRequest(IStatSession session, IStatNetContext context)
	{
		this.session = session;
		this.context = context;
	}
	
	public IStatSession getSession() {
		return session;
	}
	/**
	 * 获取请求参数
	 * @param name
	 * @return
	 */
	public String getParam(String name)
	{
		return context.getParam(name).trim();
	}
	/**
	 * 获取全部请求参数<br/>
	 * @return
	 */
	public Iterator<Entry<String, Object>> getParams()
	{
		return context.paramIterator();
	}
	
	/**
	 * 返回请求快照
	 * @return
	 */
	public String snapshot()
	{
		StringBuilder temp = new StringBuilder();
		Iterator<Entry<String, Object>> it = context.paramIterator();
		while(it.hasNext())
		{
			Entry<String, Object> entry = it.next();
			temp.append(entry.getKey()).append("=").append(entry.getValue());
		}
		return temp.toString();
	}
}
