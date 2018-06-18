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
		 * 1.����ͼƬ
		 */
		//��������
		BufferedImage image=new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
		//��û���
		Graphics g=image.getGraphics();
		//����������ɫ
		g.setColor(new Color(255,255,255));
		//���û�����ɫ
		g.fillRect(0, 0, 80, 30);
		//���¸������������ɫ
		Random r=new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		//��������
		g.setFont(new Font(null,Font.ITALIC,24));
		//����һ�������
		String number=getNumber(4);
		//����֤��󶨵�session������
		HttpSession session=request.getSession();
		session.setAttribute("number", number);
		//��ͼƬ�ϻ��������
		g.drawString(number, 5, 25);
		//��Ӹ�����
		for(int i=0;i<10;i++) {
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
		}
		/**
		 * 2.ѹ��ͼƬ�����
		 */
		//��������������������ص���һ��ͼƬ
		response.setContentType("image/jpeg");
		//����ֽ������
		OutputStream output=response.getOutputStream();
		//��ԭʼͼƬ����ָ����ʽjepg����ѹ���������
		javax.imageio.ImageIO.write(image, "jpeg", output);
		output.close();
	}
	/*
	 * ����Ϊsize���ַ��������A~Z,0~9��ѡȡ
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
