package com.yuer.dbutils.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * XML工具类，调用Dom4j的方法解析xml<br>
 * 用法:
 * <pre>
 * Document document = XMLUtil.parse( this.getClass().getResource( "src.xml" ) );
 * File f = new File( "output.xml" );
 * OutputStream out = new FileOutputStream( f );
 * XMLUtil.write( document, out, OutputFormat.createPrettyPrint() );
 * XMLUtil.write( document, out, OutputFormat.createCompactFormat() );
 * </pre>
 * @author <a href="mailto:joe.dengtao@gmail.com">DengTao</a>
 * @version 1.0
 * @since 1.0
 */
public class XMLUtils {

	/**
	 * 将输入的xml字符串转换成XML Document对象
	 * 
	 * @since 1.0
	 * @param xmlString
	 * @return 返回转换成功的Document对象
	 * @throws DocumentException
	 */
	public static Document parse(String xmlString) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlString);
		return doc;
	}

	/**
	 * 将输入的URL的文件内容转换成XML Document对象
	 * 
	 * @since 1.0
	 * @param url 
	 * @return 返回转换成功的Document对象
	 * @throws DocumentException
	 */
	public static Document parse(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(url);
		return doc;
	}

	/**
	 * 将 Document 对象写入到输出流
	 * @param document
	 * @param out
	 * @param format OutputFormat.createPrettyPrint()/OutputFormat.createCompactFormat()
	 * @throws IOException
	 * @since 1.0
	 */
	public static void write(Document document, OutputStream out,
			OutputFormat format) throws IOException {
		XMLWriter writer = new XMLWriter(out, format);
		
		writer.write(document);
	}
	
}
