package Adapter;

import java.io.IOException;

public interface ScaleThread {
	public void updateOptionSetNameSync (String modelName, String optionSetName, String newName) throws IOException;
	public void updateOptionNameSync (String modelName, String optionSetName, String option, String newName) throws IOException;
	public void updateOptionPriceSync (String modelName, String optionName, String option, float newPrice)throws IOException;
}
