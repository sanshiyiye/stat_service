package com.mop.game.service.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import com.mop.game.service.IStatBean;


public class ChargeBean implements IStatBean
{

	/** 账号ID*/
	private long userId;
	/** 账号*/
	private String user;
	/** 游戏名称*/
	private String game;
	/** 游戏版本号*/
	private String subGame;
	/** 渠道ID*/
	private String channelId;
	/** 角色ID*/
	private long roleId;
	/** 货币*/
	private int gold;
	/** 货币种类*/
	private byte goldType;

	public ChargeBean(long userId, String user, String game, String subGame, String channelId, long roleId,
			int gold, byte goldType)
	{
		super();
		this.userId = userId;
		this.user = user;
		this.game = game;
		this.subGame = subGame;
		this.channelId = channelId;
		this.roleId = roleId;
		this.gold = gold;
		this.goldType = goldType;
	}
	
	
	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}

	public String getGame()
	{
		return game;
	}

	public void setGame(String game)
	{
		this.game = game;
	}

	public String getSubGame()
	{
		return subGame;
	}

	public void setSubGame(String subGame)
	{
		this.subGame = subGame;
	}

	public long getRoleId()
	{
		return roleId;
	}

	public void setRoleId(long roleId)
	{
		this.roleId = roleId;
	}

	public int getGold()
	{
		return gold;
	}

	public void setGold(int gold)
	{
		this.gold = gold;
	}

	public byte getGoldType()
	{
		return goldType;
	}

	public void setGoldType(byte goldType)
	{
		this.goldType = goldType;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430651909322506321L;

	@Override
	public void serialize(byte[] data) throws IOException
	{
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(data));
		this.userId = dataStream.readLong();
		this.user = dataStream.readUTF();
		this.game = dataStream.readUTF();
		this.subGame = dataStream.readUTF();
		this.channelId = dataStream.readUTF();
		this.roleId = dataStream.readLong();
		this.gold = dataStream.readInt();
		this.goldType = dataStream.readByte();
		dataStream.close();
	}

	@Override
	public void readExternal(ObjectInput dataStream) throws IOException
	{
		this.userId = dataStream.readLong();
		this.user = dataStream.readUTF();
		this.game = dataStream.readUTF();
		this.subGame = dataStream.readUTF();
		this.channelId = dataStream.readUTF();
		this.roleId = dataStream.readLong();
		this.gold = dataStream.readInt();
		this.goldType = dataStream.readByte();
	}
	@Override
	public void writeExternal(ObjectOutput dos) throws IOException
	{
		dos.writeLong(this.userId);
		dos.writeUTF(this.user);
		dos.writeUTF(this.game);
		dos.writeUTF(this.subGame);
		dos.writeUTF(this.channelId);
		dos.writeLong(this.roleId);
		dos.writeInt(this.gold);
		dos.writeByte(this.goldType);
	}
	
	@Override
	public byte[] deserialize() throws IOException
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream temp = new DataOutputStream(out);
		temp.writeLong(this.userId);
		temp.writeUTF(this.user);
		temp.writeUTF(this.game);
		temp.writeUTF(this.subGame);
		temp.writeUTF(this.channelId);
		temp.writeLong(this.roleId);
		temp.writeInt(this.gold);
		temp.writeByte(this.goldType);
		byte[] data = out.toByteArray();
		temp.close();
		out.close();
		return data;
	}
	/**
	 * TODO 可以考虑使用json序列化
	 */
	@Override
	public String toString()
	{
		StringBuilder temp = new StringBuilder();
		temp.append(this.userId).append("|")
				.append(this.user).append("|")
				.append(this.game).append("|")
				.append(this.subGame).append("|")
				.append(this.channelId).append("|")
				.append(this.roleId).append("|")
				.append(this.gold).append("|")
				.append(this.goldType).append("|");
		 return temp.toString();
	}
	
}
