/**
 * project name: utils
 * created at 2013-7-13 - 下午5:06:50
 * author:yuer
 * email:yuerguang.cl@gmail.com
 */
package com.yuer.poi;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuer
 * 
 */
public class SimpleTableTest {
	public static Logger logger = LoggerFactory.getLogger(SimpleTableTest.class);

	@Test
	public void testSimpleTable() {
		try {
			SimpleTable simpleTable = new SimpleTable();
			List<String> head = new ArrayList<String>();
			List<ArrayList<String>> bodyList = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < 10; i++) {
				head.add("head" + i);
			}
			logger.info("table's head column = " + head.size());
			for (int row = 0; row < 10; row++) {
				ArrayList<String> tempList = new ArrayList<String>();
				for (int col = 0; col < 10; col++) {
					tempList.add(row + " x " + col);
				}
				bodyList.add(tempList);
			}
			logger.info("table's body items' size = " + bodyList.size());
			XWPFDocument doc = new XWPFDocument();
			String str = "第一章";
			simpleTable.createParagraph(doc, str);
			simpleTable.createStyledTable(doc, head, bodyList);
			str = "第二章";
			simpleTable.createParagraph(doc, str);
			simpleTable.createStyledTable(doc, head, bodyList);
			simpleTable.write(doc);
		} catch (Exception e) {
			logger.error("Error trying to create styled table.", e);
		}
	}
}
