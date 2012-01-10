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
package com.mop.game.serviceImp;

import com.mop.game.connection.IStatRequest;
import com.mop.game.connection.IStatResponse;
import com.mop.game.constant.StatRuntime;
import com.mop.game.service.AbstractStatSynProcessor;
import com.mop.game.service.IStatServiceContainer;
import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;

public class ManagerService extends AbstractStatSynProcessor{

	enum SERVICE_OP{
		start,stop,kill;
	}
	@Override
	public void handle(IStatResponse response) {
		
		
	}

	@Override
	public void query(IStatRequest request) {
		
		try 
		{
			String service = request.getParam("service");
			String op = request.getParam("op").toLowerCase();
			IStatServiceContainer container = (IStatServiceContainer) StatRuntime.getInstance().getApplicationAvaliable("IStatContainer");
			switch(SERVICE_OP.valueOf(op))
			{
			case start:
				break;
			case stop:
				container.stopService(service);
				break;
			case kill:
				container.killService(service);
				break;
			}
			;
			LogUtils.getLog("service").info("[SERVICE-"+service+"]"+":"+op);
		} 
		catch (Exception e) 
		{
			LogUtils.error(LOGTYPE.STAT_SERVICE, ManagerService.class, "["+ManagerService.class.getName()+"]"+request.snapshot(), e);
//			StatErrorUtils.common("["+ManagerService.class.getName()+"]"+request.snapshot(), e);
		}
		
	}

	
	
}
