package views;
import java.awt.event.*;

import javax.swing.*;
import modelos.states.LoginStates;
import controlador.Login;
import controlador.UserController;

public class LoginView  
{  
	private UserController userController = new UserController();
	private Login login = new Login();
	private JFrame f;
	private JLabel usernameLabel, passwordLabel, loginLabel;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton, registerButton;
	
	private void addLabels() {        
        loginLabel = new JLabel("Inicio de sesión");
        loginLabel.setBounds(250, 100, 100, 20);
        
        usernameLabel = new JLabel("Usuario");
        usernameLabel.setBounds(200, 180, 100, 20);
        
        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(200, 260, 100, 20);
        
        f.add(usernameLabel);
        f.add(passwordLabel);
        f.add(loginLabel);

	}
	
	private void addTextFields() {
        usernameTextField = new JTextField();
        usernameTextField.setBounds(200, 200, 200,30);
        
        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(200, 280, 200,30);
        
        f.add(usernameTextField);
        f.add(passwordTextField);
	}
	
	
	
	private void addButtons() {
		 loginButton = new JButton("Ingresar");
	        loginButton.setBounds(250,350,100,20);
	        loginButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		loginHandler();
	        	}
	        });
	        
	        registerButton = new JButton("Registrarse");
	        registerButton.setBounds(240,380,120,20);
	        registerButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		f.dispose();
	        		@SuppressWarnings("unused")
					RegisterView registerView = new RegisterView();
	        	}	
	        });
	        

	        f.add(loginButton);
	        f.add(registerButton);
	}
	
	private void loginHandler() {
		LoginStates loginState = login.verifyUser(usernameTextField.getText(), new String(passwordTextField.getPassword()));
		
		switch (loginState) {
			case emptyFields:
				JOptionPane.showMessageDialog(f, "Tiene campos vacios", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case wrongUser:
				JOptionPane.showMessageDialog(f, "El usuario ingresado no existe", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case wrongPassword:
				JOptionPane.showMessageDialog(f, "Contraseña incorrecta", "Error", JOptionPane.CLOSED_OPTION);
				return;
			case verified:
				f.dispose();
				@SuppressWarnings("unused")
				HomeView homeView = new HomeView(userController.getElementById(usernameTextField.getText()).get());
		default:
			break;
				
		}
	}
	
     public LoginView(){  
    	
        f = new JFrame();  
        
        addLabels();
        addTextFields();
        addButtons();

        f.setTitle("Login");
        f.setResizable(false);
        f.setSize(600,550);  
        f.setLayout(null);  
        f.setVisible(true);  
     }  
}  