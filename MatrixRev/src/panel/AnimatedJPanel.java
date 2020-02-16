package panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;
/**
 *  Class to handle animated things on a JPanel
 *  
 * @author anusio
 *
 */
public class AnimatedJPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Drawable> itemsns = new ArrayList<Drawable>();
	public ArrayList<DrawableStatic> itemscs = new ArrayList<DrawableStatic>();
	private CartesianJPanelConvert CartesianJPanelConvert;

	public AnimatedJPanel(panel.CartesianJPanelConvert c) {
		this.CartesianJPanelConvert = c;
		c.setAnimatedJPanel(this);
	}
	

	public void setDrawable(Drawable d) {
		itemsns.add(d);
	}
	
	public void setDrawable(DrawableStatic d) {
		itemscs.add(d);
	}
	
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(Color.WHITE);
		int width = this.getWidth();
		int height = this.getHeight();
		g2.fillRect(0, 0, width, height);

		g2.setColor(Color.BLACK);
		for (Drawable drawable : itemscs) {
			drawable.paint(g2, this.CartesianJPanelConvert);
		}
		
		g2.translate(width/2, height/2);
		
		g2.setColor(Color.BLACK);
		for (Drawable drawable : itemsns) {
			drawable.draw(g2, this.CartesianJPanelConvert);
		}
	}
	
	public void updateLogic() {
		for (Drawable drawable : itemscs) {
			drawable.update(this.CartesianJPanelConvert);
		}
		
		for (Drawable drawable : itemsns) {
			drawable.update(this.CartesianJPanelConvert);
		}
	}
}
