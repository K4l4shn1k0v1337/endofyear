import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Projectile {
private int x,y;
protected int w;
private int h;
private int dx;
private Color col;
private ImageIcon img;
public Image getImage;


	public Projectile() {
		x=0;
		y=0;
		w=0;
		h=0;
	}
	
	
	public Projectile(ImageIcon i, int xV, int yV) {
		w=50;
		h=50;
		x=xV;
		y=yV;
		dx=5;
		img=i;
	}

	public void reverseVert () {
		dx=dx*-1;
	}
	public void bmove()
	{

		x+=dx;
		
		
		}
	public void bleftmove () {
		x-=dx;
	}

		
	
	
	public void alienmove() {
		x+=dx;
		
	
	}
public ImageIcon getI() {
	return img;
}
public int getX() {
	return x;
}
public int getY() {
	return y;
}
public int getW() {
	return w;
}
public int getH() {
	return h;
}
public Color getColor() {
	return col;
}

public void setY(int dx) {
	x+=dx;
}
public void fetY(int dx) {
	x-=dx;
}
public void remove() {
	// TODO Auto-generated method stub
	w=0;
	h=0;
}

public boolean collision(Tank t) {
	
	if(y+h >= t.getY() && x<=t.getX()+t.getW() && y+h <= t.getY()+t.getH() && x+w >= t.getX()) {
		
		System.out.println(" collision");
	
		return true;
	}
	
	return false;
}


}

