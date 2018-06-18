package web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CheckcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(
			HttpServletRequest request, 
			HttpServletResponse response) 
		throws ServletException, IOException {
		
		System.out.println("checkcode");
		
		/**
		 * 1.生成图片
		 */
		//创建画布
		BufferedImage image=new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
		//获得画笔
		Graphics g=image.getGraphics();
		//给笔设置颜色
		g.setColor(new Color(255,255,255));
		//设置画布颜色
		g.fillRect(0, 0, 80, 30);
		//重新给笔设置随机颜色
		Random r=new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//设置字体
		g.setFont(new Font(null,Font.ITALIC,24));
		//生成一个随机数
		String number=getNumber(4);
		//将验证码绑定到session对象上
		HttpSession session=request.getSession();
		session.setAttribute("number", number);
		//在图片上绘制随机数
		g.drawString(number, 5, 25);
		//添加干扰线
		for(int i=0;i<10;i++) {
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		/**
		 * 2.压缩图片并输出
		 */
		//告诉浏览器，服务器返回的是一张图片
		response.setContentType("image/jpeg");
		//获得字节输出流
		OutputStream output=response.getOutputStream();
		//将原始图片按照指定格式jepg进行压缩，输出。
		javax.imageio.ImageIO.write(image, "jpeg", output);
		output.close();
	}
	/*
	 * 长度为size个字符，随机从A~Z,0~9中选取
	 */
	private String getNumber(int size) {
		String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ"+"0123456789";
		String number="";
		Random r=new Random();
		for(int i=0;i<size;i++) {
			number+=chars.charAt(r.nextInt(chars.length()));
		}
		return number;
	}
}
