package controlador;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modelos.User;

public class UserController {

	public UserController() {
		
	}
	
	public void storeObjectsToFile(String fileName, List<User> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
            System.out.println("escribiendo en el archivo");
        } catch (IOException e) {
        	createFileIfNotExists(fileName);
            // Retry storing objects after file creation
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(list);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	
	public List<User> readObjectsFromFile(String fileName) {
	    List<User> list = new ArrayList<>();
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
	        list = (List<User>) ois.readObject();
	    } catch (FileNotFoundException e) {
	        // file doesnt exist
	        createFileIfNotExists(fileName);
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
	            list = (List<User>) ois.readObject();
	        } catch (IOException | ClassNotFoundException ex) {
	            ex.printStackTrace();
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	 private void createFileIfNotExists(String fileName) {
	        File file = new File(fileName);
	        if (!file.exists()) {
	            try {
	                file.createNewFile();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	
	public List<User> getUser(String username, List<User> usersList) {
		List<User> userExistence = usersList.stream()
												.filter(e -> e.getEmail().equals(username))
												.collect(Collectors.toList());
		return userExistence;
	}
	
	
}
