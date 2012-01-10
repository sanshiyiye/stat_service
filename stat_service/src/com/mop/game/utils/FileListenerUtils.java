/*********************************************************************************
 *	(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	             *
 *********************************************************************************
 *********************************************************************************
 *																		                                                *
 *	 This program is free software; you can redistribute it and/or modify   *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	    *
 *	 (at your option) any later version.								                                *
 *																		                                                *
 ********************************************************************************/
package com.mop.game.utils;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class FileListenerUtils
{
	/**0: stop 1 :start*/
	byte status;
	/**file set */
	Map<File, Long> fileSet = new ConcurrentHashMap<File,Long>();
	public void start()
	{
		while(status != 0)
		{
			for (Entry<File, Long> eachEntry : fileSet.entrySet())
			{
				if(eachEntry.getKey().lastModified() > eachEntry.getValue())
				{
					
				}
			}
			try
			{
				Thread.sleep(1000 * 60);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[]args)
	{
	}
}
