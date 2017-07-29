package com.jiang.service;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class WaterMarkService {

	
	public static final String MARK_TEXT = "Byron";
	public static final String FONT_NAME = "微软雅黑";
	public static final int FONT_STYLE = Font.BOLD;	//黑体
	public static final int FONT_SIZE = 20;			//文字大小
	public static final Color FONT_COLOR = Color.red;//文字颜色
	
	public static final int X = 10;  //文字坐标
	public static final int Y = 10;
	
	public static float ALPHA = 0F; //文字水印透明度 
	
	public static final String LOGO = "logo.gif";	//图片形式的水印
	
	
	/**
	 * 图片文件上传
	 * @param image
	 * @param imageFileName
	 * @param uploadPath
	 * @param realUploadPath
	 * @return
	 */
	public String uploadImage(File image, String imageFileName, String uploadPath, String realUploadPath){
		InputStream is = null;	//输入流
		OutputStream os = null;	//输出流
		
		try {
			is = new FileInputStream(image);	//创建输入流对象，指向上传图片对象
			os = new FileOutputStream(realUploadPath+"/"+imageFileName);	//创建输出流对象，指向最终要保存的目标文件对象
			
			byte[] buffer = new byte[1024];		
			int len = 0;
			
			while((len = is.read(buffer))>0){
				os.write(buffer);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(is!=null){
				try{
					is.close();	//关闭输入流
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			if(os!=null){
				try{
					os.close();	//关闭输出流
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			
		}
		
		return uploadPath+"/"+imageFileName;	//相对路径
		
	}
	
	
	
	/**
	 * 多个图片水印
	 * @param image
	 * @param imageFileName
	 * @param uploadPath
	 * @param realUploadPath
	 * @return
	 */
	public String watermark(File image, String imageFileName, String uploadPath, String realUploadPath) {

		String logoFileName = "logo_"+imageFileName;	//定义目标文件输出的名称
		OutputStream os = null;
		
		try {
			//1 创建图片缓存对象
			Image image2 = ImageIO.read(image);
			
			int width = image2.getWidth(null);
			int height = image2.getHeight(null);
			
			BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			
			//2 创建Java绘图工具对象
			Graphics2D g = bufferedImage.createGraphics();

			//3 使用绘图工具对象将原图绘制到缓存图片对象
			g.drawImage(image2, 0, 0, width, height, null);
			
			//4 使用绘图工具对象将水印（文字/图片）绘制到缓存图片
			g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
			g.setColor(FONT_COLOR);
			
			int width1 = FONT_SIZE*getTextLength(MARK_TEXT);//文字水印宽度
			int height1= FONT_SIZE;							//文字水印高度
			
			//透明度的设置
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
			
			//旋转图片(30°)
			g.rotate(Math.toRadians(30),bufferedImage.getWidth()/2,bufferedImage.getHeight()/2);
			
			//设置水印的坐标
			int x= -width/2;
			int y= -height/2;
			
			while(x < width*1.5){
				y = -height/2;
				while(y < height*1.5){
					g.drawString(MARK_TEXT,x,y);
					y += height1 + 50;
				}
				x += width1 + 50;	//水印之间的间隔设为50
			}
			
			//释放工具
			g.dispose();
			
			//最终目标文件
			os = new FileOutputStream(realUploadPath+"/"+logoFileName);	
			
			//5 创建图像文件编码工具类
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			
			//6 使用图像编码工具类，输出缓存图像到目标文件
			en.encode(bufferedImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return uploadPath+"/"+logoFileName;
	}

	//处理文字水印的中英文字符的宽度转换
	public int getTextLength(String text){
		int length = text.length();
		for(int i=0;i<text.length();i++){
			String s = String.valueOf(text.charAt(i));
			if(s.getBytes().length>1){	//中文字符
				length++;
			}
		}
		length = length%2 == 0?length/2:length/2+1;  //中文和英文字符的转换
		return length;
	}

	
	
	/**
	 * 多个文字水印
	 * @param image
	 * @param imageFileName
	 * @param uploadPath
	 * @param realUploadPath
	 * @return
	 */
	public String watermark2(File image, String imageFileName, String uploadPath, String realUploadPath) {

		String logoFileName = "logo_"+imageFileName;	//定义目标文件输出的名称
		OutputStream os = null;
		
		try {
			//1 创建图片缓存对象
			Image image2 = ImageIO.read(image);
			
			int width = image2.getWidth(null);
			int height = image2.getHeight(null);
			
			BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			
			//2 创建Java绘图工具对象
			Graphics2D g = bufferedImage.createGraphics();

			//3 使用绘图工具对象将原图绘制到缓存图片对象
			g.drawImage(image2, 0, 0, width, height, null);
			
			//4 使用绘图工具对象将水印（文字/图片）绘制到缓存图片
			g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
			g.setColor(FONT_COLOR);
			
			int width1 = FONT_SIZE*getTextLength(MARK_TEXT);//文字水印宽度
			int height1= FONT_SIZE;							//文字水印高度
			
			//透明度的设置
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));
			
			//旋转图片(30°)
			g.rotate(Math.toRadians(30),bufferedImage.getWidth()/2,bufferedImage.getHeight()/2);
			
			//设置水印的坐标
			int x= -width/2;
			int y= -height/2;
			
			while(x < width*1.5){
				y = -height/2;
				while(y < height*1.5){
					g.drawString(MARK_TEXT,x,y);
					y += height1 + 50;
				}
				x += width1 + 50;	//水印之间的间隔设为50
			}
			
			//释放工具
			g.dispose();
			
			//最终目标文件
			os = new FileOutputStream(realUploadPath+"/"+logoFileName);	
			
			//5 创建图像文件编码工具类
			JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(os);
			
			//6 使用图像编码工具类，输出缓存图像到目标文件
			en.encode(bufferedImage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return uploadPath+"/"+logoFileName;
	}

	
}
