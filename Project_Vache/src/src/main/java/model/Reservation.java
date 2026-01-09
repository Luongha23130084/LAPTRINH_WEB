package model;

import java.sql.Date;
import java.sql.Time;

public class Reservation {
      private int  reservationId;
      private int customerId;
      private Date reservationDate;
      private Time reservationTime;
      private int guests;
      private String area;
      private String status;    


    public int getReservationId() {
    	return reservationId;
    }
    public void setReservationId(int reservationId) {
    	this.reservationId = reservationId;
   }
    public int getCustomerId() {
    	return customerId; 
    		
    	}
    public void setCustomerId( int customerId) {
    	this.customerId =  customerId;
    	
    }
    public Date getReservationDate() {
    	return reservationDate;
    }
    public void setReservationDate( Date reservationDate) {
    	this.reservationDate = reservationDate;
    	
    }
    public Time getReservationTime() {
    	return reservationTime;
    	
    }
    public void setReservationTime( Time reservationTime) {
    	this.reservationTime= reservationTime;
    }
    public int getGuests() {
    	return guests;
    	
    }
     public void setGuests(int guests) {
    	 this.guests = guests;
    	 
     }
     public String getArea() {
    	 return area;
     }
     public void setArea (String area) {
    	 this.area =  area;
     }
      public String getStatus() {
    	  return status;
    	  
      }
      public void setStatus( String status) {
    	  this.status = status;
      }
}