package Util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Exception.FileNameNotFound;
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
}

