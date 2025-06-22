import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//sprites/objects creating
	Grinch grinch = new Grinch();
	Sled[] sledArr = new Sled [5];
	
	Background background = new Background();
	Sled sled = new Sled();
	Sled2 sled2 = new Sled2();
	Present present = new Present();
	Log log = new Log();
	River river = new River();

	int score = 0;
	boolean win = false;
	boolean lose = false;
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	
	
	//frame width/height
	int width = 600;
	int height = 600;	
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		background.paint(g);
		grinch.paint(g);
		present.paint(g);
		sled.paint(g);
		sled2.paint(g);
		log.paint(g);	
		
		for (int i = 0; i < 3; i++) {
		river.paint(g);
		river.x -= 50;
		river.y -= 21;
			
		}
		
		g.setColor(Color.yellow);
		Font sFont = new Font( "SansSerif", Font.PLAIN, 20);
		g.setFont(sFont);
		g.drawString("Score:", 520, 20);
		g.drawString(String.valueOf(score), 540, 45);
		
		if (sled.x > 580) {
			sled.x = -150;
			sled.y = -35;
		}
		if (sled2.x < -70) {
			sled2.x = 600;
			sled2.y = 130;
		}
		if (log.x < -140) {
			log.x = 600;
			log.y = 590;
		}
		if (river.x < -140) {
			river.x = 600;
			river.y = 590;
		}
		
		if (sled.collided(grinch.getX()+35, grinch.getY()+90, grinch.getWidth(), grinch.getHeight())) {
			if (win == false){
				lose = true;
				}
			}
		if (sled2.collided(grinch.getX()+35, grinch.getY()+90, grinch.getWidth(), grinch.getHeight())){
			if (win == false){
			lose = true;
				}
			}
		if (log.collided(grinch.getX()+35, grinch.getY()+90, grinch.getWidth(), grinch.getHeight())){
			grinch.vx = -4.2;
			grinch.vy = -1.1;
			}
		else if (river.collided(grinch.getX()+35, grinch.getY()+90, grinch.getWidth(), grinch.getHeight())) {
			if (win == false){
				lose = true;
				}
			}
		
		
		if (present.collided(grinch.getX()+35, grinch.getY()+90, grinch.getWidth(), grinch.getHeight())) {
			present.setX((int)(Math.random()*500)+10);
			present.setY((int)(Math.random()*500)+10);
			score++;
			}
	
		
		if (grinch.y < 10 && lose == false) {
			win = true;
			
		}
		
		if (win == true) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			
			g.setColor(Color.green);
			Font stringFont = new Font( "SansSerif", Font.PLAIN, 32);
			g.setFont(stringFont);
			g.drawString("Success", 240, 300);
			Font strFont = new Font( "SansSerif", Font.PLAIN, 20);
			g.setFont(strFont);
			g.drawString("Press Enter to Continue", 190, 320);
			g.setColor(Color.yellow);
			g.drawString("Score +5", 255, 360);
			lose = false;
		}
		if (lose == true) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 600, 600);
			
			g.setColor(Color.red);
			Font stringFont = new Font( "SansSerif", Font.PLAIN, 32);
			g.setFont(stringFont);
			g.drawString("Fail", 270, 300);
			Font strFont = new Font( "SansSerif", Font.PLAIN, 20);
			g.setFont(strFont);
			g.drawString("Press Enter to Continue", 190, 320);
		}
	}
	
	public static void main(String[] arg) {
		Frame f = new Frame();
		
		
	}
	
	public Frame() {
		JFrame f = new JFrame("Grinch Frogger");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);
	
		
	
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
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
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		System.out.println(k.getKeyCode());
		//87 = w, 65 = a, 83 = s, 68 = d
		
		switch(k.getKeyCode()) {
		case 87:
			//switch direction of grinch
			grinch.dir = 0;
			grinch.jumpForward();
			if (log.collided(grinch.getX()+35, grinch.getY()+90, grinch.getWidth(), grinch.getHeight())){
				if(grinch.y > grinch.lim-100) {
					grinch.vy = -18;
					grinch.vx = 0;
					grinch.x += 100;
					grinch.y -= 170;
					grinch.lim -=170;
					grinch.ground -=170;
				}
				}
			break;
			
		case 83:
			grinch.dir = 3; //back
			grinch.jumpBackward();
			if (log.collided(grinch.getX()+35, grinch.getY()+90, grinch.getWidth(), grinch.getHeight())){
				if(grinch.y > grinch.lim-100) {
					grinch.vy = -18;
					grinch.vx = 0;
					grinch.x -= 100;
					grinch.y += 170;
					grinch.lim +=170;
					grinch.ground +=170;
				}
				}
			break;
			
		case 65:
			grinch.dir = 1; //left
			grinch.jumpLeft();
			break;
			
		case 68:
			grinch.dir = 2; //right
			grinch.jumpRight();
			break;
			
		case 10:
			if (win == true) {
			win = false;
			score +=5;
			grinch.x = 0;
			grinch.y = 450;
			grinch.ground = 450;
			grinch.lim = 430;
			break;
			}
			if (lose == true){
				lose = false;
				grinch.x = 0;
				grinch.y = 450;
				grinch.ground = 450;
				grinch.lim = 430;
				score = 0;
				break;
			}
		}
	
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		switch(k.getKeyCode()) {
		case 87:
			
			break;
			
		case 83:
			
			break;
			
		case 65:
			
			break;
			
		case 68:
			
			break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
