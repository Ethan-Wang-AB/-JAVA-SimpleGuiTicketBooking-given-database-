package sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sait.frms.exception.InvalidCodeException;
import sait.frms.problemdomain.Flight;


/**
*Class description: the class for the flight manager to use, 
*solve the problems of reading information from the flights and 
*airline file *booking tickets etc.
*
*
*/
public class FlightManager {
	public final static String WEEKDAY_ANY = "ANY";
	public final static String WEEKDAY_SUNDAY = "SUNDAY";
	public final static String WEEKDAY_MONDAY = "TUESDAY";
	public final static String WEEKDAY_TUESDAY = "MONDAY";
	public final static String WEEKDAY_WEDNESDAY = "WEDNESDAY";
	public final static String WEEKDAY_THURSDAY = "THURSDAY";
	public final static String WEEKDAY_FRIDAY = "FRIDAY";
	public final static String WEEKDAY_SATURDAY = "SATURDAY";
	private ArrayList<Flight> flights = new ArrayList<>();
	private ArrayList<String> airports = new ArrayList<>();
	private ArrayList<String> airportsN = new ArrayList<>();
	private ArrayList<String> airportFullName = new ArrayList<>();
	private ArrayList<String> fromC=new ArrayList<>();
	private ArrayList<String> toC=new ArrayList<>();
	private JList<Flight> pupulate = new JList<>();
	private JTextField flightCode = new JTextField();
	private JTextField airportInfo = new JTextField();
	private JTextField airLineInfo = new JTextField();
	private String[] airlineN = { "OA", "CA", "TB", "VA" };
	private String[] airlineFullName = { "Otto Airlines", "Conned Air", "Try a Bus Airways", "Vertical Airways" };
	public FlightManager()  {

	}

	/**
	 * getFromC
	FlightManager
	
	FlightManager.java
	secondAssignment
	 * @return
	 */
	public ArrayList<String> getFromC() {
		return fromC;
	}

	public ArrayList<String> getToC() {
		return toC;
	}

	/**
	 * getAirports information from file
	 * @return an arraylist for airports information
	 * @throws IOException
	 */
	public ArrayList<String> getAirports() throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> airportForUse = new ArrayList<String>();
		File file = new File("res/airports.csv");
		Scanner input = new Scanner(file);
		String line = "";
		String[] elements = null;
		while (input.hasNext()) {
			line = input.nextLine();
			airports.add(line);
			airportForUse.add(line);
			elements = line.split(",");
			airportsN.add(elements[0]);
			airportFullName.add(elements[1]);
			elements = null;
		}
		input.close();
		return airportForUse;

	}

	/**
	 * getFlights information from file
	 * @return an arraylist of flight information
	 * @throws FileNotFoundException
	 * @throws InvalidCodeException 
	 */
	/**
	 * getFlights
	FlightManager
	
	FlightManager.java
	secondAssignment
	 * @return
	 * @throws FileNotFoundException
	 * @throws InvalidCodeException
	 */
	/**
	 * getFlights
	FlightManager
	
	FlightManager.java
	secondAssignment
	 * @return
	 * @throws FileNotFoundException
	 * @throws InvalidCodeException
	 */
	/**
	 * getFlights
	FlightManager
	
	FlightManager.java
	secondAssignment
	 * @return
	 * @throws FileNotFoundException
	 * @throws InvalidCodeException
	 */
	public void getFlights() throws FileNotFoundException, InvalidCodeException {
		// TODO Auto-generated method stub
		File file = new File("res/flights.csv");
	
		Scanner input = new Scanner(file);
		String line = "";
		String[] elements = null;
		Flight flight;
		while (input.hasNext()) {
			line = input.nextLine();
			elements = line.split(",");
			try{
				if(checkCode(elements[0].trim())) {
			}
		          	 
			flight = new Flight(elements[0].trim(), elements[1].trim(), elements[2].trim(), elements[3].trim(), elements[4].trim(),
					Integer.parseInt(elements[5]), Double.parseDouble(elements[6])*1.00);
			if(fromC.contains(flight.getFrom())==false) {
				fromC.add(flight.getFrom());
			}
			if(toC.contains(flight.getTo())==false) {
				toC.add(flight.getTo());
			}
		   
			flights.add(flight);
                    	
			}catch( InvalidCodeException e){
				System.out.println("This is an invalid flight code in file "+elements[0]);
			}

			elements = null;
		
    }

	

	}

	public String findAirportByCode(String code) {
		for (int i = 0; i < airportsN.size(); i++) {
			if (code.equalsIgnoreCase(airportsN.get(i))) {
				return airportFullName.get(i);
			}
		}
		return "No airport found";
	}

	public Flight findFlightByCode(String code) throws InvalidCodeException {
		Flight flight = new Flight();
		try {
			boolean x = checkCode(code);
			for (int i = 0; i < flights.size(); i++) {
				if (flights.get(i).getCode().equals(code)) {

					return flights.get(i);
				} else
					x = false;
			}
		} catch (InvalidCodeException e) {
			JOptionPane.showMessageDialog(null, "It is an Invalid input");
		}
		return flight;

	}

	/**
	 * check the flight code validation
	 * @param code flight code 
	 * @return boolean if the code of a certain flight is valid or not
	 * @throws InvalidCodeException
	 */
	private boolean checkCode(String code) throws InvalidCodeException {

		// TODO Auto-generated method stub
		if (Character.isLetter(code.charAt(0)) && Character.isLetter(code.charAt(1)) && code.charAt(2) == '-'
				&& Character.isDigit(code.charAt(3)) && Character.isDigit(code.charAt(4))
				&& Character.isDigit(code.charAt(5)) && Character.isDigit(code.charAt(6))) {
			return true;
		} else {
			throw new InvalidCodeException(code) ;
		}
	}

	/**
	 * findFlights find a list of flights that meet searching criteria
	 * @param from the city it takes off
	 * @param to  the destination of the flight
	 * @param weekday the weekday of the flight
	 * @return an arraylist that meet requirement
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> findFlights = new ArrayList<>();
		
		if (weekday.equalsIgnoreCase("ANY")) {
			for (int i = 0; i < flights.size(); i++) {
				if (flights.get(i).getFrom().equalsIgnoreCase(from) && flights.get(i).getTo().equalsIgnoreCase(to)) {
					findFlights.add(flights.get(i));
				}
			}
		} else {
			for (int i = 0; i < flights.size(); i++) {
				if (flights.get(i).getFrom().equalsIgnoreCase(from) && flights.get(i).getTo().equalsIgnoreCase(to)
						&& flights.get(i).getWeekday().equalsIgnoreCase(weekday)) {
					findFlights.add(flights.get(i));
				}
			}
		}
		return findFlights;
	}

	/**
	 * populateFlights let flight information shown on the right panel
		 */
	public void populateFlights() {
		flightCode.setText(pupulate.getSelectedValue().getCode());
	}

	/**
	 * populateAirports let airport information shown on the right panel

	 */
	public void populateAirports() {
		airportInfo.setText(
				"From:" + pupulate.getSelectedValue().getFrom() + ",To:" + pupulate.getSelectedValue().getTo());
	}
	/**
	 * saveToFile after booking ticket, it will re-write the flight file. because after booking the seats decreases.
	 * @throws FileNotFoundException
	 */
	public void saveToFile() throws FileNotFoundException {
		File file=new File("res/flights.csv");
		PrintWriter save=new PrintWriter(file);
		for(int i=0;i<flights.size();i++) {
			String s=flights.get(i).getCode()+","+flights.get(i).getFrom()+","+flights.get(i).getTo()+","+
					flights.get(i).getWeekday()+","+flights.get(i).getTime()+","
					+flights.get(i).getSeats()+","+flights.get(i).getCostPerSeat();
			save.println(s);
		}
		save.close();
	}

	/**
	 * populateAirline display airline information on the right panel.

	 */
	public void populateAirline() {
		for (int i = 0; i < airlineN.length; i++) {
			if (pupulate.getSelectedValue().getAirlingName().equalsIgnoreCase(airlineN[i])) {
				airLineInfo.setText(airlineFullName[i]);
			}
		}
	}
}
