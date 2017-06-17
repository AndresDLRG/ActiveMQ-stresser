package andresdlrg.activemq.stresser.model;

public class LimitConfiguration {

	private long maxObjectsToSend;
	private long maxExecutionTime;

	public long getMaxObjectsToSend() {
		return maxObjectsToSend;
	}

	public void setMaxObjectsToSend(long maxObjectsToSend) {
		this.maxObjectsToSend = maxObjectsToSend;
	}

	public long getMaxExecutionTime() {
		return maxExecutionTime;
	}

	public void setMaxExecutionTime(long maxExecutionTime) {
		this.maxExecutionTime = maxExecutionTime;
	}

}
