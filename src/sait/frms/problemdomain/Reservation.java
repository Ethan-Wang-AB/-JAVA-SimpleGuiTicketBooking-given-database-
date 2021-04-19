package sait.frms.problemdomain;


/**
*	Class description: this class is used for reservation information.
*
*/
public class Reservation implements java.io.Serializable {

	
	private String code="";
	private String flightCode="";
	private String airline="";
	private String name="";
	private String citizenship="";
	private double cost;
	private boolean active;
	private int length=this.toString().length();
	private boolean domestic;

	public Reservation(){
		
	}
	/**
	*	Initializes the newly created Reservation
	*	@param code random reservation code
	*	@param airline airline abbreviation for the reservation
	*	@param name the customers name
	*/
	public Reservation(String code, String airline, String name) {
		this.code=code;
		this.airline=airline;
		this.name=name;
	}
	/**
	*	Initializes the newly created Reservation
	*	@param flight the flight of the reservation
	*	@param name the name of the customer
	*	@param citizenship the citizenship of the customer
	*/
	public Reservation(Flight flight, String name, String citizenship) {
		this.flightCode=flight.getCode();
		this.airline=flight.getAirlingName();
		this.name=name;
		this.citizenship=citizenship;
		this.cost=flight.getCostPerSeat();
		this.active=true;
		
	}
	/**
	*	Initializes the newly created Reservation
	*	@param flightCode flight code for the reservation
	*	@param name name of the customer
	*	@param citizenship citizenship of the customer
	*	@param cost cost of the ticket
	*	@param domestic if it is domestic or not
	*	@param active if the reservation is active or not.
	*/
	public Reservation(String flightCode, String name,String citizenship,double cost,boolean domestic,boolean active){
		
		this.flightCode=flightCode;
		this.airline=getAirlineName(flightCode.substring(0,2));
		this.name=name;
		this.citizenship=citizenship;
		this.cost=cost;
		this.active=active;
		
	}
	/**
	*	Initializes the newly created Reservation
	*	@param code code random reservation code
	*	@param flightCode flightCode flight code for the reservation
	*	@param name name of the customer
	*	@param citizenship
	*	@param cost cost cost of the ticket
	*	@param active f the reservation is active or not.
	*/
	public Reservation(String code, String flightCode, String name,String citizenship,double cost,boolean active){
		this.code=code;
		this.flightCode=flightCode;
		this.airline=getAirlineName(flightCode.substring(0,2));
		this.name=name;
		this.citizenship=citizenship;
		this.cost=cost;
		this.active=active;
	
		
	}
   public String getAirlineName(String flightCode) {
		if (flightCode.equals("OA")) {
			return "Otto Airlines";}
		else if (flightCode.equals("CA")) {
			return "Conned Air";}
		else if (flightCode.equals("TB")) {
			return " Try a Bus Airways";}
		else if (flightCode.equals("VA")) {
			return "Vertical Airways";}
		else return "";
   }
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCode() {
		return code;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public String getAirline() {
		return airline;
	}
	public double getCost() {
		return cost;
	}
	
	
	public String getName() {
		return name;
	}
	public String toSave() {
		String s=this.code+","+this.flightCode+","+this.name+","+this.citizenship+","+this.cost+","+this.active;
		String sa=String.format("%s%n", s);
		return sa;
	}
	public String toString() {
		String s=this.code+ " Status: "+ this.active;
		String sa=String.format("%s%n", s);
		return sa;
		}
	public int getLength() {
		return length;
	}
	
	
}
