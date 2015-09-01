package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import Adapter.BuildAuto;
import Model.Automobile;
import Util.LinkedProperties;

public class SelectCarOption {
	
	public String request() throws ClassNotFoundException{
		int userChoice = 0;
		Scanner userReq = new Scanner(System.in);
		String request = "Bye.";
		
		System.out.println("\nConfig Options:\n1: Show Auto Map\n2: Show Car in Detail\n3: Exit back to Main Menu");
		userChoice = Integer.parseInt(userReq.next());
		
		try{
			switch(userChoice){
			case 0: System.out.println("Error at CarOption");break;
			case 1: request = "AutoMap";break;
			case 2: request = requestingCar();break;
			case 3: System.out.println("Returning to Main Menu");break;
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.printf("Returning request: " + request);
		return request;
	}
	public String requestingCar() throws IOException, ClassNotFoundException{
		Scanner carNameChoice = new Scanner(System.in);
		System.out.printf("Enter Car Name: ");
		String carChoice = carNameChoice.nextLine().trim();
		return carChoice;
	}
}
