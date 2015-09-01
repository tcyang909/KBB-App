package Adapter;

import java.io.IOException;
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;

public interface CreateAuto {
	public void buildAuto(String carName)throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException;
	public void printAuto(String modelName)throws IOException;
	public void buildAuto(Object car, int fileType)throws  MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException;
}
