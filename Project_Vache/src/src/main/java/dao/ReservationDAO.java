package dao;


import model.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public boolean create(Reservation r) {
        String sql = """
            INSERT INTO reservations(customer_id,reservation_date,reservation_time,
            guests,area,status)
            VALUES(?,?,?,?,?,'pending')
        """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, r.getCustomerId());
            ps.setDate(2, r.getReservationDate());
            ps.setTime(3, r.getReservationTime());
            ps.setInt(4, r.getGuests());
            ps.setString(5, r.getArea());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<Reservation> findByCustomer(int customerId) {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE customer_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setReservationId(rs.getInt("reservation_id"));
                r.setReservationDate(rs.getDate("reservation_date"));
                r.setReservationTime(rs.getTime("reservation_time"));
                r.setGuests(rs.getInt("guests"));
                r.setArea(rs.getString("area"));
                r.setStatus(rs.getString("status"));
                list.add(r);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<Reservation> findPending() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE status='pending'";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = new Reservation();
                r.setReservationId(rs.getInt("reservation_id"));
                r.setCustomerId(rs.getInt("customer_id"));
                r.setReservationDate(rs.getDate("reservation_date"));
                r.setReservationTime(rs.getTime("reservation_time"));
                r.setGuests(rs.getInt("guests"));
                r.setArea(rs.getString("area"));
                r.setStatus(rs.getString("status"));
                list.add(r);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void updateStatus(int id, String status) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps =
                     con.prepareStatement("UPDATE reservations SET status=? WHERE reservation_id=?")) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
}
