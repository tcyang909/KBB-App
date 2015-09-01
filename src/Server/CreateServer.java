package Server;

import java.io.*;
import java.net.*;
import java.util.Properties;

import Util.LinkedProperties;
import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;

public class CreateServer {
	public static void main(String[] args) throws IOException, ClassNotFoundException, MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData {	
		ServerSocket serverSocket = null;
		DefaultSocketClient s1 = null;
		Socket soc = null;
	    try{
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			System.err.println("Could not listen on port: 4444.");
			System.exit(1);
		}
	    System.out.println("Server Created");
	    
	    while (true){
		    try {
		    	soc = serverSocket.accept();
		    	s1 = new DefaultSocketClient(soc);
		    	s1.run();
		    } catch (IOException e) {
		        System.err.println("Accept failed.");
		        System.exit(1);
		    }
		    System.out.println("Server Starting");
			s1.objWriter.writeObject("Welcome to Project 5");
			Object fromClient;
			BuildCarModelOptions build = new BuildCarModelOptions();
			while ((fromClient = s1.objReader.readObject()) != null) {
				System.out.println("Server Reading Object from Client");
				
				if (fromClient instanceof LinkedProperties){
					build.buildAuto(fromClient,1);
					s1.objWriter.writeObject("Properties was put into LHM");
				}else if(fromClient instanceof Integer){
					while (fromClient != null){
						build.printMap();
					}
				}else if (fromClient instanceof String){
					String buffer = (fromClient.toString());
					if (buffer.equals("Bye.")){
						s1.objWriter.writeObject("Bye.");
						System.out.println("Server is Exiting");
						System.exit(1);
					}else if (buffer.equals("AutoMap")){
						
						s1.objWriter.writeObject(build.printMapToString());//problem here
					}else{
						System.out.println("returning Auto");
						s1.objWriter.writeObject(build.returnAuto(buffer));
					}
						
				}else{
					System.out.println("Error, File is Not a Properties");
					System.out.println("Bye.");
				}
				
			}
        }
		
	}
}
