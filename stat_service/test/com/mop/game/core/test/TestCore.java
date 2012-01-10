/***************************************************************************
 *						(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>				                   *
 ***************************************************************************
 ***************************************************************************
 *																		                                                               *
 *	 This program is free software; you can redistribute it and/or modify  *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	     *
 *	 (at your option) any later version.								                         *
 *																		                                                               *
 **************************************************************************/
package com.mop.game.core.test;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCore
{
	public void testSpringLoad()
	{
		File f = new File("applicationContext.xml");
		System.out.println(f.getAbsolutePath());
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		
//		ChargeDao dao = (ChargeDao) ctx.getBean("ChargeDao");
//		ChargeBean bean = new ChargeBean(12L,"userName","lstx","1.0.1","duowan",12321L,100,(byte)1);
//		dao.save(bean);
	}
	public static void main(String[]args)
	{
		Thread run = new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				long time = System.currentTimeMillis();
				int num =0;
				while(true)
				{
					num ++;
					
					System.out.println("============="+num+"===================");
					if(num == 1000)
					{
						
						try
						{
							System.out.println("Rate: "+num +"/"+((System.currentTimeMillis() - time)/1000));
							Thread.sleep(1000*30);
						}
						catch (InterruptedException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
			}
		});
//		run.start();
		TestCore t = new TestCore();
		t.testSpringLoad();
	}
}
