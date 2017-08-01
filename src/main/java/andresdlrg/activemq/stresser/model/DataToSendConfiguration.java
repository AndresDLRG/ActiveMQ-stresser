package andresdlrg.activemq.stresser.model;

public class DataToSendConfiguration {

	private String classTypeToSend;
	private String attributesToWrite;
	private String constructorArgTypes;
	private String constructorArgValues;

	public String getClassTypeToSend() {
		return classTypeToSend;
	}

	public void setClassTypeToSend(String classTypeToSend) {
		this.classTypeToSend = classTypeToSend;
	}

	public String getAttributesToWrite() {
		return attributesToWrite;
	}

	public void setAttributesToWrite(String attributesToWrite) {
		this.attributesToWrite = attributesToWrite;
	}

	public String getConstructorArgTypes() {
		return constructorArgTypes;
	}

	public void setConstructorArgTypes(String constructorArgTypes) {
		this.constructorArgTypes = constructorArgTypes;
	}

	public String getConstructorArgValues() {
		return constructorArgValues;
	}

	public void setConstructorArgValues(String constructorArgValues) {
		this.constructorArgValues = constructorArgValues;
	}

}
