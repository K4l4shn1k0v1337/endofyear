import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tank {

	private int x, y, w, h, dy, dx, score;
	private ImageIcon img;
	private Walls upwall, downwall, rightwall, leftwall, lastwall;

	//might need dy
	
	

	public Tank (int xV, int yV, int wV, int hV, String file, int ddx, int ddy) {
		x = xV;
		y = yV;
		w = wV;
		h = hV;
		dx = ddx;
		dy = ddy;
		img = new ImageIcon(file);
		score=0;
		upwall  = new Walls(500, 280, 20, 180);
		downwall  = new Walls(700, 570, 20, 180);
		rightwall  = new Walls(1400, 280, 20, 180);
		leftwall  = new Walls(1200, 570, 20, 180);
		lastwall  = new Walls(950, 300, 20, 180);
		
		
	}
	public void increaseScore(int s) {
		score++;
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
	
	public void setDy(int dyFromGame) {
	dy= dyFromGame;
	
	}
	public int getScore() {
		return score;
	}
		
	
	public boolean intersects(Walls wall) {
	    Rectangle tankRect = new Rectangle(getX(), getY(), getW(), getH());
	    Rectangle wallRect = new Rectangle(wall.getX(), wall.getY(), wall.getW(), wall.getH());

	    // Check if the tank rectangle intersects with the wall rectangle
	    if (tankRect.intersects(wallRect)) {
	        // Check if the tank is trying to pass through the walls
	        // In this case, the tank is allowed to pass, so return false
	        if (getX() + getW() <= wall.getX() || getX() >= wall.getX() + wall.getW()
	                || getY() + getH() <= wall.getY() || getY() >= wall.getY() + wall.getH()) {
	            return false;
	        }

	        // Collision occurred and tank is not passing through walls, return true
	        return true;
	    }

	    // No collision, return false
	    return false;
	}

	   
	   
public void moverpalaarriba (int screenw, int screenh) {
	
	   y += -dy;
	    
	    // check for collision with invisible wall at top of screen
	    if (y < 190) {
	        y = 190;
	    }
	    
	   
}
public void moverderecha (int screenx, int screeny) {
	x += dx;
	screenx=500;
	
	 // check for collision with invisible wall at right side of screen
    if (x + w > 1735) {
        x = 1735 - w;
    }
}



public void moverpalaabajo (int screenw, int screenh) {
	  y += dy;
	    
	    // check for collision with invisible wall at bottom of screen
	    if (y + h > 850) {
	        y = 850 - h;
	    }
	}

public void moverizquierda (int screenw, int screenh) {
	
	 x += -dx;
	    
	    // check for collision with invisible wall at left of screen
	    if (x < 180) {
	        x = 180;
	    }
	}

public int getDirection() {
	return 0;
}
//method to check for collision with another tank
public boolean collision(Tank t) {
 // check if the two tanks' rectangles intersect
 return (x < t.getX() + t.getW() &&
         x + w > t.getX() &&
         y < t.getY() + t.getH() &&
         y + h > t.getY());
}
public void setX(int player1x) {
	// TODO Auto-generated method stub
	
}
public void setY(int player1y) {
	// TODO Auto-generated method stub
	
}



}

