package panel;

import java.awt.Graphics2D;

/**
 * Drawable obj in AnimatedPanel
 * @author anusi
 *
 */
public abstract class Drawable {
	
	public void paint(Graphics2D g, CartesianJPanelConvert c) {
		this.draw(g, c);
	}

	public abstract void draw(Graphics2D g, CartesianJPanelConvert c);
	public abstract void update(CartesianJPanelConvert c);
}
