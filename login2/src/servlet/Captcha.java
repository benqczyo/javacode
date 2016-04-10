package servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Configer;

public class Captcha extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int width = Configer.getWidth();
		int height = Configer.getHeight();
		
		String fontFamily = Configer.getFontFamily();
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random r = new Random();
		
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width, height);
		g.setColor(Color.YELLOW);
		g.fillRect(1, 1, width - 2, height - 2);
		
		int lines = Configer.getLines();
		for (int i = 0; i < lines; i++) {
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.drawLine(r.nextInt(width), r.nextInt(height), r.nextInt(width), r.nextInt(height));
		}
		
		int chars = Configer.getChars();
		int fontSize = Configer.getFontSize();
		int fontStyle = Configer.getFontStyle();
		int[] style = new int[] {Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars; i++) {
			g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
			g.setFont(new Font(fontFamily, fontStyle < 0 ? style[r.nextInt(style.length - 1)] : fontStyle, fontSize));
			String value = String.valueOf(r.nextInt(10));
			sb.append(value);
			g.drawString(value, (width - chars * fontSize) / 2 + fontSize * i , r.nextInt(height - fontSize) + fontSize);
		}
	
		request.getSession().setAttribute(Configer.getCaptchaSessionName(), sb.toString());

		response.setContentType("image/png");
		response.setIntHeader("Expires", -1);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		
		ImageIO.write(image, "png", response.getOutputStream());
		
	}
}
