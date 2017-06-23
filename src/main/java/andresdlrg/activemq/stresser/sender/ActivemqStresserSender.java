package andresdlrg.activemq.stresser.sender;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.jms.MessageSender;
import andresdlrg.activemq.stresser.model.AttributeMapping;
import andresdlrg.activemq.stresser.model.DataToSendConfiguration;
import andresdlrg.activemq.stresser.model.LimitConfiguration;
import andresdlrg.activemq.stresser.model.ThroughputConfiguration;
import andresdlrg.activemq.stresser.util.AttributeMappingConverter;

public class ActivemqStresserSender {

	private static Logger log = LoggerFactory.getLogger(ActivemqStresserSender.class);

	private MessageSender messageSender;
	private LimitConfiguration limits;
	private DataToSendConfiguration data;
	private ThroughputConfiguration throughput;

	private long count = 0;
	private long initialTime = 0;
	private long millisBetweenSent = 0;
	
	private static final long MILLIS_IN_HOUR = 3_600_00;
	private static final long MILLIS_IN_MINUTE = 60_000;
	private static final long MILLIS_IN_SECOND = 1_000;

	public void startSending() throws Exception {

		if ("h".equals(throughput.getTimePeriod())) {
			millisBetweenSent = MILLIS_IN_HOUR / throughput.getInstancesPerTimePeriod();
		} else if ("m".equals(throughput.getTimePeriod())) {
			millisBetweenSent = MILLIS_IN_MINUTE / throughput.getInstancesPerTimePeriod();
		} else if ("s".equals(throughput.getTimePeriod())) {
			millisBetweenSent = MILLIS_IN_SECOND / throughput.getInstancesPerTimePeriod();
		} else {
			throw new Exception();
		}
		
		Class<?> classHandle = Class.forName(data.getClassTypeToSend());
		log.info("The class to send is {}", classHandle.getName());
		List<AttributeMapping> mappings = AttributeMappingConverter
				.stringToAttributesMapping(data.getAttributesToWrite());
		
		log.info("Delaying start by {} milliseconds", throughput.getFirstDelayMillis());
		// start delaying
		Thread.sleep(throughput.getFirstDelayMillis());
		
		initialTime = System.currentTimeMillis();
		log.info("Sending start Time = [{}]", new Date(initialTime));

		// check limits
		while (count < limits.getMaxObjectsToSend()
				&& System.currentTimeMillis() - initialTime < limits.getMaxExecutionTime()) {
			long initCreatingTime = System.currentTimeMillis();
			// Generating the object to send
			Object object = classHandle.newInstance();
			for (AttributeMapping att : mappings) {
				Field field = classHandle.getDeclaredField(att.getFieldName());
				field.setAccessible(true);
				field.set(object, att.getExtraParam().getValue());
			}

			log.debug(object.toString());
			messageSender.sendObject((Serializable) object);
			count++;
			log.info("Object [{}] sent", count);
			log.debug("Object sent = [{}]", object);
			long processedTime = System.currentTimeMillis() - initCreatingTime;

			long timeToSleep = millisBetweenSent - processedTime;
			if (timeToSleep < 0) {
				timeToSleep = 0;
			}
			Thread.sleep(timeToSleep);
		}
		if (count >= limits.getMaxObjectsToSend()) {
			log.info("Max objects to send ({}) reached", limits.getMaxObjectsToSend());
		} else {
			log.info("Max execution time ({}ms)reached", limits.getMaxExecutionTime());
		}
		
		log.info("Sending end Time = [{}]", new Date());
		reset();
	}

	private void reset() {
		count = 0;
		initialTime = 0;
		millisBetweenSent = 0;
	}

	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public void setLimits(LimitConfiguration limits) {
		this.limits = limits;
	}

	public void setData(DataToSendConfiguration data) {
		this.data = data;
	}

	public void setThroughput(ThroughputConfiguration throughput) {
		this.throughput = throughput;
	}

}
