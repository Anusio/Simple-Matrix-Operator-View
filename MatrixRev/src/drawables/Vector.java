package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Jama.Matrix;
import panel.CartesianJPanelConvert;
import panel.Drawable;

/**
 * Static vector draw
 * @author anusio
 *
 */
public class Vector extends Drawable{
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private Color color;
	
	public Vector() {
		x1 = 0;
		y1 = 0;
		x2 = 0;
		x2 = 0;
		color = new Color(255,0,0);
	}
	
	public Vector(float x, float y) {
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = x;
		this.y2 = y;
		color = new Color(255,0,0);
	}
	
	public Vector(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y2;
		this.x2 = x2;
		this.y2 = y2;
		color = new Color(255,0,0);
	}
	
	public void randColor() {
		Random rd = new Random();
		color = new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255));
	}
	
	@Override
	public void draw(Graphics2D g, CartesianJPanelConvert c) {
		g.setColor(color);
		calculateArrowLine(g, c.getX(x1), c.getY(y1), c.getX(x2), c.getY(y2), 16, 8);
		
		g.drawLine(c.getX(x1), c.getY(y1), c.getX(x2), c.getY(y2));
	}
	
	public Vector recalculate(CartesianJPanelConvert c, MatrixDots m) {
		Vector ret = new Vector();
		double tmp [][] = calculate(x1, y1, m);
		
		ret.x1 = (float) tmp[0][0];
		ret.y1 = (float) tmp[1][0];
		
		tmp = calculate(x2, y2, m);
		
		ret.x2 = (float) tmp[0][0];
		ret.y2 = (float) tmp[1][0];

		return ret;
	}
	
	private double[][] calculate(double i, double j, MatrixDots m) {
		double tmp[][] = new double[2][1];
		tmp[0][0] = i;
		tmp[1][0] = j;
		return m.algMatBase.times(new Matrix(tmp)).getArray();
	}
	
	
	private void calculateArrowLine(Graphics2D g, int x1, int y1, int x2, int y2, int d, int h) {
	    double dx = x2 - x1, dy = y2 - y1;
	    double D = Math.sqrt(dx*dx + dy*dy);
	    double xm = D - d, xn = xm, ym = h, yn = -h, x;
	    double sin = dy / D, cos = dx / D;

	    x = xm*cos - ym*sin + x1;
	    ym = xm*sin + ym*cos + y1;
	    xm = x;

	    x = xn*cos - yn*sin + x1;
	    yn = xn*sin + yn*cos + y1;
	    xn = x;

		int[] xpoints = {x2, (int) xm, (int) xn};
		int[] ypoints  = {y2, (int) ym, (int) yn};

	    g.fillPolygon(xpoints, ypoints, 3);
	}
	
	@Override
	public void update(CartesianJPanelConvert c) {
		
	}
}
