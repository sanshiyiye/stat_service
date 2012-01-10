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

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

import com.mop.game.utils.LogUtils;
import com.mop.game.utils.LogUtils.LOGTYPE;

/**
 * A {@link MessageEncoder} that encodes {@link HttpResponseMessage}.
 *
 * @author The Apache Directory Project (mina-dev@directory.apache.org)
 * @version $Rev: 555855 $, $Date: 2007-07-13 12:19:00 +0900 (금, 13  7월 2007) $
 */
public class HttpResponseEncoder implements MessageEncoder<Object> {
    private static final Set<Class<?>> TYPES;

    static {
        Set<Class<?>> types = new HashSet<Class<?>>();
        types.add(HttpResponseMessage.class);
        TYPES = Collections.unmodifiableSet(types);
    }

    private static final byte[] CRLF = new byte[] { 0x0D, 0x0A };

    public HttpResponseEncoder() {
    }

    public void encode(IoSession session, Object message,
            ProtocolEncoderOutput out) throws Exception {
        HttpResponseMessage msg = (HttpResponseMessage) message;
        IoBuffer buf = IoBuffer.allocate(256);
        // Enable auto-expand for easier encoding
        buf.setAutoExpand(true);

        try {
            // output all headers except the content length
            CharsetEncoder encoder = Charset.defaultCharset().newEncoder();
            buf.putString("HTTP/1.1 ", encoder);
            buf.putString(String.valueOf(msg.getResponseCode()), encoder);
            switch (msg.getResponseCode()) {
            case HttpResponseMessage.HTTP_STATUS_SUCCESS:
                buf.putString(" OK", encoder);
                break;
            case HttpResponseMessage.HTTP_STATUS_NOT_FOUND:
                buf.putString(" Not Found", encoder);
                break;
            }
            buf.put(CRLF);
            for (Iterator it = msg.getHeaders().entrySet().iterator(); it
                    .hasNext();) {
                Entry entry = (Entry) it.next();
                buf.putString((String) entry.getKey(), encoder);
                buf.putString(": ", encoder);
                buf.putString((String) entry.getValue(), encoder);
                buf.put(CRLF);
            }
            // now the content length is the body length
            buf.putString("Content-Length: ", encoder);
            buf.putString(String.valueOf(msg.getBodyLength()), encoder);
            buf.put(CRLF);
            buf.put(CRLF);
            // add body
            buf.put(msg.getBody());
            //System.out.println("\n+++++++");
            //for (int i=0; i<buf.position();i++)System.out.print(new String(new byte[]{buf.get(i)}));
            //System.out.println("\n+++++++");
        } catch (CharacterCodingException ex) {
        	LogUtils.error(LOGTYPE.STAT_NET, HttpResponseEncoder.class, "HttpResponseEncoder encode error",ex);
//        	StatErrorUtils.common("HttpResponseEncoder encode error",ex);
        }

        buf.flip();
        out.write(buf);
    }

    public Set<Class<?>> getMessageTypes() {
        return TYPES;
    }
}
