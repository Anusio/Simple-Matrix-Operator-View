
package drawables;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import Jama.Matrix;
import panel.CartesianJPanelConvert;
import panel.Drawable;
/**
 * Static Matrix dots draw
 * @author anusio
 *
 */
public class MatrixDots extends Drawable{

	double[][] matBase;
	float matGridX[][];
	float matGridY[][];
	protected Matrix algMatBase;
	protected Color color;
	protected boolean drawlines = true;
	
	public MatrixDots(CartesianJPanelConvert c) {
		color = new Color(30,30,30);
		matBase = new double[2][2];
		matBase[0][0] = 1;
		matBase[0][1] = 0;
		matBase[1][0] = 0;
		matBase[1][1] = 1;

		matGridX = new float[(int) c.getW()+1][(int) c.getH()+1];
		matGridY = new float[matGridX.length][matGridX[0].length];
		
		algMatBase = new Matrix(matBase);
		
		recalculate(c);
	}
	
	public Matrix getAlgMatBase() {
		return algMatBase;
	}
	
	public void randColor() {
		Random rd = new Random();
		color = new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255));
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setMatBase(double[][] matBase) {
		this.matBase = matBase;
		algMatBase = new Matrix(matBase);
	}
	
	public void printMat(Matrix jama) {
		double m [][] = jama.getArray();
		for (int i = 0; i < m.length; i++) {
			
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j]+"|");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void recalculate(CartesianJPanelConvert c) {
		for (int i = 0; i < matGridX.length; i++) {
			for (int j = 0; j < matGridX[0].length; j++) {
				double tmp [][] = calculate(i - c.getH()/2,j - c.getH()/2);
				matGridX[i][j] = (float) tmp[0][0];
				matGridY[i][j] = (float) tmp[1][0];
			}
		}
	}
	
	private double[][] calculate(double i, double j) {
		double tmp[][] = new double[2][1];
		tmp[0][0] = i;
		tmp[1][0] = j;
		return algMatBase.times(new Matrix(tmp)).getArray();
	}
	

	@Override
	public void draw(Graphics2D g, CartesianJPanelConvert c) {
		g.setColor(color);
		for (int i = 0; i < matGridX.length; i++) {
			for (int j = 0; j < matGridX[0].length; j++) {
				g.drawOval(c.getX(matGridX[i][j])-4, c.getY(matGridY[i][j])-4, 8, 8);
//				g.drawString(String.format("%d ", i), c.getX(0)-15, c.getY(i)+5);
			}
//			g.fillPolygon(matGridX[i], matGridY[i], 3);
		}
	}

	@Override
	public void update(CartesianJPanelConvert c) {

	}

}
