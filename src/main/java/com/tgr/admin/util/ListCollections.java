package com.tgr.admin.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class ListCollections {

	/**
	 * 降序 pram 排序参数
	 * 
	 * @return
	 */

	public static void desc(List list, final String pram) {
		Collections.sort(list, new Comparator<Map<Object, Object>>() {
			public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {

				int map1value = (int) (Double.parseDouble(o1.get(pram).toString()) * 10000);
				int map2value = (int) (Double.parseDouble(o2.get(pram).toString()) * 10000);

				return map2value - map1value;
			}
		});
	}

	/**
	 * 升序 pram 排序参数
	 * 
	 * @return
	 */
	public static void asc(List list, final String pram) {
		Collections.sort(list, new Comparator<Map<Object, Object>>() {
			public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {

				int map1value = (int) (Double.parseDouble(o1.get(pram).toString()) * 10000);
				int map2value = (int) (Double.parseDouble(o2.get(pram).toString()) * 10000);

				return map1value - map2value;
			}
		});
	}

	/**
	 * @author liuzeke
	 * @dateTime 2016-5-27 上午11:40:04
	 * @description 去重
	 * @param list
	 * @return
	 */
	public static <T> List<T> distinct(List<T> list) {
		if (list == null || list.size() == 0)
			return list;
		List<T> retList = new ArrayList<T>();
		for (T t : list) {
			if (retList.contains(t))
				continue;
			retList.add(t);
		}
		return retList;
	}

	/**
	 * 判断集合是否不为null，有元素
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isNotNullAndEmpty(Collection c) {
		if (c != null && !c.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		List<Long> list = new ArrayList<Long>();
		list.add(new Long(1));
		list.add(new Long(1));
		list.add(new Long(2));
		list.add(new Long(3));
		list.add(new Long(2));
		list.add(new Long(4));
		List<Long> tt = distinct(list);
	}
}
