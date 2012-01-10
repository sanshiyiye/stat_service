/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License. 
 *  
 */
package com.mop.game.connection.http.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;

/**
 * Provides a protocol codec for HTTP server.
 * 
 * @author The Apache Directory Project (mina-dev@directory.apache.org)
 * @version $Rev: 555855 $, $Date: 2007-07-13 12:19:00 +0900 (금, 13  7월 2007) $
 */
public class HttpServerProtocolCodecFactory extends DemuxingProtocolCodecFactory {
    public HttpServerProtocolCodecFactory(boolean server) {
    	if(server )
    	{
    		 super.addMessageDecoder(HttpRequestDecoder.class);
             super.addMessageEncoder(HttpRequestMessage.class, HttpResponseEncoder.class);
    	}
    	else//client
    	{
    		super.addMessageEncoder(HttpResponseMessage.class, HttpResponseEncoder.class);
            super.addMessageDecoder(HttpRequestDecoder.class);
    	}
        
    }
}
