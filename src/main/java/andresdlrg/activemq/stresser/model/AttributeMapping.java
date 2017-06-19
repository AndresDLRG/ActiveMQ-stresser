package andresdlrg.activemq.stresser.model;

import andresdlrg.activemq.stresser.service.ExtraParamService;
import andresdlrg.activemq.stresser.util.AttributeExtraParamUtil;

public class AttributeMapping {

	private String fieldName;
	private String fieldType;
	private String fieldValue;
	private ExtraParamService extraParam;

	public AttributeMapping(String attributeMappingString) {
		String[] params = attributeMappingString.split("\\|");

		fieldName = params[0].trim();
		fieldType = params[1].trim();
		fieldValue = params[2].trim();

		String extraParamString = params.length == 3 ? "" : params[3].trim();

		extraParam = AttributeExtraParamUtil.generateExtraParamService(this, extraParamString);
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public ExtraParamService getExtraParam() {
		return extraParam;
	}

}
