import java.io.IOException;
import java.util.LinkedHashMap;

import Adapter.BuildAuto;
import Adapter.Fleet;
import Adapter.ScaleThread;
import Adapter.UpdateAuto;
import Client.CreateClient;
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;
import Scale.CarInfo;
import Scale.EditOptions;
import Server.CreateServer;


public class Driver5 {
	public static void main(String[] args) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException, ClassNotFoundException {
		String carFile = "GoKart", carFile2 = "FordZTW";

		//BuildAuto a1 = new BuildAuto();
		//a1.addAuto(carFile2);
		
		CreateClient c1 = new CreateClient();
		
	}
}
