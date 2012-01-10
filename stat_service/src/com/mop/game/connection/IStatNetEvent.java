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

public class IStatNetEvent {
	//网络事件类型
	
	private volatile IStatEventType type;
	public IStatNetEvent(IStatEventType type)
	{
		this.type = type;
	}
	public IStatEventType getType() {
		return type;
	}
	public void setType(IStatEventType type) {
		this.type = type;
	}
	
}
