package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.Optional;

import javax.swing.*;

import entities.UserType;
import modelos.User;
import views.adminViews.CreateRoom;
import views.adminViews.UsersView;
import views.userViews.BookView;
import views.userViews.BookingsHistory;

public class HomeView {
	private User user;
	//JMenu bookings, rooms, users;  
    //JMenuItem book, seeBooks, booksHistory, seeUsers, searchRooms;  
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
        

        if(user.getUserType() == UserType.admin) {
       	 	addAdminFunctionalities();
        }else {
        	addUserFunctionalities();
        }
		
        
		homeView.setJMenuBar(mainMenuBar); 
	}
	
	void addAdminFunctionalities() {
		JMenu rooms = new JMenu("Habitaciones");
		JMenu users = new JMenu("Usuarios");
		JMenuItem seeRooms = new JMenuItem("Buscar habitación");
		JMenuItem createRoom = new JMenuItem("Crear habitación");
		JMenuItem seeUsers = new JMenuItem("Ver usuarios");
		
		rooms.add(seeRooms); rooms.add(createRoom);
		users.add(seeUsers);
		
		mainMenuBar.add(rooms);
		mainMenuBar.add(users);
		
		seeRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(RoomsView.class);
            }
        });
        
        //action listener for seeBooks button
        createRoom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(CreateRoom.class);
            }
        });
        
        seeUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(UsersView.class);
            }
        });
        
	}
		
	void addUserFunctionalities() {
		JMenu rooms = new JMenu("Habitaciones"); 
		JMenu bookings =new JMenu("Reservas");  
		JMenuItem searchRooms = new JMenuItem("Buscar habitaciones"); 
		JMenuItem book = new JMenuItem("Realizar reserva");  
		JMenuItem seeBooks = new JMenuItem("Ver reservas");  
		JMenuItem booksHistory =new JMenuItem("Historial de reservas");  
        
        /*bookings.add(book);*/ bookings.add(seeBooks); bookings.add(booksHistory);  
        rooms.add(searchRooms);  
        mainMenuBar.add(bookings);
        mainMenuBar.add(rooms);
        
        book.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(BookView.class);
            }
        });
        
        //action listener for seeBooks button
        seeBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(BookingsView.class);
            }
        });
        
        //action listener for booksHistory button
        booksHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(BookingsHistory.class);
            }
        });
        
        //action listener for seeRooms button
        searchRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(RoomsView.class);
            }
        });
		
	}
	
	//function that receives a class (view) and instances it
	private <T> void goToView(Class<T> view) {
		try {
			Constructor<T> constructor;
			@SuppressWarnings("unused")
			T newView;
			
			if (view == CreateRoom.class) {
				constructor = view.getDeclaredConstructor(Optional.class);
				newView = constructor.newInstance(Optional.empty());
			}else if (view == RoomsView.class) {
				constructor = view.getDeclaredConstructor(User.class);
				newView = constructor.newInstance(user);
			} else if(view == BookingsView.class) {
				System.out.println("ver reservas");
				constructor = view.getDeclaredConstructor(User.class);
				newView = constructor.newInstance(user);
			}else {
				constructor = view.getDeclaredConstructor();
				newView = constructor.newInstance();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
