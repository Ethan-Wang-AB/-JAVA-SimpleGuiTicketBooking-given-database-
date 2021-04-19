package sait.frms.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sait.frms.exception.InvalidCitizenException;
import sait.frms.exception.InvalidCodeException;
import sait.frms.exception.NoSeatException;
import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;
import sait.frms.problemdomain.Reservation;


/**
	Class description:This class is used for creating Gui for this application


 * @author Su Wang (845593)
 */


public class TestGui extends JFrame {
	private static final int WINDOW_LENGTH = 800;
	private static final int WINDOW_WIDTH = 600;
	private JPanel upperPanel = new JPanel();
	private JPanel centerPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel reservePanel = new JPanel();
	private JPanel updatePanel = new JPanel();
	private JPanel flightFinder = new JPanel();
	private JPanel searchR = new JPanel();
	private JPanel lowerPanel = new JPanel();
	private JList<Flight> infoAreaFlight = new JList<Flight>();
	private JList<Reservation> infoAreaReservation = new JList<Reservation>();
	private FlightManager flightManager = new FlightManager();
	private ReservationManager reservationManager;
	private ArrayList<Flight> flightFound = new ArrayList<>();
	private ArrayList<Reservation> reservationToFound = new ArrayList<>();

	/**
	*	Constructor Initializes the newly created TestGui
	*	@throws IOException
	 * @throws InvalidCodeException 
	*/
	public TestGui() throws IOException, InvalidCodeException {

		setSize(WINDOW_LENGTH, WINDOW_WIDTH);
		this.setResizable(false);
		setLayout(new BorderLayout());
		buildUpperPanel();
		buildCenterPanel();
		buildReservePanel();
		buildUpdatePanel();
		rightPanel.add(reservePanel);
		rightPanel.add(updatePanel);
		buildFlightFinderPanel();
		buildSearchRPanel();
		try {
			reservationManager = new ReservationManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(centerPanel, "Initializing database failed");
		}

		lowerPanel.add(flightFinder);
		lowerPanel.add(searchR);
		lowerPanel.setPreferredSize(new Dimension(200, 200));
		lowerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(upperPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(lowerPanel, BorderLayout.SOUTH);
		this.add(rightPanel, BorderLayout.EAST);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	/**
	 * buildReservePanel inside	TestGui for booking ticket mode
	

	 */
	/**
	 * buildReservePanel
	TestGui
	
	TestGui.java
	secondAssignment
	 */
	/**
	 * buildReservePanel
	TestGui
	
	TestGui.java
	secondAssignment
	 */
	private void buildReservePanel() {
		// TODO Auto-generated method stub
		JPanel funcBoard = new JPanel();
		funcBoard.setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 85));
		funcBoard.setLayout(new FlowLayout(FlowLayout.RIGHT));
		funcBoard.setPreferredSize(new Dimension(320, 250));
		JLabel flightL = new JLabel("Flight:");
		JTextField flightT = new JTextField(8);
		flightT.setEditable(false);

		funcBoard.add(flightL);
		funcBoard.add(flightT);
		JLabel airlineL = new JLabel("Airline:");
		JTextField airlineT = new JTextField(8);
		airlineT.setEditable(false);

		funcBoard.add(airlineL);
		funcBoard.add(airlineT);
		JLabel dayL = new JLabel("   Day:");
		JTextField dayT = new JTextField(8);
		dayT.setEditable(false);

		funcBoard.add(dayL);
		funcBoard.add(dayT);
		JLabel timeL = new JLabel("  Time:");
		JTextField timeT = new JTextField(8);
		timeT.setEditable(false);

		funcBoard.add(timeL);
		funcBoard.add(timeT);
		JLabel costL = new JLabel("  Cost:");
		JTextField costT = new JTextField(8);
		costT.setEditable(false);

		funcBoard.add(costL);
		funcBoard.add(costT);
		ListSelectionModel find = infoAreaFlight.getSelectionModel();
		

		
		find.addListSelectionListener(new ListSelectionListener() {

			/* Anonymous class and method for adding Jlist selection listener
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				int index = lsm.getLeadSelectionIndex();
				Flight flight = flightFound.get(index);
				if (lsm.isSelectionEmpty() == false) {
					flightT.setText(flight.getCode());
					airlineT.setText(flight.getAirlingName());
					dayT.setText(flight.getWeekday());
					timeT.setText(flight.getTime());
					costT.setText("$ "+flight.getCostPerSeat() + "");
				}
			}

		});

		JLabel customerNameL = new JLabel("  Name:");
		JTextField customerNameT = new JTextField(8);
		funcBoard.add(customerNameL);
		funcBoard.add(customerNameT);
		JLabel citizenshipL = new JLabel("Citizenship:");
		JTextField citizenshipT = new JTextField(8);
		funcBoard.add(citizenshipL);
		funcBoard.add(citizenshipT);
		JButton reserve = new JButton("Reserve");
		funcBoard.add(reserve);
		this.reservePanel = funcBoard;
		reservePanel.setVisible(true);
		reserve.addActionListener(new ActionListener() {

			/* Anonymous class and actionlistener for click reserve button
			 * add reservation information to arraylist
			 * throw and catch exception of non reasonable citizen input.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {
					Flight flight = infoAreaFlight.getSelectedValue();
					String name = customerNameT.getText().trim();
					String citizenship = citizenshipT.getText().trim();
					
					Reservation reserve=reservationManager.makeReservation(flight, name, citizenship);
					flightManager.saveToFile();
			
					JOptionPane.showMessageDialog(centerPanel, "Reservation is booked. Reservation Code is "+reserve.getCode());

				} catch (InvalidCitizenException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(centerPanel, "Citizen or Name cannot be null");
				} catch (NoSeatException e2) {
					JOptionPane.showMessageDialog(centerPanel, "There is no seat for this flight");

				} catch (Exception e3) {
					JOptionPane.showMessageDialog(centerPanel, "Cannot find flight");

				}

			}

		});
	}
	

	/**
	 * buildUpdatePanel for reservation manager 
	

	 */
	private void buildUpdatePanel() {
		// TODO Auto-generated method stub
		JPanel funcBoard = new JPanel();
		funcBoard.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 90));
		funcBoard.setLayout(new FlowLayout(FlowLayout.RIGHT));
		funcBoard.setPreferredSize(new Dimension(300, 240));
		JLabel codeL = new JLabel("Code:");

		JTextField codeT = new JTextField(8);
		codeT.setEditable(false);
		funcBoard.add(codeL);
		funcBoard.add(codeT);

		JLabel flightL = new JLabel("Flight:");

		JTextField flightT = new JTextField(8);
		flightT.setEditable(false);

		funcBoard.add(flightL);
		funcBoard.add(flightT);
		JLabel airlineL = new JLabel("Airline:");
		JTextField airlineT = new JTextField(8);
		airlineT.setEditable(false);

		funcBoard.add(airlineL);
		funcBoard.add(airlineT);

		JLabel costL = new JLabel("Cost:");

		JTextField costT = new JTextField(8);
		costT.setEditable(false);

		funcBoard.add(costL);
		funcBoard.add(costT);
		JLabel customerNameL = new JLabel("Name:");
		JTextField customerNameT = new JTextField(8);
		funcBoard.add(customerNameL);
		funcBoard.add(customerNameT);
		JLabel citizenshipL = new JLabel("Citizenship:");
		JTextField citizenshipT = new JTextField(8);
		funcBoard.add(citizenshipL);
		funcBoard.add(citizenshipT);
		ListSelectionModel select = infoAreaReservation.getSelectionModel();

		JLabel statusL = new JLabel("Status:");
		String statusStr[] = { "active", "inactive" };
		JComboBox<String> status = new JComboBox<>(statusStr);

		select.addListSelectionListener(new ListSelectionListener() {
			/* (non-Javadoc)
			 * @add Jlist selection listener for the list of reservation
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();
				int index = lsm.getLeadSelectionIndex();
				Reservation reservation = new Reservation();
				;
				if (reservationToFound.size() > 0) {
					reservation = reservationToFound.get(index);
				}
				if (lsm.isSelectionEmpty() == false) {
					codeT.setText(reservation.getCode());
					flightT.setText(reservation.getFlightCode());
					airlineT.setText(reservation.getAirline());
					costT.setText("$ "+reservation.getCost() + "");
					customerNameT.setText(reservation.getName());
					citizenshipT.setText(reservation.getCitizenship());
					if (reservation.isActive() == true) {
						status.setSelectedIndex(0);
					} else {
						status.setSelectedIndex(1);
					}
				}
			}

		});
		status.setPreferredSize(new Dimension(90, 25));
		JButton reserve = new JButton("Update");
		funcBoard.add(statusL);
		funcBoard.add(status);
		funcBoard.add(reserve);
		this.updatePanel = funcBoard;
		this.updatePanel.setVisible(false);

		reserve.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 *add action listener for update button
			 *it will invoke reservationmanager method
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = customerNameT.getText().trim();
				String citizenship = citizenshipT.getText().trim();
				boolean active;
				boolean activeHistory=true;
				try{activeHistory = infoAreaReservation.getSelectedValue().isActive();}
				catch(Exception eo) {
					JOptionPane.showMessageDialog(centerPanel, "There is nothing chosen;");

				}
	
				if (status.getSelectedIndex() == 0) { 
					active = true;
				} else {
					active = false;
				}
;
				if (activeHistory != active && active==false) {
                   		
					try {
						reservationManager.updateReservation(infoAreaReservation.getSelectedValue(), name, citizenship,
								active);
						
					
						infoAreaReservation.getSelectedValue().setActive(active);
						Flight toUpdate	= flightManager.findFlightByCode(infoAreaReservation.getSelectedValue().getFlightCode());
						toUpdate.cancelBooking();
						JOptionPane.showMessageDialog(centerPanel, "Name: " + name + " Citizen: " + citizenship
								+ " Codes: " + infoAreaReservation.getSelectedValue().getCode() + " is changed."+" Status now is " + status.getSelectedItem());
					} catch (InvalidCitizenException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(centerPanel, "Name or Citizen input is invalid");
					} catch (InvalidCodeException e2) {
						JOptionPane.showMessageDialog(centerPanel, "It is not an valid flight code");

						
					}catch( Exception  eo) {
						JOptionPane.showMessageDialog(centerPanel, "There is no reservation file found;");
					}
				}else if(activeHistory != active && active==true) {
					try {
						reservationManager.updateReservation(infoAreaReservation.getSelectedValue(), name, citizenship,
								active);
						
						Flight toUpdate	= flightManager.findFlightByCode(infoAreaReservation.getSelectedValue().getFlightCode());
						toUpdate.bookSeats();
						JOptionPane.showMessageDialog(centerPanel, "Name: " + name + " Citizen: " + citizenship
								+ " Codes: " + infoAreaReservation.getSelectedValue().getCode() + " is changed. "+"Status now is" + active);

					} catch (InvalidCitizenException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(centerPanel, "Name or Citizen input is invalid");
					} catch (InvalidCodeException e2) {
						JOptionPane.showMessageDialog(centerPanel, "It is not an valid flight code");
						
					}catch(NoSeatException e3) {
						JOptionPane.showMessageDialog(centerPanel, "There is no seat available right now;");
					}catch(IOException eo1) {
						JOptionPane.showMessageDialog(centerPanel, "There is no reservation file found;");
					}
					}
					else {
						try {
							reservationManager.updateReservation(infoAreaReservation.getSelectedValue(), name, citizenship,
									active);
							
							Flight toUpdate	= flightManager.findFlightByCode(infoAreaReservation.getSelectedValue().getFlightCode());
							toUpdate.bookSeats();
							JOptionPane.showMessageDialog(centerPanel, "Name: " + name + " Citizen: " + citizenship
									+ " Codes: " + infoAreaReservation.getSelectedValue().getCode() + " is changed. Status is not changed.");

						} catch (InvalidCitizenException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(centerPanel, "Name or Citizen input is invalid");
						} catch (InvalidCodeException e2) {
							JOptionPane.showMessageDialog(centerPanel, "It is not an valid flight code");
							
						}catch(NoSeatException e3) {
							JOptionPane.showMessageDialog(centerPanel, "There is no seat available right now;");

						}catch(Exception eo2) {
							JOptionPane.showMessageDialog(centerPanel, "There is no reservation file found;");
						}
					
					
				}
				
			}

		});
	}

	/**
	 * build the panel at bottom for searching flight information 
	 */
	private void buildSearchRPanel() {
		// TODO Auto-generated method stub

		JLabel codeLow = new JLabel("Code:");
		JLabel airlineLow = new JLabel("Airline:");
		JLabel nameLow = new JLabel("Name:");
		searchR.setPreferredSize(new Dimension(800, 200));
		JTextField codeTLow = new JTextField(65);
		JTextField airlineTLow = new JTextField(65);
		JTextField nameTLow = new JTextField(65);
		JButton findReservation = new JButton("Find Reservations");
		findReservation.setPreferredSize(new Dimension(700, 20));

		searchR.add(codeLow);
		searchR.add(codeTLow);
		searchR.add(airlineLow);
		searchR.add(airlineTLow);
		searchR.add(nameLow);
		searchR.add(nameTLow);
		searchR.add(findReservation);
		searchR.setVisible(false);
		findReservation.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * add information for reservation information
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String code = codeTLow.getText();
				String airline = airlineTLow.getText();
				String name = nameTLow.getText().trim();
				
				if ((airline.trim().equals("") || airline.trim().isEmpty())
						&& (name.trim().equals("") || name.trim().isEmpty())) {

						try {
							reservationToFound.clear();
							reservationToFound.add(reservationManager.findReservationByCode(code));
							if (reservationManager.findReservationByCode(code) == null) {
								JOptionPane.showMessageDialog(centerPanel, "No reservation record is found.");
							}
						} catch (Exception n) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(centerPanel,
									"Booking Code has to be One Letter + " + "four digits");
						}
					
				
				} else {
					try {
						reservationToFound.clear();
						reservationToFound = reservationManager.findReservations(code, airline, name);
					
						if (reservationToFound.size() == 0) {
							JOptionPane.showMessageDialog(centerPanel, "No reservation record is found.");
						}
					} catch ( Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(centerPanel, "There is no reservation in system");
					}
				}
			
                
				DefaultListModel<Reservation> mod = new DefaultListModel<>();

				for (int i = 0; i < reservationToFound.size(); i++) {
					mod.addElement(reservationToFound.get(i));
				}
			

				infoAreaReservation.setModel(mod);
			}
		});

	}

	/**
	 * buildFlightFinderPanel for finding the flight and booking
	 * @throws IOException
	 * @throws InvalidCodeException 
	 */
	private void buildFlightFinderPanel() throws IOException, InvalidCodeException {
		// TODO Auto-generated method stub
		this.flightManager.getAirports();
		this.flightManager.getFlights();
		flightFinder.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JLabel from = new JLabel("From:");
		JLabel to = new JLabel("  To:");
		JLabel day = new JLabel("  Day:");
		flightFinder.setPreferredSize(new Dimension(800, 200));
		flightFinder.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
		String[] fromArray = new String[flightManager.getFromC().size()];
		String[] toArray = new String[flightManager.getToC().size()];
		flightManager.getFromC().toArray(fromArray);
		flightManager.getToC().toArray(toArray);
		JComboBox<String> fromC = new JComboBox<>(fromArray);
		fromC.setPreferredSize(new Dimension(650, 20));
		from.setPreferredSize(new Dimension(50, 20));
		String[] dayS = { "Any", "Monday", "Tuesday", "Wednesday", "Friday", "Saturday", "Sunday" };
		JComboBox<String> toC = new JComboBox<String>(toArray);
		JComboBox<String> dayC = new JComboBox<String>(dayS);
		toC.setPreferredSize(new Dimension(650, 20));
		to.setPreferredSize(new Dimension(50, 20));
		dayC.setPreferredSize(new Dimension(650, 20));
		day.setPreferredSize(new Dimension(50, 20));
		JButton findFlight = new JButton("Find Flights");
		findFlight.setPreferredSize(new Dimension(700, 20));
		flightFinder.add(from);
		flightFinder.add(fromC);
		flightFinder.add(to);
		flightFinder.add(toC);
		flightFinder.add(day);
		flightFinder.add(dayC);
		flightFinder.add(findFlight);
		flightFinder.setVisible(true);
		findFlight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String from = (String) fromC.getSelectedItem();
				String to = (String) toC.getSelectedItem();
				String day = (String) dayC.getSelectedItem();
				setFlightFound(flightManager.findFlights(from, to, day));
				if (flightFound.size() == 0) {
					JOptionPane.showMessageDialog(centerPanel, "No flight is found.");

				}
				DefaultListModel<Flight> model = new DefaultListModel<>();

				for (int i = 0; i < flightFound.size(); i++) {
					model.addElement(flightFound.get(i));
				}

				infoAreaFlight.setModel(model);
			}

		});
	}

	/**
	 * build upper pannel for choosing booking mode or reservation management mode.
	 * it will invoke different panel for center, right and bottom.
	 */
	public void buildUpperPanel() {

		JButton flightPage = new JButton("Flights");
		JButton reservationPage = new JButton("Reservations");
		JLabel flightsL = new JLabel("Flights");
		JLabel reservationL = new JLabel("Reservations");
		flightsL.setFont(new Font("sanrif", Font.HANGING_BASELINE, 62));
		reservationL.setFont(new Font("sanrif", Font.HANGING_BASELINE, 62));
		reservationL.setVisible(false);

		flightPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flightsL.setVisible(true);
				reservationL.setVisible(false);
				reservePanel.setVisible(true);
				updatePanel.setVisible(false);
				searchR.setVisible(false);
				flightFinder.setVisible(true);
				infoAreaFlight.setVisible(true);
				infoAreaReservation.setVisible(false);
			}

		});
		reservationPage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flightsL.setVisible(false);
				reservationL.setVisible(true);
				reservePanel.setVisible(false);
				updatePanel.setVisible(true);
				searchR.setVisible(true);
				flightFinder.setVisible(false);
				infoAreaFlight.setVisible(false);
				infoAreaReservation.setVisible(true);
			}

		});
		this.upperPanel.setPreferredSize(new Dimension(800, 120));

		flightPage.setPreferredSize(new Dimension(380, 20));
		reservationPage.setPreferredSize(new Dimension(380, 20));

		this.upperPanel.add(flightPage);
		this.upperPanel.add(reservationPage);
		this.upperPanel.add(flightsL);
		this.upperPanel.add(reservationL);
	}

	/**
	 * set attribute for center panel.
	
	 */
	public void buildCenterPanel() {
		this.centerPanel.setPreferredSize(new Dimension(800, 400));
		centerPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		infoAreaFlight.setPreferredSize(new Dimension(350, 200));
		infoAreaReservation.setPreferredSize(new Dimension(350, 200));
		infoAreaFlight.setVisible(true);
		infoAreaReservation.setVisible(false);
		this.centerPanel.add(infoAreaFlight);
		this.centerPanel.add(infoAreaReservation);
		this.setVisible(true);
	}

	/**
	 * getter for arraylist flight information
	 * @return flight list found
	 */
	public ArrayList<Flight> getFlightFound() {
		return flightFound;
	}

	/**
	 * setter for arraylist flight information
	 * @param arraylist of flights
	 */
	public void setFlightFound(ArrayList<Flight> flightFound) {
		this.flightFound = flightFound;
	}



}
