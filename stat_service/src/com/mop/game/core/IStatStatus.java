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
package com.mop.game.core;


public enum IStatStatus {

	INIT,
	START,
	STOP;
	IStatStatus()
	{
	}
	/**
	 * 改变状态
	 * @param now
	 * @param change
	 * @return
	 */
	public static boolean changeTo(IStatStatus now, IStatStatus change)
	{
			boolean canchange = false;
			switch(now)
			{
			case INIT:
				if(change == START)
					canchange = true;
				break;
			case START:
				if(change == STOP)
					canchange = true;
				break;
			case STOP:
				if(change == INIT)
					canchange = true;
				break;
			}
			
			return canchange;
	}
	
	public static void main(String[]args)
	{
		IStatStatus status = IStatStatus.INIT;
		System.out.println(status.equals(STOP));
	}
		
}
