package com.tgr.admin.util;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
  
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

@SuppressWarnings("all")
public class DES {
		public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";
	
		/**
	 	* DES算法，加密
	 	*
	 	* @param data 待加密字符串
	 	* @param key 加密私钥，长度不能够小于8位
	 	* @return 加密后的字节数组，一般结合Base64编码使用
	 	* @throws CryptException 异常
	 	*/
	  public static byte[] encode(String key,String data) throws Exception{
	    return encode(key, data.getBytes());
	  }
	  /**
	   * DES算法，加密
	   *
	   * @param data 待加密字符串
	   * @param key 加密私钥，长度不能够小于8位
	   * @return 加密后的字节数组，一般结合Base64编码使用
	   * @throws CryptException 异常
	   */
	  public static byte[] encode(String key,byte[] data) throws Exception{
	    try{
	        DESKeySpec dks = new DESKeySpec(key.getBytes());
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        //key的长度不能够小于8位字节
	        Key secretKey = keyFactory.generateSecret(dks);
	        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
	        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
	        AlgorithmParameterSpec paramSpec = iv;
	        cipher.init(Cipher.ENCRYPT_MODE, secretKey,paramSpec);
	        byte[] bytes = cipher.doFinal(data);
	        //return byte2hex(bytes);
	        return bytes;
	    }catch (Exception e) {
	      throw new Exception(e);
	    }
	  }
	  
	  /**
	   * DES算法，解密
	   *
	   * @param data 待解密字符串
	   * @param key 解密私钥，长度不能够小于8位
	   * @return 解密后的字节数组
	   * @throws Exception 异常
	   */
	  public static byte[] decode(String key,byte[] data) throws Exception{
	    try{
	        SecureRandom sr = new SecureRandom();
	        DESKeySpec dks = new DESKeySpec(key.getBytes());
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        //key的长度不能够小于8位字节
	        Key secretKey = keyFactory.generateSecret(dks);
	        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
	        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
	        AlgorithmParameterSpec paramSpec = iv;
	        cipher.init(Cipher.DECRYPT_MODE, secretKey,paramSpec);
	        return cipher.doFinal(data);
	    }catch (Exception e){
	      throw new Exception(e);
	    }
	  }
	  
	  //mrVCOM01
	  
	  /**
	   * 测试而已
	   * @param args
	   */
	  public static void main(String[] args) {
//		String a = "uid=123435&truename=张三&gender=0&newprofessionaltype=初中物理&workplace=人大附中&mobile=13011234321&province=北京&city=北京&county=海淀区&time=%s";
//		try {
//			byte[] encode = encode("mingren1", (String.format(a,System.currentTimeMillis())).getBytes("utf-8"));
//			String str = new String(encode,"ISO-8859-1");
//			System.out.println(str);
//			String encode2 = Urlcoder.encode(str);
//			System.out.println(encode2);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
		String a = "pointid=2:3:4&time=%s";
		String key = "mrVCOM01";
		try {
			
			//DEC 加密
			byte[] encode = encode("mrVCOM01", (String.format(a,System.currentTimeMillis())).getBytes("utf-8"));
			//base 64加密
			String base64encode = Base64.encode(encode);
			System.out.println("加密后的数据"+base64encode);
			//qGPQWM06jw5ho+QqKztPz9f0Kj9vHGD5sPSj435jU0OyFn4izxv2aw==
			
			//base 64解密
			//<br> 接收参数处理 ，base64参数的中+,会自动转化为空格,第一步还原参数
			char[] t = new char[base64encode.length()];
			for (int i = 0; i < base64encode.length(); i++) {
				t[i] = base64encode.charAt(i);
				if ((t[i] + "").equals(" ")) {
					t[i] = '+';
				}
			}
			
			//base64解码
			byte[] bb = Base64.decode(t);
	
			//DEC 解密
			byte[] decode = decode(key, bb);
			base64encode = new String(decode,"utf-8");
			System.out.println("解密后数据"+base64encode);
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
}
