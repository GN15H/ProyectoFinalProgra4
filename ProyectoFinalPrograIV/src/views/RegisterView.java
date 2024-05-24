package views;

import java.awt.event.*;

import javax.swing.*;
import controlador.Register;

public class RegisterView {
	private final int k = 4;

	
	final String[] idTypes = {"T.I","C.C"}; //list of id types
	
	JFrame f = new JFrame("Register"); 
	JComboBox<String> idTypeField;
	JTextField idField, namesField, lastNamesField, emailField,
				residenceAddressField, residenceCityField,
				phoneField;
	JPasswordField passwordField, confirmPasswordField;
	
	
	public RegisterView() {	
		
		//addition of all elements of the frame
	    addLabels();
	    addFields();
	    addButtons();	    
	    
	    // Set frame properties
	    f.setTitle("Register");
	    f.setSize(600, 650);
	    f.setResizable(false);
	    f.setLayout(null);
	    f.setVisible(true);
    } 
	
	private boolean registerHandler() {
		String passwordString = new String(passwordField.getPassword());
		String confirmPasswordString = new String(confirmPasswordField.getPassword());
				
		if(!passwordString.equals(confirmPasswordString)) {
			JOptionPane.showMessageDialog(f, "Las contraseñas no coinciden", "Error", JOptionPane.CLOSED_OPTION);
			return false;
		}
			
		return true;
	}
	
	private void addButtons() {
		JButton registerButton, loginButton;
		
		//registerbutton definition and settings
		registerButton = new JButton("Registrarse");
		registerButton.setBounds(210,570-(10*k),180, 20);
		registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!registerHandler()) {
        			return;
        		}
        		Register register = new Register();
        		register.registerUser(
        				idTypeField.getItemAt(idTypeField.getSelectedIndex()),
        				idField.getText(),
        				namesField.getText(),
        				lastNamesField.getText(),
        				emailField.getText(),
        				residenceAddressField.getText(),
        				residenceCityField.getText(),
        				phoneField.getText(),
        				new String(passwordField.getPassword())
        				);
        		
    			JOptionPane.showMessageDialog(f, "Usuario creado correctamente", "Registro Exitoso", JOptionPane.CLOSED_OPTION);
    			f.dispose();
    			@SuppressWarnings("unused")
				LoginView loginView = new LoginView();
        	}
        		
        });
		
		//loginbutton definition and settings
		loginButton = new JButton("Iniciar Sesión");
		loginButton.setBounds(210,600-(10*k),180, 20);
		loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f.dispose();
        		@SuppressWarnings("unused")
				LoginView loginView = new LoginView();
        	}
        		
        });
		
		f.add(registerButton);
		f.add(loginButton);
	}
	
	private void addFields() {
		idTypeField = new JComboBox<String>(idTypes);  
		idTypeField.setBounds(200, 90-(10*k), 200, 20);
		f.add(idTypeField);

		idField = new JTextField();
		idField.setBounds(200, 140-(10*k), 200, 20);
		f.add(idField);

		namesField = new JTextField();
		namesField.setBounds(200, 190-(10*k), 200, 20);
		f.add(namesField);

		lastNamesField = new JTextField();
		lastNamesField.setBounds(200, 240-(10*k), 200, 20);
		f.add(lastNamesField);

		emailField = new JTextField();
		emailField.setBounds(200, 290-(10*k), 200, 20);
		f.add(emailField);

		residenceAddressField = new JTextField();
		residenceAddressField.setBounds(200, 340-(10*k), 200, 20);
		f.add(residenceAddressField);

		residenceCityField = new JTextField();
		residenceCityField.setBounds(200, 390-(10*k), 200, 20);
		f.add(residenceCityField);

		phoneField = new JTextField();
		phoneField.setBounds(200, 440-(10*k), 200, 20);
		f.add(phoneField);

		passwordField = new JPasswordField();
		passwordField.setBounds(200, 490-(10*k), 200, 20);
		f.add(passwordField);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(200, 540-(10*k), 200, 20);
		f.add(confirmPasswordField);

	}
	
	private void addLabels() {
		JLabel idTypeLabel, idLabel, namesLabel, lastNamesLabel, emailLabel,
	    residenceAddressLabel, residenceCityLabel, phoneLabel,
	    passwordLabel, confirmPasswordLabel;
	    
	    //JTextField
	
		 // Definition and settings of idType label
		 idTypeLabel = new JLabel("Tipo de identificación");
		 idTypeLabel.setBounds(200, 70-(10*k), 150, 20);
		
		 // Definition and settings of id label
		 idLabel = new JLabel("ID");
		 idLabel.setBounds(200, 120-(10*k), 150, 20);
		
		 // Definition and settings of names label
		 namesLabel = new JLabel("Nombres");
		 namesLabel.setBounds(200, 170-(10*k), 150, 20);
		
		 // Definition and settings of lastNames label
		 lastNamesLabel = new JLabel("Apellidos");
		 lastNamesLabel.setBounds(200, 220-(10*k), 150, 20);
		
		 // Definition and settings of email label
		 emailLabel = new JLabel("Correo electrónico");
		 emailLabel.setBounds(200, 270-(10*k), 150, 20);
		
		 // Definition and settings of residenceAddress label
		 residenceAddressLabel = new JLabel("Dirección de residencia");
		 residenceAddressLabel.setBounds(200, 320-(10*k), 150, 20);
		
		 // Definition and settings of residenceCity label
		 residenceCityLabel = new JLabel("Ciudad de residencia");
		 residenceCityLabel.setBounds(200, 370-(10*k), 150, 20);
		
		 // Definition and settings of phone label
		 phoneLabel = new JLabel("Teléfono");
		 phoneLabel.setBounds(200, 420-(10*k), 150, 20);
		
		 // Definition and settings of password label
		 passwordLabel = new JLabel("Contraseña");
		 passwordLabel.setBounds(200, 470-(10*k), 150, 20);
		
		 // Definition and settings of confirmPassword label
		 confirmPasswordLabel = new JLabel("Confirmar contraseña");
		 confirmPasswordLabel.setBounds(200, 520-(10*k), 150, 20);
		 
		 f.add(idTypeLabel);
	     f.add(idLabel);
	     f.add(namesLabel);
	     f.add(lastNamesLabel);
	     f.add(emailLabel);
	     f.add(residenceAddressLabel);
	     f.add(residenceCityLabel);
	     f.add(phoneLabel);
	     f.add(passwordLabel);
	     f.add(confirmPasswordLabel);
	}
}
