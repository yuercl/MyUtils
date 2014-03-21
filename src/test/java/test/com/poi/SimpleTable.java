package test.com.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTable {
	public static Logger logger = LoggerFactory.getLogger(SimpleTable.class);

	public void createParagraph(XWPFDocument doc, String str) {
		XWPFParagraph p1 = doc.createParagraph();
		p1.setAlignment(ParagraphAlignment.LEFT);
		p1.setVerticalAlignment(TextAlignment.TOP);

		XWPFRun r1 = p1.createRun();
		r1.setBold(true);
		r1.setText(str);
		r1.setFontFamily("黑体");
		r1.setUnderline(UnderlinePatterns.NONE);
		r1.setTextPosition(50);
	}

	public void createStyledTable(XWPFDocument doc, List<String> headList, List<ArrayList<String>> bodyList) throws Exception {
		if (headList == null || bodyList == null) {
			logger.info("none content found...");
			return;
		}
		if (headList.size() != bodyList.get(0).size()) {
			logger.info("error col size");
			return;
		}
		// Create a new table with 6 rows and 3 columns
		int nRows = bodyList.size();
		int nCols = headList.size();
		XWPFTable table = doc.createTable(nRows, nCols);

		// Set the table style. If the style is not defined, the table style
		// will become "Normal".
		CTTblPr tblPr = table.getCTTbl().getTblPr();
		CTString styleStr = tblPr.addNewTblStyle();
		styleStr.setVal("StyledTable");

		List<XWPFTableRow> rows = table.getRows();
		int rowCt = 0;
		int colCt = 0;
		for (XWPFTableRow row : rows) {
			// get table row properties (trPr)
			CTTrPr trPr = row.getCtRow().addNewTrPr();
			// set row height; units = twentieth of a point, 360 = 0.25"
			CTHeight ht = trPr.addNewTrHeight();
			ht.setVal(BigInteger.valueOf(360));

			List<XWPFTableCell> cells = row.getTableCells();
			for (XWPFTableCell cell : cells) {
				// get a table cell properties element (tcPr)
				CTTcPr tcpr = cell.getCTTc().addNewTcPr();
				// set vertical alignment to "center"
				CTVerticalJc va = tcpr.addNewVAlign();
				va.setVal(STVerticalJc.CENTER);

				// create cell color element
				CTShd ctshd = tcpr.addNewShd();
				ctshd.setColor("auto");
				ctshd.setVal(STShd.CLEAR);
				if (rowCt == 0) {// header row
					ctshd.setFill("A7BFDE");
				} else if (rowCt % 2 == 0) {// even row
					ctshd.setFill("D3DFEE");
				} else {// odd row
					ctshd.setFill("EDF2F8");
				}

				// get 1st paragraph in cell's paragraph list
				XWPFParagraph para = cell.getParagraphs().get(0);
				// create a run to contain the content
				XWPFRun rh = para.createRun();
				// style cell as desired
				if (colCt == nCols - 1) {
					// last column is 10pt Courier
					rh.setFontSize(10);
					rh.setFontFamily("Courier");
				}
				if (rowCt == 0) {// header row
					rh.setText(headList.get(colCt));
					rh.setBold(true);
					para.setAlignment(ParagraphAlignment.CENTER);
				} else if (rowCt % 2 == 0) {// even row
					rh.setText(bodyList.get(rowCt).get(colCt));
					para.setAlignment(ParagraphAlignment.LEFT);
				} else {// odd row
					rh.setText(bodyList.get(rowCt).get(colCt));
					para.setAlignment(ParagraphAlignment.LEFT);
				}
				colCt++;
			} // end for cell
			colCt = 0;
			rowCt++;
		} // end for row
	}

	public void write(XWPFDocument doc) throws IOException {
		FileOutputStream out = new FileOutputStream("styledTable.docx");
		doc.write(out);
		out.close();
	}

}
