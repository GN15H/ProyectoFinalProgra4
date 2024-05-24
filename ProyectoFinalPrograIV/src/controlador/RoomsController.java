package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import modelos.Booking;
import modelos.Constants;
import modelos.Room;
import modelos.RoomType;

public class RoomsController extends CrudController<Room, Integer>{

	private List<Room> roomsList;
	
	public RoomsController() {
		super(Constants.ROOMS_FILE);
		roomsList = super.getAll();
	}

	@Override
	public List<Room> getAll(){
		return this.roomsList;
	}
	
	@Override
	public void addElement(Room element) {
		int id = roomsList.size() + 1;
		element.setId(id);
		super.addElement(element);
	}
	
	@Override
	public Optional<Room> getElementById(Integer id) {
		return roomsList.stream()
				.filter(e -> e.getId() == id)
				.findFirst();
	}
	
	@Override
	public void updateElement(Room room, Room updatedRoom) {
		updatedRoom.setId(room.getId());
		super.updateElement(room, updatedRoom);
		
	}
	
	public List<Room> getRoomsByCapacity(int capacity){
		return roomsList.stream()
				.filter(e -> e.getCapacity() >= capacity)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public List<Room> getRoomsByRoomType(RoomType roomType) {
		return roomsList.stream()
				.filter(e -> e.getRoomType() == roomType)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public List<Room> getAvailableRoomsByDate(LocalDate arrivalDate, LocalDate departureDate) {
		BookingsController bookingsController = new BookingsController();
		List<Booking> bookings = bookingsController.getBookingsByDate(arrivalDate, departureDate);
		List<Room> bookedRoomsByDate = bookings.stream().map(Booking::getRoom).collect(Collectors.toCollection(ArrayList::new));
		List<Room> freeRooms =  roomsList;
		freeRooms.removeAll(bookedRoomsByDate);
		return freeRooms;
	}
	
	public boolean isRoomAvailable(Room room, LocalDate arrivalDate, LocalDate departureDate) {
		List<Room> freeRooms = getAvailableRoomsByDate(arrivalDate, departureDate);
		return !freeRooms.stream()
				.filter(e -> e.getId() == room.getId())
				.toList().isEmpty();
	}
}
