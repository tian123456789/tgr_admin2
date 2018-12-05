package com.tgr.admin.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuzeke
 * @date 2016-4-20
 */
public class Ability {

	/**
	 * 开始列，结束列， 已初始化学科能力
	 */
	private static int begin_col;
	private static int end_col;
	private static Map<String, String> CACHE;
	private static String querySql;

	/**
	 * 小学数学学科能力
	 */
	private static final int BEGIN_COL_MATH_0 = 25;
	private static final int END_COL_MATH_0 = 34;
	private static final Map<String, String> CACHE_MATH_0 = new HashMap<String, String>();
	private static final String[] ABI_MATH_0 = { "数感", "符号意识", "空间观念", "几何直观", "数据分析观念", "运算能力", "推理能力", "模型思想",
			"应用意识", "创新意识" };

	/**
	 * 初中数学学科能力
	 */
	private static final int BEGIN_COL_MATH_1 = 25;
	private static final int END_COL_MATH_1 = 34;
	private static final Map<String, String> CACHE_MATH_1 = new HashMap<String, String>();
	private static final String[] ABI_MATH_1 = { "数感", "符号意识", "空间观念", "几何直观", "数据分析观念", "运算能力", "推理能力", "模型思想",
			"应用意识", "创新意识" };

	/**
	 * 高中数学学科能力
	 */
	private static final int BEGIN_COL_MATH_2 = 25;
	private static final int END_COL_MATH_2 = 31;
	private static final Map<String, String> CACHE_MATH_2 = new HashMap<String, String>();
	private static final String[] ABI_MATH_2 = { "空间想像能力", "抽象概括能力", "推理论证能力", "运算求解能力", "数据处理能力", "应用意识", "创新意识" };

	/**
	 * 高中数学理科学科能力
	 */
	private static final int BEGIN_COL_MATH_2_1 = 25;
	private static final int END_COL_MATH_2_1 = 31;
	private static final Map<String, String> CACHE_MATH_2_1 = new HashMap<String, String>();
	private static final String[] ABI_MATH_2_1 = { "空间想像能力", "抽象概括能力", "推理论证能力", "运算求解能力", "数据处理能力", "应用意识", "创新意识" };

	/**
	 * 高中数学文科学科能力
	 */
	private static final int BEGIN_COL_MATH_2_2 = 25;
	private static final int END_COL_MATH_2_2 = 31;
	private static final Map<String, String> CACHE_MATH_2_2 = new HashMap<String, String>();
	private static final String[] ABI_MATH_2_2 = { "空间想像能力", "抽象概括能力", "推理论证能力", "运算求解能力", "数据处理能力", "应用意识", "创新意识" };

	/**
	 * 初中化学学科能力
	 */
	private static final int BEGIN_COL_CHE_1 = 25;
	private static final int END_COL_CHE_1 = 33;
	private static final Map<String, String> CACHE_CHE_1 = new HashMap<String, String>();
	private static final String[] ABI_CHE_1 = { "记忆能力", "理解、获得能力", "观察能力", "实验能力", "问题解决能力", "推理能力", "归纳与分析能力", "计算能力",
			"实践应用能力" };

	/**
	 * 高中化学学科能力
	 */
	private static final int BEGIN_COL_CHE_2 = 25;
	private static final int END_COL_CHE_2 = 34;
	private static final Map<String, String> CACHE_CHE_2 = new HashMap<String, String>();
	private static final String[] ABI_CHE_2 = { "记忆能力", "理解、获得能力", "观察能力", "实验能力", "问题解决能力", "推理能力", "归纳与分析能力", "计算能力",
			"实践应用能力", "三维想象能力" };

	/**
	 * 初中物理学科能力
	 */
	private static final int BEGIN_COL_SYH_1 = 25;
	private static final int END_COL_SYH_1 = 29;
	private static final Map<String, String> CACHE_SYH_1 = new HashMap<String, String>();
	private static final String[] ABI_SYH_1 = { "观察能力", "科学探究能力", "实验能力", "自主创新能力", "解决问题的能力" };

	/**
	 * 高中物理学科能力
	 */
	private static final int BEGIN_COL_SYH_2 = 25;
	private static final int END_COL_SYH_2 = 29;
	private static final Map<String, String> CACHE_SYH_2 = new HashMap<String, String>();
	private static final String[] ABI_SYH_2 = { "理解能力", "推理能力", "分析综合能力", "应用数学处理物理问题的能力", "实验能力" };

	/**
	 * @author liuzeke
	 * @date 2016-4-20
	 * @time 下午6:36:04
	 * @desc 初始化学科能力
	 */
	public List<Object> init(Long id) {
		System.out.println("---初始化" + id + "学科能力开始...");
		List<Object> list = new ArrayList<Object>();
		if (CACHE != null)
			CACHE.clear();

		if (String.valueOf(id).equals(AbiType.MAHT_0.getId()+"")) {
			begin_col = BEGIN_COL_MATH_0;
			end_col = END_COL_MATH_0;
			int j = 0;
			CACHE_MATH_0.clear();
			for (int i = BEGIN_COL_MATH_0; i <= END_COL_MATH_0; i++) {
				CACHE_MATH_0.put(i + "BUF", ABI_MATH_0[j]);
				j++;
			}
			CACHE = CACHE_MATH_0;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.MAHT_1.getId()+"")) {
			begin_col = BEGIN_COL_MATH_1;
			end_col = END_COL_MATH_1;
			int j = 0;
			CACHE_MATH_1.clear();
			for (int i = BEGIN_COL_MATH_1; i <= END_COL_MATH_1; i++) {
				CACHE_MATH_1.put(i + "BUF", ABI_MATH_1[j]);
				j++;
			}
			CACHE = CACHE_MATH_1;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.MATH_2.getId()+"")) {
			begin_col = BEGIN_COL_MATH_2;
			end_col = END_COL_MATH_2;
			int j = 0;
			CACHE_MATH_2.clear();
			for (int i = BEGIN_COL_MATH_2; i <= END_COL_MATH_2; i++) {
				CACHE_MATH_2.put(i + "BUF", ABI_MATH_2[j]);
				j++;
			}
			CACHE = CACHE_MATH_2;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.SYH_1.getId()+"")) {
			begin_col = BEGIN_COL_SYH_1;
			end_col = END_COL_SYH_1;
			int j = 0;
			CACHE_SYH_1.clear();
			for (int i = BEGIN_COL_SYH_1; i <= END_COL_SYH_1; i++) {
				CACHE_SYH_1.put(i + "BUF", ABI_SYH_1[j]);
				j++;
			}
			CACHE = CACHE_SYH_1;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.SYH_2.getId()+"")) {
			begin_col = BEGIN_COL_SYH_2;
			end_col = END_COL_SYH_2;
			int j = 0;
			CACHE_SYH_2.clear();
			for (int i = BEGIN_COL_SYH_2; i <= END_COL_SYH_2; i++) {
				CACHE_SYH_2.put(i + "BUF", ABI_SYH_2[j]);
				j++;
			}
			CACHE = CACHE_SYH_2;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.CHE_1.getId()+"")) {
			begin_col = BEGIN_COL_CHE_1;
			end_col = END_COL_CHE_1;
			int j = 0;
			CACHE_CHE_1.clear();
			for (int i = BEGIN_COL_CHE_1; i <= END_COL_CHE_1; i++) {
				CACHE_CHE_1.put(i + "BUF", ABI_CHE_1[j]);
				j++;
			}
			CACHE = CACHE_CHE_1;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.CHE_2.getId()+"")) {
			begin_col = BEGIN_COL_CHE_2;
			end_col = END_COL_CHE_2;
			int j = 0;
			CACHE_CHE_2.clear();
			for (int i = BEGIN_COL_CHE_2; i <= END_COL_CHE_2; i++) {
				CACHE_CHE_2.put(i + "BUF", ABI_CHE_2[j]);
				j++;
			}
			CACHE = CACHE_CHE_2;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.MATH_2_1.getId()+"")) {
			begin_col = BEGIN_COL_MATH_2_1;
			end_col = END_COL_MATH_2_1;
			int j = 0;
			CACHE_MATH_2_1.clear();
			for (int i = BEGIN_COL_MATH_2_1; i <= END_COL_MATH_2_1; i++) {
				CACHE_MATH_2_1.put(i + "BUF", ABI_MATH_2_1[j]);
				j++;
			}
			CACHE = CACHE_MATH_2_1;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		} else if (String.valueOf(id).equals(AbiType.MATH_2_2.getId()+"")) {
			begin_col = BEGIN_COL_MATH_2_2;
			end_col = END_COL_MATH_2_2;
			int j = 0;
			CACHE_MATH_2_2.clear();
			for (int i = BEGIN_COL_MATH_2_2; i <= END_COL_MATH_2_2; i++) {
				CACHE_MATH_2_2.put(i + "BUF", ABI_MATH_2_2[j]);
				j++;
			}
			CACHE = CACHE_MATH_2_2;
			list.add(begin_col);
			list.add(end_col);
			list.add(CACHE);
		}
		return list;
	}

	public static String getQuerySql() {
		return querySql;
	}

	public static void setQuerySql(String querySql) {
		Ability.querySql = querySql;
	}

	public static int getBegin_col() {
		return begin_col;
	}

	public static void setBegin_col(int begin_col) {
		Ability.begin_col = begin_col;
	}

	public static int getEnd_col() {
		return end_col;
	}

	public static void setEnd_col(int end_col) {
		Ability.end_col = end_col;
	}

	public static int getBeginColMath1() {
		return BEGIN_COL_MATH_1;
	}

	public static int getEndColMath1() {
		return END_COL_MATH_1;
	}

	public static Map<String, String> getCacheMath1() {
		return CACHE_MATH_1;
	}

	public static String[] getAbiMath1() {
		return ABI_MATH_1;
	}

	public static int getBeginColMath2() {
		return BEGIN_COL_MATH_2;
	}

	public static int getEndColMath2() {
		return END_COL_MATH_2;
	}

	public static Map<String, String> getCacheMath2() {
		return CACHE_MATH_2;
	}

	public static String[] getAbiMath2() {
		return ABI_MATH_2;
	}

	public static int getBeginColChe1() {
		return BEGIN_COL_CHE_1;
	}

	public static int getEndColChe1() {
		return END_COL_CHE_1;
	}

	public static Map<String, String> getCacheChe1() {
		return CACHE_CHE_1;
	}

	public static String[] getAbiChe1() {
		return ABI_CHE_1;
	}

	public static int getBeginColChe2() {
		return BEGIN_COL_CHE_2;
	}

	public static int getEndColChe2() {
		return END_COL_CHE_2;
	}

	public static Map<String, String> getCacheChe2() {
		return CACHE_CHE_2;
	}

	public static String[] getAbiChe2() {
		return ABI_CHE_2;
	}

	public static int getBeginColSyh1() {
		return BEGIN_COL_SYH_1;
	}

	public static int getEndColSyh1() {
		return END_COL_SYH_1;
	}

	public static Map<String, String> getCacheSyh1() {
		return CACHE_SYH_1;
	}

	public static String[] getAbiSyh1() {
		return ABI_SYH_1;
	}

	public static int getBeginColSyh2() {
		return BEGIN_COL_SYH_2;
	}

	public static int getEndColSyh2() {
		return END_COL_SYH_2;
	}

	public static Map<String, String> getCacheSyh2() {
		return CACHE_SYH_2;
	}

	public static String[] getAbiSyh2() {
		return ABI_SYH_2;
	}

	public static Map<String, String> getCACHE() {
		return CACHE;
	}

	public static void setCACHE(Map<String, String> cACHE) {
		CACHE = cACHE;
	}

	/**
	 * @author liuzeke
	 * @date 2016-4-20
	 */
	public enum AbiType {

		MAHT_1("MAHT_1", "初中数学", 1), MATH_2("MAHT_2", "高中数学", 4), MATH_2_1("MATH_2_1", "高中数学理", 9), MATH_2_2(
				"MATH_2_2", "高中数学文", 8), SYH_1("SYH_1", "初中物理", 2), SYH_2("SYH_2", "高中物理", 6), CHE_1("CHE_1", "初中化学", 3), CHE_2(
				"CHE_2", "高中化学", 7), MAHT_0("MAHT_0", "小学数学", 5);

		private String type;
		private String desc;
		private int id;

		AbiType(String type, String desc, int id) {
			this.type = type;
			this.desc = desc;
			this.id = id;
		}

		public String getType() {
			return type;
		}

		public String getDesc() {
			return desc;
		}

		public int getId() {
			return id;
		}
	}
}
