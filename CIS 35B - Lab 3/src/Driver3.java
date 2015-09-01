import java.io.IOException;

import Adapter.BuildAuto;
import Adapter.CreateAuto;
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;


public class Driver3 {

	public static void main(String[] args) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException {
		String carFile = "FordZTW", carFile2 = "UglyBus6000";
		BuildAuto a1 = new BuildAuto();
		
		a1.addAuto(carFile);
	    a1.printAuto(carFile);
	    a1.addAuto(carFile2);
	    a1.printAuto(carFile2);
	    a1.printMap();
	}
}
