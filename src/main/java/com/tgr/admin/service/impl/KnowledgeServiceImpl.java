package com.tgr.admin.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.log.SysoCounter;
import com.tgr.admin.ResponseResult;
//import com.tgr.admin.entity.Code;
import com.tgr.admin.entity.Customer;
//import com.tgr.admin.entity.Shop;
import com.tgr.admin.entity.User;
//import com.tgr.admin.repository.CodeRepository;
import com.tgr.admin.repository.CustomerRepository;
//import com.tgr.admin.repository.ProviderRepository;
//import com.tgr.admin.repository.ShopRepository;
import com.tgr.admin.repository.UserRepository;
import com.tgr.admin.service.KnowledgeService;
import com.tgr.admin.util.ExcelImportUtils;

@Service
@SuppressWarnings("all")
public class KnowledgeServiceImpl implements KnowledgeService {
	/*
	@Autowired
	private CodeRepository codeRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ProviderRepository providerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;


	*//**
	 * 上传excel文件到临时目录后并开始解析
	 * @param fileName
	 * @param file
	 * @param userName
	 * @return
	 *//*
	public ResponseResult<Boolean> batchImport(String fileName,MultipartFile mfile){
		
		   File uploadDir = new  File("E:\\fileupload");
	       //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
	       if (!uploadDir.exists()) uploadDir.mkdirs();
	       //新建一个文件
	       File tempFile = new File("E:\\fileupload\\" + new Date().getTime() + ".xlsx"); 
	       //初始化输入流
	       InputStream is = null;  
	       try{
	    	   //将上传的文件写入新建的文件中
	    	   mfile.transferTo(tempFile);
	    	   
	    	   //根据新建的文件实例化输入流
	           is = new FileInputStream(tempFile);
	    	   
	    	   //根据版本选择创建Workbook的方式
	           Workbook wb = null;
	           //根据文件名判断文件是2003版本还是2007版本
	           if(ExcelImportUtils.isExcel2007(fileName)){
	        	  wb = new XSSFWorkbook(is); 
	           }else{
	        	  wb = new HSSFWorkbook(is); 
	           }
	           //根据excel里面的内容读取知识库信息
	           return readExcelValue(wb,tempFile);
	      }catch(Exception e){
	          e.printStackTrace();
	      } finally{
	          if(is !=null)
	          {
	              try{
	                  is.close();
	              }catch(IOException e){
	                  is = null;    
	                  e.printStackTrace();  
	              }
	          }
	      }
        return ResponseResult.getResult(false,"导入出错！请检查数据格式！");
    }
	
	  private ResponseResult<Boolean> readExcelValue(Workbook wb,File tempFile){
		   int sheets = wb.getNumberOfSheets();
		   if(sheets > 0) {
			   int writerConut = 0;
			   String errorMsg = "";
			   for(int i = 0;i< sheets;i++) {
				   Sheet sheet = wb.getSheetAt(i);
				   int totalRows=sheet.getPhysicalNumberOfRows();
				   int totalCells = 0; 
			       if(totalRows>=2 && sheet.getRow(0) != null && sheet.getRow(1) != null){
			           totalCells=sheet.getRow(0).getPhysicalNumberOfCells();
			           ResponseResult<Boolean> result = foreachSheet3(sheet,tempFile,totalCells,totalRows);
			           if(result.getData()) {
			        	   writerConut += Integer.valueOf(result.getMessage());
			           }else {
			        	   errorMsg += result.getMessage();
			        	   break;
			           }
			       }
			   }
			   if(writerConut > 0) {
				   return ResponseResult.getResult(true,"导入成功共"+writerConut+"条数据");
			   }else {
				   return ResponseResult.getResult(false,errorMsg);
			   }
		   }
	       return ResponseResult.getResult(false, "文件无sheet");
	  }
	  
	  public ResponseResult<Boolean> foreachSheet(Sheet sheet,File tempFile,int totalCells,int totalRows) {
		  
		   String errorMsg = "";
		   List<Customer> customerList=new ArrayList<Customer>();
	       Customer customer;    
	       String br = "<br/>";
	       for(int r=1;r<totalRows;r++){
	    	   String rowMessage = "";
	           Row row = sheet.getRow(r);
	           if (row == null){
	        	   errorMsg += br+"第"+(r+1)+"行数据有问题，请仔细检查！";
	        	   continue;
	           }
	           customer = new Customer();
	        
	           for(int c = 0; c <totalCells; c++){
	               Cell cell = row.getCell(c);
	               if (null != cell){
	            	   switch (c) {
	            	   case 0:
	            		   //id
	            		   if(StringUtils.isNoneBlank(String.valueOf(cell.getNumericCellValue()))) {
	            			  //customer.setId(Long.valueOf(cell.getNumericCellValue()));
	            			 double dou = cell.getNumericCellValue();
	            			 Long id = new Double(dou).longValue();
	            			 if(customerRepository.exists(id)) {
	            				 break;
	            			 }
	            			 customer.setId(id);
	            		   }
	            		   break;
	            	   case 1:
	            		   //create_time
	            		   if(cell.getDateCellValue() != null) {
	            			  customer.setCreateTime(cell.getDateCellValue());
	            		   }
							break;
	            	   case 2:
	            		   //update_time
	            		   if(cell.getDateCellValue() != null) {
	            			   customer.setUpdateTime(cell.getDateCellValue());
	            		   }
							break;
	            	   case 3:
	            		   //license
	            		   if(StringUtils.isNoneBlank(cell.getStringCellValue())) {
	            			   customer.setLicense(cell.getStringCellValue());
	            		   }
							break;
	            	   case 4:
	            		   //name
	            		   if(StringUtils.isNoneBlank(cell.getStringCellValue())) {
	            			   customer.setName(cell.getStringCellValue());
	            		   }
							break;
	            	   case 5:
	            		   //has_senior
	            		   if(StringUtils.isNoneBlank(String.valueOf(cell.getNumericCellValue()))) {
		            			  //customer.setId(Long.valueOf(cell.getNumericCellValue()));
		            			 double dou = cell.getNumericCellValue();
		            			 long num = new Double(dou).longValue();
		            			 if(num == 0) {
		            				 customer.setHasSenior(false);
		            			 }else if(num == 1) {
		            				 customer.setHasSenior(true);
		            			 }
		            		   }
							break;
	            	   case 6:
	            		   //is_old_custom
	            		   if(StringUtils.isNoneBlank(String.valueOf(cell.getNumericCellValue()))) {
		            			  //customer.setId(Long.valueOf(cell.getNumericCellValue()));
		            			 double dou = cell.getNumericCellValue();
		            			 long num = new Double(dou).longValue();
		            			 if(num == 0) {
		            				 customer.setOldCustom(false);
		            			 }else if(num == 1) {
		            				 customer.setOldCustom(true);
		            			 }
		            		   }
							break;
	            	   case 7:
	            		   //kva_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   double dou = cell.getNumericCellValue();
		            		   long kva_id = new Double(dou).longValue();
	            			   Optional<Code> okva = codeRepository.findById(kva_id);
	            			   if(okva.isPresent()) {
	            				   customer.setKva(okva.get());
	            			   }
	            		   }
							break;
	            	   case 8:
	            		   //shop_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   double dou = cell.getNumericCellValue();
		            		   long shop_id = new Double(dou).longValue();
	            			   Optional<Shop> os = shopRepository.findById(shop_id);
	            			   if(os.isPresent()) {
	            				   customer.setShop(os.get());
	            			   }
	            		   }
							break;
	            	   case 9:
	            		   //form_customer_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   double dou = cell.getNumericCellValue();
		            		   long form_customer_id = new Double(dou).longValue();
	            			   Optional<Customer> oc = customerRepository.findById(form_customer_id);
	            			   if(oc.isPresent()) {
	            				   customer.setFromCustomer(oc.get());
	            			   }
	            		   }
							break;
	            	   case 10:
	            		   //form_user_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   double dou = cell.getNumericCellValue();
		            		   long form_user_id = new Double(dou).longValue();
	            			   Optional<User> ou = userRepository.findById(form_user_id);
	            			   if(ou.isPresent()) {
	            				   customer.setFromUser(ou.get());
	            			   }
	            		   }
							break;
	            	   case 11:
	            		   //type_code_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   double dou = cell.getNumericCellValue();
		            		   long type_code_id = new Double(dou).longValue();
	            			   Optional<Code> oc = codeRepository.findById(type_code_id);
	            			   if(oc.isPresent()) {
	            				   customer.setType(oc.get());
	            			   }
	            		   }
							break;
	            	   case 12:
	            		   //auth_state_code_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   double dou = cell.getNumericCellValue();
		            		   long auth_state_code_id = new Double(dou).longValue();
	            			   Optional<Code> oa = codeRepository.findById(auth_state_code_id);
	            			   if(oa.isPresent()) {
	            				   customer.setAuthState(oa.get());
	            			   }
	            		   }
							break;
	            	   case 13:
	            		   //reserve
	            		   if(StringUtils.isNoneBlank(cell.getStringCellValue())) {
	            			   customer.setReserve(cell.getStringCellValue());
	            		   }
							break;
	            	   case 14:
	            		   //provider_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   //customer.set
	            		   }
							break;
	            	   case 15:
	            		   //distribution_num_code_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   double dou = cell.getNumericCellValue();
		            		   long distribution_num_code_id = new Double(dou).longValue();
	            			   Optional<Code> od = codeRepository.findById(distribution_num_code_id);
	            			   if(od.isPresent()) {
	            				   customer.setDistributionNum(od.get());
	            			   }
	            		   }
							break;
	            	   	default:
	            	   		rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
	            	   		break;
					}
	           }
	           
	       }
	           
	      if(!StringUtils.isEmpty(rowMessage)){ 
	        	errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
	        	return ResponseResult.getResult(false, errorMsg);
	      }
	      customerList.add(customer);
	      }
	      int count = save(customerList);
	      try {
	    	  removeFile(tempFile);
	      } catch (Exception e) {
	      }
	     
	      return ResponseResult.getResult(true,String.valueOf(count));
	  }
	  
	  public ResponseResult<Boolean> foreachSheet2(Sheet sheet,File tempFile,int totalCells,int totalRows) {
		  
		   String errorMsg = "";
		   List<Customer> customerList=new ArrayList<Customer>();
	       Customer customer;    
	       String br = "<br/>";
	       Map<String,Object> finalMap;
	       for1:for(int r=1;r<totalRows;r++){
	    	   String rowMessage = "";
	           Row row = sheet.getRow(r);
	           if (row == null){
	        	   errorMsg += br+"第"+(r+1)+"行数据有问题，请仔细检查！";
	        	   continue;
	           }
	           customer = new Customer();
	        
	           for(int c = 0; c <totalCells; c++){
	               Cell cell = row.getCell(c);
	               if (null != cell){
	            	   switch (c) {
	            	   case 0:
	            		   //id
	            		   if(StringUtils.isNoneBlank(String.valueOf(cell.getNumericCellValue()))) {
	            			  //customer.setId(Long.valueOf(cell.getNumericCellValue()));
	            			 double dou = cell.getNumericCellValue();
	            			 Long id = new Double(dou).longValue();
	            			 if(customerRepository.exists(id)) {
	            				 continue for1;
	            			 }
	            			 customer.setId(id);
	            		   }
	            		   break;
	            	   case 1:
	            		   //create_time
	            		   finalMap = getValue(cell, ReturnType.dateValue);
	            		   if(finalMap.get("type") == null) {
	            			   customer.setCreateTime(null);
	            		   }else {
	            			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            			   try {
	            				String date = finalMap.get("value").toString();
		            			Date d = sdf.parse(date);
								customer.setCreateTime(d);
							} catch (ParseException e) {
								e.printStackTrace();
							}
	            		   }
							break;
	            	   case 2:
	            		   //update_time
	            		   finalMap = getValue(cell, ReturnType.dateValue);
	            		   if(finalMap.get("type") == null) {
	            			   customer.setUpdateTime(null);
	            		   }else {
	            			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            			   try {
	            				String date = finalMap.get("value").toString();
	            				Date d = sdf.parse(date);
								customer.setUpdateTime(d);
							} catch (ParseException e) {
								e.printStackTrace();
							}
	            		   }
							break;
	            	   case 3:
	            		   //license
	            		   finalMap = getValue(cell, ReturnType.stringValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   customer.setLicense((String) finalMap.get("value"));
	            		   }
							break;
	            	   case 4:
	            		   //name
	            		   finalMap = getValue(cell, ReturnType.stringValue);
	            		   if(finalMap.get("type") == null) {
	            			   customer.setName(null);
	            		   }else {
	            			   customer.setName((String) finalMap.get("value"));
	            		   }
							break;
	            	   case 5:
	            		   //has_senior
	            		   finalMap = getValue(cell, ReturnType.booleanValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   customer.setHasSenior((boolean) finalMap.get("value"));
	            		   }
							break;
	            	   case 6:
	            		   //is_old_custom
	            		   finalMap = getValue(cell, ReturnType.booleanValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   customer.setOldCustom((boolean) finalMap.get("value"));
	            		   }
							break;
	            	   case 7:
	            		   //kva_id
	            		   finalMap = getValue(cell, ReturnType.longValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   long kva_id = (long) finalMap.get("value");
	            			   Optional<Code> okva = codeRepository.findById(kva_id);
	            			   if(okva.isPresent()) {
	            				   customer.setKva(okva.get());
	            			   }
	            		   }
							break;
	            	   case 8:
	            		   //shop_id
	            		   finalMap = getValue(cell, ReturnType.longValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   long shop_id = (long) finalMap.get("value");
	            			   Optional<Shop> os = shopRepository.findById(shop_id);
	            			   if(os.isPresent()) {
	            				   customer.setShop(os.get());
	            			   }
	            		   }
							break;
	            	   case 9:
	            		   //form_customer_id
	            		   finalMap = getValue(cell, ReturnType.longValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   long form_customer_id = (long) finalMap.get("value");
	            			   Optional<Customer> oc = customerRepository.findById(form_customer_id);
	            			   if(oc.isPresent()) {
	            				   customer.setFromCustomer(oc.get());
	            			   }
	            		   }
							break;
	            	   case 10:
	            		   //form_user_id
	            		   finalMap = getValue(cell, ReturnType.longValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   long form_user_id = (long) finalMap.get("value");
	            			   Optional<User> ou = userRepository.findById(form_user_id);
	            			   if(ou.isPresent()) {
	            				   customer.setFromUser(ou.get());
	            			   }
	            		   }
							break;
	            	   case 11:
	            		   //type_code_id
	            		   finalMap = getValue(cell, ReturnType.longValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   long type_code_id = (long) finalMap.get("value");
	            			   Optional<Code> oc = codeRepository.findById(type_code_id);
	            			   if(oc.isPresent()) {
	            				   customer.setType(oc.get());
	            			   }
	            		   }
							break;
	            	   case 12:
	            		   //auth_state_code_id
	            		   finalMap = getValue(cell, ReturnType.longValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   long auth_state_code_id = (long) finalMap.get("value");
	            			   Optional<Code> oa = codeRepository.findById(auth_state_code_id);
	            			   if(oa.isPresent()) {
	            				   customer.setAuthState(oa.get());
	            			   }
	            		   }
							break;
	            	   case 13:
	            		   //reserve
	            		   finalMap = getValue(cell, ReturnType.stringValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   customer.setReserve((String) finalMap.get("value"));
	            		   }
	            		 
							break;
	            	   case 14:
	            		   //provider_id
	            		   if(cell.getNumericCellValue() != 0) {
	            			   //customer.set
	            		   }
							break;
	            	   case 15:
	            		   //distribution_num_code_id
	            		   finalMap = getValue(cell, ReturnType.longValue);
	            		   if(finalMap.get("type") == null) {
	            		   }else {
	            			   long distribution_num_code_id = (long) finalMap.get("value");
	            			   Optional<Code> od = codeRepository.findById(distribution_num_code_id);
	            			   if(od.isPresent()) {
	            				   customer.setDistributionNum(od.get());
	            			   }
	            		   }
							break;
	            	   	default:
	            	   		rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
	            	   		break;
					}
	           }
	           
	       }
	           
	      if(!StringUtils.isEmpty(rowMessage)){ 
	        	errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
	        	return ResponseResult.getResult(false, errorMsg);
	      }
	      customerList.add(customer);
	      }
	      int count = save(customerList);
	      try {
	    	  removeFile(tempFile);
	      } catch (Exception e) {
	      }
	     
	      return ResponseResult.getResult(true,String.valueOf(count));
	  }
	  
	  public ResponseResult<Boolean> foreachSheet3(Sheet sheet,File tempFile,int totalCells,int totalRows) {
		  
		   String errorMsg = "";
	       String br = "<br/>";
	       
	       int threadPoolNum = 10;
		   long startTims = System.currentTimeMillis();
		   //长线程池
		   ExecutorService service = Executors.newFixedThreadPool(threadPoolNum);
		   //计数器
		   CountDownLatch rowLatch = new CountDownLatch(1);
		   CountDownLatch exceLatch = new CountDownLatch(10); 
		   int totalCount = 0;
		      for(int i =0 ; i < 10 ;i++) {
		    	  final int page = i;
		    	  service.submit(new Runnable() {
					
					@Override
					public void run() {
						
						try {
							List<Customer> customerList=new ArrayList<Customer>();
							   Map<String,Object> finalMap;
							   //List<Integer> list = new ArrayList<Integer>(200);
						       //for1:for(int r=0;r<sheet.getLastRowNum()/10;r++){
							   rowLatch.await();
							   for1:for(int r=0;r<sheet.getLastRowNum()/10;r++){
						    	   //list.add(r);
						    	   
						    	   String rowMessage = "";
						           Row row = sheet.getRow(r+page*sheet.getLastRowNum()/10);//平均分配
						           if (row == null){
						        	   continue;
						           }
						           Customer customer = new Customer();
						        
						           for(int c = 0; c <totalCells; c++){
						               Cell cell = row.getCell(c);
						               if (null != cell){
						            	   switch (c) {
						            	   case 0:
						            		   //id
						            		   if(StringUtils.isNoneBlank(String.valueOf(cell.getNumericCellValue()))) {
						            			  //customer.setId(Long.valueOf(cell.getNumericCellValue()));
						            			 double dou = cell.getNumericCellValue();
						            			 Long id = new Double(dou).longValue();
						            			 if(customerRepository.exists(id)) {
						            				 continue for1;
						            			 }
						            			 customer.setId(id);
						            		   }
						            		   break;
						            	   case 1:
						            		   //create_time
						            		   finalMap = getValue(cell, ReturnType.dateValue);
						            		   if(finalMap.get("type") == null) {
						            			   customer.setCreateTime(null);
						            		   }else {
						            			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						            			   try {
						            				String date = finalMap.get("value").toString();
							            			Date d = sdf.parse(date);
													customer.setCreateTime(d);
												} catch (ParseException e) {
													e.printStackTrace();
												}
						            		   }
												break;
						            	   case 2:
						            		   //update_time
						            		   finalMap = getValue(cell, ReturnType.dateValue);
						            		   if(finalMap.get("type") == null) {
						            			   customer.setUpdateTime(null);
						            		   }else {
						            			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						            			   try {
						            				String date = finalMap.get("value").toString();
						            				Date d = sdf.parse(date);
													customer.setUpdateTime(d);
												} catch (ParseException e) {
													e.printStackTrace();
												}
						            		   }
												break;
						            	   case 3:
						            		   //license
						            		   finalMap = getValue(cell, ReturnType.stringValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   customer.setLicense((String) finalMap.get("value"));
						            		   }
												break;
						            	   case 4:
						            		   //name
						            		   finalMap = getValue(cell, ReturnType.stringValue);
						            		   if(finalMap.get("type") == null) {
						            			   customer.setName(null);
						            		   }else {
						            			   customer.setName((String) finalMap.get("value"));
						            		   }
												break;
						            	   case 5:
						            		   //has_senior
						            		   finalMap = getValue(cell, ReturnType.booleanValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   customer.setHasSenior((boolean) finalMap.get("value"));
						            		   }
												break;
						            	   case 6:
						            		   //is_old_custom
						            		   finalMap = getValue(cell, ReturnType.booleanValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   customer.setOldCustom((boolean) finalMap.get("value"));
						            		   }
												break;
						            	   case 7:
						            		   //kva_id
						            		   finalMap = getValue(cell, ReturnType.longValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   long kva_id = (long) finalMap.get("value");
						            			   Optional<Code> okva = codeRepository.findById(kva_id);
						            			   if(okva.isPresent()) {
						            				   customer.setKva(okva.get());
						            			   }
						            		   }
												break;
						            	   case 8:
						            		   //shop_id
						            		   finalMap = getValue(cell, ReturnType.longValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   long shop_id = (long) finalMap.get("value");
						            			   Optional<Shop> os = shopRepository.findById(shop_id);
						            			   if(os.isPresent()) {
						            				   customer.setShop(os.get());
						            			   }
						            		   }
												break;
						            	   case 9:
						            		   //form_customer_id
						            		   finalMap = getValue(cell, ReturnType.longValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   long form_customer_id = (long) finalMap.get("value");
						            			   Optional<Customer> oc = customerRepository.findById(form_customer_id);
						            			   if(oc.isPresent()) {
						            				   customer.setFromCustomer(oc.get());
						            			   }
						            		   }
												break;
						            	   case 10:
						            		   //form_user_id
						            		   finalMap = getValue(cell, ReturnType.longValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   long form_user_id = (long) finalMap.get("value");
						            			   Optional<User> ou = userRepository.findById(form_user_id);
						            			   if(ou.isPresent()) {
						            				   customer.setFromUser(ou.get());
						            			   }
						            		   }
												break;
						            	   case 11:
						            		   //type_code_id
						            		   finalMap = getValue(cell, ReturnType.longValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   long type_code_id = (long) finalMap.get("value");
						            			   Optional<Code> oc = codeRepository.findById(type_code_id);
						            			   if(oc.isPresent()) {
						            				   customer.setType(oc.get());
						            			   }
						            		   }
												break;
						            	   case 12:
						            		   //auth_state_code_id
						            		   finalMap = getValue(cell, ReturnType.longValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   long auth_state_code_id = (long) finalMap.get("value");
						            			   Optional<Code> oa = codeRepository.findById(auth_state_code_id);
						            			   if(oa.isPresent()) {
						            				   customer.setAuthState(oa.get());
						            			   }
						            		   }
												break;
						            	   case 13:
						            		   //reserve
						            		   finalMap = getValue(cell, ReturnType.stringValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   customer.setReserve((String) finalMap.get("value"));
						            		   }
						            		 
												break;
						            	   case 14:
						            		   //provider_id
						            		   if(cell.getNumericCellValue() != 0) {
						            			   //customer.set
						            		   }
												break;
						            	   case 15:
						            		   //distribution_num_code_id
						            		   finalMap = getValue(cell, ReturnType.longValue);
						            		   if(finalMap.get("type") == null) {
						            		   }else {
						            			   long distribution_num_code_id = (long) finalMap.get("value");
						            			   Optional<Code> od = codeRepository.findById(distribution_num_code_id);
						            			   if(od.isPresent()) {
						            				   customer.setDistributionNum(od.get());
						            			   }
						            		   }
												break;
						            	   	default:
						            	   		rowMessage += "第"+(c+1)+"列数据有问题，请仔细检查；";
						            	   		break;
										}
						           }
						           
						       }
						           
						      if(!StringUtils.isEmpty(rowMessage)){ 
						        	errorMsg += br+"第"+(r+1)+"行，"+rowMessage;
						        	return ResponseResult.getResult(false, errorMsg);
						      }
						      customerList.add(customer);
						           
						       if(customer != null) {
						    	   customerList.add(customer);
						    	   customerRepository.save(customer);
						       }
						       
						   }
							   List<Customer> returnList = new ArrayList<Customer>();
							   if(sheet.getLastRowNum()/10 == list.size()) {
						    	   if(customerList.size() > 0) {
						    		   returnList = customerRepository.save(customerList);
						    	   }
						       }
							   
							   //totalCount =  totalCount+returnList.size();
						}catch(InterruptedException e){
							System.out.println("!!!!!!");
							e.printStackTrace();
						}finally {
							exceLatch.countDown();
						}
						   
					}
				});
		      }
		      rowLatch.countDown();

	      try {
	    	  removeFile(tempFile);
	      } catch (Exception e) {
	      }
	      service.shutdown();
	      return ResponseResult.getResult(true,String.valueOf(totalCount));
	  }
	  
	  public int save(List<Customer> customerList) {
		 
	      
		  if(customerList.size() > 0){
	    	   List<Customer> cuts = customerRepository.save(customerList);
	    	   return cuts.size();
	       }
		  return 0;
		  
	  }
	  
	  public void removeFile(File tempFile) {
	       if(tempFile.exists()){
	    	   tempFile.delete();
	       }
	  }
	  
	  *//**
	 * @param cell 单元格
	 * @param type 想要的数据类型
	 * @return
	 *//*
	public Map<String,Object> getValue(Cell cell,ReturnType type) {
		  Map<String,Object> map = new HashMap<>();
		  String value = "";
		  switch (cell.getCellType()) {
		    case HSSFCell.CELL_TYPE_NUMERIC: // 数字
		        if (HSSFDateUtil.isCellDateFormatted(cell)) {      
		           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		           value=sdf.format(HSSFDateUtil.getJavaDate(cell.
		           getNumericCellValue())).toString();
		           map = returnTypeValue(value,type);
		             break;
		         } else {
		             value = new DecimalFormat("0").format(cell.getNumericCellValue());
		             map = returnTypeValue(value, type);
		         }
		        break;
		    case HSSFCell.CELL_TYPE_STRING: // 字符串
		        value = cell.getStringCellValue();
		        map = returnTypeValue(value,type);
		        break;
		    case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
		        value = cell.getBooleanCellValue() + "";
		        map = returnTypeValue(value,type);
		        break;
		    case HSSFCell.CELL_TYPE_FORMULA: // 公式
		        value = cell.getCellFormula() + "";
		        map = returnTypeValue(value,type);
		        break;
		    case HSSFCell.CELL_TYPE_BLANK: // 空值
		        value = "";
		        map = returnTypeValue(value,type);
		        break;
		    case HSSFCell.CELL_TYPE_ERROR: // 故障
		        value = "非法字符";
		        map = returnTypeValue(value,ReturnType.nullValue);
		        break;
		    default:
		        value = "未知类型";
		        map = returnTypeValue(value, ReturnType.nullValue);
		        break;
		}
		  
	return map;
	}
	  
	*//**
	 * @param s 原始值
	 * @param hssfCell 最终想要的类型
	 * @return
	 *//*
	public Map<String,Object> returnTypeValue(String s,ReturnType type) {
		Map<String,Object> returnMap = new HashMap<>();
		if(StringUtils.isNoneBlank(s)) {
			switch (type) {
			case stringValue:
				returnMap.put("type", ReturnType.stringValue);
				returnMap.put("value", s);
				break;
			case longValue:
				Long value = Long.valueOf(s);
				returnMap.put("type", ReturnType.longValue);
				returnMap.put("value", value);
				break;
			case intValue:
				Integer value1 = Integer.valueOf(s);
				returnMap.put("type", ReturnType.intValue);
				returnMap.put("value", value1);
				break;
			case dateValue:
				returnMap.put("type", ReturnType.dateValue);
				returnMap.put("value", s);
				break;
			case booleanValue:
				int i = Integer.valueOf(s);
				if(i == 0) {
					returnMap.put("type", ReturnType.booleanValue);
					returnMap.put("value", true);
				}else if(i == 1) {
					returnMap.put("type", ReturnType.booleanValue);
					returnMap.put("value", false);
				}else {
					returnMap.put("type", null);
					returnMap.put("value", null);
				}
				break;
			 case nullValue:
				 returnMap.put("type", null);
				returnMap.put("value", null);
				break;
			 default:
				 returnMap.put("type", null);
				returnMap.put("value", null);
				break;
			}
		}else {
			returnMap.put("type", null);
			returnMap.put("value", null);
		}
		return returnMap;
	}
	
	enum ReturnType{
		stringValue,longValue,intValue,dateValue,booleanValue,nullValue;
	}
	
	public static void main(String[] args) {
		 try {
			Connection connection = DriverManager.
					getConnection("jdbc:mysql://127.0.0.1:3306/pdyw?"
							+ "useUnicode=true&characterEncoding=utf-8&autoReconnect=true"
							+ "&useSSL=false", //这个必须得
							"root", 
							"root");
			connection.setAutoCommit(false);
			String sql = "insert into customer (id,name,has_senior,is_old_custom) values(?,?,?,?)";
            PreparedStatement prepareStatement = connection.prepareStatement(sql);
            prepareStatement.setLong(1, 1L);//.setLong(1, 1L);
            prepareStatement.setString(2, "张三");
            prepareStatement.setBoolean(3, true);
            prepareStatement.setBoolean(4, true);
            prepareStatement.addBatch();
            
            prepareStatement.setLong(1, 2L);//.setLong(1, 1L);
            prepareStatement.setString(2, "张三2");
            prepareStatement.setBoolean(3, true);
            prepareStatement.setBoolean(4, true);
            prepareStatement.addBatch();
            
            prepareStatement.setLong(1, 3L);//.setLong(1, 1L);
            prepareStatement.setString(2, "张三3");
            prepareStatement.setBoolean(3, true);
            prepareStatement.setBoolean(4, true);
            prepareStatement.addBatch();
            
            prepareStatement.executeBatch();
            //prepareStatement.execute();
            connection.commit();
            prepareStatement.clearBatch();
            prepareStatement.close();
            connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	
}
