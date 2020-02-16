package panel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
/**
 * static animation generate an image to draw instead of being draw on every frame
 * 
 */
public abstract class DrawableStatic extends Drawable{
	private BufferedImage savedWork = null; 
	private int w;
	private int h;
	public DrawableStatic() {
		
	}
	
	@Override
	public void paint(Graphics2D g, CartesianJPanelConvert c) {
		if(changed(c.ajp)) {
			savedWork = new BufferedImage(c.ajp.getWidth(), c.ajp.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = savedWork.createGraphics();
			

			g2.setColor(Color.WHITE);
			int width = c.ajp.getWidth();
			int height = c.ajp.getHeight();
			g2.fillRect(0, 0, width, height);
			g2.translate(width/2, height/2);
			g2.setColor(Color.BLACK);
			
			this.draw(g2, c);
			System.out.println("Changed image");
		}
		g.drawImage(savedWork, 0, 0, null);
	}
	
	private boolean changed(AnimatedJPanel p) {
		if(savedWork == null) {
			w = p.getWidth();
			h = p.getHeight();
			return true;
		}
		if(w != p.getWidth()) {
			w = p.getWidth();
			h = p.getHeight();
			return true;
		}

		if(h != p.getHeight()) {
			w = p.getWidth();
			h = p.getHeight();
			return true;
		}
		return false;
	}

}
