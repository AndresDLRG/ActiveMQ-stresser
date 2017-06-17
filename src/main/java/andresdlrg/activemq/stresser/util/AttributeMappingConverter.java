package andresdlrg.activemq.stresser.util;

import java.util.ArrayList;
import java.util.List;

import andresdlrg.activemq.stresser.model.AttributeMapping;

public class AttributeMappingConverter {

	public List<AttributeMapping> StringToAttributeMapping(String attributes) throws Exception {
		List<AttributeMapping> mappings = new ArrayList<>();

		String[] attributeStringarray = attributes.split("@@");
		for (String attributeString : attributeStringarray) {
			String[] params = attributeString.split("|");

			AttributeMapping mapping = new AttributeMapping();
			mapping.setFieldName(params[0].trim());
			mapping.setFieldType(params[1].trim());
			mapping.setFieldValue(params[2].trim());
			mapping.setExtraParam(AttributeExtraParamUtil.generateExtraParamService(mapping, params[3].trim()));

			mappings.add(mapping);
		}

		return mappings;
	}

}
