package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Scanner;

import Exception.MissingOptionData;
import Exception.MissingOptionSetData;
import Exception.MissingPriceForAutomobileInTextFile;
import Model.Automobile;
import Server.BuildCarModelOptions;
import Util.FileIO;
import Util.LinkedProperties;

public class CarModelOptionsIO {
	private ObjectOutputStream objWriter;
	private BufferedReader in = null;
	
	public LinkedProperties readDataFromText () throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		Scanner prosStart2 = new Scanner(System.in);
		System.out.printf("Please Enter File Name: ");
		String fileName = prosStart2.nextLine().trim();
		System.out.println("Starting to read: " + fileName);
		LinkedProperties props = null;
		FileIO f1 = new FileIO();
		props = f1.textIntoProperty(fileName);
		//prosStart2.close();
		//System.out.println("Returning props to Client");
		return props;
	}
	public LinkedProperties readDataFromProps () throws MissingPriceForAutomobileInTextFile, MissingOptionSetData, MissingOptionData, IOException{
		Scanner propsStart = new Scanner(System.in);
		System.out.printf("Please Enter File Name: ");
		String fileName = propsStart.nextLine().trim();
		System.out.println("Starting to read: " + fileName);
		LinkedProperties props = null;
		FileIO f1 = new FileIO();
		props = f1.uploadProperty(fileName);
		//propsStart.close();
		return props;
	}
/*
	public void close() throws IOException{
		in.close();
		objWriter.close();
	}
	*/
}