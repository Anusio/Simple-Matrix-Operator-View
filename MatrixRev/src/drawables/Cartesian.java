package drawables;

import java.awt.Color;
import java.awt.Graphics2D;

import panel.CartesianJPanelConvert;
import panel.Drawable;

/**
 * Drawable to draw the cartesian plain
 * @author anusio
 *
 */
public class Cartesian extends Drawable {

	@Override
	public void draw(Graphics2D g, CartesianJPanelConvert c) {
		g.setColor(Color.BLACK);
//		g.fillRect(c.getX(-c.getW()/2), c.getY(-c.getH()/2), c.getX(c.getW()/2), c.getY(c.getH()/2));
		float w = c.getW()/2;
		float h = c.getH()/2;
				
		g.drawRect(c.getX(-w), c.getY(h), c.getW(c.getW()), c.getH(c.getH()));
		g.drawLine(c.getX(-w), c.getY(0), c.getX(w), c.getY(0));
		g.drawLine(c.getX(0), c.getY(-h), c.getX(0), c.getY(h));
		for (int i = (int) -w; i <= w; i++) {
			g.drawOval(c.getX(i)-2, c.getY(0)-2, 4, 4);
			g.drawString(String.format("%d ", i), c.getX(i)-5, c.getY(0)+15);
		}
		for (int i = (int) -w; i <= w; i++) {
			g.fillOval(c.getX(0)-2, c.getY(i)-2, 4, 4);
			g.drawString(String.format("%d ", i), c.getX(0)-15, c.getY(i)+5);
		}
	}

	@Override
	public void update(CartesianJPanelConvert c) {
		// TODO Auto-generated method stub
		
	}

}
