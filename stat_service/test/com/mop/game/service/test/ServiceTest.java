/***************************************************************************
 *						(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	               *
 ***************************************************************************
 ***************************************************************************
 *																		                                                               *
 *	 This program is free software; you can redistribute it and/or modify                             *
 *	 it under the terms of the GNU General Public License as published by                             *
 *	 the Free Software Foundation; either version 2 of the License, or	                               *
 *	 (at your option) any later version.								                                                   *
 *																		                                                               *
 **************************************************************************/
package com.mop.game.service.test;

import java.util.Calendar;

import org.junit.Test;

import com.mop.game.connection.IStatConnectionProtocal;

public class ServiceTest
{
	@Test
	public void testTimer()
	{
		String url = "rev://http://www.sina.com/servie";;
		String[] temp = url.split("//");
		System.out.println(IStatConnectionProtocal.valueOf("JMS"));
		
	}
	
	public long createTableTaskDelay(int hour){
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, 1);
		now.set(Calendar.HOUR_OF_DAY, hour);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND , 0);
		return now.getTimeInMillis() - System.currentTimeMillis();
	} 
	/**
	 * 
	 */
	@Test
	public void testHour()
	{
		System.out.println(createTableTaskDelay(4)/1000/60/60);
	}
}
