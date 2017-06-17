package andresdlrg.activemq.stresser.model;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class AttributeMapping {

	private String fieldName;
	private String fieldType;
	private String fieldValue;
	private ExtraParamService extraParam;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public ExtraParamService getExtraParam() {
		return extraParam;
	}

	public void setExtraParam(ExtraParamService extraParam) {
		this.extraParam = extraParam;
	}

}
