import java.awt.Color;

import javax.swing.ImageIcon;

public class Tank {

	private int x,y,w,h,score;
	private Color col;
	private ImageIcon img;
	//might need dy

	public Tank() {
	x= 700;
	y=0;
	w=50;
	h=50;
	col = Color.BLACK;
	score=0;
	}

	
	public Tank(int xV, Color c) {
		x=xV;
		y=400;
		w=50;
		h=150;
		col = Color.WHITE;
		score=0;
	}
	
	public Tank(int i, int j, int k, int l, ImageIcon imageIcon) {
		// TODO Auto-generated constructor stub
		
		x=i;
		y=j;
		w=k;
		h=l;
		img = imageIcon;
	}

	public ImageIcon getImg() {
		return img;
	}	
	public int getX() {
		return x;
	
	}
	public int getY()
	{
		return y;
		
	}
	public int getW() {
		return w;
		
	}
	public int getH() {
		return h;
	}
	private Color getColor() {
		return col;
		
	}
	public void setY(int dy) {
		if(y>0 &&y<1500)
		y += dy;
		
		if(y<=0) {
			y++;
		}
	}


	public void addPoint(int i) {
		// TODO Auto-generated method stub
		
	}
	
//need collision 

//return methods

}