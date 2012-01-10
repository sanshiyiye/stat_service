package com.mop.game.connection;

public enum IStatConnectionProtocalSite {

	/**协议中字段的位置*/
	SITE_METHOD(0),
	SITE_PROTOCAL_TYPE(1),
	//普通协议字段
	SITE_PROTOCALBODY(2),
	SITE_SERVICE(3),
	//JMS特殊字段
	SITE_JMS_PROTOCAL(2),
	SITE_JMS_PROTOCAL_BODY(3),
	SITE_JMS_SERVICE(4),
	//PNS特殊字段
	SITE_PNS_KEYSTORE(2),
	SITE_PNS_PASSWORD(3),
	SITE_PNS_PRODUCTION(4),
	SITE_PNS_ALERT(5),
	SITE_PNS_THREAD(6),
	//HTTP协议字段
	SITE_HTTP_URL(2),
	SITE_HTTP_PORT(3);
	;
	private int site;
	IStatConnectionProtocalSite(int site){
		this.site = site;
	}
	public int SITE(){return site;}
}
