package Server;

import Model.Automobile;
import Util.LinkedProperties;

public interface AutoServer {
	//public void createAutoAddtoLHM(Properties props);
	public Automobile createAutoFromProps(LinkedProperties props);
	public void putIntoLHM(Automobile auto);
	public void printMap();
}
