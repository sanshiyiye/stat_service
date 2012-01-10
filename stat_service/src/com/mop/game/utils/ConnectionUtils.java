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
package com.mop.game.utils;

import com.mop.game.connection.IStatConnection;
import com.mop.game.connection.IStatConnectionProtocalSite;
import com.mop.game.service.IStatProcessor;
import com.mop.game.utils.LogUtils.LOGTYPE;

public class ConnectionUtils {

	public static final String SEP = "//";
	
	public static <T extends Object> void exec(String net,String error,T...param )
	{
		try 
		{
			String temp = "send://"+net;
			IStatConnection.exec(temp,null,param);
		}
		catch (Exception e) 
		{
			LogUtils.error(LOGTYPE.STAT_NET, ConnectionUtils.class, error, e);
//			StatErrorUtils.common(error, e);
		}
		
	}
	/**注册网络handler*/
	public static void registNetHandler(String net , IStatProcessor processor)
	{
		String temp = "rev://"+net;
		IStatConnection.exec(temp,processor);
	}
	/**
	 * 移除网络handler
	 * @param net
	 */
	public static void removeNetHandler(String net)
	{
		String temp = "close://"+net;
		IStatConnection.close(temp);
	}
	
	/**
	 * 获取网络参数
	 * @param array_net
	 * @param site
	 * @return
	 */
	public static String getParam(String []array_net ,IStatConnectionProtocalSite protocalSite)
	{
		if(protocalSite.SITE() < 0 ||protocalSite.SITE() > array_net.length)
			return null;
		return array_net[protocalSite.SITE()].replace(":", "");
	}
	
	/**
	 * 连接协议中俩字段
	 * @param sep
	 * @param armys_net
	 * @param site1
	 * @param site2
	 * @return
	 */
	public static String join(String [] array_net, IStatConnectionProtocalSite site1, IStatConnectionProtocalSite site2)
	{
		if(site1.SITE() < 0 ||site1.SITE() > array_net.length
				|| site2.SITE() < 0 ||site2.SITE() > array_net.length)
			return null;
		return array_net[site1.SITE()]+SEP+array_net[site2.SITE()];
	}
}
