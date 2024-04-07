package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modelos.User;

public class UserData {
	private List<User> usersList = new ArrayList<>();
	
	public UserData() {
		
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
	
	public List<User> getUser(String username) {
		List<User> userExistence = usersList.stream()
												.filter(e -> e.getEmail().equals(username))
												.collect(Collectors.toList());
		return userExistence;
	}
	
	public void addUser(String idType, 
				String id, 
				String names, 
				String lastNames,
				String email,
				String residenceAddress,
				String residenceCity,
				String phone,
				String password) {
		usersList.add(new User(idType, id, names, lastNames, email, residenceAddress, residenceCity, phone, password));
	}
	
	public void addUser(User user) {
		usersList.add(user);
	}
}

