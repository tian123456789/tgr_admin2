package com.tgr.admin.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("all")
public class PDFUtil {

	public static void createPdf(List<Map<String,?>> stulist , HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException{
		
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + UUID.randomUUID().toString().replace("-", "")
					+ ".pdf");
			ServletOutputStream outputStream = response.getOutputStream();
		
			Document document = new Document(PageSize.A4.rotate(), 50, 50, 10, 0);
			PdfWriter.getInstance(document, outputStream);

			//新建字体解决中文问题
			BaseFont chinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font boldFont = new Font(chinese, 17, Font.BOLD);
			
			document.open();
			
			PdfPTable table = new PdfPTable(2);

		    table.setWidthPercentage(100);
		    table.setSpacingBefore(20);
		    table.setSpacingAfter(20);
		    int size = stulist.size();
		    for(int i = 0; i < size; i++){
		    	
		    	PdfPTable student = new PdfPTable(1);
			    
			    Chunk chunk = new Chunk(stulist.get(i).get("username").toString(), boldFont);
			    Phrase phrase = new Phrase(chunk);
			    PdfPCell cell = new PdfPCell(phrase);
			    cell.setBorder(0);
			    //cell.setPaddingTop(30);
			    cell.setPaddingLeft(20);
			    student.addCell(cell);
			    
			    chunk = new Chunk("用户名："+stulist.get(i).get("name").toString(), boldFont);
			    phrase = new Phrase(chunk);
			    cell = new PdfPCell(phrase);
			    cell.setBorder(0);
			    cell.setPaddingTop(8);
			    cell.setPaddingLeft(20);
			    student.addCell(cell);
			    
			    chunk = new Chunk("初始密码："+stulist.get(i).get("password").toString(), boldFont);
			    phrase = new Phrase(chunk);
			    cell = new PdfPCell(phrase);
			    cell.setBorder(0);
			    cell.setPaddingTop(8);
			    cell.setPaddingLeft(20);
			    student.addCell(cell);
			    
			    chunk = new Chunk(stulist.get(i).get("qqweibo").toString(), boldFont);
			    phrase = new Phrase(chunk);
			    cell = new PdfPCell(phrase);
			    cell.setBorder(0);
			    cell.setPaddingTop(8);
			    cell.setPaddingLeft(20);
			    //cell.setPaddingBottom(30);
			    student.addCell(cell);
			    
			    PdfPCell tablecell = new PdfPCell(student);
			    tablecell.setPaddingTop(45);
			    tablecell.setPaddingBottom(45);
			    //定义边框
			    tablecell.setUseBorderPadding(true);
			    switch(i%6){
			    	case 0 : 
			    		tablecell.setBorderWidthTop(0f);
			    		tablecell.setBorderWidthLeft(0f);
			    		if(i == size-1 || i == size-2){
			    			tablecell.setBorderWidthTop(0f);
				    		tablecell.setBorderWidthBottom(0f);
				    		tablecell.setBorderWidthLeft(0f);
			    		}
			    		break;
			    	case 1 :
			    		tablecell.setBorderWidthTop(0f);
			    		tablecell.setBorderWidthRight(0f);
			    		tablecell.setBorderWidthLeft(0f);
			    		if(i == size-1){
			    			tablecell.setBorderWidthTop(0f);
				    		tablecell.setBorderWidthRight(0f);
				    		tablecell.setBorderWidthBottom(0f);
				    		tablecell.setBorderWidthLeft(0f);
			    		}
			    		break;
			    	case 2 :
			    		tablecell.setBorderWidthTop(0f);
			    		tablecell.setBorderWidthLeft(0f);
			    		if(i == size-1 || i == size-2){
			    			tablecell.setBorderWidthTop(0f);
				    		tablecell.setBorderWidthBottom(0f);
				    		tablecell.setBorderWidthLeft(0f);
			    		}
			    		break;
			    	case 3 :
			    		tablecell.setBorderWidthTop(0f);
			    		tablecell.setBorderWidthRight(0f);
			    		tablecell.setBorderWidthLeft(0f);
			    		if(i == size-1){
			    			tablecell.setBorderWidthTop(0f);
				    		tablecell.setBorderWidthRight(0f);
				    		tablecell.setBorderWidthBottom(0f);
				    		tablecell.setBorderWidthLeft(0f);
			    		}
			    		break;
			    	case 4 :
			    		tablecell.setBorderWidthTop(0f);
			    		tablecell.setBorderWidthBottom(0f);
			    		tablecell.setBorderWidthLeft(0f);
			    		break;
			    	case 5 :
			    		tablecell.setBorderWidthTop(0f);
			    		tablecell.setBorderWidthRight(0f);
			    		tablecell.setBorderWidthBottom(0f);
			    		tablecell.setBorderWidthLeft(0f);
			    		break;
			    }
			    
			    table.addCell(tablecell);
			    
			    if(size%2 != 0 && i == (size - 1)){
			    	PdfPTable student2 = new PdfPTable(1);
			    	PdfPCell tablecell2 = new PdfPCell(student2);
			    	chunk = new Chunk("", boldFont);
				    phrase = new Phrase(chunk);
				    cell = new PdfPCell(phrase);
				    cell.setBorder(0);
				    cell.setPaddingTop(8);
				    cell.setPaddingLeft(20);
				    //cell.setPaddingBottom(30);
				    student2.addCell(cell);
				    switch(size%6){
				    	case 0 : 
				    		tablecell2.setBorderWidthTop(0f);
				    		tablecell2.setBorderWidthLeft(0f);
				    		break;
				    	case 1 :
				    		tablecell2.setBorderWidthTop(0f);
				    		tablecell2.setBorderWidthRight(0f);
				    		tablecell2.setBorderWidthLeft(0f);
				    		if(i == size-1){
				    			tablecell2.setBorderWidthTop(0f);
				    			tablecell2.setBorderWidthRight(0f);
				    			tablecell2.setBorderWidthBottom(0f);
				    			tablecell2.setBorderWidthLeft(0f);
				    		}
				    		break;
				    	case 2 :
				    		tablecell2.setBorderWidthTop(0f);
				    		tablecell2.setBorderWidthLeft(0f);
				    		break;
				    	case 3 :
				    		tablecell2.setBorderWidthTop(0f);
				    		tablecell2.setBorderWidthRight(0f);
				    		tablecell2.setBorderWidthLeft(0f);
				    		if(i == size-1){
				    			tablecell2.setBorderWidthTop(0f);
				    			tablecell2.setBorderWidthRight(0f);
				    			tablecell2.setBorderWidthBottom(0f);
				    			tablecell2.setBorderWidthLeft(0f);
				    		}
				    		break;
				    	case 4 :
				    		tablecell2.setBorderWidthTop(0f);
				    		tablecell2.setBorderWidthBottom(0f);
				    		tablecell2.setBorderWidthLeft(0f);
				    		break;
				    	case 5 :
				    		tablecell2.setBorderWidthTop(0f);
				    		tablecell2.setBorderWidthRight(0f);
				    		tablecell2.setBorderWidthBottom(0f);
				    		tablecell2.setBorderWidthLeft(0f);
				    		break;
				    }
				    tablecell2.setPaddingTop(45);
				    tablecell2.setPaddingBottom(45);
				    table.addCell(tablecell2);
			    }
		    }
		    
		    document.add(table);
		    
			document.close();
			outputStream.flush();
			return;
	}
}
