package com.tgr.admin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

@SuppressWarnings("all")
public class Code{
	/**
	 * 解密
	 */
	@SuppressWarnings("unchecked")
	public <J> J decode(byte[] bytes) {
		if (bytes == null || bytes.length == 0)
			return null;

		try {
			ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(bytes));
			Object object = objIn.readObject();
			return (J) object;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加密
	 */
	public <J> byte[] encode(J object) {
		if (object == null)
			return null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectOut = null;
		try {
			objectOut = new ObjectOutputStream(out);
			objectOut.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return out.toByteArray();
	}
}
