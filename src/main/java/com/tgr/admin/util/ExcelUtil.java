package com.tgr.admin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

@SuppressWarnings("all")
public class ExcelUtil {

	@SuppressWarnings("rawtypes")
	public static void export(String[] titles, String[] tableDatas, String[] dataDescription, List<Map> datas,
			OutputStream outputStream) {
		try {
			// 创建Excel的工作书册 Workbook,对应到一个excel文档
			HSSFWorkbook wb = new HSSFWorkbook();
			// 创建单元格样式
			HSSFCellStyle titleStyle = wb.createCellStyle();
			titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			// 创建字体样式
			HSSFFont font = wb.createFont();
			font.setFontName("Verdana");
			font.setBoldweight((short) 100);
			font.setFontHeight((short) 300);
			font.setColor(HSSFColor.RED.index);
			titleStyle.setFont(font);

			HSSFCellStyle dataStyle = wb.createCellStyle();
			dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 创建Excel的工作sheet,对应到一个excel文档的tab
			HSSFSheet sheet = wb.createSheet("sheet1");
			// 设置excel每列宽度
			for (int i = 0; i < tableDatas.length; i++) {
				int length = tableDatas[i].length();
				sheet.setColumnWidth(i, length * 500 + 500);
			}
			// 表头
			for (int i = 0; i < titles.length; i++) {
				// 创建Excel的sheet的一行
				HSSFRow row = sheet.createRow(i);
				row.setHeight((short) 500);// 设定行的高度
				// 创建一个Excel的单元格
				HSSFCell cell = row.createCell(0);
				// 合并单元格(startRow，endRow，startColumn，endColumn)
				sheet.addMergedRegion(new CellRangeAddress(0, 0, i, tableDatas.length - 1));
				cell.setCellStyle(titleStyle);
				cell.setCellValue(titles[i]);
			}
			// 数据头
			HSSFRow row = sheet.createRow(titles.length);
			for (int i = 0; i < tableDatas.length; i++) {
				// 创建一个Excel的单元格
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(dataStyle);
				cell.setCellValue(tableDatas[i]);
			}
			// 数据体
			for (int i = 0; i < datas.size(); i++) {
				HSSFRow datatow = sheet.createRow(1 + i + titles.length);
				Map student = datas.get(i);
				// 创建一个Excel的单元格
				for (int z = 0; z < dataDescription.length; z++) {
					String key = dataDescription[z];
					if (key.contains("#")) {
						String[] split = key.split("#");
						List subinfo = (List) student.get(split[0]);
						for (int j = 0; j < subinfo.size(); j++) {
							Map point = (Map) subinfo.get(j);
							HSSFCell cell = datatow.createCell(j + z);
							cell.setCellStyle(dataStyle);
							cell.setCellValue(point.get(split[1]).toString());
						}
						break;
					} else {
						HSSFCell cell = datatow.createCell(z);
						cell.setCellStyle(dataStyle);
						String value = student.get(key).toString();
						cell.setCellValue(value);
					}
				}
			}
			wb.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 去除Excel表格中完全由空字符串组成的行
	 * 
	 * @param sheet
	 * @return HSSFSheet
	 */
	public static HSSFSheet returnHSSFSheet(HSSFSheet sheet) {
		int allRow = sheet.getLastRowNum();
		List<Integer> index = new ArrayList<Integer>();
		for (Integer i = 1; i <= allRow; i++) {
			HSSFRow row = sheet.getRow(i);
			int cellcount = row.getLastCellNum();
			int count = 0;
			for (Integer j = 0; j < cellcount; j++) {
				HSSFCell cell = row.getCell(j);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if (cell == null || cell.getStringCellValue().trim().equals("")) {
					count++;
				}
			}
			if (count == cellcount) {
				index.add(i);
			}
		}
		if (index.size() > 0) {
			for (int i = 0; i < index.size(); i++) {
				HSSFRow row = sheet.getRow(index.get(i));
				sheet.removeRow(row);
			}
		}
		return sheet;
	}

	/**
	 * 生成Excel文件并压缩处理
	 * */
	@SuppressWarnings("rawtypes")
	public static void exportBJInfo(HttpServletRequest request, List<Map> infos, ServletOutputStream outputStream)
			throws Exception {
		List<String> fileNames = new ArrayList<String>();
		
		String excelDirPath = request.getRealPath("/excel");
		System.out.println("临时文件夹目录路径：" + excelDirPath);
		File excelDir = new File(excelDirPath);
		if(!excelDir.exists()){
			excelDir.mkdir();
		}
		for (int h = 0; h < infos.size(); h++) {

			String[] titles = (String[]) infos.get(h).get("titles");
			String[] tableDatas = (String[]) infos.get(h).get("tableDatas");
			String[] dataDescription = (String[]) infos.get(h).get("tableDescription");
			List<Map> datas = (List<Map>) infos.get(h).get("students");
			String banjiName = (String) infos.get(h).get("banjiName");
			String unitName = (String) infos.get(h).get("unitName");
			FileOutputStream o = null;
			
			try {
				String fileName = request.getRealPath("/excel") + "/" + banjiName + "-" + unitName + "-"
						+DateUitl.formateDate(new Date(), "yyyyMMdd-")+ "9时30分.xls";
				fileNames.add(fileName);
				File f = new File(fileName);
				o = new FileOutputStream(f);
				// 创建Excel的工作书册 Workbook,对应到一个excel文档yyyy-MM-dd HH:mm:ss
				HSSFWorkbook wb = new HSSFWorkbook();
				// 创建单元格样式
				HSSFCellStyle titleStyle = wb.createCellStyle();
				titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				// 创建字体样式
				HSSFFont font = wb.createFont();
				font.setFontName("Verdana");
				font.setBoldweight((short) 100);
				font.setFontHeight((short) 300);
				font.setColor(HSSFColor.RED.index);
				titleStyle.setFont(font);

				HSSFCellStyle dataStyle = wb.createCellStyle();
				dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				// 创建Excel的工作sheet,对应到一个excel文档的tab
				HSSFSheet sheet = wb.createSheet("sheet1");
				// 设置excel每列宽度
				for (int i = 0; i < tableDatas.length; i++) {
					int length = tableDatas[i].length();
					sheet.setColumnWidth(i, length * 500 + 500);
				}
				// 表头
				for (int i = 0; i < titles.length; i++) {
					// 创建Excel的sheet的一行
					HSSFRow row = sheet.createRow(i);
					row.setHeight((short) 500);// 设定行的高度
					// 创建一个Excel的单元格
					HSSFCell cell = row.createCell(0);
					// 合并单元格(startRow，endRow，startColumn，endColumn)
					sheet.addMergedRegion(new CellRangeAddress(0, 0, i, tableDatas.length - 1));
					cell.setCellStyle(titleStyle);
					cell.setCellValue(titles[i]);
				}
				// 数据头
				HSSFRow row = sheet.createRow(titles.length);
				for (int i = 0; i < tableDatas.length; i++) {
					// 创建一个Excel的单元格
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(dataStyle);
					cell.setCellValue(tableDatas[i]);
				}
				// 数据体
				for (int i = 0; i < datas.size(); i++) {
					HSSFRow datatow = sheet.createRow(1 + i + titles.length);
					Map student = datas.get(i);
					// 创建一个Excel的单元格
					for (int z = 0; z < dataDescription.length; z++) {
						String key = dataDescription[z];
						if (key.contains("#")) {
							String[] split = key.split("#");
							List subinfo = (List) student.get(split[0]);
							for (int j = 0; j < subinfo.size(); j++) {
								Map point = (Map) subinfo.get(j);
								HSSFCell cell = datatow.createCell(j + z);
								cell.setCellStyle(dataStyle);
								cell.setCellValue(point.get(split[1]).toString());
							}
							break;
						} else {
							HSSFCell cell = datatow.createCell(z);
							cell.setCellStyle(dataStyle);
							String value = student.get(key).toString();
							cell.setCellValue(value);
						}
					}
				}
				wb.write(o);
				o.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}// end for

		File zip = new File(request.getRealPath("/excel") + "/" + "Maths.zip");// 压缩文件
		File srcfile[] = new File[fileNames.size()];
		for (int i = 0, n = fileNames.size(); i < n; i++) {
			srcfile[i] = new File(fileNames.get(i));
		}

		ZipFiles(srcfile, zip);
		
		FileInputStream inStream = new FileInputStream(zip);
		byte[] buf = new byte[4096];
		int readLength;
		while (((readLength = inStream.read(buf)) != -1)) {
			outputStream.write(buf, 0, readLength);
		}
		inStream.close();
		outputStream.flush();
		outputStream.close();
		
		if(excelDir.exists()){
			File[] files = excelDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				files[i].delete();
				if(files[i].exists()){
					System.out.println("待删除文件："+files[i].getPath());
				}
			}
			excelDir.delete();
		}else{
			System.out.println("临时文件夹已删除！");
		}
	}

	/**
	 * 压缩文件
	 * @param srcfile
	 * @param zipfile
	 */
	private static void ZipFiles(File[] srcfile, File zipfile) {
		// TODO Auto-generated method stub
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			//此设置去除压缩文件为盘符情况
			out.setEncoding("gb2312");
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len = 0;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成Excel文件并压缩处理
	 * */
	@SuppressWarnings("rawtypes")
	public static void exportInfo(HttpServletRequest request, List<Map> infos)
			throws Exception {
		List<String> fileNames = new ArrayList<String>();
		
		String excelDirPath = request.getRealPath("/excel2015");
		System.out.println("文件夹目录路径：" + excelDirPath);
		File excelDir = new File(excelDirPath);
		if(!excelDir.exists()){
			excelDir.mkdir();
		}
		for (int h = 0; h < infos.size(); h++) {

			String[] titles = (String[]) infos.get(h).get("titles");
			String[] tableDatas = (String[]) infos.get(h).get("tableDatas");
			String[] dataDescription = (String[]) infos.get(h).get("tableDescription");
			List<Map> datas = (List<Map>) infos.get(h).get("students");
			String banjiName = (String) infos.get(h).get("banjiName");
			String unitName = (String) infos.get(h).get("unitName");
			FileOutputStream o = null;
			
			try {
				String fileName = request.getRealPath("/excel2015") + "/" + banjiName + "-" + unitName + "-"
						+DateUitl.formateDate(new Date(), "yyyyMMdd-")+ "9时30分.xls";
				fileNames.add(fileName);
				File f = new File(fileName);
				o = new FileOutputStream(f);
				// 创建Excel的工作书册 Workbook,对应到一个excel文档yyyy-MM-dd HH:mm:ss
				HSSFWorkbook wb = new HSSFWorkbook();
				// 创建单元格样式
				HSSFCellStyle titleStyle = wb.createCellStyle();
				titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

				// 创建字体样式
				HSSFFont font = wb.createFont();
				font.setFontName("Verdana");
				font.setBoldweight((short) 100);
				font.setFontHeight((short) 300);
				font.setColor(HSSFColor.RED.index);
				titleStyle.setFont(font);

				HSSFCellStyle dataStyle = wb.createCellStyle();
				dataStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				dataStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				// 创建Excel的工作sheet,对应到一个excel文档的tab
				HSSFSheet sheet = wb.createSheet("sheet1");
				// 设置excel每列宽度
				for (int i = 0; i < tableDatas.length; i++) {
					int length = tableDatas[i].length();
					sheet.setColumnWidth(i, length * 500 + 500);
				}
				// 表头
				for (int i = 0; i < titles.length; i++) {
					// 创建Excel的sheet的一行
					HSSFRow row = sheet.createRow(i);
					row.setHeight((short) 500);// 设定行的高度
					// 创建一个Excel的单元格
					HSSFCell cell = row.createCell(0);
					// 合并单元格(startRow，endRow，startColumn，endColumn)
					sheet.addMergedRegion(new CellRangeAddress(0, 0, i, tableDatas.length - 1));
					cell.setCellStyle(titleStyle);
					cell.setCellValue(titles[i]);
				}
				// 数据头
				HSSFRow row = sheet.createRow(titles.length);
				for (int i = 0; i < tableDatas.length; i++) {
					// 创建一个Excel的单元格
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(dataStyle);
					cell.setCellValue(tableDatas[i]);
				}
				// 数据体
				for (int i = 0; i < datas.size(); i++) {
					HSSFRow datatow = sheet.createRow(1 + i + titles.length);
					Map student = datas.get(i);
					// 创建一个Excel的单元格
					for (int z = 0; z < dataDescription.length; z++) {
						String key = dataDescription[z];
						if (key.contains("#")) {
							String[] split = key.split("#");
							List subinfo = (List) student.get(split[0]);
							for (int j = 0; j < subinfo.size(); j++) {
								Map point = (Map) subinfo.get(j);
								HSSFCell cell = datatow.createCell(j + z);
								cell.setCellStyle(dataStyle);
								cell.setCellValue(point.get(split[1]).toString());
							}
							break;
						} else {
							HSSFCell cell = datatow.createCell(z);
							cell.setCellStyle(dataStyle);
							String value = student.get(key).toString();
							cell.setCellValue(value);
						}
					}
				}
				wb.write(o);
				o.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}// end for

		File zip = new File(request.getRealPath("/excel2015") + "/" + DateUitl.formateDate(new Date(), "yyyy-MM-dd")+ ".zip");// 压缩文件
		File srcfile[] = new File[fileNames.size()];
		for (int i = 0, n = fileNames.size(); i < n; i++) {
			srcfile[i] = new File(fileNames.get(i));
		}

		ZipFiles(srcfile, zip);
		
		if(excelDir.exists()){
			File[] files = excelDir.listFiles();
			for (int i = 0; i < files.length; i++) {
				String str = files[i].getName();
				if(str.endsWith(".xls")){
					files[i].delete();
				}
			}
		}
	}
}
