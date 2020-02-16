package panel;
/**
 * Class to handle the conversion between java plane and cartesian plane
 * @author anusio
 *
 */
public class CartesianJPanelConvert {
	
	private float imaginaryW;
	private float imaginaryH;
	protected AnimatedJPanel ajp;
	
	
	public CartesianJPanelConvert(AnimatedJPanel animatedJPanel) {
		setAnimatedJPanel(animatedJPanel);
		this.imaginaryH = 20;
		this.imaginaryW = 20;
				
	}

	public void setWidth(float imaginaryW) {
		this.imaginaryW = imaginaryW;
	}
	
	public void setHeigth(float imaginaryH) {
		this.imaginaryH = imaginaryH;
	}
	
	public void setAnimatedJPanel(AnimatedJPanel j) {
		this.ajp = j;
	}
	
	private float treeRule(float value, float to, float actual) {
		if(value == 0) {
			return 0;
		}
		return (actual*to) / value;
	}
	
	public float getH() {
		return imaginaryH;
	}
	
	public float getW() {
		return imaginaryW;
	}
	
	public int getH(float c) {
		return (int) treeRule(imaginaryH, getDimMax(), c);
	}
	
	public int getW(float c) {
		return (int) treeRule(imaginaryW, getDimMax(), c);
	}
	
	public int getX(float x) {		
		return (int)((treeRule(imaginaryW, getDimMax(), x)));
	}
	
	public int getY(float y) {  
		return (int)(-(treeRule(imaginaryH, getDimMax(), y)));
	}

	private float getDimMax() {
		float a = ajp.getWidth();
		float b = ajp.getHeight();
		if (a<b) {
			return a;
		}
		return b;
	}
	
	public float getAspectRatio() {
		
		float a = ajp.getWidth();
		float b = ajp.getHeight();
		if (a>b) {
			float tmp = a;
			a=b;
			b=tmp;
		}
		if (b == 0) {
			return 0;
		}
		return a/b;
	}
}
