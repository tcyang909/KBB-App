package Scale;

import java.io.IOException;

import Model.Automobile;
import Util.FileIO;
import Adapter.ProxyAutomobile;
import Adapter.ScaleThread;

public class EditOptions extends ProxyAutomobile implements Runnable, ScaleThread {

	private Thread a1;
	private boolean running = true;
    int ops;
    String modelName, optionSetName, option, newName;
    float newPrice;
    
    public EditOptions(int ops, CarInfo car) {
        this.ops = ops; //create an instance of a1 for a given ops
        a1 = new Thread(this);
        
        modelName = car.getModelName();
        optionSetName = car.getOptionSetName();
        option = car.getOption();
        newName = car.getNewName();
        newPrice = car.getNewPrice();
        
        //a1.start();
    }
    void randomWait() {
        try {
            Thread.currentThread().sleep((long)(3000*Math.random()));
        } catch(InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }  
    public void run() {
    	try{
	        switch(ops)
	        {
	           case 0: updateOptionSetNameSync (modelName, optionSetName, newName); break;
	           case 1: updateOptionNameSync (modelName, optionSetName, option, newName); break;
	           case 2: updateOptionPriceSync (modelName, optionSetName, option, newPrice); break;
	        }
    	}catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void start () {
    	a1.start();
    }
    void stop () {
    	running = false;
    }    
    //Update operations for Fleet/Automobile/OptionSet/Option - Wrapper methods
    public synchronized void updateOptionSetNameSync (String modelName, String optionSetName,String newName) throws IOException {
    	Automobile auto = autoMap.get(modelName);
		auto.updateOptionSetName(optionSetName, newName);
		auto.print();
		auto.printWithOutSync();
		autoMap.put(modelName, auto);
    }
    public synchronized void updateOptionNameSync (String modelName, String optionSetName, String option, String newName) throws IOException{
    	Automobile auto = autoMap.get(modelName);
		auto.updateOptionName(optionSetName, option, newName);
		auto.print();
		autoMap.put(modelName, auto);
    }
    public synchronized void updateOptionPriceSync (String modelName, String optionSetName, String option, float newPrice) throws IOException{
    	Automobile auto = autoMap.get(modelName);
		auto.updateOptionPrice(optionSetName, option, newPrice);
		auto.print();
		autoMap.put(modelName, auto);
    }
    
    
    
}
