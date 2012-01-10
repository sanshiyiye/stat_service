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
package com.mop.game.service;

import com.mop.game.connection.IStatNetEvent;
import com.mop.game.core.IStatService;
import com.mop.game.core.IStatStatus;
import com.mop.game.utils.ConnectionUtils;
import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;

public abstract class IStatProcessorService implements IStatService,IStatProcessor{

	//网络连接
	private String net = null;
	//服务名称 也为服务ID 注册到服务容器中
	private String name = null;
	//当前服务状态
	private IStatStatus status = IStatStatus.STOP;
	
//	public IStatStatus getStatus() {
//		return status;
//	}

	public  void setStatus(IStatStatus status) {
		this.status = status;
	}

	@Override
	public synchronized void init(String... args)
	{
			if(IStatStatus.changeTo(status, IStatStatus.INIT))
			{
				if(args != null) 
				{
					int index = 0;
					while(index < args.length)
					{
						if("net".equals(args[index]))
						{
							net = args[++index];
						}
						if("name".equals(args[index]))
						{
							name = args[++index];
						}
						index+=1;
					}
				}
				//改变状态
				status = IStatStatus.INIT;
				LogUtils.getLog(name).info("["+name+"]"+":"+"has inited!");
			}
	}

	@Override
	public synchronized void restart() 
	{
		shutDown();
		start();
	}

	@Override
	public synchronized void shutDown() 
	{
		if(IStatStatus.changeTo(status, IStatStatus.STOP))
		{
			String m = net + ConnectionUtils.SEP + name;
			ConnectionUtils.removeNetHandler(m);
			//改变状态
			status = IStatStatus.STOP;
			LogUtils.getLog(name).info("["+name+"]"+":"+"has  shutdown!");
		}
	}

	@Override
	public synchronized void start() 
	{
		if(IStatStatus.changeTo(status, IStatStatus.START))
		{
			String m = net + ConnectionUtils.SEP + name;
			ConnectionUtils.registNetHandler(m, this);
			//改变状态
			status = IStatStatus.START;
			LogUtils.info(LOGTYPE.STAT_SERVICE, this.getClass(), "["+name+"]"+":"+"has started!");
		}
		
	}

	@Override
	public void handleNetEvent(IStatNetEvent event)
	{
		switch(event.getType())
		{
		case CLOSED:
			shutDown();
			break;
			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNet() {
		return net;
	}

	public void setNet(String net) {
		this.net = net;
	}
}
