package sait.frms.problemdomain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.swing.JOptionPane;


/**
*	Class description: This class is used to read and write information from random access file of the reservation.
*
*
*/
public class RandomAccessObject extends RandomAccessFile {

	/**
	*	Initializes the newly created RandomAccessObject
	*	@param file file address
	*	@param mode set mode for read and write
	*	@throws FileNotFoundException
	*/
	public RandomAccessObject(File file, String mode) throws FileNotFoundException {
		super(file, mode);
		// TODO Auto-generated constructor stub

	}

	/**
	 * readObject this method is used to read data from random access file.
	 * @return arraylist for reservation information.
	 * @throws IOException
	 */
	public ArrayList<Reservation> readObject() throws IOException {

	
		String line = "";
		ArrayList<Reservation> readFromFile = new ArrayList<>();
		line = this.readLine();
		while (line != null) {
	
			String reservation[] = line.split(",");
			Reservation toRead = new Reservation(reservation[0], reservation[1], reservation[2], reservation[3],
					Double.parseDouble(reservation[4]), Boolean.parseBoolean(reservation[5]));
			readFromFile.add(toRead);
			reservation = null;
            line=this.readLine();
		}
		return readFromFile;

	}

	/**
	
	 *  this method is used to write data to random access file.
	 
	 * @throws IOException
	 
	  @param reservation the arraylist of most updated reservation information
	 
	 */
	public void writeObject(Reservation reservation) {
		String info = String.format(reservation.toSave());
		try {
			this.writeBytes(info);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "writing data problem");
		}

	}

}
