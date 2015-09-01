
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;
import Util.*;

import java.io.*;

public class Driver {
	
	public static void main(String[] args) throws ClassNotFoundException, IOException, MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData {
		
		AutomobileBuilder autoBuilder = new AutomobileBuilder();
		Automobile FordZTW = autoBuilder.buildAutoObject("FordZTW.txt");
		//FordZTW.printOptionSet();
		FordZTW.print();
		
		FileIO.serializeAuto(FordZTW);
		Automobile newFordZTW = FileIO.deserializeAuto("FordZTW.ser");
	    newFordZTW.print();
	    
	}
}
