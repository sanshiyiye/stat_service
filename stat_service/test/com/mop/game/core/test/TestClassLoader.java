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
package com.mop.game.core.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;

import org.apache.activemq.protobuf.BufferOutputStream;

public class TestClassLoader extends ClassLoader {

	public Class load(String namefile,String classname) throws java.lang.Exception 
	{ 
	try { 
		//进行判断这个class是否已经调入,已经有就直接返回,不然就调入 
		Class ctmp=this.findLoadedClass(classname); 
		System.out.println(ctmp.getName()+" is load"); 
		return ctmp; 
	} 
	catch (Exception e)
	{ 
//		e.printStackTrace();
	} 
		java.io.FileInputStream in=new java.io.FileInputStream(namefile); 
		byte[] classbyte=new byte[4000]; 
		//实际应用时完全可以对一个文件进行加解密处理,只要保证使用defineClass时classbyte中 
		//已经解密后的内容就可以 
		int readsize; 
		readsize=in.read(classbyte); 
		// System.out.println("读文件长:"+readsize); 
		in.close(); 
		return defineClass(classname,classbyte,0,readsize); 
	} 

	public static void main(String[]args)
	{
		TestClassLoader load = new TestClassLoader();
		try {
//			Class v = load.load("D:/w.l.hikaru/project/admobService/bin/test/DAO$Boy.class", "test.DAO$Boy");
//			Class c = load.load("D:/w.l.hikaru/project/chuli/bin/CRC.class", "CRC");
//			Object o = c.newInstance();
//			o.getClass().getDeclaredMethod("test", null).invoke(o, null);
			Process p = Runtime.getRuntime().exec("java D:/w.l.hikaru/project/chuli/bin/CRC.class");
			String line = null;
			
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			byte[] data = new byte[in.available()];
			in.read(data);
			System.out.println(new String(data));
			in = new BufferedInputStream(p.getErrorStream());
			byte[] data2 = new byte[in.available()];
			in.read(data2);
			System.out.println(new String(data2));
		} 
		 catch (Exception e) {
			e.printStackTrace();
		}
	}
}
