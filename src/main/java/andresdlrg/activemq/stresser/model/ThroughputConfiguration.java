package andresdlrg.activemq.stresser.model;

public class ThroughputConfiguration {

	private char timePeriod;
	private long instancesPerTimePeriod;

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
