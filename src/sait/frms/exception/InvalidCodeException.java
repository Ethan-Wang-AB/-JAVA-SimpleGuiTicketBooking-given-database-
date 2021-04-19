package sait.frms.exception;

/**
*	Class description:This class is about Invalid searching code
*
*
*/

public class InvalidCodeException extends Exception {

	private String code;


	public InvalidCodeException(String code){
		 super("Invalid flight code "+code);
	
	}
	  public String getCode(String code) {
		  return code;
	  } 
	  public String toString() {
		  String s="The code "+code+" is an invalid code";
		  return s;
	  }
	}