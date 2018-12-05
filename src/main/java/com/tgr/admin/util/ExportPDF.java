package com.tgr.admin.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportPDF {
	
	/**
	 * 导出pdf模版
	 * 
	 * 
	 * @param pdffile
	 * @param imgfile1
	 * @param imgfile2
	 * @param context
	 * @return url
	 * 
	 * @author lvwenwen
	 * @throws Exception 
	 */
	
	public static String exportpdf(String pdffile,String imgfile1,String imgfile2,String context) throws Exception {
		try {
			Document pdfDoc = new Document();
			// 将要生成的 pdf 文件的路径输出流
			FileOutputStream pdfFile = new FileOutputStream(new File(pdffile));

			// pdf 文件中的一个文字段落
			BaseFont baseFontChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(baseFontChinese, 25, Font.NORMAL);
			Font fontChinese2 = new Font(baseFontChinese, 20, Font.NORMAL);
			Paragraph paragraph = new Paragraph("扫描二维码，加入有谱爱学习班级", fontChinese);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			File file1 = FileUtil.getnetFile(imgfile1);
			File file2 = FileUtil.getnetFile(imgfile2);
			Image image1 = Image.getInstance(file1.getPath());
			Image image2 = Image.getInstance(file2.getPath());

			// 用 Document 对象、File 对象获得 PdfWriter 输出流对象
			PdfWriter.getInstance(pdfDoc, pdfFile);
			pdfDoc.open(); // 打开 Document 文档

			// 添加一个文字段落、一张图片
			pdfDoc.add(paragraph);
			pdfDoc.add(new Paragraph(" ", fontChinese));
			pdfDoc.add(new Paragraph(
					"    亲爱的同学们，请用手机下载有谱爱学习App（左下图），打开并注册登录，点击个人中心的关联班级，点击扫描图标，扫描下面（右下图）的班级二维码，申请加入：",
					fontChinese2));
			pdfDoc.add(new Paragraph(" ", fontChinese));
			PdfPTable table = new PdfPTable(2);
			PdfPCell cell1 = new PdfPCell(image1);
			PdfPCell cell2 = new PdfPCell(image2);
			cell1.setBorder(0);
			cell1.setUseAscender(true);
			cell1.setUseDescender(true);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);
			cell2.setBorder(0);
			table.addCell(cell1);
			table.addCell(cell2);
			pdfDoc.add(table);
			
			PdfPTable mytable = new PdfPTable(2);
			Paragraph paragraph11 = new Paragraph("第一步 下载", fontChinese2);
			Paragraph paragraph12 = new Paragraph("有谱爱学习app", fontChinese2);
			Paragraph paragraph21 = new Paragraph("第二步 申请加入", fontChinese2);
			Paragraph paragraph22 = new Paragraph(context, fontChinese2);
			PdfPCell cell3 = new PdfPCell(paragraph11);
			PdfPCell cell4 = new PdfPCell(paragraph12);
			PdfPCell cell5 = new PdfPCell(paragraph21);
			PdfPCell cell6 = new PdfPCell(paragraph22);
			cell3.setBorder(0);
			cell3.setUseAscender(true);
			cell3.setUseDescender(true);
			cell3.setVerticalAlignment(Element.ALIGN_CENTER);
			cell4.setBorder(0);
			cell5.setBorder(0);
			cell6.setBorder(0);
			mytable.addCell(cell3);
			mytable.addCell(cell5);
			mytable.addCell(cell4);
			mytable.addCell(cell6);
			pdfDoc.add(mytable);
			file1.delete();
			file2.delete();
			pdfDoc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pdffile;
	}
}



