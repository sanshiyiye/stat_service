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

import java.net.URLDecoder;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.mop.game.connection.IStatConnectionProtocalSite;
import com.mop.game.connection.IStatNetService;
import com.mop.game.service.IStatProcessor;
import com.mop.game.utils.ConnectionUtils;
import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;
import com.mop.jmstools.Configuration;
import com.mop.jmstools.JMSTools;
import com.mop.jmstools.exception.JMSToolsException;
import com.mop.jmstools.servie.JMSService;

public class JMSNetService implements IStatNetService, MessageListener
{

	/**processor handler set*/
	Set<IStatProcessor> processors = new CopyOnWriteArraySet<IStatProcessor>();
	/**连接url*/
	private String _url;
	/**连接服务名称*/
	private String _service;
	/** 连接配置信息句柄*/
	private Configuration config;
	/**连接状态*/
	private boolean connected;
	@Override
	public void init(String... args)
	{
		this._url = ConnectionUtils.join(args, IStatConnectionProtocalSite.SITE_JMS_PROTOCAL,IStatConnectionProtocalSite.SITE_JMS_PROTOCAL_BODY);
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
		config = null;
		connected =false;
	}

	@Override
	public void start() 
	{
		try 
		{
			config = Configuration.DEFAULT();
			config.setUrl(_url);
			JMSTools tools = JMSTools.config(config);
			//开启服务监听
			JMSService service = tools.starService(_service);
			service.receiveTextMessage(this);
			connected = true;
		}
		catch (JMSToolsException e) 
		{
			shutDown();
			LogUtils.error(LOGTYPE.STAT_NET, JMSNetService.class, "JMSNetService start error",e);
//			StatErrorUtils.common("JMSNetService start error",e);
		}
		
	}
	

	@Override
	public void addProcessorListener(IStatProcessor processor)
	{
		processors.add(processor);
	}

	@Override
	public void onMessage(Message msg) 
	{
		try 
		{
			//TODO 暂只支持textMessage 如有消息长度过大或者效率问题 可提供ObjectMessage或者其他方式，暂时没有此瓶颈
			if(msg instanceof TextMessage)
			{
				TextMessage m = (TextMessage)msg;
				
				for (IStatProcessor processor : processors) 
				{
					JMSContext context =  new JMSContext(config);
					context.acceptTextMsg(URLDecoder.decode(m.getText(),"UTF-8"));
					processor.process(context.buildStatRequest(), context.buildStatResponse());
				}
				
			}
		}
		catch (Exception e)
		{
			LogUtils.error(LOGTYPE.STAT_NET, JMSNetService.class, "JMSNetService onMessage error!"+msg,e);
//			StatErrorUtils.common("JMSNetService onMessage error!"+msg,e);
		}
		
	}

	@Override
	public boolean isConnected() {
		return connected;
	}
	
}
