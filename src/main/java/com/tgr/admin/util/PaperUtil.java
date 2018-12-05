package com.tgr.admin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import jxl.Sheet;

@SuppressWarnings("all")
public class PaperUtil {
	
	/*private static final int QUESTION_SEQ_X = 0;
	private static final int QUESTION_TYPE_NO_X = 1;
	private static final int VALUE_X = 2;
	private static final int VALID_VALUE_X = 3;
	private static final int POINT_1 = 5;
	private static final int RELATION_WEIGHT_1 = 6;
	private static final int POINT_2 = 8;
	private static final int RELATION_WEIGHT_2 = 9;
	private static final int POINT_3 = 11;
	private static final int RELATION_WEIGHT_3 = 12;
	private static final int POINT_4 = 14;
	private static final int RELATION_WEIGHT_4 = 15;
	private static final int POINT_5 = 17;
	private static final int RELATION_WEIGHT_5 = 18;
	private static final int POINT_6 = 20;
	private static final int RELATION_WEIGHT_6 = 21;
	private static final int POINT_7 = 23;
	private static final int RELATION_WEIGHT_7 = 24;
	private static int question_point = 0;
	private static int test_question = 0;
	private static int question_ability = 0;
	private static final int DATA_BEGIN = 1;
	private static final int INDEX = 0;
	private static boolean isRollbak = false;
	
	public List<List<Object>> getPaperFromExcel(Sheet sheet,List<Object> list) {
		List<List<Object>> retList = new ArrayList<List<Object>>();
		List<Object> rowList = null;
		List<String> ablList = null;

		int rows = sheet.getRows();
		for (int i = DATA_BEGIN; i < rows; i++) {
			String questionSeq = sheet.getCell(QUESTION_SEQ_X, i).getContents();
			String questionTypeNo = sheet.getCell(QUESTION_TYPE_NO_X, i).getContents();
			String value = sheet.getCell(VALUE_X, i).getContents();
			String validValue = sheet.getCell(VALID_VALUE_X, i).getContents();
			String point1 = sheet.getCell(POINT_1, i).getContents();
			String relationWeight1 = sheet.getCell(RELATION_WEIGHT_1, i).getContents();
			String point2 = sheet.getCell(POINT_2, i).getContents();
			String relationWeight2 = sheet.getCell(RELATION_WEIGHT_2, i).getContents();
			String point3 = sheet.getCell(POINT_3, i).getContents();
			String relationWeight3 = sheet.getCell(RELATION_WEIGHT_3, i).getContents();
			String point4 = sheet.getCell(POINT_4, i).getContents();
			String relationWeight4 = sheet.getCell(RELATION_WEIGHT_4, i).getContents();
			String point5 = sheet.getCell(POINT_5, i).getContents();
			String relationWeight5 = sheet.getCell(RELATION_WEIGHT_5, i).getContents();
			String point6 = sheet.getCell(POINT_6, i).getContents();
			String relationWeight6 = sheet.getCell(RELATION_WEIGHT_6, i).getContents();
			String point7 = sheet.getCell(POINT_7, i).getContents();
			String relationWeight7 = sheet.getCell(RELATION_WEIGHT_7, i).getContents();

			ablList = new ArrayList<String>(10);
			for (int j = Integer.parseInt(String.valueOf(list.get(0))); j <= Integer.parseInt(String.valueOf(list.get(1))); j++)
				if ("Y".equalsIgnoreCase(sheet.getCell(j, i).getContents())
						|| "H".equalsIgnoreCase(sheet.getCell(j, i).getContents()))
					ablList.add(((Map<String, String>)list.get(2)).get(j + "BUF"));

			if (BlankUtil.isBlank(questionSeq))
				break;

			rowList = new ArrayList<Object>();

			rowList.add(BlankUtil.isBlank(questionSeq) ? "" : questionSeq);// 0
			rowList.add(BlankUtil.isBlank(questionTypeNo) ? "" : questionTypeNo);// 1
			rowList.add(BlankUtil.isBlank(value) ? "" : value);// 2
			rowList.add(BlankUtil.isBlank(validValue) ? "" : validValue);// 3
			rowList.add(BlankUtil.isBlank(point1) ? "" : point1);// 4
			rowList.add(BlankUtil.isBlank(relationWeight1) ? "" : relationWeight1);// 5
			rowList.add(BlankUtil.isBlank(point2) ? "" : point2);// 6
			rowList.add(BlankUtil.isBlank(relationWeight2) ? "" : relationWeight2);// 7
			rowList.add(BlankUtil.isBlank(point3) ? "" : point3);// 8
			rowList.add(BlankUtil.isBlank(relationWeight3) ? "" : relationWeight3);// 9
			rowList.add(BlankUtil.isBlank(point4) ? "" : point4);// 10
			rowList.add(BlankUtil.isBlank(relationWeight4) ? "" : relationWeight4);// 11
			rowList.add(BlankUtil.isBlank(point5) ? "" : point5);// 12
			rowList.add(BlankUtil.isBlank(relationWeight5) ? "" : relationWeight5);// 13
			rowList.add(BlankUtil.isBlank(point6) ? "" : point6);// 14
			rowList.add(BlankUtil.isBlank(relationWeight6) ? "" : relationWeight6);// 15
			rowList.add(BlankUtil.isBlank(point7) ? "" : point7);// 16
			rowList.add(BlankUtil.isBlank(relationWeight7) ? "" : relationWeight7);// 17
			rowList.add(ablList);// 18

			retList.add(rowList);
		}
		System.out.println("---获取excel数据结束.");
		return retList;
	}*/
}
