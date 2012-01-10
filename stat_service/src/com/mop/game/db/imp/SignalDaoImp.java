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
package com.mop.game.db.imp;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.mop.game.db.IStatDbDao;
import com.mop.game.db.IStatDbDaoHandler;

public class SignalDaoImp extends JdbcDaoSupport implements IStatDbDao{

	
	@Override
	public void save(Object obj) {
//		String param = (String)obj;
//		String [] temp = param.split("\\|",-1);
	}

	@Override
	public void query(Object obj, IStatDbDaoHandler<?> handler) {
		// TODO Auto-generated method stub
		
	}

}
