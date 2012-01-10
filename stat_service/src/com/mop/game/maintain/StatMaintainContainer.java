package com.mop.game.maintain;

import java.util.List;

import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;


public class StatMaintainContainer {

	private List<StatTaskService> container;
	
	
	public List<StatTaskService> getContainer()
	{
		return container;
	}

	public void setContainer(List<StatTaskService> container)
	{
		this.container = container;
	}
	/**
	 * 开启服务
	 */
	public void start()
	{
		for (StatTaskService eachService : container)
		{
			try 
			{
//				eachService.init(null);
				eachService.start();
			} 
			catch (Exception e)
			{
				LogUtils.error(LOGTYPE.STAT_MAINTAIN, StatMaintainContainer.class, "["+eachService.toString()+"]:"+"start error",e);
//				StatErrorUtils.common("["+eachService.toString()+"]:"+"start error",e);
			}
			
		}
	}

	/**
	 * 监听ExtService
	 */
	private void startListener() 
	{
		//TODO
	}
}
