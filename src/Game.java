
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;
//add this 

public class Game extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{

	private ImageIcon LoseScreen, WinScreen, Space;
	private BufferedImage back; 
	private int key;
	private Playership spaceship; 
	private ArrayList <Alienship> aliens;
	private ArrayList <PlayerProj> pbullet;
	private ArrayList <AlienProj>abullet;
	private long starttime;
	private long currenttime;
	private boolean gameOver;
	private boolean gameWon;
	private static int contador = 0;
	 private Clip clip;
	 private boolean playMusic;
	 private Player p;

	
	public Game() {
		new Thread(this).start();	
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		aliens= setaliens();
		
		Space= new ImageIcon("spaceback.gif");
		spaceship= new Playership(900,1800,150,150);
		
		pbullet = new ArrayList <PlayerProj> ();
		abullet =new ArrayList <AlienProj> ();
		currenttime= System.currentTimeMillis() /1000;
		p =new Player();
		p.playmusic("mixkit-game-whip-shot-1512.wav");
		playMusic = true;
		gameOver = false;
		gameWon = false;
		
        Timer timer = new Timer();
        timer.schedule(new ContadorTask(), 0, 1000);

        
	}
	public ImageIcon getRandomColor() {
	
		return new ImageIcon();
		
	}
	
	
	private ArrayList<Alienship> setaliens() {
		// TODO Auto-generated method stub
		ArrayList<Alienship> temp = new ArrayList();
		for(int i=0; i<5; i++) {
			for(int j=0; j<21; j++) {
				//System.out.println("i is"+i);
				//System.out.println("j is"+j);
			
				temp.add(new Alienship( i*75,j*75));
			}
		
			
		}

	
	//System.out.println(temp.size());
		return temp;
	}




	public void run()
	   {
	   	try
	   	{
	   		while(!gameOver)
	   		{
	   		   Thread.currentThread().sleep(5);
	            repaint();
	         }
	      }
	   		catch(Exception e)
	      {
	      }
	  	}
	

    static class ContadorTask extends TimerTask {
        public void run() {
            contador++;
            System.out.println(contador);
        }
    }

	
	
	public void paint(Graphics g){
		
		Graphics2D twoDgraph = (Graphics2D) g; 
		if( back ==null)
			back=(BufferedImage)( (createImage(getWidth(), getHeight()))); 
		

		Graphics g2d = back.createGraphics();
		   Color white = new Color(255, 255, 255);
	
		g2d.clearRect(0,0,getSize().width, getSize().height);
		
		g2d.setFont( new Font("Broadway", Font.BOLD, 50));
		g2d.drawImage(Space.getImage(), 0,0, getWidth(), getHeight(), this); 
		
		g2d.drawImage(spaceship.getpick().getImage(),spaceship.getX(), spaceship.getY(), spaceship.getW(), spaceship.getH(),this);
		
		// Get current time in seconds
	    int currentTimeSec = (int) (System.currentTimeMillis() / 1000);

	    // Display time in seconds
	    g2d.setFont(new Font("Press Start 2P", Font.PLAIN, 20));
	    g2d.setColor(Color.WHITE);
	    g2d.drawString("Time: " + contador + " seconds", 1300, 1100);

		
		drawpbullet(g2d);	
		drawaliens(g2d);
		spacecollision(g2d);
		if(!aliens.isEmpty()) {
			alienBullet(g2d);
		}

		if(!abullet.isEmpty()) {
			drawAbullet(g2d);
		}
		if(aliens.isEmpty()) {
			gameWon = true;
		    g2d.setFont (new Font ("Press Start 2P", Font.BOLD, 62));

            g2d.setColor(white);

            g2d.drawString("CONGRATS!!!", 650, 400);

		//	gameOver = true;
			

		}
		
		
		twoDgraph.drawImage(back, null, 0, 0);
	}

	
    private void playercollision(Graphics g2d) {
        // TODO Auto-generated method stub

               for(int j=0; j<abullet.size(); j++) {
                     AlienProj al = new AlienProj(spaceship.getX(),spaceship.getY());
                            if(abullet.get(j).getY()+ abullet.get(j).getH() >=spaceship.getY() && abullet.get(j).getX()<=spaceship.getX()+spaceship.getW()&& abullet.get(j).getY()<= spaceship.getY()+spaceship.getH()&& abullet.get(j).getX()+ abullet.get(j).getW() >= spaceship.getX()) {
                            	abullet.remove(j);
               System.out.println("lose");
               gameOver = true;
                            }
               }
    }
             
              
        
	 
	private void alienBullet(Graphics g2d) {
		if((System.currentTimeMillis()-currenttime)%200==0) {
			abullet.add(new AlienProj(aliens.get(getrandomalien()).getX(), aliens.get(getrandomalien()).getY()));
			//drawAbullet(g2d);
			System.out.println("Shoot");
			currenttime = System.currentTimeMillis();
		}
	}
	
	
		
	
	private void drawAbullet(Graphics g2d) {
		for(AlienProj a: abullet) {
			System.out.println(abullet.size());
			g2d.drawImage(a.getI().getImage(), a.getX(), a.getY(), a.getW(), a.getH(),this);
			a.alienmove();
			System.out.println("move");
		}
		
	}
	private void spacecollision(Graphics g2d) {
		// TODO Auto-generated method stub
		 for(int i=0; i<aliens.size(); i++) {
			 for(int j=0; j<pbullet.size(); j++) {
				 PlayerProj al = new PlayerProj(aliens.get(i).getX(),aliens.get(i).getY());
					if(pbullet.get(j).getY()+pbullet.get(j).getH() >=aliens.get(i).getY() && pbullet.get(j).getX()<=aliens.get(i).getX()+aliens.get(i).getW()&&pbullet.get(j).getY()<= aliens.get(i).getY()+aliens.get(i).getH()&& pbullet.get(j).getX()+pbullet.get(j).getW() >= aliens.get(i).getX()) {
			 pbullet.remove(j);
		 aliens.remove(i);
		 if(playMusic) {
			 p.playmusic("mixkit-sci-fi-laser-in-space-sound-2825.wav");
			 p.playmusic("stop");
		 }
		 }
			 }
		 }
	}

	private void drawpbullet(Graphics g2d) {
		// TODO Auto-generated method stub
		for(PlayerProj pa: pbullet) {
			g2d.drawImage(pa.getI().getImage(), pa.getX(),pa.getY(), pa.getW(), pa.getH(),this);
		pa.bmove();
		}
			
			
	}
	private void drawaliens(Graphics g2d) {
		// TODO Auto-generated method stub
		if(!aliens.isEmpty()) {
			for(Alienship a :aliens) {
				g2d.drawImage(a.getpick().getImage(), a.getX(), a.getY(), a.getW(), a.getH(),this);
				
				a.move(getWidth(), getHeight());
				
				if(a.wallhit(getWidth(), getHeight())){
				movealiens();
                if(a.getY( )> spaceship.getY()) {
                	 g2d.setFont(new Font("Press Start 2P", Font.PLAIN, 60));
             	    g2d.setColor(Color.WHITE);
                    g2d.drawString("YOU LOSE", 600, 400);
                    System.out.println("lose");
                    gameOver = true;
             }

			}
			}
			
	}
		else {
            if(aliens.isEmpty()) {
                  /*g2d.drawString("You Win", 100, 100);*/
                  System.out.println("win");
                  gameWon = true;
            }
		}
	}
	public int getrandomalien() {
		return (int) (Math.random()*aliens.size());
		}
		

	public void movealiens() {
		//x+=spaceship.getdx;
		//1 is left, 2 is right


		for(Alienship a :aliens) {
			a.reverseHorz();
			a.setY(20);
			a.move(getWidth(), getHeight());
			a.setY(0);
		}
		}
		
	//DO NOT DELETE
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




//DO NOT DELETE
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		key= e.getKeyCode();
		System.out.println(key);
		
        if (e.getKeyCode()==81) {
        	
		aliens.clear();

	}             

	                                if (e.getKeyCode()==80) {

	                          
	                            int x = e.getKeyCode();
	                            spaceship.setX(x);
	                            

	                }            

		
	
	}


	//DO NOT DELETE
	@Override
	public void keyReleased(KeyEvent e) {
		
		
		
		
	}



	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		spaceship.setX(x);
	
		}

	


	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//left click
		if(arg0.getButton()==1) {
		pbullet.add(new PlayerProj( spaceship.getX()+50, spaceship.getY()-50));
		}
		System.out.println("button"+arg0.getButton());
		//bomb right click
		if (arg0.getButton()==3) {
			System.out.println("bomb");
		}
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

	
}
