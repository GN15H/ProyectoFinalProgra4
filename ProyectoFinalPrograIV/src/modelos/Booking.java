package modelos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Booking implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int guestAmount;
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	private User user;
	private Room room;
	
	public Booking(
			int guestAmount, 
			LocalDate arrivalDate, 
			LocalDate departureDate, 
			User user, 
			Room room) 
	{
		this.guestAmount = guestAmount;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.user = user;
		this.room = room;
	}
	
	public Booking(
			int id,
			int guestAmount, 
			LocalDate arrivalDate, 
			LocalDate departureDate, 
			User user, 
			Room room) 
	{
		this.id = id;
		this.guestAmount = guestAmount;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.user = user;
		this.room = room;
	}
	
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
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
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
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Booking other = (Booking) obj;
        return id == other.id &&
               guestAmount == other.guestAmount &&
               Objects.equals(arrivalDate, other.arrivalDate) &&
               Objects.equals(departureDate, other.departureDate) &&
               Objects.equals(user, other.user) &&
               Objects.equals(room, other.room);
    }
	
	@Override
	public String toString() {
		return this.id +" "+this.arrivalDate+" "+this.departureDate;
	}
}
