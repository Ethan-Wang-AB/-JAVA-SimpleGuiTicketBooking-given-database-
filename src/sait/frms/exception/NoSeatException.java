package sait.frms.exception;

import sait.frms.problemdomain.Flight;
/**
*	Class description:This class is about no available seats exceptions
*
*
*/
public class NoSeatException  extends Exception {

private Flight flight;


  public NoSeatException(Flight flight){
	super("No seat available for this flight"+ flight.getCode());
	 }

  public String getFlight(String flight) {
	  return flight;
  } 
  public String toString() {
	  String s="The flight "+flight+" has no seat right now";
	  return s;
  }
}
