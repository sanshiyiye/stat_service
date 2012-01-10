package com.mop.game.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsynPool
{
	/** �̳߳�ʵ��*/
	private Executor executor = null;
	
	/** 定时执行器*/
	private Executor tasks = null;
	
	private int _exe_process_num = (int)(Runtime.getRuntime().availableProcessors() *1.5);
	
	private static AsynPool _instance;
	/**
	 * ��ȡ�̳߳ص���
	 * @return
	 */
	public  static AsynPool getInstance()
	{
		if(_instance == null)
		{
			_instance = new AsynPool();
			_instance.init();
		}
		return _instance;
	}
	
	/**
	 * �첽�̳߳ع��߳�ʼ��
	 */
	public void init()
	{
			executor = Executors.newFixedThreadPool(_exe_process_num);
			tasks = Executors.newScheduledThreadPool(_exe_process_num);
	}
	/**
	 * �̳߳عر�
	 */
	public synchronized void shutdown()
	{
		((ExecutorService)executor).shutdown();
		((ScheduledExecutorService)tasks).shutdown();
	}
	/**
	 * ���߳��첽ִ��
	 * @param task
	 */
	public void  executeAsyn(Runnable task)
	{
		if(!((ExecutorService)executor).isShutdown())
			executor.execute(task);
	}
	/**
	 * ���߳�ͬ��ִ��
	 * @param <T>
	 * @param task
	 * @return 
	 */
	public <T extends Object> Future<T> executeSyn(Callable<T> task)
	{
		return ((ExecutorService)executor).submit(task);
	}
	
	/**�������� �̳߳�����*/
	public synchronized void resetProcessNum(int process_num)
	{
		_exe_process_num = process_num;
	}
	
	public synchronized Executor getExecutor()
	{
		return executor;
	}
	
	public synchronized void setExecutor(Executor executor)
	{
		this.executor = executor;
	}

	/**定时任务*/
	public void addTimerTask(Runnable task,long delay, long interval) {
		((ScheduledExecutorService)tasks).scheduleAtFixedRate(task, delay,interval, TimeUnit.SECONDS);
		
		
	}
	
}
