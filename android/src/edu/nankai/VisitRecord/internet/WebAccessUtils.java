package edu.nankai.VisitRecord.internet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class WebAccessUtils {

	// 属性：自定义一个网络地址属性
	private static final String URI = "http://"+InternetConfig.IP+":"+InternetConfig.PORT+"/"+InternetConfig.PROJECT+"/";
	
	// 方法1：自定义一个不带参数的请求响应方法
	public static String httpRequest(final String webServiceName){
		String uri = URI + webServiceName;
		//System.out.println("URI:>" + uri);
		HttpPost httpPostRequest = new HttpPost(uri);
		try {
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpPostRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				String data = EntityUtils.toString(httpResponse.getEntity());
				return data;
			}else{
				return "101";
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "102";
	}
	
	// 方法2：自定义一个带参数的请求响应方法
	public static String httpRequest(final String webServiceName, final List<? extends NameValuePair> lstNameValuePairs){
		String uri = URI + webServiceName;
		System.out.println("URI:>" + uri);
		HttpPost httpPostRequest = new HttpPost(uri);
		try {
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(lstNameValuePairs, HTTP.UTF_8);
			httpPostRequest.setEntity(entity);
			
			HttpResponse httpResponse = new DefaultHttpClient().execute(httpPostRequest);
			if(httpResponse.getStatusLine().getStatusCode() == 200){
				String data = EntityUtils.toString(httpResponse.getEntity());
				return data;
			}else{
				return "101";
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "102";
	}
	
}
