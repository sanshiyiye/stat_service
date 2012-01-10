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

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.mop.game.connection.IStatConnectionProtocalSite;
import com.mop.game.connection.IStatNetPusherService;
import com.mop.game.service.IStatProcessor;
import com.mop.game.utils.ConnectionUtils;
import com.mop.game.utils.HttpUtil;

public class HttpNetPusher implements IStatNetPusherService
{

	/**processor handler set*/
	Set<IStatProcessor> processors = new CopyOnWriteArraySet<IStatProcessor>();
	/**连接url*/
	private String _url;
	/**连接服务名称*/
	public String _service;
	
	@Override
	public void init(String... args)
	{
		this._url = ConnectionUtils.join(args, IStatConnectionProtocalSite.SITE_PROTOCAL_TYPE,IStatConnectionProtocalSite.SITE_PROTOCALBODY);
		this._service = ConnectionUtils.getParam(args, IStatConnectionProtocalSite.SITE_JMS_SERVICE);
	}

	@Override
	public void restart() 
	{
		
	}

	@Override
	public void shutDown()
	{
		processors.clear();
		
	}

	@Override
	public void start() 
	{
		try 
		{
			HttpUtil.requestHttpContent(_url);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	

	@Override
	public void addProcessorListener(IStatProcessor processor)
	{
		processors.add(processor);
	}

	@Override
	public void pushParam(Object... obj) {
		
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
