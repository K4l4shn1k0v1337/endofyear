import javax.swing.ImageIcon;

public class Tank {
    private int x, y, w, h,dx;
    private ImageIcon img;

    
	public Tank()
	{
		x=700;
		y=700;
		dx=0;
		w=100;
		h=100;
		img = new ImageIcon("bluetank.png");
	
	}
    
	public Tank(int xV, int yV, int width, int height, String s)
	{
		x=xV;
		y=yV;
		dx=0;
		w=width;
		h=height;
		img = new ImageIcon (s);
	}
	
	
	
	
	public void setX(int xV) {
	x+=xV;
	}
	
	public void setY(int yV) {
		y += yV;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	
	public void setW(int width) {
		w+=width;
	}
	
	public void setH(int height) {
		h+=height;
	}
	
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}

	
	public void move()
	{
		x+=dx;
		
	}
	
	public void setDx(int dxFromGame)
	{
		dx= dxFromGame;
	}
	
	public ImageIcon getImg() {
		return img;
	}	
	
}
