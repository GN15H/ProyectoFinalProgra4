package views;

import javax.swing.*;

public class HomeView {

	public HomeView() {
		JFrame homeView = new JFrame();
		
		JLabel mainLabel = new JLabel("Bienvenido a MyHotel");
		mainLabel.setBounds(125,125,200,100);
		
		homeView.add(mainLabel);
		
		homeView.setTitle("Home");
		homeView.setSize(400,400);
		homeView.setLayout(null);
		homeView.setVisible(true);
		
	}
}
