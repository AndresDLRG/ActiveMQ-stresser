package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class ConsecutiveNumberExtraParamServiceImpl implements ExtraParamService{

	private long currentvalue;
	private int toAdd;
	
	public ConsecutiveNumberExtraParamServiceImpl(long initValue, int toAdd) {
		this.currentvalue = initValue;
		this.toAdd = toAdd;
	}

	@Override
	public Object getValue() {
		long toReturn = currentvalue;
		currentvalue += toAdd;
		return toReturn ;
	}

}