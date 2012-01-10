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
package com.mop.game.service.bean;

public class ReportBean 
{
	private long id ;
	
	private String appid;
	
	private String areaid;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getServerid() {
		return serverid;
	}

	public void setServerid(String serverid) {
		this.serverid = serverid;
	}

	public String getPassportid() {
		return passportid;
	}

	public void setPassportid(String passportid) {
		this.passportid = passportid;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}

	public String getDeviceversion() {
		return deviceversion;
	}

	public void setDeviceversion(String deviceversion) {
		this.deviceversion = deviceversion;
	}

	public String getOperaiont() {
		return operaiont;
	}

	public void setOperaiont(String operaiont) {
		this.operaiont = operaiont;
	}

	public String getDtoken() {
		return dtoken;
	}

	public void setDtoken(String dtoken) {
		this.dtoken = dtoken;
	}

	public String getCversion() {
		return cversion;
	}

	public void setCversion(String cversion) {
		this.cversion = cversion;
	}

	private String serverid ;
	private String passportid;
	private String udid;
	
	private String ip;
	
	private String devicetype;
	
	private String deviceversion;
	
	private String operaiont ;
	
	private String dtoken;
	
	private String cversion ;
}
