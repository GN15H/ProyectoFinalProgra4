package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import main.ProyectoFinal;
import modelos.Booking;
import modelos.Constants;
import modelos.Room;
import modelos.RoomFilter;
import modelos.RoomType;
import modelos.User;

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
	
	private List<Room> getRoomsByCapacity(int capacity, List<Room> list) {
        return list.stream()
                .filter(e -> e.getCapacity() >= capacity)
                .collect(Collectors.toList());
    }

    private List<Room> getRoomsByRoomType(RoomType roomType, List<Room> list) {
        return list.stream()
                .filter(e -> e.getRoomType() == roomType)
                .collect(Collectors.toList());
    }

    private List<Room> getRoomsByPrice(double price, List<Room> list) {
        return list.stream()
                .filter(e -> e.getPrice() <= price)
                .collect(Collectors.toList());
    }

	private List<Room> getAvailableRoomsByDate(LocalDate arrivalDate, LocalDate departureDate) {
		BookingsController bookingsController = new BookingsController();
		List<Booking> bookings = bookingsController.getBookingsByDate(arrivalDate, departureDate);
		List<Room> bookedRoomsByDate = bookings.stream().map(Booking::getRoom).collect(Collectors.toCollection(ArrayList::new));
		List<Room> freeRooms =  roomsList;
		freeRooms.removeAll(bookedRoomsByDate);
		return freeRooms;
	}
	
	private List<Room> getAvailableRoomsByDate(LocalDate arrivalDate, LocalDate departureDate, User user){
		BookingsController bookingsController = new BookingsController();
		List<Booking> bookings = bookingsController.getBookingsByDate(arrivalDate, departureDate);
		List<Booking> userBookings = bookingsController.getUserBookings(user);
		bookings.removeAll(userBookings);
		List<Room> bookedRoomsByDate = bookings.stream().map(Booking::getRoom).collect(Collectors.toCollection(ArrayList::new));
		List<Room> freeRooms =  roomsList;
		freeRooms.removeAll(bookedRoomsByDate);
		return freeRooms;
	}
	
	public List<Room> getFilteredRooms(RoomFilter roomFilter){
		List<Room> filteredRooms = getAvailableRoomsByDate(roomFilter.getArrivalDate(),roomFilter.getDepartureDate());
		ProyectoFinal.printArray(filteredRooms);
		filteredRooms = getRoomsByRoomType(roomFilter.getRoomType(), filteredRooms);
		ProyectoFinal.printArray(filteredRooms);
		filteredRooms = getRoomsByCapacity(roomFilter.getCapacity(), filteredRooms);
		ProyectoFinal.printArray(filteredRooms);
		filteredRooms = getRoomsByPrice(roomFilter.getPrice(), filteredRooms);
		return filteredRooms;
	}
	
	public boolean isRoomAvailable(Room room, LocalDate arrivalDate, LocalDate departureDate) {
		List<Room> freeRooms = getAvailableRoomsByDate(arrivalDate, departureDate);
		return !freeRooms.stream()
				.filter(e -> e.getId() == room.getId())
				.toList().isEmpty();
	}
	
	public boolean isRoomAvailable(Room room, LocalDate arrivalDate, LocalDate departureDate, User user) {
		List<Room> freeRooms = getAvailableRoomsByDate(arrivalDate, departureDate, user);
		return !freeRooms.stream()
				.filter(e -> e.getId() == room.getId())
				.toList().isEmpty();
	}
}
