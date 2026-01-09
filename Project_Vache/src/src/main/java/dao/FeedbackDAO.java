package dao;

import model.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public boolean add(Feedback f) {
        String sql = """
            INSERT INTO feedback(reservation_id,customer_id,rating,comment,created_at)
            VALUES(?,?,?,?,CURRENT_TIMESTAMP)
        """;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getReservationId());
            ps.setInt(2, f.getCustomerId());
            ps.setInt(3, f.getRating());
            ps.setString(4, f.getComment());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public List<Feedback> findAll() {
        List<Feedback> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             ResultSet rs = con.createStatement()
                     .executeQuery("SELECT * FROM feedback")) {

            while (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackId(rs.getInt("feedback_id"));
                f.setRating(rs.getInt("rating"));
                f.setComment(rs.getString("comment"));
                f.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(f);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

	public List<Feedback> getAllFeedback() {
		return null;
	}
}
