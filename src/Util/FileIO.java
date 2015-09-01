package Util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Properties;

import Model.Automobile;

public class FileIO {
	
	public static void serializeAuto(Automobile auto) throws IOException{
		String saveFileName = auto.getName().replaceAll("\\s+", "") + ".ser";
		//System.out.println("Printing serialize " + saveFileName);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(saveFileName));
			out.writeObject(auto);
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static Automobile deserializeAuto(String autoName) throws IOException{
		Automobile a = null;
		try{
			ObjectInputStream ois = new ObjectInputStream( new FileInputStream(autoName));
			a = (Automobile) ois.readObject();
			ois.close();
		}catch(ClassNotFoundException e){
			System.out.println("Automotive class not found");
			e.printStackTrace();
		}
		return a;
	}
	public LinkedProperties textIntoProperty(String fileName){
		String autoName = fileName + ".txt";
		LinkedProperties props= new LinkedProperties();
		try{
			FileReader file = new FileReader(autoName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			int lineNum = 1;
			String[] tokens = null;
			
			while(!eof){
				String line = buff.readLine();
				if(line == null){
					eof = true;
					break;
				}
				tokens = line.split("=");
				//System.out.println("help1");
				if(tokens[0].contains("CarMake")){
					props.setProperty("CarMake", tokens[1]);
					//System.out.println("Test in creation: " + )
				}
				else if(tokens[0].contains("CarModel")){
					props.setProperty("CarModel", tokens[1]);
				}
				else if (tokens[0].contains("OptionSet")){
					props.setProperty(tokens[0], tokens[1]);
				}
				else if(tokens[0].contains("OptionValue")){
					props.setProperty(tokens[0], tokens[1]);
				}
				else
					System.out.println("Type on line: " + lineNum);
				lineNum++;
				//System.out.println("help2");
			}
			File fileout = new File(fileName + ".properties");
			FileOutputStream out = new FileOutputStream(fileout);
			props.store(out, fileName);
			buff.close();
			out.close();
			
			
		} catch (IOException ex){
			System.out.println("File Not Found!");
			//ex.printStackTrace();
		}
		return props;
	}
	public LinkedProperties uploadProperty(String fileName) throws IOException{
		LinkedProperties props = new LinkedProperties();
		String propName = fileName + ".properties";
		File f = new File(propName);
		FileInputStream in = new FileInputStream(f);
		props.load(in);
		
		return props;
	}
}