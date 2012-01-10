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
package com.mop.game.service.test;

import com.mop.game.service.IStatProcessorService;
import com.mop.game.serviceImp.IOSPusherService;

public class ServiceStatusTest {

	public static void main(String[]args)
	{
		IStatProcessorService service = new IOSPusherService();
		service.restart();
		System.out.println("success");
	}
}
