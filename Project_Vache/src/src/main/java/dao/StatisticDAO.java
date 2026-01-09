package dao;

import model.Statistic;
import java.sql.*;

public class StatisticDAO {

    public Statistic getStatistic() {
        Statistic s = new Statistic();
        try (Connection con = DBConnection.getConnection()) {

            ResultSet r1 = con.createStatement()
                    .executeQuery("SELECT COUNT(*) FROM reservations");
            if (r1.next()) s.setTotalReservations(r1.getInt(1));

            ResultSet r2 = con.createStatement()
                    .executeQuery("SELECT COUNT(*) FROM reservations WHERE status='completed'");
            if (r2.next()) s.setCompletedReservations(r2.getInt(1));

            ResultSet r3 = con.createStatement()
                    .executeQuery("SELECT SUM(guests*300000) FROM reservations WHERE status='completed'");
            if (r3.next()) s.setEstimatedRevenue(r3.getDouble(1));

            ResultSet r4 = con.createStatement()
                    .executeQuery("SELECT AVG(rating) FROM feedback");
            if (r4.next()) s.setAvgRating(r4.getDouble(1));

        } catch (Exception e) { e.printStackTrace(); }
        return s;
    }
}
