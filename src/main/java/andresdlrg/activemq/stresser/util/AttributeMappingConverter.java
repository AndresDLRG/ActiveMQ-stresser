package andresdlrg.activemq.stresser.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.exception.BadAttributeConfigurationException;
import andresdlrg.activemq.stresser.model.AttributeMapping;

public class AttributeMappingConverter {

	private static Logger log = LoggerFactory.getLogger(AttributeMappingConverter.class);

	public static List<AttributeMapping> stringToAttributesMapping(String attributes) {
		log.debug("Generating AttributteMappings from String - {}", attributes);
		List<AttributeMapping> mappings = new ArrayList<AttributeMapping>();

		String[] attributeStringarray = attributes.split("@@");
		for (String attributeString : attributeStringarray) {
			AttributeMapping mapping;
			try {
				mapping = new AttributeMapping(attributeString);
				mappings.add(mapping);
			} catch (Exception e) {
				throw new BadAttributeConfigurationException(
						"Could not convert String [" + attributeString + "] to AttributeMapping", e);
			}
		}
		log.debug("{} AttributeMapping generated in the list", mappings.size());

		return mappings;
	}

}
