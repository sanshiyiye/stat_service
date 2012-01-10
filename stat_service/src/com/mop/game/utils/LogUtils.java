/***************************************************************************
 *		(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	   *
 ***************************************************************************
 ***************************************************************************
 *																		                                                               *
 *	 This program is free software; you can redistribute it and/or modify  *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	   *
 *	 (at your option) any later version.								   *
 *																		   *
 **************************************************************************/
package com.mop.game.utils;


import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author w.l.hikaru
 *
 */
public class LogUtils {

//	static
//	{
//		PropertyConfigurator.configure(LogUtils.class.getResource("/").getPath() + "log4j.properties"); 
//	}
	//log type enum
	public enum LOGTYPE{STAT_NET,STAT_MAINTAIN,STAT_SERVICE,STAT_DEFAULT}
	//log entity map
	static Map<LOGTYPE, Logger> logMap = new HashMap<LOGTYPE, Logger>();
	//打印输出接口
	static PrintWriter print;
	//开发模式
	public static boolean demo = false;
	
	/**
	 * 获取模式
	 * @return
	 */
	public boolean isDemo()
	{
		return demo;
	}
	
	/**
	 * LogUtils 初始化
	 */
	public static  void init(String path)
	{
		//设置log路径
		if(path!= null )
		{
			File f = new File(path+"log4j.properties");
			if(f != null && f.exists())PropertyConfigurator.configure(path + "log4j.properties"); 
		}
		for (LOGTYPE eachLogType : LOGTYPE.values()) {
			logMap.put(eachLogType, Logger.getLogger(eachLogType.name()));
		}
		print = new PrintWriter(System.out);
	}
	
	/**
	 * 根据long类型名称返回log<br/>
	 * @param logName log类型名称
	 * @return
	 */
	public  static Logger getLog(String logName)
	{
		try 
		{
			LOGTYPE type = LOGTYPE.valueOf(logName);
			return getLog(type);
		} 
		catch (IllegalArgumentException e)
		{
			//
			return getLog(LOGTYPE.STAT_DEFAULT);
		}
		
	}
	/**
	 * 根据long类型返回log<br/>
	 * @param type log类型
	 */
	public static  Logger getLog(LOGTYPE type)
	{
		Logger log =  logMap.get(type);
		if(log != null) return log;
		else 
			return logMap.get(LOGTYPE.STAT_DEFAULT);
	}
	/**
	 * 记录错误日志
	 * @param type
	 * @param error
	 * @param param
	 */
	public static void error(LOGTYPE type, Class<?> obj,String error)
	{
		//打印Demo
		demoInfo(type+"#"+error);
		StringBuilder temp = new StringBuilder();
		temp.append("[").append(obj.getName()).append("]");
		temp.append(" - ").append("[ERROR] ");
		temp.append(error);
		getLog(type).error(temp.toString());
	}
	/**
	 * 记录错误日志
	 * @param type
	 * @param error
	 * @param param
	 */
	public static void error(LOGTYPE type, Class<?> obj,String error, Exception e)
	{
		//打印Demo
		demoInfo(type+"#"+error);
		StringBuilder temp = new StringBuilder();
		temp.append("[").append(obj.getName()).append("]");
		temp.append(" - ").append("[ERROR] ");
		temp.append(error);
		getLog(type).error(temp.toString(),e);
	}
	
	/**
	 * 记录提示日志
	 * @param type
	 * @param info
	 * @param param
	 */
	public static void info(LOGTYPE type, Class<?> obj,String info)
	{
		//打印Demo
		demoInfo(type+"#"+info);
		
		StringBuilder temp = new StringBuilder();
		temp.append("[").append(obj.getName()).append("]");
		temp.append(" - ").append("[INFO] ");
		temp.append(info);
		getLog(type).info(temp.toString());
		
		demoInfo(temp.toString());
	}
	/**
	 * 记录调试日志
	 * @param type
	 * @param debug
	 * @param param
	 */
	public static void debug(LOGTYPE type, Class<?> obj,String debug)
	{
		//打印Demo
		demoInfo(type+"#"+debug);
		StringBuilder temp = new StringBuilder();
		temp.append("[").append(obj.getName()).append("]");
		temp.append(" - ").append("[DEBUG] ");
		temp.append(debug);
		getLog(type).debug(temp.toString());
		
		
	}
	
	/**
	 * 记录警告日志
	 * @param type
	 * @param error
	 * @param param
	 */
	public static void warn(LOGTYPE type, Class<?> obj, String warn)
	{
		//打印Demo
		demoInfo(type+"#"+warn);
		
		StringBuilder temp = new StringBuilder();
		temp.append("[").append(obj.getName()).append("]");
		temp.append(warn);
		getLog(type).warn(temp.toString());
	}
	/**
	 * 开发模式打印
	 * @param info
	 */
	public static void demoInfo(String info)
	{
		if(!demo)return;
		print.println("/***********************************/");
		print.println(info);
		print.flush();
	}
	
	
	public static void main(String[]args)
	{
		LogUtils.init(null);
//		LogUtils.demo = true;
		LogUtils.error(LOGTYPE.STAT_DEFAULT,LogUtils.class, "{0}is wrong");
	}
}
