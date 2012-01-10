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
package com.mop.game.maintain;

import com.mop.game.utils.AsynPool;


public class IStatTimerTask implements IStatTask, Runnable
{

	@Override
	public void doTask() {
		AsynPool.getInstance().addTimerTask(this,3,3);
	}

	@Override
	public void run() {
		//TODO task run
		
	}

	
}
