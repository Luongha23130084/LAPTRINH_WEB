package service;

import dao.ReservationDAO;
import model.Reservation;
import java.util.List;

public class ReservationManager {
    private ReservationDAO dao = new ReservationDAO();

    public boolean book(Reservation r) {
        return dao.create(r);
    }

    public List<Reservation> getCustomerReservations(int customerId) {
        return dao.findByCustomer(customerId);
    }

    public List<Reservation> getPendingReservations() {
        return dao.findPending();
    }

    public void confirm(int id) {
        dao.updateStatus(id, "confirmed");
    }

    public void cancel(int id) {
        dao.updateStatus(id, "cancelled");
    }
}
