import javax.swing.JLabel;


public class Animate implements Runnable {

	Bpanel bp;
	
	Animate(Bpanel b){
		bp=b;
	}
	
	public void run() {
		// TODO Auto-generated method stub
		while(Blocks.bc!=0 && Blocks.isball)
		{
			bp.update();
			try {
				Thread.sleep(10);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		if(!Blocks.isball){
			JLabel j = new JLabel("<html><font style='font-size:24px;' color='red'>Game Over <br> Score:"+(26-Blocks.bc)+"</font></html>");
			j.setBounds(150,100,350,240);
			
			bp.add(j);
		}
		
		else if(Blocks.bc==0){
			JLabel j = new JLabel("<html><font style='font-size:24px;' color='green'>Level Crossed <br> Score:"+26+"</font></html>");
			j.setBounds(150,100,350,240);
			
			bp.add(j);
		}
		
	}

}
