package com.tgr.admin.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

//import cn.emay.sdk.client.api.Client;

@SuppressWarnings("all")
public class SingletonClient {
	/*private static Client client=null;
	private SingletonClient(){
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public synchronized static Client getClient(){
		ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
		if(client==null){
			try {
				client=new Client(bundle.getString("softwareSerialNo"),bundle.getString("key"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public static void main(String str[]){
		Client client2 = SingletonClient.getClient();
		int sendSMS = client2.sendSMS(new String[]{"15811484064"}, "9527", 3);
		System.out.println(sendSMS);
	}*/
}
