/*********************************************************************************
 *	(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	             *
 *********************************************************************************
 *********************************************************************************
 *																		                                                *
 *	 This program is free software; you can redistribute it and/or modif     *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	    *
 *	 (at your option) any later version.								                                *
 *																		                                                *
 ********************************************************************************/
package com.mop.game.connection.jms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.mop.game.connection.IStatNetContext;
import com.mop.game.connection.IStatRequest;
import com.mop.game.connection.IStatResponse;
import com.mop.game.connection.IStatSession;
import com.mop.jmstools.Configuration;

public class JMSContext implements IStatNetContext {

	//参数map
	private Map<String, Object> param = new HashMap<String, Object>();
	//连接全局变量
	private IStatSession session;
	
	private IStatRequest request;
	
	private IStatResponse response;
	
	public JMSContext(Configuration config)
	{
		session = new IStatSession();
		session.setConnection(config);
		
		request = new IStatRequest(session, this);
		
		response = new IStatResponse(session, this);
	}

	/**
	 * 获得text消息文本<br/>
	 * <code>msg:key=value|key1=value1</code>
	 * 消息文本采用特殊格式<br/>
	 * @param msg
	 */
	public void acceptTextMsg(String msg)
	{
		String[] params = msg.split("\\|");
		for (String each : params) 
		{
			String[] temp = each.split("=");
			switch(temp.length)
			{
			case 1:
				param.put(temp[0], null);
				break;
			case 2:
				param.put(temp[0], temp[1]);
				break;
			}
		}
		
	}
	
	@Override
	public IStatRequest buildStatRequest() {
		
		return request;
	}

	@Override
	public IStatResponse buildStatResponse() {
		return response;
	}

	@Override
	public IStatSession buildStatSession() {
		
		return null;
	}

	@Override
	public String getParam(String name) {
		
		return (String) param.get(name);
	}
	@Override
	public Iterator<Entry<String, Object>> paramIterator()
	{
		return param.entrySet().iterator();
	}
}
