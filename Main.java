import java.awt.Color;
import javax.swing.JFrame;


public class Main {
	//JLabel t1;
	public static void main(String[] args){
		JFrame frame =new JFrame("BreakDBricks");
		
		Bpanel panel = new Bpanel();
		panel.setBackground(Color.CYAN);
		
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,600);
		frame.setVisible(true);
		frame.setResizable(false);
		
		
		
	}
}
