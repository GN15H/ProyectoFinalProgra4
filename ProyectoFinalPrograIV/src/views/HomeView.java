package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Optional;

import javax.swing.*;

import controlador.RoomsController;
import modelos.UserType;
import modelos.User;
import views.adminViews.CreateRoom;
import views.userViews.BookView;
import views.userViews.RoomFilterView;

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
		JMenu exit = new JMenu("Salir");
		JMenuItem seeRooms = new JMenuItem("Buscar habitación");
		JMenuItem createRoom = new JMenuItem("Crear habitación");
		JMenuItem logOut = new JMenuItem("Cerrar Sesión");
		
		rooms.add(seeRooms); rooms.add(createRoom);
		exit.add(logOut);
		
		mainMenuBar.add(rooms);
		mainMenuBar.add(exit);
		
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
        
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	homeView.dispose();
                goToView(LoginView.class);
            }
        });
        
	}
		
	void addUserFunctionalities() {
		JMenu rooms = new JMenu("Habitaciones"); 
		JMenu bookings =new JMenu("Reservas");  
		JMenu exit = new JMenu("Salir");

		JMenuItem searchRooms = new JMenuItem("Buscar habitaciones"); 
		JMenuItem book = new JMenuItem("Realizar reserva");  
		JMenuItem seeBooks = new JMenuItem("Ver reservas");  
		JMenuItem logOut = new JMenuItem("Cerrar Sesión");

		
        bookings.add(seeBooks);
        rooms.add(searchRooms); 
        exit.add(logOut);
        mainMenuBar.add(bookings);
        mainMenuBar.add(rooms);
        mainMenuBar.add(exit);
        
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
        
        searchRooms.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                goToView(RoomFilterView.class);
            }
        });
        
        logOut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	homeView.dispose();
                goToView(LoginView.class);
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
				RoomsController roomsController = new RoomsController();
				constructor = view.getDeclaredConstructor(User.class, List.class);
				newView = constructor.newInstance(user, roomsController.getAll());
			}else if(view == RoomFilterView.class) {
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
