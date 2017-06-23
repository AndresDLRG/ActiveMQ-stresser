package andresdlrg.activemq.stresser.model;

public class ThroughputConfiguration {

	private long firstDelayMillis;
	private char timePeriod;
	private long instancesPerTimePeriod;

	public long getFirstDelayMillis() {
		return firstDelayMillis;
	}

	public void setFirstDelayMillis(long firstDelayMillis) {
		this.firstDelayMillis = firstDelayMillis;
	}

	public char getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(char timePeriod) {
		this.timePeriod = timePeriod;
	}

	public long getInstancesPerTimePeriod() {
		return instancesPerTimePeriod;
	}

	public void setInstancesPerTimePeriod(long instancesPerTimePeriod) {
		this.instancesPerTimePeriod = instancesPerTimePeriod;
	}

}
