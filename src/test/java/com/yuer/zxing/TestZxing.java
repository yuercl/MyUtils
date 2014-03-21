package com.yuer.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class TestZxing {

	/**
	 * 
	 */
	public TestZxing() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestZxing t = new TestZxing();
		t.encode();
		t.decode();
	}

	// 编码
	public void encode() {
		try {
			String str = "CN:男;COP:公司;ZW:职务";// 二维码内容
			String path = "test.png";
			BitMatrix byteMatrix;
			byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("utf-8"), "iso-8859-1"), BarcodeFormat.QR_CODE, 200, 200);
			File file = new File(path);

			MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 解码
	public void decode() {
		try {
			String imgPath = "test.png";
			// String imgPath =
			// getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
			// imgPath = imgPath + "hwy.png";
			File file = new File(imgPath);
			BufferedImage image;
			try {
				image = ImageIO.read(file);
				if (image == null) {
					System.out.println("Could not decode image");
				}
				System.out.println(image.toString());
				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
				hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
				Result result = new MultiFormatReader().decode(bitmap, hints);
				String resultStr = result.getText();
				System.out.println(resultStr);
			} catch (IOException ioe) {
				System.out.println(ioe.toString());
			} catch (ReaderException re) {
				System.out.println(re.toString());
			}
		} catch (Exception ex) {

		}
	}

}
