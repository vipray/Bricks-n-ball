import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Bpanel extends JPanel implements KeyListener{
	JLabel j;
	
	
	ArrayList<Blocks> blocks = new ArrayList<Blocks>();
	ArrayList<Blocks> balls = new ArrayList<Blocks>();
	ArrayList<Blocks> power = new ArrayList<Blocks>();
	Blocks paddle;
	Animate animate;
	int bsize=25; //size of ball
	int bCount=0; // no of balls
	int pSpeed=0; // slider speed dx
	Random r;
	Bpanel(){
		
		paddle= new Blocks(175,480,150,30,"paddle.jpg");
		
		for(int i=0;i<7;i++){
			blocks.add(new Blocks((i*70+2),0,60,25,"b1.png"));
		}
		for(int i=0;i<6;i++){
			blocks.add(new Blocks((i*70+35),30,60,25,"b2.png"));
		}
		for(int i=0;i<7;i++){
			blocks.add(new Blocks((i*70+2),60,60,25,"b3.png"));
		}
		for(int i=0;i<6;i++){
			blocks.add(new Blocks((i*70+35),90,60,25,"b4.png"));
		}
		for(int i=0;i<7;i++){
			blocks.add(new Blocks((i*70+2),120,60,25,"b1.png"));
		}
		for(int i=0;i<6;i++){
			blocks.add(new Blocks((i*70+35),150,60,25,"b2.png"));
		}
		for(int i=0;i<7;i++){
			blocks.add(new Blocks((i*70+2),180,60,25,"b3.png"));
		}
		
		r = new Random();
		// Returns number between 0-13
		int powerCount=r.nextInt(14);
		// Setting Power randomly
		for(int i=0;i<powerCount;i++)
		blocks.get(r.nextInt(26)).powerup=true;
		
		//first ball
		balls.add(new Blocks(237,437,25,25,"source.gif"));
		bCount++;
		
		
		addKeyListener(this);
		setFocusable(true);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(Blocks b: blocks){
			b.draw(g, this);
		}
		for(Blocks b: balls){
			b.draw(g, this);
		}
		for(Blocks p: power){
			p.draw(g, this);
		}
		paddle.draw(g, this);
	}
	
	public void update(){
		
		//will take care of paddle speed
		paddle.speedHandlerVip(getWidth(),pSpeed);
		
		if(bCount==0)
			Blocks.isball=false;
		
		for(Blocks p: power){
			
			// falling towards paddle
			p.y+=1;
			
			if(p.intersects(paddle) && !p.destroyed){
				p.destroyed=true;
				
				//power of 3 type
				if(r.nextInt(14)%3==0){
					balls.add(new Blocks(paddle.x+75,437,25,25,"source.gif"));
					bCount++;
				}
				else if(r.nextInt(14)%3==1&&paddle.width>2){
					paddle.width/=2;
				}
				else if((2*paddle.width)<=getWidth()){
					paddle.width*=2;
				}
			}
		}
		
		
		
		
		for(Blocks b: balls){
			b.x+=b.dx;
			if(b.x>(getWidth()-bsize)&& b.dx>0||b.x<0){
				System.out.println("x"+b.x);
				b.dx*=-1;
			}//to flip the ball direction on reaching walls
			
			
			if(b.y<0 ||b.intersects(paddle)){
				System.out.println(b.y);
				
				b.dy*=-1; //to flip the ball direction on striking paddle
			}
			
			
			if((b.y-60)>paddle.y && !b.destroyed){ //in case paddle miss the ball
				b.destroyed=true;
				bCount--;
			}
				
			b.y+=b.dy;
			for(Blocks bl : blocks){
				if(b.intersects(bl)&& !bl.destroyed){
					bl.destroyed=true;
					Blocks.bc--;
					
					b.dy*=-1;
					if(bl.powerup){
						power.add(new Blocks(bl.x,bl.y,50,30,"power.png"));
					}
				}
			}
		}
		repaint();	//calls the painComponent method
	}

	

	public void keyPressed(KeyEvent e) {
		
		// Enter will start the Game and thread
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			animate= new Animate(this);
			Thread thread =new Thread(animate);
			thread.start();
		}
	
		if(e.getKeyCode()==KeyEvent.VK_LEFT && paddle.x>0){
			pSpeed=-5;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT && paddle.x<(getWidth()-paddle.getWidth())){
			pSpeed=5;
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT ){
			pSpeed=0;
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT ){
			pSpeed=0;
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
