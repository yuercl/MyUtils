package com.yuer.pdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfWriter;

public class FontSelectionExample {

	/** Some text */
	public static final String TEXT = "These are the protagonists in 'Hero', a movie by Zhang Yimou:\n"
			+ "\u7121\u540d (Nameless), \u6b98\u528d (Broken Sword), " + "\u98db\u96ea (Flying Snow), \u5982\u6708 (Moon), "
			+ "\u79e6\u738b (the King), and \u9577\u7a7a (Sky)." + "中华人民共和国万岁劳动人民是光荣的，岁月峥嵘，百舸争流";

	/**
	 * Creates a PDF document.
	 */
	public void createPdf(String filename) throws IOException, DocumentException {
		// step 1
		Document document = new Document(PageSize.A4);
		// step 2
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		// step 3: we open the document
		document.open();
		// step 4:
		FontSelector selector = new FontSelector();
		selector.addFont(FontFactory.getFont(FontFactory.TIMES_ROMAN, 12));
		// selector.addFont(FontFactory.getFont("MSung-Light","UniCNS-UCS2-H",  BaseFont.NOT_EMBEDDED));
		selector.addFont(FontFactory.getFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));

		Phrase ph = selector.process(TEXT);
		document.add(new Paragraph(ph));
		// step 5: we close the document
		document.close();
	}

	/**
	 * Main method.
	 * 
	 * @param args
	 *            no arguments needed
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, DocumentException {
		new FontSelectionExample().createPdf("good.pdf");
	}
}