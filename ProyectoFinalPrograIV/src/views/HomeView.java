package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;

import javax.swing.*;

import entities.UserType;
import modelos.User;

public class HomeView {
	private User user;
	JMenu bookings, rooms, users;  
    JMenuItem book, seeBooks, booksHistory, seeUsers, seeRooms;  
    JFrame homeView;
    JMenuBar mainMenuBar;
	
	public HomeView(User user) {
		this.user = user;
		homeView = new JFrame();
		
		addMenuBarItems();
		  
		homeView.setTitle("Home "+ user.getNames()+" "+ user.getLastNames()+" "+ user.getUserType().getValue());
		homeView.setResizable(false);
		homeView.setSize(800,800);
		homeView.setLayout(null);
		homeView.setVisible(true);
		
	}
	
	private void addMenuBarItems() {
		mainMenuBar=new JMenuBar();  
        bookings =new JMenu("Reservas");  
        rooms = new JMenu("Habitaciones");  
        users = new JMenu("Usuarios");
        book = new JMenuItem("Realizar reserva");  
        seeBooks = new JMenuItem("Ver reservas");  
        booksHistory =new JMenuItem("Historial de reservas");  
        seeUsers = new JMenuItem("Ver usuarios");  
        seeRooms = new JMenuItem("Ver habitaciones");  
        bookings.add(book); bookings.add(seeBooks); bookings.add(booksHistory);  
        rooms.add(seeRooms);  
        //menu.add(submenu);  
        mainMenuBar.add(bookings);
        mainMenuBar.add(rooms);
        users.add(seeUsers);
        if(user.getUserType() == UserType.admin) {
       	 mainMenuBar.add(users);        	 
        }
		
        
        book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(BookView.class);
            }
        });
        
        seeBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(BookingsView.class);
            }
        });
        
        booksHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(BookingsHistory.class);
            }
        });
        
        seeRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(RoomsView.class);
            }
        });
        
        seeUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(UsersView.class);
            }
        });
        
		homeView.setJMenuBar(mainMenuBar); 
	}
	
	private <T> void goToView(Class<T> view) {
		try {
			Constructor<T> constructor = view.getDeclaredConstructor();
			//homeView.dispose();
			T newView = constructor.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
