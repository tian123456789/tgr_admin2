package com.tgr.admin.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MathUtil {
	private static Map<String, Integer> allResult = new HashMap<String, Integer>();

	static {
		// from 1（差）
		allResult.put("1_0,1_0,", 1);
		allResult.put("1_0,1_1,2_0,", 2);
		allResult.put("1_0,1_1,2_1,3_0,", 3);
		allResult.put("1_0,1_1,2_1,3_1,", 4);
		allResult.put("1_1,2_0,1_0,", 2);
		allResult.put("1_1,2_0,1_1,2_0,", 2);
		allResult.put("1_1,2_0,1_1,2_1,3_0,", 3);
		allResult.put("1_1,2_0,1_1,2_1,3_1,", 4);
		allResult.put("1_1,2_1,3_0,", 3);
		allResult.put("1_1,2_1,3_1,", 4);
		// from unkown or 2(中)
		allResult.put("2_1,3_1,3_1,", 4);
		allResult.put("2_1,3_1,3_0,", 3);
		allResult.put("2_1,3_0,2_1,", 3);
		allResult.put("2_1,3_0,2_0,1_1,", 2);
		allResult.put("2_1,3_0,2_0,1_0,", 1);
		allResult.put("2_0,1_1,2_1,3_1,", 4);
		allResult.put("2_0,1_1,2_1,3_0,", 3);
		allResult.put("2_0,1_1,2_0,", 2);
		allResult.put("2_0,1_0,1_1,", 2);
		allResult.put("2_0,1_0,1_0,", 1);
		// from 3(良)
		allResult.put("3_1,3_1,", 4);
		allResult.put("3_1,3_0,2_1,", 3);
		allResult.put("3_1,3_0,2_0,1_1,", 2);
		allResult.put("3_1,3_0,2_0,1_0,", 1);
		allResult.put("3_0,2_1,3_1,", 4);
		allResult.put("3_0,2_1,3_0,", 3);
		allResult.put("3_0,2_0,1_1,", 2);
		allResult.put("3_0,2_0,1_0,", 1);
		// from 4（优）
		allResult.put("4_1,", 4);
		allResult.put("4_0,4_1,", 4);
		allResult.put("4_0,4_0,4_1,", 4);
		allResult.put("4_0,4_0,4_0,", 3);
	}

	/**
	 * 等级划分标准
	 * 
	 * @param star
	 * @param startotal
	 * @return
	 */
	public static int parseGrade(Integer star, Integer startotal) {
		if (startotal == 0) {
			return 0;
		}
		int i = star * 10000 / startotal;
		if (i >= 8000) {
			return 4;
		} else if (i < 8000 && i >= 6600) {
			return 3;
		} else if (i < 6600 && i >= 3000) {
			return 2;
		} else if (i < 3000 && i >= 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 获取随机正整数
	 * 
	 * @param max
	 * @return
	 */
	public static int randomInt(int max) {
		if (max == 0) {
			return 0;
		}
		Random rand = new Random();
		int nextInt = rand.nextInt(max);
		return nextInt;
	}

	/**
	 * 计算是否结束测试，返回null为未结束
	 * 
	 * @param allQuestions
	 */
	public static Integer parseOver(String allQuestions) {
		return allResult.get(allQuestions);
	}

	public static Map<String, BigDecimal> getDiffRegion(BigDecimal difficultya, BigDecimal difficultyb, int region) {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		switch (region) {
		case 4:
			map.put("max", new BigDecimal(1));
			map.put("min", difficultya);
			map.put("region", new BigDecimal(4));
			break;
		case 3:
			map.put("max", new BigDecimal(1));
			map.put("min", difficultya);
			map.put("region", new BigDecimal(3));
			break;
		case 2:
			map.put("max", difficultya);
			map.put("min", difficultyb);
			map.put("region", new BigDecimal(2));
			break;
		case 1:
			map.put("max", difficultyb);
			map.put("min", new BigDecimal(0));
			map.put("region", new BigDecimal(1));
			break;

		default:
			map.put("max", difficultya);
			map.put("min", difficultyb);
			map.put("region", new BigDecimal(2));
			break;
		}
		return map;
	}

	/**
	 * 获得难度值所在的区间范围
	 * 
	 * @param difficultya
	 *            区间换分界线a
	 * @param difficultyb
	 *            区间换分界线b
	 * @param difficulty
	 *            此时难度值
	 */
	public static Map<String, BigDecimal> getDiffRegion(BigDecimal difficultya, BigDecimal difficultyb,
			BigDecimal difficulty) {
		// 划分难度区间
		Map<String, BigDecimal> mapA = new HashMap<String, BigDecimal>();
		mapA.put("max", new BigDecimal(1));
		mapA.put("min", difficultya);
		mapA.put("region", new BigDecimal(3));

		Map<String, BigDecimal> mapB = new HashMap<String, BigDecimal>();
		mapB.put("max", difficultya);
		mapB.put("min", difficultyb);
		mapB.put("region", new BigDecimal(2));

		Map<String, BigDecimal> mapC = new HashMap<String, BigDecimal>();
		mapC.put("max", difficultyb);
		mapC.put("min", new BigDecimal(0));
		mapC.put("region", new BigDecimal(1));

		// 获取所在难度区域
		if (difficulty != null) {
			if (difficulty.compareTo(difficultya) >= 0) {
				return mapA;
			} else if (difficulty.compareTo(difficultya) == -1 && difficulty.compareTo(difficultyb) >= 0) {
				return mapA;
			} else if (difficulty.compareTo(difficultyb) == -1) {
				return mapC;
			} else {
				return mapB;
			}
		} else {
			return mapB;
		}
	}

	/**
	 * 是否拥有回答主观题资格
	 * 
	 * @param allQuestions
	 * @return
	 */
	public static boolean testAllRight(String allQuestions) {
		String[] split = allQuestions.split(",");
		if (split.length < 3) {
			boolean result = true;
			for (String str : split) {
				if ("0".equals(str.split("_")[1])) {
					result = false;
					break;
				}
			}
			return result;
		} else {
			return false;
		}
	}

	/**
	 * 高精度加法
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static double add(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.add(b2).doubleValue();
	}

	/**
	 * 高精度减法
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static double sub(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 高精度乘法
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static double mul(double value1, double value2) {
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 高精度除法
	 * 
	 * @param value1
	 * @param value2
	 * @param scale
	 * @return
	 */
	public static double div(double value1, double value2, int scale) {
		if (scale < 0) {
			throw new IllegalAccessError("精度范围不能小于0");
		}
		if (value2 == 0d) {
			throw new IllegalAccessError("除数不能为0");
		}
		BigDecimal b1 = BigDecimal.valueOf(value1);
		BigDecimal b2 = BigDecimal.valueOf(value2);
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	/**
	 * 四舍五入
	 * 
	 * @param value
	 * @param scale
	 * @return
	 */
	public static double round(double value, int scale) {
		if (scale < 0) {
			throw new IllegalAccessError("精度范围不能小于0");
		}
		BigDecimal b1 = BigDecimal.valueOf(value);
		return b1.setScale(scale, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	public static void main(String[] args) {
		System.out.print((int) round(1.533, 0));
	}

}
