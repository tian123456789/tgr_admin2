package com.tgr.admin.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzeke
 * @date 2016-5-19
 */
@SuppressWarnings("all")
public class BeanUtil {

	private final static Map<String, CachedMethods> METHODS = new HashMap<String, CachedMethods>();

	private BeanUtil() {
	}

	/**
	 * @author liuzeke
	 * @description 赋值Object到T Object中的null值不copy
	 * @param src
	 * @param dest
	 * @param attrs
	 * @return
	 */
	public static <T> T CopyBeanToBean(Object src, T dest, String... attrs) {

		if (src == null)
			return null;

		if (dest == null) {
			try {
				T newInstance = (T) src.getClass().newInstance();
				dest = newInstance;
			} catch (Exception e) {
				return null;
			}
		}

		final Map<String, Method> gets = cacheClass(src.getClass()).getter;
		final Map<String, Method> sets = cacheClass(dest.getClass()).setter;

		Object[] getPara = new Object[0];
		Object[] setPara = new Object[1];

		for (Map.Entry<String, Method> e : sets.entrySet()) {
			Method get = gets.get(e.getKey());
			if (get == null || indexAt(e.getKey(), attrs) >= 0)
				continue;
			try {
				setPara[0] = get.invoke(src, getPara);
				Method mSet = e.getValue();
				if (setPara[0] != null)
					mSet.invoke(dest, setPara);
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
		return dest;
	}

	/**
	 * @author liuzeke
	 * @description 复制Object到T Object中的null值不copy
	 * @param src
	 * @param clazz
	 * @param attrs
	 * @return
	 */
	public static <T> T CopyBeanToBean(Object src, Class<T> clazz, String... attrs) {

		if (src == null)
			return null;

		final Map<String, Method> gets = cacheClass(src.getClass()).getter;
		final Map<String, Method> sets = cacheClass(clazz).setter;
		T dest = null;

		try {
			dest = clazz.newInstance();
		} catch (Exception e) {
			return null;
		}

		Object[] getPara = new Object[0];
		Object[] setPara = new Object[1];

		for (Map.Entry<String, Method> e : sets.entrySet()) {
			Method get = gets.get(e.getKey());
			if (get == null || indexAt(e.getKey(), attrs) >= 0)
				continue;
			try {
				setPara[0] = get.invoke(src, getPara);
				Method mSet = e.getValue();
				if (setPara[0] != null)
					mSet.invoke(dest, setPara);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return dest;
	}

	public static Map<String, Method> getGetters(Object o) {
		return cacheClass(o.getClass()).getGetter();
	}

	public static Map<String, Method> getSetters(Object o) {
		return cacheClass(o.getClass()).getSetter();
	}

	private static CachedMethods cacheClass(Class<?> c) {
		final String cName = c.getName();
		CachedMethods mi = METHODS.get(cName);
		if (mi != null)
			return mi;
		Method mm[] = c.getMethods();
		mi = new CachedMethods(cName);
		METHODS.put(cName, mi);

		for (Method m : mm) {
			String method = m.getName();
			if (method.startsWith("get") || method.startsWith("is")) {
				if (m.getParameterTypes().length == 0)
					mi.getter.put(getAttr(method), m);
			} else if (method.startsWith("set"))
				if (m.getParameterTypes().length == 1)
					mi.setter.put(getAttr(method), m);

		}
		mi.getter.remove("class");
		return mi;
	}

	private static String getAttr(String method) {
		StringBuilder bb = new StringBuilder();
		if (method.startsWith("is")) {
			bb.append(Character.toLowerCase(method.charAt(2)));
			bb.append(method.substring(3));
		} else {
			bb.append(Character.toLowerCase(method.charAt(3)));
			bb.append(method.substring(4));
		}
		return bb.toString();
	}

	private static class CachedMethods {

		final String className;
		final Map<String, Method> getter = new HashMap<String, Method>();
		final Map<String, Method> setter = new HashMap<String, Method>();

		public CachedMethods(String className) {
			this.className = className;
		}

		Map<String, Method> getSetter() {
			return new HashMap<String, Method>(setter);
		}

		Map<String, Method> getGetter() {
			return new HashMap<String, Method>(getter);
		}
	}

	private static int indexAt(String src, String[] dest) {
		if (dest == null || dest.length == 0)
			return -1;
		for (int i = 0; i < dest.length; i++)
			if (src.equals(dest[i]))
				return i;
		return -1;
	}
}
