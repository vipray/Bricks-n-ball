import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


public class Blocks extends Rectangle{

	Image pic;
	float dx=3;
	float dy=-3;
	static int bc=46; //block count
	static boolean isball=true;
	boolean powerup=false;
	boolean destroyed=false;
	int vx=0; 
	
	Blocks(int a, int b, int w, int h, String s){
		//these var are already in Rectangle Class
		// x,y is left top most corner point of rectangle
		x=a;
		y=b;
		width=w;
		height=h;
		pic=Toolkit.getDefaultToolkit().getImage(s);
	}
	
	//to control smooth movement for slider
	public void speedHandlerVip(int wid,int vx ){
		if(x>0&&vx<0)
			x+=vx;
		if(x<(wid-getWidth())&&vx>0)
			x+=vx;
		
	}
	
	public void draw(Graphics g,Component c){
		if(!destroyed) //repaint if not destroyed yet
			g.drawImage(pic, x, y, width, height, c);
	}
}
