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
package com.mop.game.connection.pns;

public class PNSConfig {

	private String keystore;
	
	private String password;
	
	public String getKeystore() {
		return keystore;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getAlert() {
		return alert;
	}

	public PNSConfig() {
		super();
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getThread() {
		return thread;
	}

	public PNSConfig(String keystore, String password, String production,
			String alert, String thread) {
		this.keystore = keystore;
		this.password = password;
		this.production = production;
		this.alert = alert;
		this.thread = thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	private String production;
	
	private String alert;
	
	private String thread;
}
