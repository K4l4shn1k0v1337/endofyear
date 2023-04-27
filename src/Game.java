import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


	//How to create a menu for my game?

public class Game extends JPanel implements Runnable, KeyListener, ActionListener{
	private static final long serialVersionUID = 1L;
	private BufferedImage back;
  private ImageIcon background;
  private Tank player1, player2;
  private Walls upwall, downwall, rightwall, leftwall;
  private Walls walls;
  //private Clip clip;

  private boolean playMusic;
  private Sound s;
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
 
	
	public Game() {
		

		back=null;
		new Thread(this).start();
		
    background = new ImageIcon ("background.jpg");
		back=null;
    player1 = new Tank(250,300,100,100, "bluetank.png", 2 ,2 );
    player2 = new Tank(850,300,100,100, "greentank.png", 2 ,2 );

	upwall  = new Walls(0, 180, 1900, 20);
	downwall  = new Walls(0, 840, 1900, 20);
	rightwall  = new Walls(1730, 200, 20, 640);
	leftwall  = new Walls(160, 200, 20, 640);
	
	
	
	
   s =new Sound();
    s.playmusic("battle-of-the-dragons-8037.wav");
                        		


	
	new Thread(this).start();	
	this.addKeyListener(this);

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

	

		g2d.drawImage(player1.getImg().getImage(), player1.getX(), player1.getY(), player1.getW(), player1.getH(), this);
		g2d.drawImage(player2.getImg().getImage(), player2.getX(), player2.getY(), player2.getW(), player2.getH(), this);
		g2d.setColor(Color.BLACK);
		//g2d.drawRect(0, 180, 1900, 20); //Parte superior
		//g2d.drawRect(0, 840, 1900, 20); //Parte inferior
		//g2d.drawRect(160, 200, 20, 640); //Parte izquierda
		//g2d.drawRect(1730, 200, 20, 640); //Parte derecha

		
		moveplayers();
		
		
		//Start message                                                  //Mensaje de Inicio
		if (!juego) {
			g2d.setFont(new Font("Consolas", Font.BOLD,42));
			g2d.fillRect(445, 195, 470, 50);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Press enter to start", 450, 230);
			
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {      //Tecla espacio
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
		
	}
	@Override
	public void keyTyped(KeyEvent e) { }                //Teclas presionadas
	
}


