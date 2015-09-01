package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;

public class AutomobileBuilder {
	
	public void readSource(String fileName){
		try{
			FileReader file = new FileReader(fileName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			while (!eof){
				String line = buff.readLine();
				if (line == null)
					eof = true;
				else
					System.out.println(line);
			}
			buff.close();
		} catch (IOException e){
			System.out.println("Error -- " + e.toString());
		}
	}
	
	public Automobile buildAutoObject (String filename) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData{
		Automobile a1 = new Automobile();
		String autoName = filename + ".txt";
		try{
			FileReader file = new FileReader(autoName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			int lineNum = 1;
			
			while (!eof){
				String line = buff.readLine();
				if (line == null){
					eof = true;
					break;
				}
				//System.out.println(line);
				String[] tokens = line.split(",");
				
				if(lineNum == 1){
					a1.setName(tokens[0]);
					if(tokens[1] == null || isNumeric(tokens[1]) == false) 
						throw new MissingPriceForAutomobileInTextFile("Missing Price Data");
					else{
						a1.setBasePrice(Float.parseFloat(tokens[1]));
					}
				}
				
				else{
					a1.AddNewOptionSet(tokens[0]);
					if (tokens.length < 2)
						throw new MissingOptionData("Missing Options Data");
					else
						for (int i = 1; i < tokens.length; i++){
							if (i+1 < tokens.length && tokens[i+1] != null && isNumeric(tokens[i+1]) == true){
								float f = Float.parseFloat(tokens[i+1].trim());
								a1.AddNewOptionNamePrice(tokens[0],tokens[i], f);
								i++;
							}
							else 
								a1.AddNewOptionName(tokens[0],tokens[i]);
						}
				}
				lineNum++;
			}
			buff.close();
		} catch (IOException e){
			System.out.println("Error -- " + e.toString());
		} 
		return a1;
	}
	
	public boolean isNumeric (String input){
		try{
			double d = Double.parseDouble(input);
		}
		catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
/*
* Editted 5/13
* public Automobile buildAutoObject (String filename) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData{
		Automobile a1 = new Automobile();
		try{
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			int lineNum = 1, opsetNum = 0, MIAPrice = 1, OptionSets = 0;
			
			while (!eof){
				String line = buff.readLine();
				if (line == null){
					eof = true;
					if(lineNum < OptionSets + 2)
						throw new MissingOptionData("Missing Options Data");
					break;
				}
				System.out.println(line);
				String[] tokens = line.split(",");
				
				if(lineNum == 1){
					a1.setName(tokens[0]);
					if(isInteger(tokens[1]) == false) 
						throw new MissingPriceForAutomobileInTextFile("Missing Price Data");
					else{
						a1.setBasePrice(Float.parseFloat(tokens[1]));
						MIAPrice = 2;
						OptionSets = tokens.length - 2;
					}
					if(OptionSets <= 0)
						throw new MissingOptionSetData("Missing Option Sets Data");
					else{
					
//I need to recreate all this, so that when I read in the file,
//I don't have to "set" up the array, but I'm just adding it into
//an arraylist, the whole thing at once, not one part at a time.
					//a1.setOpset(tokens.length-MIAPrice);
					a1.add
			        for (int i = MIAPrice; i < tokens.length; i++)
			        	a1.setOpsetName(i-MIAPrice, tokens[i]);
					}
				}
				else{
					//a1.setOptNum(opsetNum, tokens.length);
					for (int i = 0; i < tokens.length; i++)
						a1.setOptName(opsetNum, i, tokens[i]);
					opsetNum++;
				}
				lineNum++;
			}
			buff.close();
		} catch (IOException e){
			System.out.println("Error -- " + e.toString());
		} 
		return a1;
	} 
* First Edition 
	public Automobile buildAutoObject(String filename){
		Automobile a1 = new Automobile();
		try{
			FileReader file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false, noMoreOpt = true;
			int opsetNum = -1, lineNum = 1, optNum = 0, optNumCap = 0;
			while (!eof){
				String line = buff.readLine();
				
				if (line == null)
					eof = true;
					
				else if (line.isEmpty()) {
					System.out.println(line);
					continue;
				}
				else{
					System.out.println(line);	
												
					if(optNum == optNumCap){								//trigger if no more input for options in the optionSet
						noMoreOpt = true;
						optNumCap = 0;
						optNum = 0;
					}
					if(lineNum == 1){										//name of car
						a1.setName(line);
						a1.setBasePrice(18445);
					}
					else if (lineNum == 2){										//input amount of option sets 
						a1.setOpset(Integer.parseInt(line));
					}
					
					else if(isInteger(line) == true){							//
						int i = Integer.parseInt(line);							
						opsetNum++;												
						a1.setOptNum(opsetNum, i);								
						optNumCap = i;										
					}
					else if(isInteger(line) != true && noMoreOpt == true){
						a1.setOpsetName(line, opsetNum);
						noMoreOpt = false;
					}
					else if(isInteger(line) != true && noMoreOpt != true ){
						a1.setOpt(opsetNum, optNum, line);
						optNum++;
					}	
				}
				//System.out.printf("OPTNUM: %d", optNum);
				lineNum++;
			}
			buff.close();
		} catch (IOException e){
			System.out.println("Error -- " + e.toString());
		}
		return a1;
	}
	*/
}
