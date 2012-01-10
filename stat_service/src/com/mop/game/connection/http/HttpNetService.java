/*********************************************************************************
 *	(C) Copyright 2011 - mailto:w.l.hikaru  <inuyashabuaa@126.com>	             *
 *********************************************************************************
 *********************************************************************************
 *																		                                                *
 *	 This program is free software; you can redistribute it and/or modify     *
 *	 it under the terms of the GNU General Public License as published by  *
 *	 the Free Software Foundation; either version 2 of the License, or	    *
 *	 (at your option) any later version.								                                *
 *																		                                                *
 ********************************************************************************/
package com.mop.game.connection.http;

import java.net.InetSocketAddress;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.ssl.SslFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import com.mop.game.connection.IStatConnectionProtocalSite;
import com.mop.game.connection.IStatNetService;
import com.mop.game.connection.http.codec.HttpRequestMessage;
import com.mop.game.connection.http.codec.HttpResponseMessage;
import com.mop.game.connection.http.codec.HttpServerProtocolCodecFactory;
import com.mop.game.service.IStatProcessor;
import com.mop.game.utils.ConnectionUtils;
import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;

public class HttpNetService extends IoHandlerAdapter implements IStatNetService{
	//端口制定为8080
	private  int _PORT = 8080;
	
	/**processor handler set*/
	Set<IStatProcessor> processors = new CopyOnWriteArraySet<IStatProcessor>();
	
	@Override
	public void init(String... args) {
		
		_PORT = Integer.parseInt(ConnectionUtils.getParam(args, IStatConnectionProtocalSite.SITE_HTTP_PORT));
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() {
		try 
		{
			SocketAcceptor acceptor = new NioSocketAcceptor();
			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
			acceptor.setReuseAddress(true);
			chain.addLast(
	                    "protocolFilter",
	                    new ProtocolCodecFilter(new HttpServerProtocolCodecFactory(true)));
			acceptor.setHandler(this);
			acceptor.bind(new InetSocketAddress(_PORT));
	        
		} 
		catch (Exception e) {
			LogUtils.error(LOGTYPE.STAT_NET, HttpNetService.class, "start error",e);
//			StatErrorUtils.common("httpNetService start error:",e);
		}
		
		
	}

	@Override
	public void addProcessorListener(IStatProcessor processor) {
		
		processors.add(processor);
	}

	@Override
    public void sessionCreated(IoSession session) {
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        //TODO use ssl or not
        // We're going to use SSL negotiation notification.
        session.setAttribute(SslFilter.USE_NOTIFICATION);
    }
	
	 @Override
	public void sessionIdle(IoSession session, IdleStatus status) {
	        session.close(true);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
	        session.close(true);
	}
	@Override
	public void messageReceived(IoSession session, Object message) {
			
			 if(!(message instanceof HttpRequestMessage))
				 return;
			 //产生一个httpResponse 并注册到连接中,若运行超时则直接返回请求
			 //避免http client段请求阻塞
			HttpRequestMessage httpMsg = (HttpRequestMessage)message;
	        // Check that we can service the request context
	        HttpResponseMessage response = new HttpResponseMessage();
	        response.setContentType("text/plain");
	        response.setResponseCode(HttpResponseMessage.HTTP_STATUS_SUCCESS);
	        response.appendBody("CONNECTED");
	        for (IStatProcessor eachProcessor : processors)
	        {
//	        	eachProcessor.process(request, response);
			}
	        if(response != null)
	        	 session.write(response).awaitUninterruptibly(10,TimeUnit.SECONDS);
	}
	
	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}
}
