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

import com.mop.game.core.IStatService;
import com.mop.game.core.IStatStatus;

public class StatTaskService implements IStatService{

	//任务模式
	IStatTask task ;
	
	public IStatTask getTask() {
		return task;
	}

	public void setTask(IStatTask task) {
		this.task = task;
	}

	//服务状态
	IStatStatus status = IStatStatus.STOP;
	@Override
	public void init(String... args)
	{
		//改变状态
		status = IStatStatus.INIT;
	}

	@Override
	public void shutDown()
	{
		
	}

	@Override
	public void restart() {
		
	}

	@Override
	public void start()
	{
		task.doTask();
	}

	
}
