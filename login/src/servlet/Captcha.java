package servlet;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Captcha extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ServletConfig config = getServletConfig();
		int width = Integer.parseInt(config.getInitParameter("captcha-width"));
		int height = Integer.parseInt(config.getInitParameter("captcha-height"));
		int lines = Integer.parseInt(config.getInitParameter("captcha-lines"));
		int chars = Integer.parseInt(config.getInitParameter("captcha-chars"));
		int charSize = Integer.parseInt(config.getInitParameter("captcha-char-size"));
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics g = image.getGraphics();
		
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, width, height);
		
		g.setColor(Color.YELLOW);
		g.fillRect(1, 1, width - 2, height - 2);
		
		Random random = new Random();
		
		g.setColor(Color.BLUE);
		for (int i = 0; i < lines; i++)
			g.drawLine(random.nextInt(width), random.nextInt(height), 
					random.nextInt(width), random.nextInt(height));
		
		g.setColor(Color.RED);
		g.setFont(new Font("����", Font.BOLD | Font.ITALIC, charSize));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars; i++) {
			String value = String.valueOf(random.nextInt(10));
			sb.append(value);
			g.drawString(value, 30 + charSize * i, 
					random.nextInt(height - charSize) + charSize);
		}
		request.getSession().setAttribute("captcha", sb.toString());
		
		ImageIO.write(image, "png", response.getOutputStream());
	}
	
}
