package sait.frms.problemdomain;

import sait.frms.exception.NoSeatException;


/**
*	Class description: This is the class for flight information
*
*    the parameter for if it is domestic, need to be calculated by the method in this class
*
*/
public class Flight implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code = "";
	private String airlingName = "";
	private String from = "";
	private String to = "";
	private String weekday = "";
	private String time = "";
	private int seats;
	private double costPerSeat;
	private boolean domestic;
	public Flight() {
		
	}
	/**
	*	Initializes the newly created Flight
	*	@param code for the flight code
	*	@param from for the airport it starts from
	*	@param to for the airport the flight is going to
	*	@param weekday for the weekday of the flight line
	*	@param time for the time of the flight line
	*	@param seats for the available seats of the flight
	*	@param costPerSeat for the price fo the tickets
	*   
	*/
	public Flight(String code, String from, String to, String weekday, String time, int seats, double costPerSeat) {
		this.code = code;
		parseCode(code);
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
		this.setDomestic();

	}

	public String getCode() {
		return code;
	}

	public String getAirlingName() {
		if (airlingName.equals("OA")) {
			return "Otto Airlines";}
		else if (airlingName.equals("CA")) {
			return "Conned Air";}
		else if (airlingName.equals("TB")) {
			return " Try a Bus Airways";}
		else if (airlingName.equals("VA")) {
			return "Vertical Airways";}
		else return "";
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getWeekday() {
		return weekday;
	}

	public String getTime() {
		return time;
	}

	public int getSeats() {
		return seats;
	}

	public double getCostPerSeat() {
		return costPerSeat;
	}

	public boolean isDomestic() {
	
		return domestic;
	}

	public void bookSeats() throws NoSeatException {
		
		if (this.seats >= 1) {
			this.seats--;
		} else {
			throw new NoSeatException(this);
		}

	}
    public void cancelBooking() {
		
		this.seats++;

	}

	private void parseCode(String code) {
		this.airlingName = code.substring(0, 2);
	
	}

	@Override
	public String toString() {
		return  code + ", From: "+ from + ", To: " + to + ", Day: "+ weekday+ ", Cost: " +costPerSeat;
	}
	/**
	 * this method is used to calculate if the flight is domestic or not
		 */
	public void setDomestic() {
		if(from.charAt(0)=='Y' && to.charAt(0)=='Y') {
			domestic=true;
		}
		else domestic=false;
		}


	
}
