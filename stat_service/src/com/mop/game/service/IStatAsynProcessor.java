package com.mop.game.service;

import com.mop.game.connection.IStatResponse;

public interface IStatAsynProcessor extends IStatProcessor
{
	public void handle(IStatResponse response);
}
