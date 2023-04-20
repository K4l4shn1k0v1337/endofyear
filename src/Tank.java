import java.awt.Color;

import javax.swing.ImageIcon;

public class Tank {

	private int x, y, w, h, dy, dx, score;
	private Color col;
	private ImageIcon img;
	//might need dy

	public Tank (int xV, int yV, int wV, int hV, String file, int ddx, int ddy) {
		x = xV;
		y = yV;
		w = wV;
		h = hV;
		dx = ddx;
		dy = ddy;
		img = new ImageIcon(file);
		
		
	}
	public void increaseScore(int s) {
		score+=s;
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

	public void setDx(int dxFromGame) {
	dx= dxFromGame;
	
	}
	public int getScore() {
		return score;
	}
		


public void moverpalaarriba (int screenw, int screenh) {
	x+=dx;
	y+=-dy;		
	if((x>=screenw)) {
		dx = dx * 1;
	
	}
	
	
	if((x<=0)) {
		dx = dx * 1;
	}
	
	
	
if((y>=555)) {
dy = dy * 1;

}


	if((y<=60)) {
		dy = dy * 1;
	
}
}

public void moverpalaabajo (int screenw, int screenh) {
	x+=dx;
	y+=dy;		
	if((x>=screenw)) {
		dx = dx * 1;
	}
	if((x<=0)) {
		dx = dx * 1;
	}
if((y>=555)) {
dy = dy * 1;
}

	if((y<=60)) {
		dy = dy * 1;
	
}
	

}
}