import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Rectangle;

public class Grinch{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	double vx, vy;						//movement variables
	int ay = 3;						//gravity
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image

	public Grinch() {
		forward 	= getImage("/imgs/"+"finalgrinch.png"); //load the image for Tree
		backward 	= getImage("/imgs/"+"finalgrinch2.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"finalgrinch3.png"); //load the image for Tree
		right 		= getImage("/imgs/"+"finalgrinch4.png"); //load the image for Tree

		//alter these
		width = 33;
		height = 15;
		x = 0;
		y = 450;
		vx = 0;
		vy = 0;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
	//getters for x and y
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	//setters for x and y
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.x = newY;
	}
	
	
	int ground = 450;
	int lim = 430;
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		vy += ay;
		
		
		if( y>= ground) { //keep the objects from going underground
			y = ground;
			vy = 0;
		}
		
		if (ground > 450) {
			ground = 450;
		}
		if (lim > 430) {
			lim = 430;
		}
		if (lim < 0) {
			lim = 0;
		}
		
		
		//make sure grinch stays between the boundaries
		if( x <= -20) { 
			x = -20;
			vx = 0;
		}
		if( x >= 510) { 
			x = 510;
			vx = 0;
		}
	
		
		init(x,y);
		
		switch(dir) {
		case 0:
			g2.drawImage(forward, tx, null);
			break;
		case 1:
			g2.drawImage(backward, tx, null);

			break;
		case 2:
			g2.drawImage(left, tx, null);

			break;
		case 3:
			g2.drawImage(right, tx, null);
			break;
		}
		
		/*inside paint draw the hitbox until you have debugged all collisions
		 */
		g.setColor(Color.pink);
		g.drawRect(x+35, y+90, 33, 15);

	}
	
	public void jumpForward() {
			if(y > lim) {
				vy = -18;
				x += 30;
				y -= 30;
				lim -=30;
				ground -=30;
			}
			
	}
	
	public void jumpBackward() {
		if(y > lim) {
			vy = -18;
			x -= 30;
			y += 30;
			lim +=30;
			ground +=30;
		}
		
	}
	
	public void jumpLeft() {
		if(y > lim ) {
			vy = -18;
			x -= 30;
			y -= 25;
			lim -=25;
			ground -=25;
		}
	
	}
	
	public void jumpRight() {
		if(y > lim) {
			vy = -18;
			x += 30;
			y += 25;
			lim +=25;
			ground +=25;
		}
		
	}
	
public boolean collided(int x, int y, int width, int height) {
		
		/*
		 * return true if an object represented by x,y,w,h occupies
		 * any space occupied by this object
		 */
	
	//if you are scaling your images, be sure width and height represent what users see
	//on the GUI
	Rectangle otherObject = new Rectangle(x,y,width,height);
	Rectangle thisObject = new Rectangle(this.x,this.y,this.width,this.height);
	return otherObject.intersects(thisObject);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Grinch.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
