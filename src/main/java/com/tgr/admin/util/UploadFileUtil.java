package com.tgr.admin.util;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author liuzeke
 * @date 2016-6-8
 */
public abstract class UploadFileUtil {

	private final static int MAX_FILE_SIZE = 5000 * 1024;
	private final static int MAX_MEM_SIZE = 5000 * 1024;
	private final static String FILE_PATH = "./../temp";

	/**
	 * @author liuzeke
	 * @dateTime 2016-6-8 上午10:49:49
	 * @description 文件上传
	 * @param request
	 * @return
	 */
	public static RetMsg uploadFile(HttpServletRequest request) {

		if (request == null)
			return RetMsg.FAILED;
		if (request.getContentType().indexOf("multipart/form-data") < 0)
			return RetMsg.FAILED;

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MAX_MEM_SIZE);
		factory.setRepository(new File(FILE_PATH));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(MAX_FILE_SIZE);

		/** 上传文件解析 */
		List<FileItem> fileItems = null;
		try {
			fileItems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
			return RetMsg.FAILED;
		}
		Iterator<FileItem> item = fileItems.iterator();
		FileItem fi = null;
		File file = null;
		while (item.hasNext()) {
			fi = item.next();
			if (!fi.isFormField()) {
				String fileName = fi.getName();
				if (fileName.lastIndexOf("\\") >= 0)
					file = new File(FILE_PATH, fileName.substring(fileName.lastIndexOf("\\")));
				else
					file = new File(FILE_PATH, fileName.substring(fileName.lastIndexOf("\\") + 1));
				try {
					fi.write(file);
				} catch (Exception e) {
					e.printStackTrace();
					return RetMsg.FAILED;
				}
			}
		}
		RetMsg rm = RetMsg.SUCCESS;
		rm.setFile(file);
		return rm;
	}

	public enum RetMsg {

		SUCCESS(1, "Upload successfully."), FAILED(0, "Upload failed.");

		private int type;
		private String msg;
		private File file;

		RetMsg(int type, String msg) {
			this.type = type;
			this.msg = msg;
		}

		RetMsg(File file) {
			this.file = file;
		}

		public int getType() {
			return type;
		}

		public String getMsg() {
			return msg;
		}

		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}
	}
}
