package sait.frms.manager;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.RandomAccessObject;
import sait.frms.problemdomain.Reservation;
import sait.frms.exception.InvalidCitizenException;
import sait.frms.exception.InvalidCodeException;
import sait.frms.exception.NoSeatException;

import java.io.*;


/**
*	Class description: This class is for reservation manager
*   to read and write reservation information to the random file.
*   and deal with updating information of a certain reservation
*   such as change name, citizen or set status to inactive or active 
*
*/
public class ReservationManager {
	private ArrayList<Reservation> reservations=new ArrayList<>() ;

	public ReservationManager() throws ClassNotFoundException, IOException {
		populateFromBinary();
	}

	/**
	 * updateReservation to update a certain reservation
	 * @param reservation the reservation to be modified
	 * @param name the name to be modified
	 * @param citizenship the citizen to be modified
	 * @param active the status of the reservation to be modified
	 * @throws InvalidCitizenException throw exception for null input
	 * @throws IOException 
	 */
	/**
	 * updateReservation
	ReservationManager
	
	ReservationManager.java
	secondAssignment
	 * @param reservation
	 * @param name
	 * @param citizenship
	 * @param active
	 * @throws InvalidCitizenException
	 * @throws IOException
	 */
	/**
	 * updateReservation
	ReservationManager
	
	ReservationManager.java
	secondAssignment
	 * @param reservation
	 * @param name
	 * @param citizenship
	 * @param active
	 * @throws InvalidCitizenException
	 * @throws IOException
	 */
	/**
	 * updateReservation
	ReservationManager
	
	ReservationManager.java
	secondAssignment
	 * @param reservation
	 * @param name
	 * @param citizenship
	 * @param active
	 * @throws InvalidCitizenException
	 * @throws IOException
	 */
	public void updateReservation(Reservation reservation, String name, String citizenship, boolean active)
			throws InvalidCitizenException, IOException {
		if (name.isEmpty() == false && name.trim().equals("") == false && citizenship.isEmpty() == false
				&& citizenship.trim().equals("") == false) {
	        
		
		for(int i=0;i<reservations.size();i++) {
		if(reservations.get(i).getCode().equals(reservation.getCode())){
			reservations.get(i).setActive(active);
			reservations.get(i).setName(name.trim());
			reservations.get(i).setCitizenship(citizenship.trim());
 	        
	
		       }
	     	}
		} else {
			throw new InvalidCitizenException();
		}
	          /*for(int i=0;i<reservations.size();i++) {
	        	  System.out.println(reservations.get(i).toString());
	          }*/
	  persist();

	}

	/**
	 * makeReservation this is for making reservation
	 * @param flight the flight to be booked
	 * @param name the name of the customer
	 * @param citizenship the citizenship of the customer
	 * @return a reservation object
	 * @throws NoSeatException if available seat is zero then throw an exception
	 * @throws InvalidCitizenException if the citizenship input is null then throw an exception
	 * @throws IOException if the file is not found when saving information to the random access file.
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship)
			throws NoSeatException, InvalidCitizenException, IOException {
		Reservation reserve = new Reservation();

		if (name.isEmpty() || name.trim().equals("") || citizenship.isEmpty() || citizenship.trim().equals("")) {
			throw new InvalidCitizenException();
			// catch exceptions
		} else {
			flight.bookSeats();
			reserve = new Reservation(flight, name.trim(), citizenship.trim());

			reserve.setCode(generateReservationCode(flight));
			reservations.add(reserve);
		
		}
		
		persist();

		return reserve;
	}

	/**
	 * findReservations find a certain reservation in the reservation list
	
	 * @param code reservation code
	 * @param airline airline abbreviation of the airline
	 * @param name the customer name
	 * @return a list of reservations that meet the criteria
	 * @throws ClassNotFoundException 
	 * @throws IOException if the file is not found when reading data from binary file
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name)
			throws IOException {
		File readFile = new File("res/reservations.dat");
		ArrayList<Reservation> reservationToFind = new ArrayList<>();
		if (readFile.exists() == true) {
			populateFromBinary();

			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getCode().equalsIgnoreCase(code)
						&& reservations.get(i).getAirline().equalsIgnoreCase(airline)
						&& reservations.get(i).getName().equalsIgnoreCase(name)) {
					reservationToFind.add(reservations.get(i));
				} else if (reservations.get(i).getAirline().equalsIgnoreCase(airline)
						&& reservations.get(i).getName().equalsIgnoreCase(name)) {
					reservationToFind.add(reservations.get(i));
				} else if (reservations.get(i).getCode().equalsIgnoreCase(code)
						&& reservations.get(i).getName().equalsIgnoreCase(name)) {
					reservationToFind.add(reservations.get(i));
				} else if (reservations.get(i).getCode().equalsIgnoreCase(code)
						&& reservations.get(i).getAirline().equalsIgnoreCase(airline)) {
					reservationToFind.add(reservations.get(i));
				} else if (reservations.get(i).getCode().equalsIgnoreCase(code)) {
					reservationToFind.add(reservations.get(i));
				} else if (reservations.get(i).getAirline().equalsIgnoreCase(airline)) {
					reservationToFind.add(reservations.get(i));
				} else if (reservations.get(i).getName().equalsIgnoreCase(name)) {
					reservationToFind.add(reservations.get(i));
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "There is no reservation in system");
		}
       
		return reservationToFind;
	}

	/**
	 * findReservationByCode the method for finding reservations by reservation code
	 * @param code reservation code to be found
	 * @return a reservation object
	 * @throws ClassNotFoundException 
	 * @throws IOException
	 * @throws InvalidCodeException
	 */
	public Reservation findReservationByCode(String code)
			throws  IOException, InvalidCodeException {
		
		if (checkCode(code) == false) {
			throw new InvalidCodeException(code);
		}
		File readFile = new File("res/reservations.dat");
		if (readFile.exists() == true) {
			populateFromBinary();

			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).getCode().equalsIgnoreCase(code)) {
					return reservations.get(i);
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "There is no reservation in system");
		}
		
		return null;
	}

	/**
	 * getAvailableSeats get the available seats from flight obnjcet
	 * @param flight
	 * @return the number of available seats of a certain flight
	 */
	public int getAvailableSeats(Flight flight) {
		int available = flight.getSeats();

		return available;
	}

	/**
	 * generateReservationCode the method is to generate a random reservation code
	 * @param flight is to check the flight is domestic or not
	 * @return a string of reservation code
	 */
	public String generateReservationCode(Flight flight) {
		int x = 1000 + (int) (Math.random() * 9000);
		String reservationCode = "";
		if (flight.isDomestic() == true) {

			reservationCode = "D" + x;
		} else {
			reservationCode = "I" + x;
		}

		return reservationCode;
	}

	/**
	 * checkCode check if the reservation code is valid or not
	 * @param code reservation code to be checked
	 * @return boolean if it is valid or not
	 * @throws InvalidCodeException
	 */
	private boolean checkCode(String code) throws InvalidCodeException {

		// TODO Auto-generated method stub
		if (code.length() == 5
				&& (code.substring(0, 1).equalsIgnoreCase("D") || code.substring(0, 1).equalsIgnoreCase("I"))
				&& Character.isDigit(code.charAt(1)) && Character.isDigit(code.charAt(2))
				&& Character.isDigit(code.charAt(3)) && Character.isDigit(code.charAt(4))) {
			return true;
		} else {
			return false;
		}
	}


	private void persist() throws IOException {

		File save = new File("res/reservations.dat");
		if (save.exists() == false) {
			save.createNewFile();
		}
		RandomAccessObject inout = new RandomAccessObject(save, "rw");
		for (int i = 0; i < reservations.size(); i++) {
			inout.writeObject(reservations.get(i));
		}
		inout.seek(0);
	}

	/**
	 * populateFromBinary1 reading reservation data from the file.
	 * @throws FileNotFoundException
	 */
	private void populateFromBinary() throws FileNotFoundException {
		File save = new File("res/reservations.dat");
		Reservation fromFile = new Reservation();

		RandomAccessObject inout = new RandomAccessObject(save, "rw");
		try {
			if (inout.length() != 0) {
				reservations = inout.readObject();
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Loading file problem");

		}

	}
}
