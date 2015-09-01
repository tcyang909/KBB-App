import java.io.IOException;

import Adapter.*;
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;


public class Driver2 {

	public static void main(String[] args) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException {
		CreateAuto a1 = new BuildAuto();
	    a1.buildAuto("FordZTW2");
	    a1.printAuto("FordZTW");
	  
	    UpdateAuto a2 = new BuildAuto();
	    a2.updateOptionSetName("FordZTW", "Color", "Rainbow");
	    a2.updateOptionPrice("FordZTW", "Rainbow", "Fort Knox Gold Clearcoat Metallic", 100);
	    
	    
	}
}
