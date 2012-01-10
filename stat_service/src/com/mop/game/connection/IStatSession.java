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
package com.mop.game.connection;

public class IStatSession
{
	private Object connection;

	public IStatSession()
	{
	}
	
	public Object getConnection() {
		return connection;
	}

	public void setConnection(Object connection) {
		this.connection = connection;
	}
	
	
}
