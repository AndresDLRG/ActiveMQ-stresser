package andresdlrg.activemq.stresser.sender;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.exception.InvalidTimePeriod;
import andresdlrg.activemq.stresser.jms.MessageSender;
import andresdlrg.activemq.stresser.model.AttributeMapping;
import andresdlrg.activemq.stresser.model.DataToSendConfiguration;
import andresdlrg.activemq.stresser.model.LimitConfiguration;
import andresdlrg.activemq.stresser.model.ThroughputConfiguration;
import andresdlrg.activemq.stresser.util.AttributeMappingConverter;
import andresdlrg.activemq.stresser.util.ReflectionUtil;

public class ActivemqStresserSender {

	private static Logger log = LoggerFactory.getLogger(ActivemqStresserSender.class);

	private MessageSender messageSender;
	private LimitConfiguration limits;
	private DataToSendConfiguration data;
	private ThroughputConfiguration throughput;

	private long count = 0;
	private long initialTime = 0;
	private long nanosBetweenSent = 0;
	private static final long NANOS_IN_HOUR = 3600000000000L; // 3_600_000_000_000L
	private static final long NANOS_IN_MINUTE = 60000000000L; // 60_000_000_000L
	private static final long NANOS_IN_SECOND = 1000000000L; // 1_000_000_000L
	private static final int NANOS_IN_MILLI = 1000000; // 1_000_000

	public void startSending() throws Exception {
		StringBuilder targetSpeed = new StringBuilder();
		log.info("Destination to write the objects: {}", messageSender.getJmsTemplate().getDefaultDestinationName());
		targetSpeed.append("Target speed = ").append(throughput.getInstancesPerTimePeriod());

		if ("h".equalsIgnoreCase(throughput.getTimePeriod())) {
			nanosBetweenSent = NANOS_IN_HOUR / throughput.getInstancesPerTimePeriod();
			targetSpeed.append(" instances per hour");
		} else if ("m".equalsIgnoreCase(throughput.getTimePeriod())) {
			nanosBetweenSent = NANOS_IN_MINUTE / throughput.getInstancesPerTimePeriod();
			targetSpeed.append(" instances per minute");
		} else if ("s".equalsIgnoreCase(throughput.getTimePeriod())) {
			nanosBetweenSent = NANOS_IN_SECOND / throughput.getInstancesPerTimePeriod();
			targetSpeed.append(" instances per second");
		} else {
			throw new InvalidTimePeriod("timePeriod [" + throughput.getTimePeriod() + "] is not supported");
		}

		log.info("The class to send is {}", data.getClassTypeToSend());
		List<AttributeMapping> mappings = AttributeMappingConverter
				.stringToAttributesMapping(data.getAttributesToWrite());
		log.info(targetSpeed.toString());
		log.info("nanosBetweenSent = {}", nanosBetweenSent);
		log.info("Delaying start by {} milliseconds", throughput.getFirstDelayMillis());
		// start delaying
		Thread.sleep(throughput.getFirstDelayMillis());

		initialTime = System.currentTimeMillis();
		log.info("Sending start Time = [{}]", dateToHumanReadable(new Date(initialTime)));

		long initCreatingTime;
		long processedTime;
		int nanosToSleep;
		long millisToSleep;
		Object object;
		// check limits
		while (count < limits.getMaxObjectsToSend()
				&& System.currentTimeMillis() - initialTime < limits.getMaxExecutionTime()) {
			initCreatingTime = System.nanoTime();

			// Generating the object to send
			String[] argTypes = data.getConstructorArgTypes().equals("") ? new String[0] : data.getConstructorArgTypes().split(",");
			String[] argValues = data.getConstructorArgValues().equals("") ? new String[0] : data.getConstructorArgValues().split(",");
			object = ReflectionUtil.createInstance(data.getClassTypeToSend(), argTypes, argValues);
			for (AttributeMapping att : mappings) {
				ReflectionUtil.set(object, att.getFieldName(), att.getExtraParam().getValue());
			}

			messageSender.sendObject((Serializable) object);
			count++;
			log.info("Objects sent [{}/ {}]", count, limits.getMaxObjectsToSend());
			processedTime = System.nanoTime() - initCreatingTime;

			nanosToSleep = (int) (nanosBetweenSent - processedTime);
			nanosToSleep = nanosToSleep < 0 ? 0 : nanosToSleep;
			millisToSleep = TimeUnit.NANOSECONDS.toMillis(nanosToSleep);
			nanosToSleep = nanosToSleep % NANOS_IN_MILLI;

			Thread.sleep(millisToSleep, nanosToSleep);
		}
		if (count >= limits.getMaxObjectsToSend()) {
			log.info("Max objects to send ({}) reached", limits.getMaxObjectsToSend());
		} else {
			log.info("Max execution time ({}ms) reached", limits.getMaxExecutionTime());
		}
		log.info("Sending start Time = [{}]", dateToHumanReadable(new Date(initialTime)));
		long endTime = System.currentTimeMillis();
		log.info("Sending end Time = [{}]", dateToHumanReadable(new Date(endTime)));
		log.info(targetSpeed.toString());
		log.info("{} objects sent in {} ", count, millisToTimeHumanReadable(System.currentTimeMillis() - initialTime));
		reset();
	}

	private void reset() {
		count = 0;
		initialTime = 0;
		nanosBetweenSent = 0;
	}

	private String millisToTimeHumanReadable(long millis) {
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
		long millis2 = millis % 1000;

		return String.format("%dh %dm %ds %dms", hours, minutes, seconds, millis2);
	}

	private String dateToHumanReadable(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm:ss SSS");
		return sdf.format(date);
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
