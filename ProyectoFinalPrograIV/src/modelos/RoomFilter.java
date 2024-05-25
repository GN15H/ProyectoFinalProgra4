package modelos;

import java.time.LocalDate;

public class RoomFilter {
	private RoomType roomType;
	private int capacity;
	private double price;
	private LocalDate arrivalDate;
	private LocalDate departureDate;
	
	public RoomFilter(
		RoomType roomType,
		int capacity,
		double price,
		LocalDate arrivalDate,
		LocalDate departureDate
		) {
		this.roomType = roomType;
		this.capacity = capacity;
		this.price = price;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
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
	
}
