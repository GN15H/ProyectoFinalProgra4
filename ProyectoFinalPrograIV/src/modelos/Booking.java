package modelos;

import java.util.Date;

public class Booking {

	private int id;
	private int guestAmount;
	private Date arrivalDate;
	private Date departureDate;
	private User user;
	private Room room;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGuestAmount() {
		return guestAmount;
	}
	public void setGuestAmount(int guestAmount) {
		this.guestAmount = guestAmount;
	}
	public Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
}
