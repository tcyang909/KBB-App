package Server;

import java.net.*;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.io.*;

import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;
import Util.AutomobileBuilder;
import Util.FileIO;
import Util.LinkedProperties;

public class DefaultSocketClient extends Thread implements SocketClientInterface, SocketClientConstants{
	public ObjectInputStream objReader;
	public ObjectOutputStream objWriter;
	private Socket sock;
	private String strHost;
	private int iPort;
	
	public void setHost (String strHost){
		this.strHost = strHost;
	}
	public void setPort (int iPort){
		this.iPort = iPort;
	}
	public DefaultSocketClient (String strHost, int iPort){
		setPort (iPort);
		setHost(strHost);
	}
	public DefaultSocketClient (Socket s1){
		sock = s1;
		
	}
	public void run(){
		System.out.println("Default Socket Client Running");
		if (openConnection()){/*
			System.out.println("Connection Opened");
			try {
				System.out.println("Handling Session");
				handleSession();
			} catch (MissingPriceForAutomobileInTextFile | MissingOptionSetData
					| MissingOptionData e) {
				e.printStackTrace();
			}
			closeSession();*/
		}
	}
	public boolean openConnection(){
		System.out.println("Opening Connection");
		try{
					//System.out.println("are you stuck here at open connection?");
			objWriter = new ObjectOutputStream(sock.getOutputStream());
			objWriter.flush();
			objReader = new ObjectInputStream(sock.getInputStream());
					//System.out.println("maybe stuck after objReader?");
			
					//System.out.println("or stuck after objWriter?");
		}catch (Exception e){
			if (DEBUG) System.err.println("Unable to obtain stream ");//to/from " + strHost);
			return false;
		}
		return true;
	}
	
	
	public void handleSession() throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData{
		System.out.println("Handle Session Starting");
		if (DEBUG) System.out.println ("Handling session ");//with " + strHost + ":" + iPort);
		Object o;
		try{
			o = objReader.readObject();
			if (o instanceof LinkedProperties){
				while (o != null){
					BuildCarModelOptions build = new BuildCarModelOptions();
					build.buildAuto(o,1);
					//System.out.println("Properties was put into LHM");
				}
			}else if(o instanceof Integer){
				while (o != null){
					BuildCarModelOptions build2 = new BuildCarModelOptions();
					build2.printMap();
					
				}
			}else{
				System.out.println("Error, File is Not a Properties");
			}
		}catch (IOException e){
			if (DEBUG) System.out.println("Handling session");// with " + strHost + ":" + iPort));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void sendProps(LinkedProperties props){
		try{
			objWriter.writeObject(props);
		}catch (IOException e){
			if (DEBUG) System.out.println("Error writing");// to " + strHost);
		}
	}
	public void handleInput (Object o){};
	public void handleInput(String strInput){
		System.out.println(strInput);
	}   
	public void closeSession(){
		try{
			objWriter = null;
			objReader = null;
			sock.close();
		}catch (IOException e){
			if(DEBUG) System.err.println ("Error closing socket to ");// + strHost);
		}
	}
	/*
	public String startMenu() {
		System.out.println("Menu:\n1: Upload a Properties File\n2: Configure a car\n3: Exit");
		Scanner start = new Scanner(System.in);
		int input = start.nextInt();
		start.close();
		
		System.out.printf("Please Enter File Name: ");
		String fileName = start.nextLine().trim();
		BuildCarModelOptions a1 = new BuildCarModelOptions();
		
		try{
			switch(input){
			case 1: a1.buildAuto(fileName,input);break;//props
			case 2: 
			case 3: 
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	*/
	
	/*
	public void sendOutput (String strOutput){
		try{
			writer.write(strOutput, 0, strOutput.length());
		}catch (IOException e){
			if (DEBUG) System.out.println("Error writing");// to " + strHost);
		}
	}
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
	}
	*/
	/*
	
	
	public static void main (String arg[]){
		//debug main; does daytime on local host
		String strLocalHost = "";
		try{
			strLocalHost = InetAddress.getLocalHost().getHostName();
		}catch (UnknownHostException e){
			System.err.println ("Unable to find local host");
		}
		DefaultSocketClient d = new DefaultSocketClient (strLocalHost, iDAYTIME_PORT);
		d.start();
	}
	*/
}//class Default SocketClient
/*
public void handleSession(){
	String strInput = "";
	if (DEBUG) System.out.println ("Handling session with " + strHost + ":" + iPort);
	try{
		while ((strInput = reader.readLine()) != null)
			handleInput (strInput);
	}catch (IOException e){
		if (DEBUG) System.out.println(("Handling session with " + strHost + ":" + iPort));
	}
}*/
