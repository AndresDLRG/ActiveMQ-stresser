package andresdlrg.activemq.stresser.sender;

import andresdlrg.activemq.stresser.jms.MessageSender;
import andresdlrg.activemq.stresser.model.DataToSendConfiguration;
import andresdlrg.activemq.stresser.model.LimitConfiguration;
import andresdlrg.activemq.stresser.model.ThroughputConfiguration;

public class ActivemqStresserService {

	private MessageSender messageSender;
	private LimitConfiguration limits;
	private DataToSendConfiguration data;
	private ThroughputConfiguration throughput;
	
	public void startSending() {
		// TODO
	}

	public MessageSender getMessageSender() {
		return messageSender;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public LimitConfiguration getLimits() {
		return limits;
	}

	public void setLimits(LimitConfiguration limits) {
		this.limits = limits;
	}

	public DataToSendConfiguration getData() {
		return data;
	}

	public void setData(DataToSendConfiguration data) {
		this.data = data;
	}

	public ThroughputConfiguration getThroughput() {
		return throughput;
	}

	public void setThroughput(ThroughputConfiguration throughput) {
		this.throughput = throughput;
	}

}
