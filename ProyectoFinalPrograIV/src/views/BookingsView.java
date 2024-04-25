package views;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BookingsView {
	
	public BookingsView() {
		JFrame f= new JFrame("Label Example");  
	    JLabel l1,l2;  
	    l1=new JLabel("First Label.");  
	    l1.setBounds(50,50, 100,30);  
	    l2=new JLabel("Second Label.");  
	    l2.setBounds(50,100, 100,30);  
	    f.add(l1); f.add(l2);  
	    f.setTitle("Reservas");
	    f.setSize(300,300);  
	    f.setLayout(null);  
	    f.setVisible(true);  
	}
}
