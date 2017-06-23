package andresdlrg.activemq.stresser.model;

public class DataToSendConfiguration {

	private String classTypeToSend;
	private String attributesToWrite;

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

}
