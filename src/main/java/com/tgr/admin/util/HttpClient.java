package com.tgr.admin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.youpu.vcom.VcomMethod;

@SuppressWarnings("all")
public class HttpClient {
	private static Log log = LogFactory.getLog(HttpClient.class);
	private static final String GET_USER_INFO_URL_QQ = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&access_token=%s&openid=%s&format=json";
	private static final String GET_USER_INFO_URL_WEIBO = "https://api.weibo.com/2/users/show.json?source=%s&access_token=%s&uid=%s";
	private static final String GET_USER_INFO_URL_WEIXIN = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
	private static final String GET_USER_INFO_URL_SAFETREE = "http://www.safetree.com.cn/userhandler/LoginHandler.ashx?username=%s&password=%s&loginType=1&type=1";
	/**
	 * get请求
	 * @param url
	 */
	public static String doGet(final String url) {
		try {
			URL getUrl = new URL(url);
			log.info("connect:"+url);
			// 根据拼凑的URL，打开连接，URL.openConnection()函数会根据
			// URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			// 建立与服务器的连接，并未发送数据
			connection.connect();
			// 发送数据到服务器并使用Reader读取返回的数据
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
			StringBuffer result = new StringBuffer();
			String lines;
			while ((lines = reader.readLine()) != null) {
				result.append(lines);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
			return result.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static JSONObject getQQUserInfo(String oauthConsumerKey,String openId,String access_token){
		String doGet = doGet(String.format(GET_USER_INFO_URL_QQ, oauthConsumerKey, access_token, openId));
		if(doGet!=null){
			JSONObject object = JSONObject.fromObject(doGet);
			return object;
		}else{
			return new JSONObject();
		}
	}
	
	public static JSONObject getVkmUserInfo(String oauthConsumerKey,String openId,String access_token){
		String doGet = doGet(String.format(GET_USER_INFO_URL_QQ, oauthConsumerKey, access_token, openId));
		if(doGet!=null){
			JSONObject object = JSONObject.fromObject(doGet);
			return object;
		}else{
			return new JSONObject();
		}
	}
	public static void main(String[] args) {
		/*String openId = "BF75A3A80DDB2A29681D7B65301B3500";
		String access_token = "05BCA7BDE484825EAB72124C4F6C3183";
		JSONObject info = getQQUserInfo(SysUtil.getValue("OAUTH_CONSUMER_KEY"),openId, access_token);
		System.out.println(info);
		String ret = info.getString("ret");
		System.out.println(ret);*/
		String username = "370285200411041726";
		String password = "041726";
		JSONObject info = getSafetreeUserInfo(username, password);
		System.out.println(info);
		String ret = info.getString("ret");
		System.out.println(ret);
		info = info.getJSONObject("data");
		System.out.println(info.getString("UserId"));
		
	}
	public static JSONObject getWeiboUserInfo(String oauthConsumerKey,String openId,String access_token) {
		String doGet = doGet(String.format(GET_USER_INFO_URL_WEIBO, oauthConsumerKey, access_token, openId));
		if(doGet!=null){
			JSONObject object = JSONObject.fromObject(doGet);
			return object;
		}else{
			return new JSONObject();
		}
	}
	
	public static JSONObject getWeixinUserInfo(String openId,String access_token) {
		String doGet = doGet(String.format(GET_USER_INFO_URL_WEIXIN,access_token, openId));
		if(doGet!=null){
			JSONObject object = JSONObject.fromObject(doGet);
			return object;
		}else{
			return new JSONObject();
		}
	}

	public static JSONObject getSafetreeUserInfo(String username,String password) {
		String doGet = doGet(String.format(GET_USER_INFO_URL_SAFETREE, username, password));
		if(doGet!=null){
			JSONObject object = JSONObject.fromObject(doGet);
			return object;
		}else{
			return new JSONObject();
		}
	}
	public static JSONArray getUjByPhoneUserInfo(String username,long timestamp,String ssourl) {
		String userZh="http://"+ssourl+"/sso//interface/queryLoginUser.jsp?userName=%s&limit=5&timestamp=%s";
		String doGet = null;//VcomMethod.doGet(String.format(userZh,username,timestamp));
		if(doGet!=null){
			JSONArray object = JSONArray.fromObject(doGet);
			return object;
		}else{
			return new JSONArray();
		}
	}
	//http://IP/sso/verifyAuthInfo?inputname=15637191587&username=66467ea6c6d6522ad050365f76423bbb&pwd=e10adc3949ba59abbe56e057f20f883e&schoolId=&appFlg=webmail&isPortal=1
	public static JSONObject getUserCenter(String inputname,String username,String passWord,String ssourl) {
		String userCenter="http://"+ssourl+"/sso/verifyAuthInfo?inputname=%s&username=%s&pwd=%s&appFlg=youpuPhone&isPortal=1&encodeU=0";
		String doGet = null;//VcomMethod.doGet(String.format(userCenter,inputname,username,passWord));
		if(doGet!=null){
			JSONObject object = JSONObject.fromObject(doGet);
			return object;
		}else{
			return new JSONObject();
		}
	}
	public static JSONObject getUserLogin(String username,String passWord,String ssourl) {
		String userCenter="http://"+ssourl+"//sso/verifyAuthInfo?username=%s&pwd=%s&appFlg=youpuPhone&isPortal=1&encodeU=0";
		String doGet = null;//VcomMethod.doGet(String.format(userCenter,username,passWord));
		if(doGet!=null){
			JSONObject object = JSONObject.fromObject(doGet);
			return object;
		}else{
			return new JSONObject();
		}
	}
}
