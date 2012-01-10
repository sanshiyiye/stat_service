package com.mop.game.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.mop.game.connection.IStatRequest;
import com.mop.game.connection.IStatResponse;
import com.mop.game.utils.AsynPool;

public abstract class AbstractStatSynProcessor extends IStatProcessorService implements IStatAsynProcessor 
{
	/** ҵ����ӿ�*/
	public abstract void query(IStatRequest request);
	
	@Override
	public void process(IStatRequest request, IStatResponse response)
	{
		final IStatRequest f_request = request;
		Future<Object> future = AsynPool.getInstance().executeSyn(new Callable<Object>()
		{

			@Override
			public Object call() throws Exception
			{
				query(f_request);
				return null;
			}
			
		});
		try {
			future.get();
			handle(response);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
