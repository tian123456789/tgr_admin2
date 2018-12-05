package com.tgr.admin.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xalan.internal.res.XSLMessages;
import com.sun.org.apache.xerces.internal.xs.XSLoader;

/**
 * <br> 传统 DOM 方式解析xml
 */
@SuppressWarnings("all")
public class XMLUtil {
	
	//得到 DOM 解析器的工厂实例
	private static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	//然后从 DOM 工厂获得 DOM 解析器
	public static DocumentBuilder builder = null;
	
	static{
		try {
			//然后从 DOM 工厂获得 DOM 解析器
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	//创建类的单例模式
	private static XMLUtil xmlUtil = new XMLUtil();
	private XMLUtil(){}
	public static XMLUtil getInstance(){ return xmlUtil;}
	
	/**
	 * 载入文件中xml 解析成Document
	 * @param file
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document getDocument(File file) throws SAXException, IOException{
		//解析 XML 文档的输入流，得到一个 Document
		Document parse = builder.parse(file);
		return parse;
	}
	
	/**
	 * 载入 xml 字符串解析成  Document
	 * @param xmlDoc
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public Document getDocument(String xmlDoc) throws SAXException, IOException{
		//把字符串转化为 StringReader
		StringReader read = new StringReader(xmlDoc);
		//创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
		InputSource source = new InputSource(read);
		//解析 XML 文档的输入流，得到一个 Document
		Document parse = builder.parse(source);
		return parse;
	}
	
	/**
	 * 获取 XML 文档的根节点
	 * @param file
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public Element getElementRoot(File file) throws SAXException, IOException{
		Document document = getDocument(file);
		Element documentElement = document.getDocumentElement();
		return documentElement;
	}
	
	/**
	 * 获取 XML 文档的根节点
	 * @param xmlDoc
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public Element getElementRoot(String xmlDoc) throws SAXException, IOException{
		Document document = getDocument(xmlDoc);
		Element documentElement = document.getDocumentElement();
		return documentElement;
	}
	
	/**
	 * 解析例子
	 * @param args
	 */
	public static void main(String[] args) {
//		<?xml version="1.0" encoding="UTF-8"?>
//		<employees>
//		<employee>
//		<name>ddviplinux</name>
//		<sex>m</sex>
//		<age>30</age>
//		</employee>
//		</employees>
		File file = new File("C:\\Users\\C146\\Desktop\\web.xml");
		try {
			Element elementRoot = XMLUtil.getInstance().getElementRoot(file);
			System.out.println(elementRoot);
			NodeList childNodes = elementRoot.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node item = childNodes.item(i);
				NodeList childNodes2 = item.getChildNodes();
				for (int j = 0; j < childNodes2.getLength(); j++) {
					Node item2 = childNodes2.item(j);
					if(item2 instanceof Element){
						System.out.println(item2.getNodeName());
					}
				}
			}
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}