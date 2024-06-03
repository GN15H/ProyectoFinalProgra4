package views;

import java.awt.event.*;
import java.util.Arrays;

import javax.swing.*;
import controlador.Validator;
import controlador.RegisterValidator;
import controlador.UserController;
import modelos.User;
import modelos.states.RegisterStates;

public class RegisterView {
	private final int k = 4;
		
	final UserController userController = new UserController();
	final Validator<User, RegisterStates> registerValidator = new RegisterValidator();
	
	final String[] idTypes = {"T.I","C.C"}; //list of id types
	JFrame f = new JFrame("Register"); 
	JComboBox<String> idTypeField;
	JTextField idField, namesField, lastNamesField, emailField,
				residenceAddressField, residenceCityField,
				phoneField;
	JPasswordField passwordField, confirmPasswordField;
	
	
	public RegisterView() {	
		addLabels();
	    addFields();
	    addButtons();	    
	    
	    f.setTitle("Register");
	    f.setSize(600, 650);
	    f.setResizable(false);
	    f.setLayout(null);
	    f.setVisible(true);
    } 
	
	private void registerHandler() {
		final RegisterStates registerState = registerValidator.validate(Arrays.asList(
				idTypeField.getItemAt(idTypeField.getSelectedIndex()),
				idField.getText(),
				namesField.getText(),
				lastNamesField.getText(),
				emailField.getText(),
				residenceAddressField.getText(),
				residenceCityField.getText(),
				phoneField.getText(),
				new String(passwordField.getPassword()),
				new String(confirmPasswordField.getPassword())));
		
		switch(registerState) {
		case emptyFields:
			JOptionPane.showMessageDialog(f, "Tiene campos vacios", "Error", JOptionPane.CLOSED_OPTION);
			return;
		case wrongPassword:
			JOptionPane.showMessageDialog(f, "Las contraseñas no concuerdan", "Error", JOptionPane.CLOSED_OPTION);
			return;
		case verified:
			User user = registerValidator.parseObject(Arrays.asList(
					idTypeField.getItemAt(idTypeField.getSelectedIndex()),
					idField.getText(),
					namesField.getText(),
					lastNamesField.getText(),
					emailField.getText(),
					residenceAddressField.getText(),
					residenceCityField.getText(),
					phoneField.getText(),
					new String(passwordField.getPassword()),
					new String(confirmPasswordField.getPassword())));
			userController.addElement(user);
			JOptionPane.showMessageDialog(f, "Registro exitoso", "Éxito", JOptionPane.CLOSED_OPTION);
			f.dispose();
			@SuppressWarnings("unused")
			LoginView loginView = new LoginView();
			return;
		default:
			break;
				
		}
	}
	
	private void addButtons() {
		JButton registerButton, loginButton;
		
		registerButton = new JButton("Registrarse");
		registerButton.setBounds(210,570-(10*k),180, 20);
		registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registerHandler();
        	}
        		
        });
		
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
	    	
		 idTypeLabel = new JLabel("Tipo de identificación");
		 idTypeLabel.setBounds(200, 70-(10*k), 150, 20);
		
		 idLabel = new JLabel("ID");
		 idLabel.setBounds(200, 120-(10*k), 150, 20);
		
		 namesLabel = new JLabel("Nombres");
		 namesLabel.setBounds(200, 170-(10*k), 150, 20);
		
		 lastNamesLabel = new JLabel("Apellidos");
		 lastNamesLabel.setBounds(200, 220-(10*k), 150, 20);
		
		 emailLabel = new JLabel("Correo electrónico");
		 emailLabel.setBounds(200, 270-(10*k), 150, 20);
		
		 residenceAddressLabel = new JLabel("Dirección de residencia");
		 residenceAddressLabel.setBounds(200, 320-(10*k), 150, 20);
		
		 residenceCityLabel = new JLabel("Ciudad de residencia");
		 residenceCityLabel.setBounds(200, 370-(10*k), 150, 20);
		
		 phoneLabel = new JLabel("Teléfono");
		 phoneLabel.setBounds(200, 420-(10*k), 150, 20);
		
		 passwordLabel = new JLabel("Contraseña");
		 passwordLabel.setBounds(200, 470-(10*k), 150, 20);
		
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
