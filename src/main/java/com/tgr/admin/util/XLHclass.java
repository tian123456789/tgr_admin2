package com.tgr.admin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("all")
public class XLHclass {
	
	/**
	 * 反序列化
	 * 
	 * @param bytes
	 * @return
	 */
	public static <T> T deserialization(byte[] bytes) {
		if (bytes == null || bytes.length == 0)
			return null;

		try {
			ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object object = objIn.readObject();
			return (T) object;
		} catch (Exception e) {
			System.err.println("%%%% ERROR 反序列化报错  %%%%");
		}
		return null;
	}

	/**
	 * 序列化
	 * @param object
	 * @return
	 */
	public static <T> byte[] serialized(T object) {
		if (object == null)
			return null;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(object);
		} catch (IOException e) {
			System.err.println("%%%% ERROR 序列化报错  %%%%");
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				System.err.println("%%%% ERROR 序列化报错  %%%%");
			}
		}
		return out.toByteArray();
	}
}
