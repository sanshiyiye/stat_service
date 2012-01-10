package com.mop.game.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;


public class IStatServiceContainer {

	private List<IStatProcessorService> container;
	

	public List<IStatProcessorService> getContainer()
	{
		return new CopyOnWriteArrayList<IStatProcessorService>(container);
	}

	public void setContainer(List<IStatProcessorService> container)
	{
		this.container = container;
	}
	/**
	 * 开启服务
	 */
	public void startAll()
	{
		for (IStatProcessorService eachService : getContainer())
		{
			try 
			{
				//服务初始化 @modify by w.l.hikaru 采用spring框架依赖注入此处不进行初始化
//				eachService.init();
				//启动服务
				eachService.start();
			} 
			catch (Exception e)
			{
				LogUtils.error(LOGTYPE.STAT_SERVICE, IStatServiceContainer.class, "["+eachService.toString()+"]:"+"start error",e);
//				StatErrorUtils.common("["+eachService.toString()+"]:"+"start error",e);
			}
			
		}
	}
	/**
	 * 关闭所有服务
	 */
	public void shutdownAll()
	{
		for (IStatProcessorService eachService : getContainer()) {
			if(eachService!= null)
			{
				eachService.shutDown();
			}
		}
	}
	
	/**
	 * 杀死服务线程
	 */
	public void killService(String name)
	{
		
		for (IStatProcessorService eachService : getContainer()) {
			if(eachService!= null && eachService.getName()== name)
			{
				eachService.shutDown();
			}
		}
		
	}
	
	/**
	 * 杀死服务线程
	 */
	public void stopService(String name)
	{
		for (IStatProcessorService eachService : container) {
			if(eachService!= null && eachService.getName().equals(name))
			{
				eachService.shutDown();
			}
		}
		
	}
}
