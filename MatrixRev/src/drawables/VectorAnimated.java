package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Jama.Matrix;
import panel.CartesianJPanelConvert;
import panel.Drawable;

/**
 * Animated Vector drawable
 * @author anusio
 *
 */
public class VectorAnimated extends Drawable {
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private float x3;
	private float y3;
	private float x4;
	private float y4;
	private Color color;
	private Color colorf;
	private float animatorfac = 0;

	private boolean showOrigi = true;
	private boolean showLanded = true;

	public VectorAnimated() {
		x1 = 0;
		y1 = 0;
		x2 = 0;
		x2 = 0;
		color = new Color(1.0f, 0.0f, 0.0f);
		colorf = newColor(1.0f, 0.0f, 0.0f);
		setNew(x1, x2, y1, y2);
	}

	private Color newColor(float r, float g, float b) {
		return new Color(r / 2 + r / 4, g / 2 + g / 4, b / 2 + b / 4);
	}

	public float getX3() {
		return x3;
	}

	public float getX4() {
		return x4;
	}

	public float getY3() {
		return y3;
	}

	public float getY4() {
		return y4;
	}

	public VectorAnimated(float x, float y) {
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = x;
		this.y2 = y;
		color = new Color(1.0f, 0.0f, 0.0f);
		colorf = newColor(1.0f, 0.0f, 0.0f);
		setNew(x1, x2, y1, y2);
	}

	public VectorAnimated(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		color = new Color(1.0f, 0.0f, 0.0f);
		colorf = newColor(1.0f, 0.0f, 0.0f);
		setNew(x1, x2, y1, y2);
	}

	public void setShowLanded(boolean showLanded) {
		this.showLanded = showLanded;
	}
	
	public boolean isShowOrigi() {
		return showOrigi;
	}
	
	public boolean isShowLanded() {
		return showLanded;
	}
	
	public void setShowOrigi(boolean showOrigi) {
		this.showOrigi = showOrigi;
	}
	
	
	
	private void setNew(float x1, float x2, float y1, float y2) {
		x3 = x1;
		y3 = y1;
		x4 = x2;
		y4 = y2;
	}

	public void randColor() {
		Random rd = new Random();
		float r, g, b;
		r = rd.nextFloat();
		g = rd.nextFloat();
		b = rd.nextFloat();

		color = new Color(r, g, b);
		colorf = newColor(r, g, b);
	}

	@Override
	public void draw(Graphics2D g, CartesianJPanelConvert c) {

		if (showLanded) {
			g.setColor(colorf);
			float trax1 = animatorfac * (x3 - x1);
			float tray1 = animatorfac * (y3 - y1);
			float trax2 = animatorfac * (x4 - x2);
			float tray2 = animatorfac * (y4 - y2);
			calculateArrowLine(g, c.getX(x1 + trax1), c.getY(y1 + tray1), c.getX(x2 + trax2), c.getY(y2 + tray2), 16,
					8);

			g.drawLine(c.getX(x1 + trax1), c.getY(y1 + tray1), c.getX(x2 + trax2), c.getY(y2 + tray2));
		}
		if (showOrigi) {
			g.setColor(color);
			calculateArrowLine(g, c.getX(x1), c.getY(y1), c.getX(x2), c.getY(y2), 16, 8);

			g.drawLine(c.getX(x1), c.getY(y1), c.getX(x2), c.getY(y2));
		}
	}

	public void recalculate(CartesianJPanelConvert c, Matrix m) {
		double tmp[][] = calculate(x1, y1, m);

		x3 = (float) tmp[0][0];
		y3 = (float) tmp[1][0];

		tmp = calculate(x2, y2, m);

		x4 = (float) tmp[0][0];
		y4 = (float) tmp[1][0];

	}

	private double[][] calculate(double i, double j, Matrix m) {
		double tmp[][] = new double[2][1];
		tmp[0][0] = i;
		tmp[1][0] = j;
		return m.times(new Matrix(tmp)).getArray();
	}

	private void calculateArrowLine(Graphics2D g, int x1, int y1, int x2, int y2, int d, int h) {
		double dx = x2 - x1, dy = y2 - y1;
		double D = Math.sqrt(dx * dx + dy * dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;

		x = xm * cos - ym * sin + x1;
		ym = xm * sin + ym * cos + y1;
		xm = x;

		x = xn * cos - yn * sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;

		int[] xpoints = { x2, (int) xm, (int) xn };
		int[] ypoints = { y2, (int) ym, (int) yn };

		g.fillPolygon(xpoints, ypoints, 3);
	}

	public void setAnimatorfac(float animatorfac) {
		this.animatorfac = animatorfac;
	}

	@Override
	public void update(CartesianJPanelConvert c) {

	}

	public boolean compare(VectorAnimated vectorAnimated) {
		if (x1 == vectorAnimated.x1 && x2 == vectorAnimated.x2 && y1 == vectorAnimated.y1 && y2 == vectorAnimated.y2) {
			return true;
		}

		return false;
	}
}
