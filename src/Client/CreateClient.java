package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;
import Server.DefaultSocketClient;

public class CreateClient extends DefaultSocketClient{
	public Socket Soc = null;
	Scanner start = new Scanner(System.in);
	
	public CreateClient() throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, NumberFormatException, ClassNotFoundException, UnknownHostException, IOException{
		super (new Socket("localhost", 4444));
		this.run();
		try {
	        System.out.println("Client Created");
	        selectServiceOption();
	    } catch (UnknownHostException e) {
	        System.err.println("Don't know about host: taranis.");
	        System.exit(1);
	    } catch (IOException e) {
	        System.err.println("Couldn't get I/O for the connection to: taranis.");
	        e.printStackTrace();
	        System.exit(1);
	    }
	}
	public void selectServiceOption() throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException, NumberFormatException, ClassNotFoundException{
		Object fromServer;
		int input2;
		
		while ((fromServer = objReader.readObject()) != null){
			
			if (fromServer.getClass() == String.class){
				System.out.println("Server: " + fromServer.toString());
				if (fromServer.toString().equals("Bye.")){
					System.out.println("Closing!");
					System.exit(1);
				}
			}
			try{
				int restarting= 0;
				do{
					input2 = printMainMenu();
					restarting = performOperation(input2);
				}while (restarting == 1);
			}catch (NumberFormatException e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	public int printMainMenu(){
		String fromUser = null;
		int input = 0;
		System.out.println("\nMenu:\n1: Upload a Properties File\n2: Upload a Text File\n3: Configure a car\n4: Exit");
		fromUser = start.next();
		if (fromUser != null) 
			input = Integer.parseInt(fromUser);
		return input;
	}
	public int performOperation(int input) throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, ClassNotFoundException {
		CarModelOptionsIO io = new CarModelOptionsIO();
		int restart = 0;
		try{
			switch(input){
			case 0: System.out.println("Error, Try Again");break;
			case 1: objWriter.writeObject(io.readDataFromProps());break;
			case 2: objWriter.writeObject(io.readDataFromText());break;
			case 3: carOptions();restart = 1;break;
			case 4: objWriter.writeObject("Bye.");break;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return restart;
	}
	public void carOptions() throws ClassNotFoundException, IOException{
		SelectCarOption caropt = new SelectCarOption();
		Automobile localAuto = null;
		Object fromServer;
		Object fromClient;
		
		fromClient = caropt.request();
		while (fromClient != "Bye."){
			objWriter.writeObject(fromClient);
			fromServer = objReader.readObject();
			while (fromServer != null){
				if(fromServer instanceof String){
					//System.out.println("I should be printing automap");
					System.out.println(fromServer.toString());
				}
				else if(fromServer instanceof Automobile){
					localAuto = (Automobile)fromServer;
					localAuto.printAsProp();
				} else {
					System.out.println ("Error, requesting car is not an automobile");
				}
				fromServer = null;
			}
			fromClient = caropt.request();
		}
	}
	public void close() throws IOException{
		Soc.close();
	}
}
