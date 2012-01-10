package com.mop.game.constant;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StatRuntime
{
	ApplicationContext ctx;
	
	private static StatRuntime instance;
	
	public void init()
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static StatRuntime getInstance()
	{
		if(instance == null)
			instance = new StatRuntime();
		return instance;
	}
	/**
	 * 获取全局变量
	 * @param key
	 * @return
	 */
	public Object getApplicationAvaliable(String key)
	{
		return ctx.getBean(key);
	}
}
