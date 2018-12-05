package com.tgr.admin.util;

import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;*/

@SuppressWarnings("all")
public class PDFBanjiIndex {
/*
	public static void write(HttpServletRequest request, HttpServletResponse response, String banjiindex, String banjiname, String banjinickname, String createtime) {
		try {
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + UUID.randomUUID().toString().replace("-", "") + ".pdf");
			ServletOutputStream outputStream = response.getOutputStream();

			Document document = new Document(PageSize.A4, 80, 80, 50, 0);
			PdfWriter.getInstance(document, outputStream);
			// 创建汉字字体
			BaseFont bfSong = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
			Font fontSong = new Font(bfSong, 10, Font.NORMAL);
			// 为页增加页头信息
			HeaderFooter header = new HeaderFooter(new Phrase("铭仁教育", fontSong), false);
			header.setBorder(2);
			header.setAlignment(Element.ALIGN_RIGHT);
			document.setHeader(header);
			// 大标题
			BaseFont bfChinese1 = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font title = new Font(bfChinese1, 17, Font.BOLD);
			// 小标题
			Font minor = new Font(bfChinese1, 15, Font.BOLD);
			// 内容
			Font content = new Font(bfChinese1, 12, Font.BOLD);
			// 打开文档
			document.open();
			// 标题
			Paragraph paragraphtitle = new Paragraph(" 加入“有谱数据分析系统”班级的说明书", title);
			paragraphtitle.setAlignment(Paragraph.ALIGN_CENTER);
			paragraphtitle.setLeading(30);// 行间距
			// 第一段
			Paragraph content1 = new Paragraph("各位同学好，", content);
			// 第二段
			Paragraph content2 = new Paragraph(
					"为 了方便教师及时解班级的学习情况，也为 了方便教师及时解班级的学习情况，也了方便每个同学随时了解自己 的学习情况，请有移动设备（ Android或 iOS均可）的同学自愿加入到“有谱 数据分析系统”的班级中。",
					content);
			content2.setFirstLineIndent(25);//  首行缩进
			content2.setLeading(24);// 行间距
			// 小标题
			Paragraph contentminor = new Paragraph("请牢记我们班级的编号： "+banjiindex, minor);
			contentminor.setFirstLineIndent(25);//  首行缩进
			contentminor.setLeading(26);// 行间距
			// 第三段
			Paragraph content3 = new Paragraph("我们的班级名称为："+banjiname+"（"+banjinickname+"创建于 "+createtime+"）", content);
			content3.setFirstLineIndent(25);//  首行缩进
			content3.setLeading(26);// 行间距
			// 第四段
			Paragraph content4 = new Paragraph("加入班级的步骤如下：", content);
			content4.setLeading(24);// 行间距
			// 第五到十一段
			Paragraph content5 = new Paragraph();
			Phrase phrase1 = new Phrase("步骤 1：下载和安装“有谱”客户端”客户端", content);
			Phrase phrase2 = new Phrase("                  扫描右侧二维 码或在各主要应用市场搜索“有", content);
			Phrase phrase3 = new Phrase("                  谱”，请认准二维码中间的应用图标；", content);
			Phrase phrase4 = new Phrase("步骤 2：注册用户", content);
			Phrase phrase5 = new Phrase("                  按通常的方法，在应用界面引导下，完成用户", content);
			Phrase phrase6 = new Phrase("                  注册，并登录到系统中（见图 1）；使用正确的姓", content);
			Phrase phrase7 = new Phrase("                  名方便教师辨 认（见图 2）；", content);
			Phrase phrase = new Phrase("\n\n", content);
			phrase.setLeading(50);
			content5.add(new Phrase("\n", content));
			content5.add(phrase1);
			content5.add(phrase);
			content5.add(phrase2);
			content5.add(phrase);
			content5.add(phrase3);
			content5.add(phrase);
			content5.add(phrase4);
			content5.add(phrase);
			content5.add(phrase5);
			content5.add(phrase);
			content5.add(phrase6);
			content5.add(phrase);
			content5.add(phrase7);
			// 第十二段
			Paragraph content12 = new Paragraph(" 步骤 3：申请加入班级", content);
			content12.setLeading(24);// 行间距
			// 第十三段
			Paragraph content13 = new Paragraph("                  输入上面的班级编号，申请加耐心等待教师审核通过（见图 3）", content);
			content13.setLeading(24);// 行间距
			// 最后三张图片下面
			Paragraph jpgcontent = new Paragraph(
					"图  1                                                   图  2                                                  图  3",
					content);
			jpgcontent.setAlignment(Paragraph.ALIGN_CENTER);

			// 加入图片Deepinpl.jpg
			Image jpg1 = Image.getInstance("/data/mrjy/img.51youpu.com/Public/images/pdfbanji/1.png");
			jpg1.scaleAbsolute(110, 110);// 直接设定显示尺寸

			Image jpg11 = Image.getInstance("/data/mrjy/img.51youpu.com/Public/images/pdfbanji/11.png");
			jpg11.scaleAbsolute(122, 220);// 直接设定显示尺寸

			Image jpg22 = Image.getInstance("/data/mrjy/img.51youpu.com/Public/images/pdfbanji/22.jpg");
			jpg22.scaleAbsolute(122, 220);// 直接设定显示尺寸

			Image jpg33 = Image.getInstance("/data/mrjy/img.51youpu.com/Public/images/pdfbanji/33.jpg");
			jpg33.scaleAbsolute(122, 220);// 直接设定显示尺寸

			document.add(paragraphtitle);
			document.add(new Paragraph("\n"));
			document.add(content1);
			document.add(content2);
			document.add(contentminor);
			document.add(content3);
			document.add(content4);
			// 放入二维码
			PdfPTable table = new PdfPTable(2); // 表格两列
			table.setHorizontalAlignment(Element.ALIGN_CENTER); // 垂直居中
			table.setWidthPercentage(100);// 表格的宽度为100%
			table.setTotalWidth(500);// 设置表格的宽度
			table.getDefaultCell().setBorderWidth(0); // 不显示边框
			float[] wid1 = { 0.71f, 0.29f }; // 两列宽度的比例
			table.setWidths(wid1);
			table.addCell(content5);
			table.addCell(jpg1);

			document.add(table);
			document.add(content12);
			document.add(content13);
			document.add(new Paragraph("\n"));
			// 展示图片
			PdfPTable table1 = new PdfPTable(3); // 表格两列
			table1.setHorizontalAlignment(Element.ALIGN_CENTER); // 垂直居中
			table1.setWidthPercentage(100);// 表格的宽度为100%
			table1.getDefaultCell().setBorderWidth(0); // 不显示边框
			table1.addCell(jpg11);
			table1.addCell(jpg22);
			table1.addCell(jpg33);
			// 加载显示图片
			document.add(table1);
			document.add(jpgcontent);
			document.close();
			outputStream.flush();
		} catch (Exception e) {
			System.out.println(e);
			return;
		}
	}*/
}