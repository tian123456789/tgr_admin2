package com.tgr.admin.util;
import java.util.ResourceBundle;

public class SysUtil {
    public static ResourceBundle resources = ResourceBundle.getBundle("config");

    /**
     * 取得配置文件的值
     * 
     * @param key
     * @return
     */
    public static String getValue(String key) {
        String strValue = resources.getString(key).trim();
        return strValue;
    }
    
}
