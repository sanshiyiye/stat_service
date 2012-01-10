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

import java.util.ArrayList;
import java.util.List;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.Payload;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.transmission.NotificationThreads;

import com.mop.game.connection.IStatConnectionProtocalSite;
import com.mop.game.connection.IStatNetPusherService;
import com.mop.game.service.IStatProcessor;
import com.mop.game.utils.ConnectionUtils;
import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;

public class PNSNetPusher implements IStatNetPusherService{

	@Override
	public void addProcessorListener(IStatProcessor processor) {
		
		
	}

	AppleNotificationServer server;
	
	private String keystore;
	
	private String password;
	
	private String production;
	
	private String alert;
	
	private String thread;
	
	@Override
	public void init(String... args)
	{
		try 
		{
			this.keystore = ConnectionUtils.getParam(args, IStatConnectionProtocalSite.SITE_PNS_KEYSTORE);
			this.password =  ConnectionUtils.getParam(args, IStatConnectionProtocalSite.SITE_PNS_PASSWORD);
			this.production = ConnectionUtils.getParam(args, IStatConnectionProtocalSite.SITE_PNS_PRODUCTION);
			this.alert = ConnectionUtils.getParam(args, IStatConnectionProtocalSite.SITE_PNS_ALERT);
			this.thread = ConnectionUtils.getParam(args, IStatConnectionProtocalSite.SITE_PNS_THREAD);
			server = new AppleNotificationServerBasicImpl(PNSNetPusher.class.getResource("/").getPath()+keystore, password, Boolean.parseBoolean(production));
		} 
		catch (Exception e) 
		{
			LogUtils.error(LOGTYPE.STAT_NET, PNSNetPusher.class, "pnsNetPusher init error",e);
//			StatErrorUtils.common("pnsNetPusher init error",e);
		}
		
		
	}

	@Override
	public void shutDown() {
		
		
	}

	@Override
	public void restart()
	{
		
		
	}

	@Override
	public void start() 
	{
		try 
		{
			if(param == null || param.length == 0)
				return;
			List<Device> devices = new ArrayList<Device>();
			for (Object p : param)
			{
				devices.add(new BasicDevice((String)p));
			}
			Payload payload = PushNotificationPayload.alert(alert);
			NotificationThreads work = new NotificationThreads(server, payload, devices, Integer.parseInt(thread)); 
			work.start();
			work.waitForAllThreads();
			//
		    List<PushedNotification> notifications = work.getPushedNotifications();
            for (PushedNotification notification : notifications) {
                if (notification.isSuccessful()) {
                        /* Apple accepted the notification and should deliver it */  
                        System.out.println("Push notification sent successfully to: " +
                                                        notification.getDevice().getToken());
                        /* Still need to query the Feedback Service regularly */  
                } else {
                        String invalidToken = notification.getDevice().getToken();
                        /* Add code here to remove invalidToken from your database */  
                        System.out.println("Push notification sent failed to: " + invalidToken);
                }
        }
		} 
		catch (Exception e)
		{
			LogUtils.error(LOGTYPE.STAT_NET, PNSNetPusher.class, "PNSNetPusher start error",e);
//			StatErrorUtils.common("PNSNetPusher start error",e);
		}
		
	}

	private Object[] param;
	
	@Override
	public void pushParam(Object... param) 
	{
		this.param = param;
		
	}

	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return true;
	}

}
