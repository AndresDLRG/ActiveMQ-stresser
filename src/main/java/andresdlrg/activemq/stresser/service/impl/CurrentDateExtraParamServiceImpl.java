package andresdlrg.activemq.stresser.service.impl;

import java.util.Date;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class CurrentDateExtraParamServiceImpl implements ExtraParamService{

	@Override
	public Object getValue() {
		return new Date();
	}

}