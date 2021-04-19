package sait.frms.application;

import java.io.IOException;

import sait.frms.exception.InvalidCodeException;
import sait.frms.gui.TestGui;


/**
*	Class description:This is where main method is. it is for customers uses.
*
*	@author Su Wang (845593)
*
*/
public class Application {
	public static void main(String[] args) throws IOException, InvalidCodeException {
		new TestGui();
	}
}
