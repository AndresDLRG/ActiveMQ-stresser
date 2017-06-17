package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class NullExtraParamServiceImpl implements ExtraParamService{

	private String originalValue;
	
	public NullExtraParamServiceImpl(String originalValue) {
		this.originalValue = originalValue;
	}

	@Override
	public Object getValue() {
		return originalValue;
	}

}