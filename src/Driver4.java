import java.io.IOException;
import java.util.LinkedHashMap;

import Adapter.BuildAuto;
import Adapter.Fleet;
import Adapter.ScaleThread;
import Adapter.UpdateAuto;
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;
import Scale.CarInfo;
import Scale.EditOptions;


public class Driver4 {
	public static void main(String[] args) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException {
		String carFile = "FordZTW", carFile2 = "UglyBus6000";
		BuildAuto a1 = new BuildAuto();
		
		a1.addAuto(carFile);
	    //a1.printAuto(carFile);
	    a1.addAuto(carFile2);
	    //a1.printAuto(carFile2);
	    //a1.printMap();
	    
	    CarInfo car1 = new CarInfo ("FordZTW", "Color", "Rainbow");
	    CarInfo car2 = new CarInfo ("FordZTW", "Rainbow", "NOTRainbow");
	    
	    EditOptions t1 = new EditOptions(0, car1);
	    EditOptions t2 = new EditOptions(0, car2);
	    
	    t1.start();
	    t2.start();
	    
	    
	}
}
