import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Rectangle;

public class River{
	private Image forward, backward, left, right; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	double vx, vy;						//movement variables
	double scaleWidth = 0.1;		//change to scale image
	double scaleHeight = 0.1; 		//change to scale image

	public River() {
		forward 	= getImage("/imgs/"+"empty.png"); //load the image for Tree
		backward 	= getImage("/imgs/"+"empty.png"); //load the image for Tree
		left 		= getImage("/imgs/"+"empty.png"); //load the image for Tree
		right 		= getImage("/imgs/"+"empty.png"); //load the image for Tree

		//alter these
		width = 105;
		height = 65;
		x = 300;
		y = 410;
		vx = -4.2;
		vy = -1.1;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y); 				//initialize the location of the image
									//use your variables
		
	}
	
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

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
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

		g.setColor(Color.pink);
		g.drawRect(x+5, y+25, 105, 65);

		
	}


public boolean collided(int x, int y, int width, int height) {
	
	/*
	 * return true if an object represented by x,y,w,h occupies
	 * any space occupied by this object
	 */

//if you are scaling your images, be sure width and height represent what users see
//on the GUI
	
Rectangle otherObject = new Rectangle(x,y,width,height);
Rectangle thisObject = new Rectangle(getX()+5,getY()+25,getWidth(),getHeight());
return otherObject.intersects(thisObject);
}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
