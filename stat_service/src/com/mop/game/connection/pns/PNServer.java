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
package com.mop.game.connection.pns;

import com.mop.game.connection.IStatConnectionMethod;
import com.mop.game.connection.IStatNetService;


public class PNServer {

	/** PNS server instance */
	private static PNServer instance;
	
	private static Object server = new Object();
	
	public static PNServer getInstance()
	{
		synchronized (server){
			
			if(instance == null)
				instance = new PNServer();
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
		IStatNetService net = null;
		switch(method)
		{
		case REV:
			net = getService(service);
			break;
		case SEND:
			net = getPusher(service);
			break;
		}
		return net;
	}

	/**
	 * 获取PNS
	 * @param service
	 * @return
	 */
	private IStatNetService getPusher(String service) 
	{
		return new PNSNetPusher();
	}
	/**
	 * 
	 * @param service
	 * @return
	 */
	private IStatNetService getService(String service) {
		// TODO Auto-generated method stub
		return null;
	}
}
