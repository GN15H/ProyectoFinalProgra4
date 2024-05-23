package controlador;

import java.util.List;
import java.util.Optional;

import modelos.Constants;
import modelos.Room;

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
	

}
