package Server;

import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;

public interface SocketClientInterface {
	boolean openConnection();
	void handleSession() throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData;
	void closeSession();
}
