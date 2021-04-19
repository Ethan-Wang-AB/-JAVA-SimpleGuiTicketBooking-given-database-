package sait.frms.exception;


/**
*	Class description:This class is about Invalid Citizen Exception
*
*/
public class InvalidCitizenException extends Exception{
	private String citizenship;


	public InvalidCitizenException(){
		  super("Invalid input of citizen or Name, they cannot be null");
		
	}
	  public String getCitizenship(String citizenship) {
		  return citizenship;
	  } 
	  public String toString() {
		  String s="The citizenship "+citizenship+" is an invalid citizenship";
		  return s;
	  }
}
