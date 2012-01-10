package com.mop.game;

import com.mop.game.constant.StatRuntime;
import com.mop.game.maintain.StatMaintainContainer;
import com.mop.game.service.IStatServiceContainer;
import com.mop.game.utils.AsynPool;
import com.mop.game.utils.LogUtils;

public class StatServer 
{
	/**初始化*/
	public void init()
	{
		//初始化全局环境变量
		System.out.println("===StatRuntime Init===");
		StatRuntime.getInstance().init();
		//初始化LoG工具
		try 
		{
			System.out.println("===LogUtils Init===");
			LogUtils.init((String)StatRuntime.getInstance().getApplicationAvaliable("log"));
		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
			LogUtils.init(null);
		}
		
	}
	/**服务器启动*/
	public void start()
	{
		//对外服务
		System.out.println("===IStatServiceContainer start===");
		IStatServiceContainer container = (IStatServiceContainer) StatRuntime.getInstance().getApplicationAvaliable("IStatContainer");
		container.startAll();
		//内部服务
		System.out.println("===StatMaintainContainer start===");
		StatMaintainContainer maintain = (StatMaintainContainer) StatRuntime.getInstance().getApplicationAvaliable("maintain");
		maintain.start();
	}
	
	public void shutdown()
	{
		//对外服务
		IStatServiceContainer container = (IStatServiceContainer) StatRuntime.getInstance().getApplicationAvaliable("IStatContainer");
		container.shutdownAll();
		AsynPool.getInstance().shutdown();
	}
	
	public static void main(String[]args)
	{
		StatServer server = new StatServer();
		server.init();
		server.start();
	}
}
