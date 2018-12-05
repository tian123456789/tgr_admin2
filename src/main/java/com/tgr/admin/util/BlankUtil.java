package com.tgr.admin.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author liuzeke
 * @date 2016-3-23
 * @description 空值判断
 */
@SuppressWarnings("all")
public final class BlankUtil {

	/**
	 * 防止通过反射实例化
	 */
	private BlankUtil() {
		throw new RuntimeException();
	}

	/**
	 * @author liuzeke
	 * @datetime 2016-3-23 上午10:03:40
	 * @description 对CharSequence、CharSequence Array、Collection、Map是否为空判断
	 * @param o
	 * @return
	 */
	public static boolean isBlank(Object o) {

		if (o == null)
			return true;

		if (o instanceof CharSequence)
			if (o == "" || "".equals(o))
				return true;

		if (o instanceof String)
			if (((String) o).trim().length() == 0)
				return true;

		if (o instanceof CharSequence[])
			if (((CharSequence[]) o).length == 0)
				return true;

		if (o instanceof Collection)
			if (((Collection) o).size() == 0)
				return true;

		if (o instanceof Map)
			if (((Map) o).isEmpty())
				return true;

		return false;
	}

	/**
	 * @author liuzeke
	 * @datetime 2016-3-23 上午10:05:16
	 * @description 对CharSequence、CharSequence Array、Collection、Map是否不为空判断
	 * @param o
	 * @return
	 */
	public static boolean isNotBlank(Object o) {
		return !isBlank(o);
	}

	/**
	 * @author liuzeke
	 * @datetime 2016-3-23 上午10:07:41
	 * @description 仅当所有参数都为空时返回true
	 * @param o
	 * @return
	 */
	public static boolean isBlankAll(Object... o) {

		if (o == null || o.length == 0)
			return true;

		for (Object obt : o)
			if (!isBlank(obt))
				return false;

		return true;
	}

	/**
	 * @author liuzeke
	 * @datetime 2016-3-23 上午10:18:05
	 * @description 仅当所有参数都不为空时返回true
	 * @param o
	 * @return
	 */
	public static boolean isNotBlankAll(Object... o) {

		if (o == null || o.length == 0)
			return false;

		for (Object obt : o)
			if (isBlank(obt))
				return false;

		return true;
	}
}
