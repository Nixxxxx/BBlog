package com.jiang.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成和读取
 * @author JH
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class QRCodeUtil {

	/**
	 * 生成二维码
	 * @param format 图片格式
	 * @param content 二维码内容
	 * @param path 文件路径
	 * @param width 宽度
	 * @param height 高度
	 */
	public static String createQRCode(String format, String content, String path, int width, int height){
		
		//定义二维码参数
		HashMap hints = new HashMap();
		//设置编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		//设置容错等级，等级越高，容量越小
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		//设置边距
		hints.put(EncodeHintType.MARGIN, 2);
		//防止插入logo黑白
        MatrixToImageConfig config = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
		try {
			BitMatrix bitmatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			Path file = new File(path).toPath();	//文件路径  "E:/img.png"
			MatrixToImageWriter.writeToPath(bitmatrix, format, file, config); //format 图片格式（png、jpg等）	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	/**
	 * 二维码添加中心图片
	 * @param path
	 * @param format
	 * @param file
	 */
	public static void pic(String path, String format) {
			//读取二维码图片
			BufferedImage twodimensioncode;
			try {
				twodimensioncode = ImageIO.read(new File(path));
				//获取画笔
				Graphics2D g = twodimensioncode.createGraphics(); 
				//读取logo图片 
				BufferedImage logo = ImageIO.read(new File("E:/12.jpg")); 
				//加入的log图片 //设置二维码大小，太大，会覆盖二维码，此处20% 
				int logoWidth = logo.getWidth() > twodimensioncode.getWidth()*2 /10 ? (twodimensioncode.getWidth()*2 /10) : logo.getWidth(); 
				int logoHeight = logo.getHeight() > twodimensioncode.getHeight()*2 /10 ? (twodimensioncode.getHeight()*2 /10) : logo.getHeight(); 
				//设置logo图片放置位置 //中心
				int x = (twodimensioncode.getWidth() - logoWidth) / 2;
				int y = (twodimensioncode.getHeight() - logoHeight) / 2;
				//开始合并绘制图片
				g.drawImage(logo, x, y, logoWidth, logoHeight, null);
				g.drawRoundRect(x, y, logoWidth, logoHeight, 15 ,15);
				//logo边框大小 
				g.setStroke(new BasicStroke(2));
				//logo边框颜色
				g.setColor(Color.WHITE);
				g.drawRect(x, y, logoWidth, logoHeight);
				g.dispose(); 
				logo.flush(); twodimensioncode.flush();
				ImageIO.write(twodimensioncode, format, new File("E:/img2.png"));
			} catch (IOException e) {
				e.printStackTrace();
			} 
	}
	
	public static void main(String[] args) {
		String path = createQRCode("png", "ssassa", "E:/img.png", 400, 400);
		pic(path, "png");
	}
	
	/**
	 * 读取二维码
	 */
	public static String readQRCode(String path){
		try{
			MultiFormatReader formatReader = new MultiFormatReader();
			File file = new File(path);  //文件路径  "E:/img.png"
			BufferedImage image = ImageIO.read(file);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
			
			HashMap hints = new HashMap();						
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");		//设置支持中文编码
			
			Result result = formatReader.decode(binaryBitmap, hints);
			
//			System.out.println("解析结果"+result.toString());
//			System.out.println("二维码格式"+result.getBarcodeFormat());
//			System.out.println("二维码文本内容"+result.getText());
			return result.toString();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 条形码
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public void encode(String contents, int width, int height, String imgPath) {
        int codeWidth = 3 + // start guard
                (7 * 6) + // left bars
                5 + // middle guard
                (7 * 6) + // right bars
                3; // end guard
        codeWidth = Math.max(codeWidth, width);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.EAN_13, codeWidth, height, null);
 
            MatrixToImageWriter.writeToStream(bitMatrix, "png",
                    new FileOutputStream(imgPath));
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    /**
     * 解析条形码
     * 
     * @param imgPath
     * @return
     */
    public String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
 
            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
}
