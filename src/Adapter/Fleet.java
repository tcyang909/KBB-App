package Adapter;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;
import Model.OptionSet;
import Util.FileIO;

public class Fleet {
	private static LinkedHashMap<String, Automobile> autoMap = null;
	private Fleet(){}
	public static LinkedHashMap<String, Automobile> getInstance() {
		if (autoMap == null){
			autoMap = new LinkedHashMap<String, Automobile>();
		}
		return autoMap;
	}
}
