package com.mop.game.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

public class HttpUtil {
	static final int TIMEOUT = 10000;

	static final String CHARSET = "utf-8";

	/**
	 * POST方法
	 * 
	 * @param url
	 * @param timeout
	 * @param params
	 * @param values
	 * @return
	 */
	public static byte[] postData(String url, byte[] postData) {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		client.getParams().setContentCharset(CHARSET);
		PostMethod method = new PostMethod();
		try {
			method.setURI(new URI(url, true, CHARSET));
		} catch (URIException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		method.setRequestEntity(new ByteArrayRequestEntity(postData));
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			} else {
				byte[] responseBody = null;
				Header contentEncodingHeader = method
						.getResponseHeader("Content-Encoding");
				if (contentEncodingHeader != null
						&& contentEncodingHeader.getValue().equalsIgnoreCase(
								"gzip")) {
					GZIPInputStream is = new GZIPInputStream(method
							.getResponseBodyAsStream());
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					IOUtils.copy(is, os);
					responseBody = os.toByteArray();
				} else {
					responseBody = method.getResponseBody();
				}

				byte[] data = formatData(responseBody);
				return data;
			}
		} catch (HttpException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * POST�������
	 * 
	 * @param url
	 * @param timeout
	 * @param params
	 * @param values
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String postData(String url, String postData) {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		client.getParams().setContentCharset(CHARSET);
		PostMethod method = new PostMethod();
		try {
			method.setURI(new URI(url, true, CHARSET));
		} catch (URIException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		method.setRequestEntity(new StringRequestEntity(postData));
		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			} else {
				byte[] responseBody = null;
				Header contentEncodingHeader = method
						.getResponseHeader("Content-Encoding");
				if (contentEncodingHeader != null
						&& contentEncodingHeader.getValue().equalsIgnoreCase(
								"gzip")) {
					GZIPInputStream is = new GZIPInputStream(method
							.getResponseBodyAsStream());
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					IOUtils.copy(is, os);
					responseBody = os.toByteArray();
				} else {
					responseBody = method.getResponseBody();
				}

				byte[] data = formatData(responseBody);
				String encoding = CHARSET;
				Header contentTypeHeader = method
						.getResponseHeader("Content-Type");
				if (contentTypeHeader != null) {
					String contentType = contentTypeHeader.getValue();
					// System.out.println("content-type:" + contentType);
					int offset = contentType.indexOf("=");
					if (offset != -1)
						encoding = contentType.substring(offset + 1);
					else {
						String body = new String(data, encoding);
						offset = body.indexOf("encoding");
						if (offset != -1) {
							int begin = body.indexOf("\"", offset);
							int end = body.indexOf("\"", begin + 1);
							encoding = body.substring(begin + 1, end);
						}
					}
				}
				return new String(data, encoding);
			}
		} catch (HttpException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static byte[] requestHttpContentAsStream(String url)
			throws IOException {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		GetMethod method = new GetMethod();
		try {
			method.setURI(new URI(url, false, "utf-8"));

		} catch (URIException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(8, false));
		try {

			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] responseBody = null;
			Header contentEncodingHeader = method
					.getResponseHeader("Content-Encoding");
			if (contentEncodingHeader != null
					&& contentEncodingHeader.getValue()
							.equalsIgnoreCase("gzip")) {
				GZIPInputStream is = new GZIPInputStream(method
						.getResponseBodyAsStream());
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				IOUtils.copy(is, os);
				responseBody = os.toByteArray();
				is.close();
				os.close();
			} else {
				responseBody = method.getResponseBody();
			}

			return responseBody;

		} catch (HttpException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * Get http content from url
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String requestHttpContent(String url) throws IOException {
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(
				TIMEOUT);
		client.getParams().setParameter("http.socket.timeout", TIMEOUT);
		GetMethod method = new GetMethod();
		try {
			method.setURI(new URI(url, false, "utf-8"));

		} catch (URIException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(8, false));
		try {

			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			byte[] responseBody = null;
			Header contentEncodingHeader = method
					.getResponseHeader("Content-Encoding");
			if (contentEncodingHeader != null
					&& contentEncodingHeader.getValue()
							.equalsIgnoreCase("gzip")) {
				GZIPInputStream is = new GZIPInputStream(method
						.getResponseBodyAsStream());
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				IOUtils.copy(is, os);
				responseBody = os.toByteArray();
				is.close();
				os.close();
			} else {
				responseBody = method.getResponseBody();
			}

			byte[] data = formatData(responseBody);
			String encoding = CHARSET;
			Header contentTypeHeader = method.getResponseHeader("Content-Type");
			if (contentTypeHeader != null) {
				String contentType = contentTypeHeader.getValue();
				// System.out.println("content-type:" + contentType);
				int offset = contentType.indexOf("=");
				if (offset != -1)
					encoding = contentType.substring(offset + 1);
				else {
					String body = new String(data, encoding);
					offset = body.indexOf("encoding");
					if (offset != -1) {
						int begin = body.indexOf("\"", offset);
						int end = body.indexOf("\"", begin + 1);
						encoding = body.substring(begin + 1, end);
					}
				}
			}
			return new String(data, encoding);

		} catch (HttpException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return null;
	}

	/**
	 * @param data
	 * @return
	 */
	private static byte[] formatData(byte[] data) {
		return data;
//		if (data == null) {
//			return null;
//		}
//		int k = 0;
//		for (; k < data.length && data[k] <= 32; k++) {
//			;
//		}
//
//		if (k == data.length) {
//			return null;
//		}
//		byte[] formatData = new byte[data.length - k];
//		java.lang.System.arraycopy(data, k, formatData, 0, formatData.length);
//
//		return formatData;
	}
	
	public static void main(String[] args){
		
		System.out.println(isHostIP("192.168.1.224"));
	}
	/**
	 * 验证本地IP
	 * @param ip
	 * @return
	 */
	public static boolean isHostIP(String ip){
		
		String [] ips = getHostIps();
		for(String eachIp : ips){
			
			if(eachIp.equals(ip))
				return true;
		}
		return false;
	}
	
	
	public static String[] getHostIps(){
		List<String> iplist = new ArrayList<String>();
		try {   
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();   
		    while (netInterfaces.hasMoreElements()) {   
		        NetworkInterface ni = netInterfaces.nextElement(); 
		        Enumeration<InetAddress> ips = ni.getInetAddresses(); 
		        while (ips.hasMoreElements()) {   

		        	iplist.add(ips.nextElement().getHostAddress());
		        }   
		    }   
		} catch (Exception e) {   
		    e.printStackTrace();   
		}  

		return iplist.toArray(new String[iplist.size()]);
	}
}
