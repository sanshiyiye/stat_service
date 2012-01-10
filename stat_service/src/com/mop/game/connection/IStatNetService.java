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

import com.mop.game.core.IStatService;
import com.mop.game.service.IStatProcessor;

public interface IStatNetService extends IStatService
{
	/**添加消息监听器*/
	public void addProcessorListener(IStatProcessor processor);
	
	public boolean isConnected();
	
}
