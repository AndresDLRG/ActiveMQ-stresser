package andresdlrg.activemq.stresser.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.model.AttributeMapping;

public class AttributeMappingConverter {
	
	private static Logger log = LoggerFactory.getLogger(AttributeMappingConverter.class);

	public static List<AttributeMapping> stringToAttributesMapping(String attributes) throws Exception {
		log.debug("Generating AttributteMappings from String - {}", attributes);
		List<AttributeMapping> mappings = new ArrayList<>();

		String[] attributeStringarray = attributes.split("@@");
		for (String attributeString : attributeStringarray) {
			AttributeMapping mapping = new AttributeMapping(attributeString);
			mappings.add(mapping);
		}
		log.debug("{} AttributeMapping generated in the list", mappings.size());

		return mappings;
	}

}
