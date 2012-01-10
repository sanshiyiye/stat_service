/*********************************************************************************
 *	(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	             *
 *********************************************************************************
 *********************************************************************************
 *																		                                                *
 *	 This program is free software; you can redistribute it and/or modify   *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	    *
 *	 (at your option) any later version.								                                *
 *																		                                                *
 ********************************************************************************/
package com.mop.game.connection;

import java.util.Iterator;
import java.util.Map.Entry;

public interface IStatNetContext
{
	/** 构造IStatSession对象*/
	public IStatSession buildStatSession();
	/** 构造IStatRequest*/
	public IStatRequest buildStatRequest();
	/** 构造IStatResponse*/
	public IStatResponse buildStatResponse();
	/** 获取参数*/
	public String getParam(String name);
	/** 获取全部参数*/
	public Iterator<Entry<String, Object>> paramIterator();
}
