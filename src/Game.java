import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;




	//How to create a menu for my game?

public class Game extends JPanel implements Runnable, KeyListener, ActionListener{
	private static final long serialVersionUID = 1L;
	private BufferedImage back;
	
  private ImageIcon background, menu;
  private Tank player1, player2;
  private Walls upwall, downwall, rightwall, leftwall, lastwall;
  
  private boolean playMusic;
  private Sound s;
  private boolean cPressed = false;
  private boolean spacePressed = false;
  private Projectile bullets;
  
  
  private ArrayList <PlayerBlueProj> pbluebullet;
  private ArrayList <PlayerGreenProj> pgreenbullet;
  
  private boolean juego = false;
  private boolean gameover = false;
  private boolean up1 = false;
  private boolean up2 = false;
  private boolean down1 = false;
  private boolean down2 = false;
  private boolean right = false;
  private boolean right2 = false;
  private boolean left = false;
  private boolean left2 = false;  
  private boolean dispararazul = false;
  private boolean dispararverde = false;
  private SoundPlayer st;

	public Game() {
		

		back=null;
		new Thread(this).start();
		
    background = new ImageIcon ("background.jpg");
    menu = new ImageIcon ("menu.png");
		back=null;
    player1 = new Tank(250,475,100,100, "bluetank.png", 2 ,2);
    player2 = new Tank(1550,475,100,100, "greentank.png", 2 ,2);

	upwall  = new Walls(500, 280, 20, 180);
	downwall  = new Walls(700, 570, 20, 180);
	rightwall  = new Walls(1400, 280, 20, 180);
	leftwall  = new Walls(1200, 570, 20, 180);
	lastwall  = new Walls(950, 300, 20, 180);
	pbluebullet = new ArrayList <PlayerBlueProj> ();
	pgreenbullet = new ArrayList <PlayerGreenProj>();
	st = new SoundPlayer();
	
	
	
   s =new Sound();
    s.playmusic("battle-of-the-dragons-8037.wav");
                        		
    
    

    

	
	new Thread(this).start();	
	this.addKeyListener(this);
	addKeyListener(this);

    this.setFocusable(true);
    this.requestFocusInWindow();                                   
	
    
		
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
		//Walls 
		g2d.fillRect(500, 280, 20, 180);
		g2d.fillRect(700, 570, 20, 180);
		g2d.fillRect(1400, 280, 20, 180);
		g2d.fillRect(1200, 570, 20, 180);
		g2d.fillRect(950, 300, 20, 180);
	

		g2d.drawImage(player1.getImg().getImage(), player1.getX(), player1.getY(), player1.getW(), player1.getH(), this);
		g2d.drawImage(player2.getImg().getImage(), player2.getX(), player2.getY(), player2.getW(), player2.getH(), this);
		// draw the bullets
	
	    
		g2d.setColor(Color.BLACK);
		
		//Score                                                          //Puntuacion
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("Consolas", Font.PLAIN,22));
		g2d.drawString("SCORE: " + player2.getScore(), 560, 100);
		g2d.drawString("SCORE: " + player1.getScore(), 710, 100);
		
		

		
		moveplayers();
		drawpbullet(g2d);     
		drawgreenbullet(g2d);
		collisionBullets(g2d);
		
		
		
		//Start message                                                  //Mensaje de Inicio
		if (!juego) {
			g2d.setFont(new Font("Consolas", Font.BOLD,42));
			g2d.fillRect(445, 195, 470, 50);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Press enter to start", 450, 230);
			g2d.drawImage(menu.getImage(), 0, 0, getWidth(), getHeight(), this); 
			
		}
		//Win and Lose                                                   //Ganador y perdedor
		
		if (player1.getScore()>=5) {
			g2d.setColor(Color.BLACK);
			g2d.setFont(new Font("Consolas", Font.PLAIN,42));
			g2d.drawString("You Win!", 350, 200);
			g2d.drawString("You Lose!", 750, 200);
		}
			else if (player2.getScore()>=5) {
				g2d.drawString("You Lose!", 350, 200);
				g2d.drawString("You Win!", 750, 200);
				
			
			
		}
			
			
			
			
			
		
			
		
		twoDgraph.drawImage(back, 0, 0, null);
		
	

	
	

	
	}

	
	
	 private void drawpbullet(Graphics g2d) {
         // TODO Auto-generated method stub
         for(PlayerBlueProj pa: pbluebullet) {
                         g2d.drawImage(pa.getI().getImage(), pa.getX(),pa.getY(), pa.getW(), pa.getH(),this);
         pa.bmove();
         
        
         }
         
	 }
	 
	 private void drawgreenbullet (Graphics g2d) {
		 
		for(PlayerGreenProj pg: pgreenbullet) {
			g2d.drawImage(pg.getI().getImage(), pg.getX(),pg.getY(), pg.getW(), pg.getH(),this);
	         pg.bleftmove();
	         
	         
	         
	         }
	 }
	 
	
	 private void collisionBullets(Graphics g2d) {
		    // Check collision between pbluebullet and walls
		    for (int i = 0; i < pbluebullet.size(); i++) {
		        PlayerBlueProj pb = pbluebullet.get(i);
		        Rectangle pbRect = new Rectangle(pb.getX(), pb.getY(), pb.getW(), pb.getH());

		        // Check collision with upwall
		        Rectangle upwallRect = new Rectangle(upwall.getX(), upwall.getY(), upwall.getW(), upwall.getH());
		        if (pbRect.intersects(upwallRect)) {
		            pbluebullet.remove(i);
		            continue;
		        }

		        // Check collision with downwall
		        Rectangle downwallRect = new Rectangle(downwall.getX(), downwall.getY(), downwall.getW(), downwall.getH());
		        if (pbRect.intersects(downwallRect)) {
		            pbluebullet.remove(i);
		            continue;
		        }

		        // Check collision with rightwall
		        Rectangle rightwallRect = new Rectangle(rightwall.getX(), rightwall.getY(), rightwall.getW(), rightwall.getH());
		        if (pbRect.intersects(rightwallRect)) {
		            pbluebullet.remove(i);
		            continue;
		        }

		        // Check collision with leftwall
		        Rectangle leftwallRect = new Rectangle(leftwall.getX(), leftwall.getY(), leftwall.getW(), leftwall.getH());
		        if (pbRect.intersects(leftwallRect)) {
		            pbluebullet.remove(i);
		            continue;
		        }
		    }

		    // Check collision between pgreenbullet and walls
		    for (int i = 0; i < pgreenbullet.size(); i++) {
		        PlayerGreenProj pg = pgreenbullet.get(i);
		        Rectangle pgRect = new Rectangle(pg.getX(), pg.getY(), pg.getW(), pg.getH());

		        // Check collision with upwall
		        Rectangle upwallRect = new Rectangle(upwall.getX(), upwall.getY(), upwall.getW(), upwall.getH());
		        if (pgRect.intersects(upwallRect)) {
		            pgreenbullet.remove(i);
		            continue;
		        }

		        // Check collision with downwall
		        Rectangle downwallRect = new Rectangle(downwall.getX(), downwall.getY(), downwall.getW(), downwall.getH());
		        if (pgRect.intersects(downwallRect)) {
		            pgreenbullet.remove(i);
		            continue;
		        }

		        // Check collision with rightwall
		        Rectangle rightwallRect = new Rectangle(rightwall.getX(), rightwall.getY(), rightwall.getW(), rightwall.getH());
		        if (pgRect.intersects(rightwallRect)) {
		            pgreenbullet.remove(i);
		            continue;
		        }

		        // Check collision with leftwall
		        Rectangle leftwallRect = new Rectangle(leftwall.getX(), leftwall.getY(), leftwall.getW(), leftwall.getH());
		        if (pgRect.intersects(leftwallRect)) {
		            pgreenbullet.remove(i);
		            continue;
		        }
		    }
		}

	 


					
					
	public void moveplayers() {
		if (juego) {
			
			
			if (up1) {
				player2.moverpalaarriba(getWidth(), getHeight());  //Mover hacia arriba jugador2
				
			}
				 
			if(up2) {
				 player1.moverpalaarriba(getWidth(), getHeight());  //Mover hacia arriba jugador1
					
			}
			
			if (down1) {
				player2.moverpalaabajo(getWidth(), getHeight());   //Mover hacia abajo jugador2
			}
			
			if (down2) {
				player1.moverpalaabajo(getWidth(), getHeight());   //Mover hacia abajo jugador1
			}
			
			if (right2) {
				player1.moverderecha(getWidth(), getHeight());    
			}
			
			if (right) {
				player2.moverderecha(getWidth(), getHeight());
			}
			
			if (left) {
				player2.moverizquierda(getWidth(), getHeight());
			}
			
			if (left2) {
				player1.moverizquierda(getWidth(), getHeight());
				
			}
			
			
		}
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {       //Acciones performadas
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {         //Tecla flecha arriba
		up1=true;
	}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {       //Tecla flecha abajo
			down1 =true;
	}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {      //Tecla flecha derecha
			right = true;
	}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {       //Tecla flecha left
			left = true;
	}
		
	    if (e.getKeyCode() == KeyEvent.VK_W) {          //Tecla W
			up2 = true; 
	}
	    if (e.getKeyCode() == KeyEvent.VK_D) {          //Tecla D
	    	right2 = true;
	}
	    if (e.getKeyCode() == KeyEvent.VK_A) {          //Tecla A
	    	left2 = true;
	}
			
		if (e.getKeyCode() == KeyEvent.VK_S) {          //Tecla S
			down2 = true;
	}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && !spacePressed) {      //Tecla espacio
			dispararverde=true;
			spacePressed = true;
	}
		
		if (e.getKeyCode() == KeyEvent.VK_C && !cPressed) {
            dispararazul=true;
            cPressed = true;
          
        }
		
		
		if  (e.getKeyCode() == KeyEvent.VK_ENTER) {
			juego=true;
			
			
		}
    }
	
	@Override
	public void keyReleased(KeyEvent e) {               //Activar teclas
		
	if (e.getKeyCode() == KeyEvent.VK_UP) {             //Tecla flecha arriba
		up1=false;
	}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {       //Tecla flecha abajo
			down1 =false;
	}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {      //Tecla flecha derecha
			right = false;
	}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)  {      //Tecla flecha left
			left = false;
	}
		
	    if (e.getKeyCode() == KeyEvent.VK_W) {          //Tecla W
			up2 = false;
	}
	    if (e.getKeyCode() == KeyEvent.VK_D) {          //Tecla D
	    	right2 = false;
	}
	    if (e.getKeyCode() == KeyEvent.VK_A) {          //Tecla A
	    	left2 = false;
	}
			
		if (e.getKeyCode() == KeyEvent.VK_S) {          //Tecla S
			down2 = false;
	}
		if (e.getKeyCode() == KeyEvent.VK_SPACE && spacePressed) {
			dispararverde = false;
			spacePressed = false;
			if (juego)
			st.playtirosound("disparo.wav");
			if (juego)
			pgreenbullet.add(new PlayerGreenProj( player2.getX()-34, player2.getY()+24));
	}
		if (e.getKeyCode() == KeyEvent.VK_C && cPressed) {
			dispararazul = false;
			cPressed = false;
			if (juego)
			st.playtirosound("disparo.wav");
			if (juego)
			pbluebullet.add(new PlayerBlueProj( player1.getX()+80, player1.getY()+26)); 
	}
		
		if  (e.getKeyCode() == KeyEvent.VK_ENTER) {
			juego=true;
			
			
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }                //Teclas presionadas
	
}


