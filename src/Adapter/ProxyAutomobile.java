package Adapter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.*;
import Util.*;
import Adapter.Fleet;

public abstract class ProxyAutomobile{
	protected static LinkedHashMap<String, Automobile> autoMap = Fleet.getInstance();
	
	//public void createAutoAddtoLHM(Properties props){putIntoLHM(createAutoFromProps(props));}
	public Automobile createAutoFromProps(LinkedProperties props){
		Automobile auto = new Automobile();
		String CarMake = props.getProperty("CarMake"); 
		String OptionSet = "Nothing";
		String[] tokens = null;
		
		//if(!CarMake.equals(null)){
		Enumeration<?> e = props.propertyNames();
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			String value = props.getProperty(key);
			if (key.startsWith("CarMake"))
				auto.setMake(CarMake);
			else if(key.startsWith("CarModel")){
				tokens = value.split(",");
				auto.setModel(tokens[0]);
				auto.setBasePrice(Float.parseFloat(tokens[1].trim()));
			}
			else if (key.startsWith("OptionSet")){
				OptionSet = value;
				auto.AddNewOptionSet(value);
				//System.out.println("The OptionSet name is: " + OptionSet);
			}else if (key.startsWith("OptionValue")){
				if (value.contains(",")){
					tokens = value.split(",");
					//System.out.println("OptionSet: " + OptionSet + " OptionValue: " + tokens[0] + " Price: " + tokens[1]);
					//auto.AddNewOptionName(OptionSet, tokens[0]);
					auto.AddNewOptionNamePrice(OptionSet, tokens[0], Float.parseFloat(tokens[1].trim()));
				}else
					auto.AddNewOptionName(OptionSet, value);
			}else{
				System.out.println("ERROR: Key : " + key + ", Value : " + value);
			}
		}
		return auto;
	}
	public void putIntoLHM(Automobile auto){
		autoMap.put(auto.getMake(),auto);
	}
	
	public void addAuto(String carName) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		buildAuto(carName);
		System.out.println("Printing addAuto: " +carName+".ser");
		Automobile auto = FileIO.deserializeAuto(carName+".ser");
		autoMap.put(carName, auto);
		System.out.println("Done adding " + carName + " to LHM");
		//printMap();
	}
	public void removeAuto(String fileName){
		autoMap.remove(fileName);
	}
	
	public void printMap(){
		int i = 1;
		Set set = autoMap.entrySet();
		Iterator<OptionSet> list = set.iterator();
		System.out.println("LinkedHashMap Entries: ");
		while (list.hasNext()){
			Map.Entry car = (Map.Entry)list.next();
			System.out.println(i + ": " + car.getKey());
			i++;
		}
	}
	public String printMapToString(){
		StringBuilder stringMap = new StringBuilder("LinkedHashMap Entries:\n");
		int i = 1;
		Set set = autoMap.entrySet();
		Iterator<OptionSet> list = set.iterator();
		//System.out.println("LinkedHashMap entries: ");
		while (list.hasNext()){
			Map.Entry car = (Map.Entry)list.next();
			stringMap.append(i);
			stringMap.append(':');
			stringMap.append(car.getKey());
			stringMap.append('\n');
			//System.out.println(i + ": " + car.getKey());
			i++;
		}
		//System.out.println("printing map to string");
		//System.out.println(stringMap.toString());
		return stringMap.toString();
	}
	
	public synchronized void updateOptionSetNameSync (String modelName, String optionSetName, String newName) throws IOException{
		
	}
	public synchronized void updateOptionNameSync (String modelName, String optionSetName, String option, String newName) throws IOException{
	    
	}
	public synchronized void updateOptionPriceSync (String modelName, String optionSetName, String option, float newPrice)throws IOException{
	    
	}
	
	public void buildAuto(String carName) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		AutomobileBuilder autoBuilder = new AutomobileBuilder();
		Automobile a1 = new Automobile();
		a1 = autoBuilder.buildAutoObject(carName);
		FileIO.serializeAuto(a1);
	}
	/*
	public void buildAuto(String carName, int fileType)throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		Automobile a1 = new Automobile();
		if (fileType == 1){
			FileIO file = new FileIO();
			a1 = createAutoFromProps(file.readProperty(carName));
			putIntoLHM(a1);
		}
		else if (fileType == 2){
			AutomobileBuilder autoBuilder = new AutomobileBuilder();
			a1 = autoBuilder.buildAutoObject(carName);
			FileIO.serializeAuto(a1);
		}
	}*/
	public void buildAuto(Object car, int fileType)throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		Automobile a1 = new Automobile();
		if (fileType == 1){
			a1 = createAutoFromProps((LinkedProperties)car);
			putIntoLHM(a1);
		}
		else if (fileType == 2){
			AutomobileBuilder autoBuilder = new AutomobileBuilder();
			a1 = autoBuilder.buildAutoObject(((Automobile) car).getName());
			FileIO.serializeAuto(a1);
		}
	}
	public void printAuto(String modelName) throws IOException{
		Automobile auto = null;
		String carName = modelName + ".ser";
		auto = FileIO.deserializeAuto(carName);
		auto.print();
	}
	public Automobile returnAuto(String modelName) throws IOException{
		Automobile auto = null;
		auto = autoMap.get(modelName);
		return auto;
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