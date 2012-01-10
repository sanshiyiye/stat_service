package com.mop.game.db;

import java.util.List;

public interface IStatDbDaoHandler<T> {

	public void handle(List<T> result);
}
