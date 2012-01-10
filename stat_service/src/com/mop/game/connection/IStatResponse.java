package com.mop.game.connection;

public class IStatResponse
{
	/** 持有访问Session对象*/
	private IStatSession session;
	
	public IStatSession getSession() {
		return session;
	}

	public void setSession(IStatSession session) {
		this.session = session;
	}

	public IStatNetContext getContext() {
		return context;
	}

	public void setContext(IStatNetContext context) {
		this.context = context;
	}

	private IStatNetContext context;

	public IStatResponse(IStatSession session, IStatNetContext context) {
		this.session = session;
		this.context = context;
	}
}
