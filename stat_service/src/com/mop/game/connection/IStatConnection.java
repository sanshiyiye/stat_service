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
package com.mop.game.connection;

import com.mop.game.connection.http.HttpServer;
import com.mop.game.connection.jms.JMSServer;
import com.mop.game.connection.pns.PNServer;
import com.mop.game.service.IStatProcessor;
import com.mop.game.utils.ConnectionUtils;
import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;

public class IStatConnection {

	/**
	 * 执行网络请求
	 */
	public static void exec(String protocal, IStatProcessor processor,Object ...param)
	{
		String [] array_net = protocal.split(ConnectionUtils.SEP);
		IStatNetService con = parseNetService(array_net);
		con.init(array_net);
		if(processor != null)
			con.addProcessorListener(processor);
		if((con instanceof IStatNetPusherService) && param != null && param.length != 0)
			((IStatNetPusherService)con).pushParam(param);
		con.start();
	}
	/**
	 * 执行网络请求
	 * @param temp
	 * @param processor
	 */
	public static void exec(String protocal, IStatProcessor processor) {
		exec(protocal, processor,new Object[0]);
	}
	/**
	 * 注销网络接口<br/>
	 * @param temp
	 */
	public static void close(String protocal)
	{
		String [] array_net = protocal.split(ConnectionUtils.SEP);
		IStatNetService con = parseNetService(array_net);
		con.shutDown();
	}
	/**
	 * 解析网络服务
	 * @param array_net
	 * @return
	 */
	private static IStatNetService parseNetService(String ...array_net)
	{
		IStatNetService net = null;
		try 
		{
			//获取标准协议类型
			String str_protocal = ConnectionUtils.getParam(array_net, IStatConnectionProtocalSite.SITE_PROTOCAL_TYPE).toUpperCase();
			IStatConnectionProtocal enum_protocal = IStatConnectionProtocal.valueOf(str_protocal);
			//获取标准协议连接方式
			String method = ConnectionUtils.getParam(array_net, IStatConnectionProtocalSite.SITE_METHOD).toUpperCase();
			IStatConnectionMethod enum_method = IStatConnectionMethod.valueOf(method);
			//根据协议类型选择连接方式
			switch(enum_protocal)
			{
			//JMS类型网络
			case JMS:
				String service = ConnectionUtils.getParam(array_net, IStatConnectionProtocalSite.SITE_JMS_SERVICE);
				net = JMSServer.getInstance().switchService(enum_method,service);
				break;
			//TCP/IP类型网络
			case TCP:
				break;
			//UDP类型网络
			case UDP:
				break;
			//HTTP类型网络
			case HTTP:
				net = HttpServer.getInstance().switchService(enum_method, "");
				break;
			//PNS类型网络
			case PNS:
				net = PNServer.getInstance().switchService(enum_method, "");
				break;
			}
		} 
		catch (Exception e) 
		{
			LogUtils.error(LOGTYPE.STAT_NET, IStatConnection.class, "parseNetService error",e);
//			StatErrorUtils.common(""+e.getMessage(),e.getCause());
		}
		
		return net;
	}
}
