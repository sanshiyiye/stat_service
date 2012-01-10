package com.mop.game.service;

import java.io.IOException;

import com.mop.game.core.IStatSerializable;

public interface IStatBean extends IStatSerializable
{
	/** ����bean���л�*/
	public void serialize(byte[] data) throws IOException;
	/** �����л�*/
	public byte[] deserialize() throws IOException;
}
