package Adapter;

import java.io.IOException;

public interface UpdateAuto {
	public void updateOptionSetName(String modelName, String optionSetName, String newName)throws IOException;
	public void updateOptionPrice(String modelName, String optionName, String Option, float newPrice) throws IOException;
}
