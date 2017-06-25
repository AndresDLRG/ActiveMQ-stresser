package andresdlrg.activemq.stresser.model;

public class ThroughputConfiguration {

	private Long firstDelayMillis;
	private Character timePeriod;
	private Long instancesPerTimePeriod;

	public Long getFirstDelayMillis() {
		return firstDelayMillis;
	}

	public void setFirstDelayMillis(Long firstDelayMillis) {
		this.firstDelayMillis = firstDelayMillis;
	}

	public Character getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(Character timePeriod) {
		this.timePeriod = timePeriod;
	}

	public Long getInstancesPerTimePeriod() {
		return instancesPerTimePeriod;
	}

	public void setInstancesPerTimePeriod(Long instancesPerTimePeriod) {
		this.instancesPerTimePeriod = instancesPerTimePeriod;
	}

}
