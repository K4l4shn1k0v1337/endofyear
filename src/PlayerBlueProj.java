import javax.swing.ImageIcon;

public class PlayerBlueProj extends Projectile{
	public PlayerBlueProj() {
		super();
	}
	public PlayerBlueProj(int x, int y) {
		super(new ImageIcon("bullet.png"),x,y);
	}
	public boolean intersects(PlayerBlueProj al) {
		
		return false;
	}
		
}
