package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class About extends JFrame implements ActionListener  {
	JButton b1;
	
	About(){
		setBounds(450,150,650,500);
		setLayout(null); //so that we can use our own dimensions in setBounds
		
////		
		JLabel l3 = new JLabel("<html>This is simple Notepad<br> You can write any text in this "
				+ " and you can save the text file in any "
				+ "destination folder."
				+ "Notepad is a simple text editor <br>for basic text editing program."
				+ " If facing any difficulty reach out to us at 'kumar.ak16121999@gmail.com'</html>");
		l3.setBounds(100,50,500,300);
		l3.setFont(new Font("SAN_SERIF",Font.PLAIN,18));
		add(l3);
//		
		b1 = new JButton("Close");
		b1.setBounds(580,500,80,25);
		b1.addActionListener(this);
		add(b1);
//		
		
		
	}
	
	
	public void actionPerformed(ActionEvent ap) {
		this.setVisible(false);
	}
	
	
	public static void main(String args[]) {
		new About().setVisible(true);
	}
}
