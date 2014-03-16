import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;

public class Drawing extends JFrame{
	
	Screen sc;
		
	public Drawing() {
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(600, 600);
		setResizable(false);
		 
		
		sc = new Screen();
		
		Container pane = getContentPane();
		pane.setLayout(new GridLayout(1,1));
		JPanel p = sc;
		pane.add(p);

		/**sc.run();*/
		
		
	}
		
	public static void main(String[] args){	
		
		Drawing game = new Drawing();
	}
	
}