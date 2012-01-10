package com.mop.game.service;

import com.mop.game.connection.IStatRequest;
import com.mop.game.connection.IStatResponse;
import com.mop.game.utils.AsynPool;

public abstract class AbstractStatAsynProcessor extends IStatProcessorService
{
	/** ҵ����ӿ�*/
	public abstract void query(IStatRequest request);
	
	@Override
	public void process(IStatRequest request, IStatResponse response)
	{
		final IStatRequest f_request = request;
		AsynPool.getInstance().executeAsyn(new Runnable() {
			
			@Override
			public void run() {
				query(f_request);
			}
		});
		
	}
	
}
