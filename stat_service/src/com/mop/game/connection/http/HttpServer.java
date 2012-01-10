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
package com.mop.game.connection.http;

import com.mop.game.connection.IStatConnectionMethod;
import com.mop.game.connection.IStatNetService;


public class HttpServer {

	/** HTTP server instance */
	private static HttpServer instance;
	
	private static Object server = new Object();
	
	public static HttpServer getInstance()
	{
		synchronized (server){
			
			if(instance == null)
				instance = new HttpServer();
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
	 * 获取发布者类型网络接口
	 * @param service
	 * @return
	 */
	private IStatNetService getPusher(String service) {
		// 每提交一次http请求 创建一次http连接
		return new HttpNetPusher();
	}

	/**
	 * 获取服务类型网络接口
	 * @param service
	 * @return
	 */
	private IStatNetService getService(String service) {
		// TODO Auto-generated method stub
		return new HttpNetService();
	}
	
	
}
