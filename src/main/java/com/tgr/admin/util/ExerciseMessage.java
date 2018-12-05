package com.tgr.admin.util;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("all")
public class ExerciseMessage {

	private static BaseColor black = new BaseColor(0, 0, 0); // 黑色  
	private static BaseColor red = new BaseColor(255, 0, 0); // 红色  
	private static BaseColor blue = new BaseColor(0, 0, 255); // 蓝色  
	private static int bold = Font.BOLD; // 粗体  
	private static int normal = Font.NORMAL; // 正常字体  
	private static int italic = Font.ITALIC; // 斜体  
	private static int boldItalic = Font.BOLDITALIC; // 粗斜体 
	private static float setting = 100; // 首行缩进参数
	private static HttpServletResponse response = null;
	private static ServletOutputStream outputStream  = null;
	
	public static Document createDoc(Map map) throws Exception {
		response = (HttpServletResponse) map.get("response");
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + "Notification" + ".pdf");
		outputStream = response.getOutputStream();
		// 新建document对象
		// 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
		Document document = new Document(PageSize.A4, 50, 50, 50, 50); 
		
		// 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
		// 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
		PdfWriter.getInstance(document, outputStream);
		return document;
	}
	 
	/**
	 * 向PDF里写入内容
	 * 
	 * @throws Exception
	 */
	public static void writePdf(Map map) throws Exception {
		// various fonts
        BaseFont bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);
        BaseFont bf_times = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252", false);
        BaseFont bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
        BaseFont bf_symbol = BaseFont.createFont(BaseFont.SYMBOL, "Cp1252", false);
        
		Document document = createDoc(map);    // 打开文档
		document.open();    // 文档里写入
		Paragraph para1 = convertParToChinese("教学安排通知单", 20, bold, black);
		para1.setAlignment(Element.ALIGN_CENTER);
		document.add(para1);
		document.add(new Paragraph("\n"));
		
		para1 = convertParToChinese(map.get("exercisename").toString(), 16, bold, black);
		para1.setAlignment(Element.ALIGN_CENTER);
		document.add(para1);
		document.add(new Paragraph("\n"));

		para1 = convertParToChinese("组织者："+map.get("initiatornickname").toString()+"", 14, bold, black);
		para1.setAlignment(Element.ALIGN_CENTER);
		document.add(para1);
		document.add(new Paragraph("\n"));

		para1 = convertParToChinese("补习范围：", 14, bold, black);
		document.add(para1);
		para1.setSpacingBefore(8);
		
		Object object = map.get("exercisepointlist");
		List exercisepointlist = new ArrayList();
		if(object != null){
			exercisepointlist = (List) object;
		}
		
		Chunk chunk11 = null;
		Chunk chunk22 = null;
		
		if(exercisepointlist != null && exercisepointlist.size() > 0){
			for (int i = 0; i < exercisepointlist.size(); i++) {
				Map mappoint = (Map) exercisepointlist.get(i);
				para1 = new Paragraph();
				Font fontEnglish = new Font(bf_courier, 12, bold, black);
				chunk11 = new Chunk(""+(i+1)+". ",fontEnglish);
				chunk22 = new Chunk(convertChunkByChinese(mappoint.get("pointname").toString(), 12, bold, black));
				para1.add(chunk11);
				para1.add(chunk22);
				document.add(para1);
			}
		}
		
		para1 = convertParToChinese("具体安排：", 14, bold, black);
		para1.setSpacingBefore(20);
		document.add(para1);
		
		para1 = convertParToChinese("上课时间："+map.get("starttime")+"", 12, normal, black);
		para1.setSpacingBefore(8);
		document.add(para1);
		para1 = convertParToChinese("上课地点："+map.get("exercisesite")+"", 12, normal, black);
		document.add(para1);
		para1 = convertParToChinese("授课教师："+map.get("teachername")+"", 12, normal, black);
		document.add(para1);
		
		
		Object object2 = map.get("groupuserlist");
		List<Map> groupuserlist = new ArrayList<Map>();
		if(object2 != null){
			groupuserlist = (List) object2;
		}
		
		para1 = new Paragraph();
		para1.setSpacingBefore(20);
		chunk11 = new Chunk(convertChunkByChinese("补习班级及学生范围", 14, bold, black));
		chunk22 = new Chunk(convertChunkByChinese("（共"+groupuserlist.size()+"个班，"+map.get("countStudentTotal")+"人）：", 12, normal, black));
		para1.add(chunk11);
		para1.add(chunk22);
		document.add(para1);
		
		if(groupuserlist != null && groupuserlist.size() > 0){
			for (Map map2 : groupuserlist) {
				para1 = convertParToChinese(""+map2.get("groupname")+"（"+map2.get("total")+"人）", 10, bold, black);
				para1.setSpacingBefore(30);
				document.add(para1);
				
				Object object3 = map2.get("groupstudentlist");
				List<Map> groupstudentlist = new ArrayList<Map>();
				if(object3 != null){
					groupstudentlist = (List<Map>) object3;
				}
				
				if(groupstudentlist != null && groupstudentlist.size() > 0){
					for (Map map3 : groupstudentlist) {
						Phrase phrase = new Phrase(20);
						chunk11 = new Chunk(convertChunkByChinese(""+map3.get("studentname")+"，", 10, normal, black));
						phrase.add(chunk11);
						//for(int i=0;i<30;i++){
							document.add(phrase);
						//}
					}
				}
				
			}
		}
		
		document.close();
		outputStream.flush();
	}
		
	/**
	 *  汉字转换 
	 *
	 * @param text    
	 * @return 
	 * @throws Exception
	 */
	public static Paragraph convertParToChinese(String text, int fontsize,int fontStyle, BaseColor color) throws Exception {
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, fontsize, fontStyle, color);
		Paragraph graph = new Paragraph(text, fontChinese);
		return graph;
	}
	/**
	 *  同行字体转换
	 *  
	 *  @param text
	 *  @param fontsize
	 *  @param fontStyle
	 *  @param color
	 *  @return
	 *  @throws Exception
	 */
	public static Chunk convertChunkByChinese(String text, int fontsize, int fontStyle, BaseColor color) throws Exception {
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, fontsize, fontStyle, color);
		Chunk chunk = new Chunk(text, fontChinese);
		return chunk; 
	}


}
