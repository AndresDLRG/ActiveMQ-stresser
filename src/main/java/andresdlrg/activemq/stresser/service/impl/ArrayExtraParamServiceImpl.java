package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class ArrayExtraParamServiceImpl implements ExtraParamService {

	private String[] stringArray;

	public ArrayExtraParamServiceImpl(String valueString, String separator) {
		stringArray = valueString.split(separator);
	}

	@Override
	public Object getValue() {
		return stringArray;
	}

}
