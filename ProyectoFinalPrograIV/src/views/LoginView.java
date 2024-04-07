package views;
import java.awt.event.*;
import javax.swing.*;
import entities.UserData;
import main.Login;

public class LoginView  
{  
	private Login login;
	private UserData userData;
	
     public LoginView(UserData userData){  
    	this.userData = userData;
    	login = new Login(this.userData);
    	System.out.println(this.userData.getUsersList().get(0).getEmail() + " " + this.userData.getUsersList().get(0).getPassword());
    	
        JFrame f= new JFrame();  
        
        //declaration of login and password labels
        JLabel usernameLabel, passwordLabel, loginLabel;
        
        //definition and settings of username label
        loginLabel = new JLabel("Inicio de sesión");
        loginLabel.setBounds(250, 100, 100, 20);
        
        //definition and settings of username label
        usernameLabel = new JLabel("Usuario");
        usernameLabel.setBounds(200, 180, 100, 20);
        
        //definition and settings of password label
        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(200, 260, 100, 20);

        
        //definition and settings of username textfield
        JTextField usernameTextField = new JTextField();
        usernameTextField.setBounds(200, 200, 200,30);
        
        
        //definition and settings of password textfield
        JPasswordField passwordTextField = new JPasswordField();
        passwordTextField.setBounds(200, 280, 200,30);
        
        
        //definition and settings of password textfield
        JButton loginButton = new JButton("Ingresar");
        loginButton.setBounds(250,350,100,20);
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {

        		if(login.verifyUser(usernameTextField.getText(), new String(passwordTextField.getPassword()))) {
        			f.dispose();
        			@SuppressWarnings("unused")
        			HomeView homeView = new HomeView();
        		}else {
        			JOptionPane.showMessageDialog(f, "El usuario ingresado es incorrecto o no existe", "Error", JOptionPane.CLOSED_OPTION);
        		}
        	}
        });
        
        //definition and settings of password textfield
        JButton registerButton = new JButton("Registrarse");
        registerButton.setBounds(240,380,120,20);
        registerButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		f.dispose();
        		@SuppressWarnings("unused")
				RegisterView registerView = new RegisterView(userData);
        	}
        		
        });
        
        
        
        //adding elements to frame
        f.add(usernameTextField);
        f.add(passwordTextField);
        f.add(loginButton);
        f.add(registerButton);
        f.add(usernameLabel);
        f.add(passwordLabel);
        f.add(loginLabel);
        
        //frame settings
        f.setTitle("Login");
        f.setSize(600,550);  
        f.setLayout(null);  
        f.setVisible(true);  
     }  
}  