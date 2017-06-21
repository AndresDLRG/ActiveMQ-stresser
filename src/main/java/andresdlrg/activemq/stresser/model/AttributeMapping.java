package andresdlrg.activemq.stresser.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.service.ExtraParamService;
import andresdlrg.activemq.stresser.util.AttributeExtraParamUtil;

public class AttributeMapping {
	
	private static Logger log = LoggerFactory.getLogger(AttributeMapping.class);

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
		
		log.debug("Generated AttribteMappping - {}", this);
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttributeMapping [");
		if (fieldName != null) {
			builder.append("fieldName=");
			builder.append(fieldName);
			builder.append(", ");
		}
		if (fieldType != null) {
			builder.append("fieldType=");
			builder.append(fieldType);
			builder.append(", ");
		}
		if (fieldValue != null) {
			builder.append("fieldValue=");
			builder.append(fieldValue);
			builder.append(", ");
		}
		if (extraParam != null) {
			builder.append("extraParam=");
			builder.append(extraParam.getClass().getSimpleName());
		}
		builder.append("]");
		return builder.toString();
	}
	
	

}
