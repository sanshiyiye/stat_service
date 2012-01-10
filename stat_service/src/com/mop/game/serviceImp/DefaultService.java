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
package com.mop.game.serviceImp;

import com.mop.game.connection.IStatRequest;
import com.mop.game.db.IStatDbDao;
import com.mop.game.service.AbstractStatAsynProcessor;

public class DefaultService extends AbstractStatAsynProcessor {

	IStatDbDao dao ;
	

	@Override
	public void query(IStatRequest request) {}


	public IStatDbDao getDao() {
		return dao;
	}


	public void setDao(IStatDbDao dao) {
		this.dao = dao;
	}

	
	
}
