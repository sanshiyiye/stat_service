/*********************************************************************************
 *	(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	             *
 *********************************************************************************
 *********************************************************************************
 *																		                                                *
 *	 This program is free software; you can redistribute it and/or modify     *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	    *
 *	 (at your option) any later version.								                                *
 *																		                                                *
 ********************************************************************************/
package com.mop.game.connection.socket;

import com.mop.game.connection.IStatConnectionProtocalSite;
import com.mop.game.connection.IStatNetService;
import com.mop.game.service.IStatProcessor;
import com.mop.game.utils.ConnectionUtils;

public class SocketService implements IStatNetService {

	/**连接url*/
	private String _url;
	
	@Override
	public void init(String... args) {
		this._url = ConnectionUtils.join(args, IStatConnectionProtocalSite.SITE_PROTOCAL_TYPE,IStatConnectionProtocalSite.SITE_PROTOCALBODY);
		
	}

	@Override
	public void shutDown() {

	}

	@Override
	public void restart() {

	}

	@Override
	public void start() {
		
	}

	@Override
	public void addProcessorListener(IStatProcessor processor) {

	}

	@Override
	public boolean isConnected() {
		return false;
	}

}
