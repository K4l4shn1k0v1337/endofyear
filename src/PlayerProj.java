import javax.swing.ImageIcon;

public class PlayerProj extends Projectile{
	public PlayerProj() {
		super();
	}
	public PlayerProj(int x, int y) {
		super(new ImageIcon("bullet.png"),x,y);
	}
	public boolean intersects(PlayerProj al) {
		
		return false;
	}
		
}
