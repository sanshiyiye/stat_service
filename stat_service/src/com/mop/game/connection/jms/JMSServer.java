/*********************************************************************************
 *	(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	             *
 *********************************************************************************
 *********************************************************************************
 *																		                                                *
 *	 This program is free software; you can redistribute it and/or modify     *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	    *
 *	 (at your option) any later version.								                                *
 *																		                                                *
 ********************************************************************************/
package com.mop.game.connection.jms;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.mop.game.connection.IStatConnectionMethod;
import com.mop.game.connection.IStatNetService;


public class JMSServer 
{
	private Map<String, IStatNetService> jmsNetServices = new ConcurrentHashMap<String, IStatNetService>();
	/** JMS server instance */
	private static JMSServer instance;
	
	private static Object server = new Object();
	
	public static JMSServer getInstance()
	{
		synchronized (server){
			
			if(instance == null)
				instance = new JMSServer();
			return instance;
		}
	}
	/**
	 * 切换服务
	 * @param service
	 * @param method
	 * @return
	 */
	public IStatNetService switchService(IStatConnectionMethod method, String service)
	{
		return getService( service);
	}
	/**
	 * get JMSNetService  method 
	 * @param arrayNet
	 * @return
	 */
	public IStatNetService getService(String service) 
	{
		String key = service;
		IStatNetService net = null;
		if(jmsNetServices.containsKey(key))
		{
			net = jmsNetServices.get(key);
		}
		else 
		{
			net = new JMSNetService();
			jmsNetServices.put(service, net);
		}
		
		return net;
	}
}
