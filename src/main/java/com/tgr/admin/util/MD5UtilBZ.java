package com.tgr.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author 标准MD5加密
 * <br> 用于一般意义的开发，区别于本地分库分表
 * <br> 一般的开发用这个加密方式
 */
public class MD5UtilBZ {
	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	/**
	 * 得到参数加密后的MD5值
	 * @param inStr
	 * @return 32byte MD5 Value
	 */
	public static String getMD5(String inStr){
		byte[] inStrBytes = inStr.getBytes();
		try {
			MessageDigest MD = MessageDigest.getInstance("MD5");
			MD.update(inStrBytes);
			byte[] mdByte = MD.digest();
			char[] str = new char[mdByte.length * 2];
			int k = 0;
			for(int i=0;i<mdByte.length;i++) {
				byte temp = mdByte[i];
				str[k++] = hexDigits[temp >>> 4 & 0xf];
				str[k++] = hexDigits[temp & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**  
     * 生成文件的md5校验值  
     *   
     * @param file  
     * @return  
     * @throws IOException  
     */  
    public static String getFileMD5String(File file) throws Exception {     
    	MessageDigest messagedigest=MessageDigest.getInstance("MD5");  
        InputStream fis;   
        fis = new FileInputStream(file);   
        byte[] buffer = new byte[1024];   
        int numRead = 0;   
        while ((numRead = fis.read(buffer)) > 0) {   
            messagedigest.update(buffer, 0, numRead);   
        }   
        fis.close();   
        return bufferToHex(messagedigest.digest());   
    }   
    private static String bufferToHex(byte bytes[]) {   
        return bufferToHex(bytes, 0, bytes.length);   
    }   
    private static String bufferToHex(byte bytes[], int m, int n) {   
        StringBuffer stringbuffer = new StringBuffer(2 * n);   
        int k = m + n;   
        for (int l = m; l < k; l++) {   
            appendHexPair(bytes[l], stringbuffer);   
        }   
        return stringbuffer.toString();   
    }   
    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {   
        char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同    
        char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换    
        stringbuffer.append(c0);   
        stringbuffer.append(c1);   
    }   
    
    public static void main(String[] args) {
    	String md5 = getMD5("测试");
    	System.out.println(md5);
	}
}
