package com.mop.game.service;

import com.mop.game.connection.IStatNetEvent;
import com.mop.game.connection.IStatRequest;
import com.mop.game.connection.IStatResponse;

public interface IStatProcessor
{
	/** 处理消息接口*/
	public void process(IStatRequest request, IStatResponse response);
	/** 处理网络事件*/
	public void handleNetEvent(IStatNetEvent event);
}
