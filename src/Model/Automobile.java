package Model;

import java.util.*;

public class Automobile implements java.io.Serializable {
	private String name, make, model;
	private float basePrice;
	ArrayList<OptionSet> opset;
	
	
	public String getOptionChoice (String setName){
		return findOpset(setName).getChoice().getName();
	}
	public int getOptionChoicePrice (String setName){
		return (int) findOpset(setName).getChoice().getPrice();
	}
	public synchronized void setOptionChoice (String setName, String optionName){
		findOpset(setName).setChoice(findOpt(setName, optionName));
	}
	public int getTotalPrice(){
		Iterator<OptionSet> list = opset.iterator();
		int total = (int) getBasePrice();
		while (list.hasNext()){
			total += getOptionChoicePrice(getOptionChoice(list.next().getName()));
		}
		return total;
	}
	
	
	public Automobile() {
		opset = new ArrayList();
	}
	public Automobile(String n, float price){
		opset = new ArrayList();
		this.name = n;
		this.basePrice = price;
	}
	public String getMake() {
		return make;
	}
	public synchronized void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public synchronized void setModel(String model) {
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public float getBasePrice() {
		return basePrice;
	}
	public synchronized void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	public OptionSet getOpset(int x) {
		return opset.get(x);
	}
	public synchronized OptionSet findOpset(String opsetName){
		for (int i = 0; i < opset.size(); i++)
			if (opset.get(i).getName().equals(opsetName))
				return opset.get(i);
		return null;
	}
	public synchronized Option findOpt(String opsetName, String optName){
		for (int j = 0; j < findOpset(opsetName).opt.size(); j++)
			if (findOpset(opsetName).opt.get(j).getName().equals(optName))
				return findOpset(opsetName).opt.get(j);
		return null;
	}
	public void AddNewOptionSet (String name){
		opset.add(new OptionSet(name));
	}
	public void AddNewOptionName (String opsetName, String optName){
		findOpset(opsetName).opt.add(new Option(optName));
	}
	public void AddNewOptionNamePrice (String opsetName, String optName, float optPrice){
		findOpset(opsetName).opt.add(new Option(optName, optPrice));
	}
	public synchronized void setOpsetName (int x, String opsetName){
		this.opset.get(x).setName(opsetName);
	}
	public synchronized void setOptName(int opsetNum, int optNum, String optName) {
		this.opset.get(opsetNum).setOptName(optNum, optName);
	}
	public synchronized void setOpt(OptionSet opset, Option opt, int x){
		opset.opt.set(x, opt);
	}
	public synchronized void deleteOptionSet (int x){		
		opset.remove(x);
	}
	public synchronized void deleteOption (OptionSet opset, int x){
		opset.opt.remove(x);
	}
	public synchronized void updateOptionSetName(String opsetName, String newName){
		findOpset(opsetName).setName(newName);
	}
	public synchronized void updateOptionName(String opsetName, String optName, String newName){
		findOpt(opsetName, optName).setName(newName);
	}
	public synchronized void updateOptionPrice(String opsetName, String optName, float newPrice){
		findOpt(opsetName, optName).setPrice(newPrice);
	}
	public synchronized void updateOption(OptionSet opset, Option opt, int x){
		opset.opt.set(x,opt);
	}
	public void printOptionSet(){
		//System.out.printf("\nTESTING\n");
		for (int t = 0; t < opset.size(); t++)
			System.out.println(this.opset.get(t).getName());
	}
	public String printOptionSetToString(){
		StringBuilder stringCar = new StringBuilder(2000);
		for (int t = 0; t < opset.size(); t++){
			//System.out.println(this.opset.get(t).getName());
			stringCar.append(this.opset.get(t).getName());
		}
		return stringCar.toString();
	}
	public void printWithOutSync(){
		randomWait();
		System.out.printf("\nPrinting Automotive W/O SYNC:");
		System.out.printf("\nName: %s", this.name);
		System.out.printf("\nBase Price: %.2f", this.basePrice);
		for (int r = 0; r < this.opset.size(); r++){
			System.out.printf("\nOption: %s", this.opset.get(r).getName());
			for (int c = 0; c < opset.get(r).opt.size(); c++){
				randomWait();
				System.out.printf("\n%d: %s = %.2f", c+1, this.opset.get(r).opt.get(c).getName(),  this.opset.get(r).opt.get(c).getPrice());
			}
		}
		System.out.printf("\nEnd of Printing W/O SYNC\n\n");
	}
	public synchronized void print(){
		System.out.printf("\nPrinting Automotive WITH SYNC:");
		System.out.printf("\nName: %s", this.name);
		System.out.printf("\nBase Price: %.2f", this.basePrice);
		for (int r = 0; r < this.opset.size(); r++){
			System.out.printf("\nOption: %s", this.opset.get(r).getName());
			for (int c = 0; c < opset.get(r).opt.size(); c++){
				System.out.printf("\n%d: %s = %.2f", c+1, this.opset.get(r).opt.get(c).getName(),  this.opset.get(r).opt.get(c).getPrice());
			}
		}
		System.out.printf("\nEnd of Printing WITH SYNC\n\n");
	}
	public void printAsProp(){
		System.out.printf("\nMake: %s", this.make);
		System.out.printf("\nModel: %s", this.model);
		System.out.printf("\nBase Price: %.2f", this.basePrice);
		for (int r = 0; r < this.opset.size(); r++){
			System.out.printf("\nOption Set: %s", this.opset.get(r).getName());
			for (int c = 0; c < opset.get(r).opt.size(); c++){
				//randomWait();
				System.out.printf("\n%d: %s = %.2f", c+1, this.opset.get(r).opt.get(c).getName(),  this.opset.get(r).opt.get(c).getPrice());
			}
		}
		System.out.printf("\n");
		//System.out.printf("\nEnd of Printing W/O SYNC\n\n");
	}
	//Lab 4 test with wait
    void randomWait() {
        try {
            Thread.currentThread().sleep((long)(300));
        } catch(InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }  
}
/*
public Automobile(String n, float price, int size){
	opset = new OptionSet[size];
	for (int i = 0; i < opset.length; i++)
		opset[i] = new OptionSet();
	this.name = n;
	this.basePrice = price;
}
public OptionSet getOpset(int x) {
		return opset[x];
	}
	public OptionSet findOpset(String opsetName){
		for (int i = 0; i < opset.length; i++)
			if (opset[i].getName().equals(opsetName))
				return opset[i];
		return null;
	}
	public Option findOpt(String opsetName, String optName){
			for (int j = 0; j < findOpset(opsetName).opt.length; j++)
				if (findOpset(opsetName).opt[j].getName().equals(optName))
					return findOpset(opsetName).opt[j];
		return null;
	}
	public void setOpset (int x){
		this.opset = new OptionSet[x];
		for (int i = 0; i < opset.length; i++)
			opset[i] = new OptionSet();
	}
	public void setOpsetName (int x, String opsetName){
		opset[x].setName(opsetName);
	}
	public void setOptNum (int opsetNum, int optNum){
		this.opset[opsetNum].setOptNum(optNum);
	}
	public void setOptName(int opsetNum, int optNum, String optName) {
		this.opset[opsetNum].setOptName(optNum, optName);
	}
	public void setOpt(OptionSet opset, Option opt, int x){
		opset.opt[x] = opt;
	}
	public void deleteOptionSet (int x){		
		//opset[x] = ArrayUtils.removeElement(optset[x], element);
		opset[x] = null;
		opset[x] = new OptionSet();
	}
	public void deleteOption (OptionSet opset, int x){
		opset.opt[x] = null;
		opset.opt[x] = new Option();
	}
	public void updateOption(OptionSet opset, Option opt, int x){
		opset.opt[x] = null;
		opset.opt[x] = opt;
	}
	public void printOptionSet(){
		System.out.printf("\nTESTING\n");
		for (int t = 0; t < opset.length; t++)
			System.out.println(this.opset[t].getName());
	}
	public void print(){
		System.out.printf("\nPrinting Automotive:");
		System.out.printf("\nName: %s", this.name);
		System.out.printf("\nBase Price: %.2f", this.basePrice);
		for (int r = 0; r < this.opset.length; r++){
			System.out.printf("\nOption: %s", this.opset[r].getName());

			//System.out.println(opset[r].opt.length);
			for (int c = 0; c < opset[r].opt.length; c++){
				//System.out.println(opset[r].opt.length);
				System.out.printf("\n%d: %s -- %.2f", c+1, this.opset[r].opt[c].getName(),  this.opset[r].opt[c].getPrice());
			}
		}
		System.out.printf("\nEnd of Printing\n");
	}
 */
