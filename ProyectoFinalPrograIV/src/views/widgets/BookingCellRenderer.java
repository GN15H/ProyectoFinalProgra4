package views.widgets;

import javax.swing.*;

import modelos.Booking;

import java.awt.*;

public class BookingCellRenderer extends JPanel implements ListCellRenderer<Booking> {

	private static final long serialVersionUID = 1L;
	private JLabel idLabel;
    private JLabel guestAmountLabel;
    private JLabel arrivalDateLabel;
    private JLabel departureDateLabel;
    private JLabel userLabel;
    private JLabel roomLabel;

    public BookingCellRenderer() {
        setLayout(new GridLayout(7, 1));

        idLabel = new JLabel();
        guestAmountLabel = new JLabel();
        arrivalDateLabel = new JLabel();
        departureDateLabel = new JLabel();
        userLabel = new JLabel();
        roomLabel = new JLabel();

        add(idLabel);
        add(guestAmountLabel);
        add(arrivalDateLabel);
        add(departureDateLabel);
        add(userLabel);
        add(roomLabel);
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Booking> list,
            Booking booking,
            int index,
            boolean isSelected,
            boolean cellHasFocus) {

        idLabel.setText("ID: " + booking.getId());
        guestAmountLabel.setText("Huespedes: " + booking.getGuestAmount());
        arrivalDateLabel.setText("Fecha de llegada: " + booking.getArrivalDate());
        departureDateLabel.setText("Fecha de salida: " + booking.getDepartureDate());
        userLabel.setText("Usuario: " + booking.getUser().getNames() + " " + booking.getUser().getLastNames());
        roomLabel.setText("Habitación: " + booking.getRoom().getId());

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
