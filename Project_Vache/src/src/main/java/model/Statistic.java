package model;

public class Statistic {
    private int totalReservations;
    private int completedReservations;
    private double estimatedRevenue;
    private double avgRating;
	public int getTotalReservations() {
		return totalReservations;
	}
	public void setTotalReservations(int totalReservations) {
		this.totalReservations = totalReservations;
	}
	public int getCompletedReservations() {
		return completedReservations;
	}
	public void setCompletedReservations(int completedReservations) {
		this.completedReservations = completedReservations;
	}
	public double getEstimatedRevenue() {
		return estimatedRevenue;
	}
	public void setEstimatedRevenue(double estimatedRevenue) {
		this.estimatedRevenue = estimatedRevenue;
	}
	public double getAvgRating() {
		return avgRating;
	}
	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
    
    
}
