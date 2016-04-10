package tool;

import java.io.IOException;
import java.util.Properties;

public abstract class Configer {
	
	protected static final String CFG_PATH = "config.cfg";
	
	public static final String CAPTCHA_SESSION_NAME = "captcha";
	public static final String ATTR_CAPTCHA_WIDTH = "width";
	public static final String ATTR_CAPTCHA_HEIGHT = "height";
	public static final String ATTR_CAPTCHA_LINES = "lines";
	public static final String ATTR_CAPTCHA_CHARS = "chars";
	public static final String ATTR_CAPTCHA_FONT_FAMILY = "font-family";
	public static final String ATTR_CAPTCHA_FONT_SIZE = "font-size";
	public static final String ATTR_CAPTCHA_FONT_STYLE = "font-style";
	
	protected static Properties p;
	
	protected static String captchaSessionName;
	protected static int width;
	protected static int height;
	protected static int lines;
	protected static int chars;
	protected static String fontFamily;
	protected static int fontSize;
	protected static int fontStyle;
	
	static {
		
		p = new Properties();
		try {
			p.load(Configer.class.getClassLoader().getResourceAsStream(CFG_PATH));
			captchaSessionName = p.getProperty(CAPTCHA_SESSION_NAME);
			width = Integer.parseInt(p.getProperty(ATTR_CAPTCHA_WIDTH));
			height = Integer.parseInt(p.getProperty(ATTR_CAPTCHA_HEIGHT));
			lines = Integer.parseInt(p.getProperty(ATTR_CAPTCHA_LINES));
			chars = Integer.parseInt(p.getProperty(ATTR_CAPTCHA_CHARS));
			fontFamily = p.getProperty(ATTR_CAPTCHA_FONT_FAMILY);
			fontSize = Integer.parseInt(p.getProperty(ATTR_CAPTCHA_FONT_SIZE));
			fontStyle = Integer.parseInt(p.getProperty(ATTR_CAPTCHA_FONT_STYLE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public static String getCaptchaSessionName() {
		return captchaSessionName;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static int getLines() {
		return lines;
	}

	public static int getChars() {
		return chars;
	}

	public static String getFontFamily() {
		return fontFamily;
	}

	public static int getFontSize() {
		return fontSize;
	}

	public static int getFontStyle() {
		return fontStyle;
	}
	
}
