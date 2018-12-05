package com.tgr.admin.util;

import java.io.IOException;

/*import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;*/
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

@SuppressWarnings("all")
public class ShotMessageUtil {
	/*private static Log log = LogFactory.getLog(ShotMessageUtil.class);
	private static String URL = SysUtil.getValue("send_message_url");
	private static String ACCOUNT = SysUtil.getValue("send_message_user");
	private static String PASSWORD = SysUtil.getValue("send_message_password");

	public static int sendMessage(String mobile,String content) {
		int stuts = 0;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(URL);

		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

		NameValuePair[] data = {
					new NameValuePair("account", ACCOUNT), 
					new NameValuePair("password", PASSWORD), 
					new NameValuePair("mobile", mobile), 
					new NameValuePair("content", content), 
				};
		method.setRequestBody(data);
		try {
			client.executeMethod(method);
			String SubmitResult = method.getResponseBodyAsString();
			Document doc = DocumentHelper.parseText(SubmitResult);
			Element root = doc.getRootElement();
			String code = root.elementText("code");
			String msg = root.elementText("msg");
			String smsid = root.elementText("smsid");
			log.info("发送短消息：结果：code" + code + ",msg:" + msg +",smsid:" + smsid);
			return Integer.parseInt(code);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return stuts;
	}
	public static void main(String[] args) {
		int stuts = sendMessage("15811484064", "您的验证码是：9527。请不要把验证码泄露给其他人。如非本人操作，可不用理会！");
		System.out.println(stuts);
	}*/
}
