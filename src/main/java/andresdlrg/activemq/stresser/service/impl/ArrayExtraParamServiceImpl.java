package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class ArrayExtraParamServiceImpl<T> implements ExtraParamService {

	private T[] values;

	public ArrayExtraParamServiceImpl(T[] values) {
		this.values = values;
	}

	@Override
	public Object getValue() {
		return values;
	}

}
