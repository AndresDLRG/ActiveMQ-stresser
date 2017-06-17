package andresdlrg.activemq.stresser.model;

public class DataToSendConfiguration {

	private String classTypeToSend;
	private boolean useSameObjectAlways;
	private String attributesToWrite;

	public String getClassTypeToSend() {
		return classTypeToSend;
	}

	public void setClassTypeToSend(String classTypeToSend) {
		this.classTypeToSend = classTypeToSend;
	}

	public boolean isUseSameObjectAlways() {
		return useSameObjectAlways;
	}

	public void setUseSameObjectAlways(boolean useSameObjectAlways) {
		this.useSameObjectAlways = useSameObjectAlways;
	}

	public String getAttributesToWrite() {
		return attributesToWrite;
	}

	public void setAttributesToWrite(String attributesToWrite) {
		this.attributesToWrite = attributesToWrite;
	}

}
