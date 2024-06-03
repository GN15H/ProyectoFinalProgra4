package controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public abstract class CrudController<T, V> {
	private String fileName;
	
	public CrudController(String fileName) {
		this.fileName = fileName;
	}
	
	public List<T> getAll(){
		List<T> itemsList = readObjectsFromFile(fileName);
		return itemsList;
	}
	
	public abstract Optional<T> getElementById(V id);
	
	public void updateElement(T element, T updatedElement) {
		List<T> itemsList = readObjectsFromFile(fileName);
		itemsList.remove(element);
		itemsList.add(updatedElement);
		storeObjectsToFile(fileName, itemsList);
	}
	
	public void deleteElement(T element) {
		List<T> itemsList = readObjectsFromFile(fileName);
		itemsList.remove(element);
		storeObjectsToFile(fileName, itemsList);
	}
	
	public void addElement(T element) {  
		List<T> itemsList = readObjectsFromFile(fileName);
		itemsList.add(element);
		storeObjectsToFile(fileName, itemsList);
	}
	
	private void storeObjectsToFile(String fileName, List<T> list) {
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
	
	@SuppressWarnings("unchecked")
	private List<T> readObjectsFromFile(String fileName) {
	    List<T> list = new ArrayList<>();
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
	        list = (List<T>) ois.readObject();
	    } catch (FileNotFoundException e) {
	        // file doesnt exist
	        createFileIfNotExists(fileName);
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
	            list = (List<T>) ois.readObject();
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
	
}
