package com.mop.game.db;

public interface IStatDbDao {

	/** save OBJ*/
	public void save(Object obj);
	/** 请求响应*/
	public void query(Object obj, IStatDbDaoHandler<?> handler);
	
}
