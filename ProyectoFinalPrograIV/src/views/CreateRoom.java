package views;

import java.awt.event.*;

import javax.swing.*;


public class CreateRoom {
	private final int k = 4;
	
	final String[] roomTypes = {"Simple","Multiple"}; //list of id types
	
	JFrame f = new JFrame("Register"); 
	JComboBox<String> roomTypeField;
	JTextField capacity, price, comfort;
	
	
	public CreateRoom() {	
		
		//addition of all elements of the frame
	    addLabels();
	    addFields();
	    addButtons();	    
	    
	    // Set frame properties
	    f.setTitle("Crear Habitación");
	    f.setSize(600, 500);
	    f.setResizable(false);
	    f.setLayout(null);
	    f.setVisible(true);
    } 
	
	private boolean registerHandler() {

		return true;
	}
	
	private void addButtons() {
		JButton registerButton, loginButton;
		
		//registerbutton definition and settings
		registerButton = new JButton("Registrarse");
		registerButton.setBounds(210,380-(10*k),180, 20);
		registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
    			f.dispose();
        	}
        		
        });
		
		//loginbutton definition and settings
		loginButton = new JButton("Salir");
		loginButton.setBounds(210,430-(10*k),180, 20);
		loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f.dispose();
        	}
        		
        });
		
		f.add(registerButton);
		f.add(loginButton);
	}
	
	private void addFields() {
		roomTypeField = new JComboBox<String>(roomTypes);  
		roomTypeField.setBounds(200, 90-(10*k), 200, 20);
		f.add(roomTypeField);

		capacity = new JTextField();
		capacity.setBounds(200, 140-(10*k), 200, 20);
		f.add(capacity);

		price = new JTextField();
		price.setBounds(200, 190-(10*k), 200, 20);
		f.add(price);

		comfort = new JTextField();
		comfort.setBounds(200, 240-(10*k), 200, 20);
		f.add(comfort);

	}
	
	private void addLabels() {
		JLabel roomTypeLabel, capacityLabel, priceLabel, comfortLabel;
	    
	    //JTextField
	
		 // Definition and settings of idType label
		roomTypeLabel = new JLabel("Tipo de habitación");
		roomTypeLabel.setBounds(200, 70-(10*k), 150, 20);
		
		 // Definition and settings of id label
		capacityLabel = new JLabel("Capacidad");
		capacityLabel.setBounds(200, 120-(10*k), 150, 20);
		
		 // Definition and settings of names label
		priceLabel = new JLabel("Precio");
		priceLabel.setBounds(200, 170-(10*k), 150, 20);
		
		 // Definition and settings of lastNames label
		comfortLabel = new JLabel("Comodidades");
		comfortLabel.setBounds(200, 220-(10*k), 150, 20);

		f.add(roomTypeLabel);
	    f.add(capacityLabel);
	    f.add(priceLabel);
	    f.add(comfortLabel);
	}
}
