package drawables;

import java.awt.Graphics2D;
import java.util.ArrayList;

import panel.CartesianJPanelConvert;
import panel.Drawable;

/**
 * Animated Matrix dots handle
 * @author anusio
 *
 */
public class MatrixAnimator extends Drawable {
	private MatrixDots d1;
	private MatrixDots d2;
	private float animatorfac = 0;
	private boolean reverse = false;
	private ArrayList<VectorAnimated> vectors = new ArrayList<VectorAnimated>();

	public MatrixAnimator(MatrixDots d1, MatrixDots d2) {
		this.d1 = d1;
		this.d2 = d2;
		VectorAnimated tmp = new VectorAnimated(0, 1);
		tmp.randColor();
		vectors.add(tmp);
		tmp = new VectorAnimated(1, 0);
		tmp.randColor();
		vectors.add(tmp);
	}

	public void setVector(VectorAnimated vec) {
		vec.randColor();
		vectors.add(vec);
	}

	public void setShowOrigi() {
		for (VectorAnimated vectorAnimated : vectors) {
			vectorAnimated.setShowOrigi(!vectorAnimated.isShowOrigi());
		}
	}

	public void setShowLanded() {
		for (VectorAnimated vectorAnimated : vectors) {
			vectorAnimated.setShowLanded(!vectorAnimated.isShowLanded());
		}
	}
	
	@Override
	public void draw(Graphics2D g, CartesianJPanelConvert c) {
		d1.paint(g, c);

		for (VectorAnimated vectorAnimated : vectors) {
			vectorAnimated.recalculate(c, d2.algMatBase);
			vectorAnimated.setAnimatorfac(animatorfac);
			vectorAnimated.draw(g, c);
		}
		g.setColor(d2.color);
		for (int i = 0; i < d2.matGridX.length; i++) {
			for (int j = 0; j < d2.matGridX[0].length; j++) {
				float trax = animatorfac * (d2.matGridX[i][j] - d1.matGridX[i][j]);
				float tray = animatorfac * (d2.matGridY[i][j] - d1.matGridY[i][j]);
				g.fillOval(c.getX(d1.matGridX[i][j] + trax) - 4, c.getY(d1.matGridY[i][j] + tray) - 4, 8, 8);
			}
		}
	}

	public void setAnimatorfac(float animatorfac) {
		this.animatorfac = animatorfac;
	}

	public void changeReverse() {
		if (reverse) {
			reverse = false;
		}else {
			reverse = true;
		}
		System.out.println(reverse);
	}
	
	@Override
	public void update(CartesianJPanelConvert c) {
		if (reverse) {
			if (animatorfac > 0) {
				animatorfac -= 0.01;
			}
			if (animatorfac <= 0) {
				animatorfac = 0;
			}
		} else {
			if (animatorfac < 1) {
				animatorfac += 0.01;
			}
			if (animatorfac >= 1) {
				animatorfac = 1;
			}
		}
	}

	public void clearVectors() {
		vectors.clear();
	}

	public void removeVector(VectorAnimated vectorAnimated) {
		VectorAnimated torem = null;
		for (VectorAnimated vectorAnimated2 : vectors) {
			if (vectorAnimated2.compare(vectorAnimated)){
				torem = vectorAnimated2;
				break;
			}
		}
		vectors.remove(torem);
	}
}
