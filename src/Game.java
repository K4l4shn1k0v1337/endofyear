import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class Game extends JPanel implements Runnable {
	private BufferedImage back;
  private ImageIcon background, backeffect;
  private Tank player1, player2;

	 private boolean playMusic;
	 private Sound s;
  //tt
	 //gdgdgdrg
	
	public Game() {
    background = new ImageIcon ("background.jpg");
    backeffect = new ImageIcon ("BOTback.gif");
		back=null;
    player1 = new Tank(250,300,100,100, new ImageIcon("bluetank.png"));
	player2 = new Tank(1450,300,100,100, new ImageIcon("greentank.png"));
   Sound s =new Sound();
    s.playmusic("battle-of-the-dragons-8037.wav");
                        		



    
		
	}
	
	public void run() {
		try {
			while(true) {
				Thread.currentThread().sleep(5);
				repaint();
			}
		}
		catch(Exception e) {}                               
	}



  
	
	public void paint (Graphics g)
	{
		Graphics2D twoDgraph = (Graphics2D)g;

		if (back==null) {
			back =(BufferedImage) (createImage(getWidth(), getHeight()));
				}

		Graphics g2d = back.createGraphics();
		g2d.clearRect(0, 0, getSize().width, getSize().height); 
		g2d.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this); 

	

		g2d.drawImage(player1.getImg().getImage(), player1.getX(), player1.getY(), player1.getW(), player1.getH(), this);
		g2d.drawImage(player2.getImg().getImage(), player2.getX(), player2.getY(), player2.getW(), player2.getH(), this);

		
		
		twoDgraph.drawImage(back, 0, 0, null);
	}
	
}

