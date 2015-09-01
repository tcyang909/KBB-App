package Scale;

public class CarInfo {
	private String modelName, optionSetName, option, newName;
	private float newPrice;
	
	public CarInfo(String modelName, String optionSetName, String newName){
		this.modelName = modelName;
		this.optionSetName = optionSetName;
		this.newName = newName;
	}
	public CarInfo(String modelName, String optionSetName, String option, String newName){
		this.modelName = modelName;
		this.optionSetName = optionSetName;
		this.option = option;
		this.newName = newName;
	}
	public CarInfo(String modelName, String optionSetName, String option, float newPrice){
		this.modelName = modelName;
		this.optionSetName = optionSetName;
		this.option = option;
		this.newPrice = newPrice;
	}
	
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getOptionSetName() {
		return optionSetName;
	}

	public void setOptionSetName(String optionSetName) {
		this.optionSetName = optionSetName;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public float getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(float newPrice) {
		this.newPrice = newPrice;
	}
}