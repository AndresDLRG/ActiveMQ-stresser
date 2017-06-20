package andresdlrg.activemq.stresser.sender;

import java.io.Serializable;
import java.lang.reflect.Field;
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

	public void startSending() throws Exception {
		Class<?> classHandle = Class.forName(data.getClassTypeToSend());

		List<AttributeMapping> mappings = AttributeMappingConverter
				.stringToAttributesMapping(data.getAttributesToWrite());
		long count = 0;
		long secondsRunning = 0;

		// check limits

		while (count < limits.getMaxObjectsToSend()) {
			Object object = classHandle.newInstance();
			for (AttributeMapping att : mappings) {
				Field field = classHandle.getDeclaredField(att.getFieldName());
				field.setAccessible(true);
				field.set(object, att.getExtraParam().getValue());
			}
			log.debug(object.toString());
			System.out.println(object);
			messageSender.sendObject((Serializable) object);

			count++;
		}
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
