package views.widgets;

import javax.swing.*;

import modelos.Room;

import java.awt.*;

public class RoomCellRenderer extends JPanel implements ListCellRenderer<Room> {

	private static final long serialVersionUID = 1L;
	private JLabel idLabel;
    private JLabel capacityLabel;
    private JLabel priceLabel;
    private JLabel comfortLabel;
    private JLabel roomTypeLabel;

    public RoomCellRenderer() {
        setLayout(new GridLayout(6, 1));

        idLabel = new JLabel();
        capacityLabel = new JLabel();
        priceLabel = new JLabel();
        comfortLabel = new JLabel();
        roomTypeLabel = new JLabel();

        add(idLabel);
        add(capacityLabel);
        add(priceLabel);
        add(comfortLabel);
        add(roomTypeLabel);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Room> list,
            Room room,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        idLabel.setText("ID: " + room.getId());
        capacityLabel.setText("Capacity: " + room.getCapacity());
        priceLabel.setText("Price: $" + room.getPrice());
        comfortLabel.setText("Comfort: " + room.getComfort());
        roomTypeLabel.setText("Room Type: " + room.getRoomType());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
    }
}
