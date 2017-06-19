package andresdlrg.activemq.stresser.util;

import java.util.ArrayList;
import java.util.List;

import andresdlrg.activemq.stresser.model.AttributeMapping;

public class AttributeMappingConverter {

	public static List<AttributeMapping> stringToAttributesMapping(String attributes) throws Exception {
		List<AttributeMapping> mappings = new ArrayList();

		String[] attributeStringarray = attributes.split("@@");
		for (String attributeString : attributeStringarray) {
			AttributeMapping mapping = new AttributeMapping(attributeString);
			mappings.add(mapping);
		}

		return mappings;
	}

}
