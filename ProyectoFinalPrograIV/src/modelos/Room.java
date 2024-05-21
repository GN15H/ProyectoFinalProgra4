package modelos;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private int capacity;
	private double price;
	private String comfort;
	private RoomType roomType;
	
	public Room(int capacity, double price, String comfort, RoomType roomType) {
		this.capacity = capacity;
		this.price = price;
		this.comfort = comfort;
		this.roomType = roomType;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getComfort() {
		return comfort;
	}
	public void setComfort(String comfort) {
		this.comfort = comfort;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Room other = (Room) obj;
        return id == other.id &&
               capacity == other.capacity &&
               Double.compare(other.price, price) == 0 &&
               Objects.equals(comfort, other.comfort) &&
               Objects.equals(roomType, other.roomType);
    }
}
