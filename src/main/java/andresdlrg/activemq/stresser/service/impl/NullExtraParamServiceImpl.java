package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class NullExtraParamServiceImpl implements ExtraParamService {

	public NullExtraParamServiceImpl() {
	}

	@Override
	public Object getValue() {
		return null;
	}

}