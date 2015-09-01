package Model;

import java.util.ArrayList;
import Exception.MissingOptionChoice;

public class OptionSet implements java.io.Serializable {
	private String name;
	ArrayList<Option> opt;
	private Option choice;
	
	
	OptionSet(){
		opt = new ArrayList();
	}
	OptionSet(String name){
		opt = new ArrayList();
		this.name = name;
	}
	protected Option getChoice() {
		return choice;
	}
	protected void setChoice(Option choice) {
		this.choice = choice;
	}
	protected String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}
	protected Option getOptionChoice() throws MissingOptionChoice{
		return this.getChoice();
	}
	protected void setOptionChoice(String optionName){
		this.setChoice(findOpt(optionName));
	}
	protected ArrayList<Option> getOpt() {
		return opt;
	}
	protected void setOptName(int i, String name){
		this.opt.get(i).setName(name);
	}
	protected void setOpt(int i, String name, int price){
		this.opt.add(i, new Option(name, price));
	}
	protected Option getOpt(String name){
		for (int i = 0; i < opt.size(); i++)
			if (name.equals(opt.get(i)))
				return opt.get(i);
		return null;
	}
	protected float getOptPrice (String name){
		for (int i = 0; i < opt.size(); i++)
			if (name.equals(opt.get(i)))
				return opt.get(i).getPrice();
		return -1;
	}
	public Option findOpt(String optName){
		for (int j = 0; j < this.opt.size(); j++)
			if (this.opt.get(j).getName().equals(optName))
				return this.opt.get(j);
		return null;
	}
	protected void delete (int x){
		opt.remove(x);
	}
	
	protected void updateOpsetName(String newName){
		this.name = newName;
	}
	protected void print(){
		System.out.printf("Name: %s", name);
	}
}
/*
OptionSet(int size){
	this.opt = new Option[size];
	for (int j = 0; j < opt.length; j++)
		opt[j] = new Option();
}
OptionSet(String name, int size){
	this.opt = new Option[size];
	for (int j = 0; j < opt.length; j++)
		opt[j] = new Option();
	this.name = name;
}
protected Option getOpt(String name){
		for (int i = 0; i < opt.length; i++)
			if (name.equals(opt[i]))
				return opt[i];
		return null;
	}
	protected void setOpt(Option opt) {
		this.opt = opt;
	}
	
	protected void setOptNum(int x){
		this.opt = new Option[x];
		for (int j = 0; j < opt.length; j++)
			opt[j] = new Option();
	}
	protected void setOptName(String oldName, String newName){
		//this.opt.setName(name);
	}
	protected float getOptPrice (String name){
		for (int i = 0; i < opt.length; i++)
			if (name.equals(opt[i]))
				return opt[i].getPrice();
		return -1;
	}
	protected int findOpt (String name){
		for (int i = 0; i < opt.length; i++)
			if (name.equals(opt[i]))
				return i;
		return -1;
	}
	protected void delete (int x){
		opt[x] = null;
		opt[x] = new Option();
	}
*/
