package Adapter;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.*;
import Util.*;

public abstract class ProxyAutomobile{
	private static LinkedHashMap<String, Automobile> autoMap = new LinkedHashMap<String, Automobile>();
	
	public void addAuto(String carName) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		buildAuto(carName);
		System.out.println("Printing addAuto: " +carName+".ser");
		Automobile auto = FileIO.deserializeAuto(carName+".ser");
		autoMap.put(carName, auto);
	}
	public void removeAuto(String fileName){
		autoMap.remove(fileName);
	}
	public void printMap(){
		int i = 1;
		Set set = autoMap.entrySet();
		Iterator<OptionSet> list = set.iterator();
		System.out.println("LinkedHashMap entries: ");
		while (list.hasNext()){
			Map.Entry car = (Map.Entry)list.next();
			System.out.println(i + ": " + car.getKey());
			i++;
		}
	}
	
	public void buildAuto(String carName) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		AutomobileBuilder autoBuilder = new AutomobileBuilder();
		Automobile a1 = new Automobile();
		a1 = autoBuilder.buildAutoObject(carName);
		FileIO.serializeAuto(a1);
	}
	public void printAuto(String modelName) throws IOException{
		Automobile auto = null;
		String carName = modelName + ".ser";
		auto = FileIO.deserializeAuto(carName);
		auto.print();
	}
	public void updateOptionSetName(String modelName, String optionSetName,String newName) throws IOException {
		Automobile auto = null;
		String carName = modelName + ".ser";
		auto = FileIO.deserializeAuto(carName);
		auto.updateOptionSetName(optionSetName, newName);
		auto.print();
		FileIO.serializeAuto(auto);
	}
	public void updateOptionPrice(String modelName, String optionName,String Option, float newPrice) throws IOException {
		Automobile auto = null;
		String carName = modelName + ".ser";
		auto = FileIO.deserializeAuto(carName);
		auto.updateOptionPrice(optionName, Option, newPrice);
		auto.print();
		FileIO.serializeAuto(auto);
	}
}