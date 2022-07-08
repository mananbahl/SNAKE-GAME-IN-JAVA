import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame=new JFrame("Snake");
		frame.setBounds(450,150,905,700);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Gameplay gameplay=new Gameplay();
		gameplay.setBackground(Color.BLUE);
		frame.add(gameplay);
		frame.setVisible(true);	

	}

}
