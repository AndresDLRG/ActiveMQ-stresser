package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class DirectObjectExtraParamServiceImpl<T> implements ExtraParamService {

	private T value;

	public DirectObjectExtraParamServiceImpl(T value) {
		this.value = value;
	}

	@Override
	public T getValue() {
		return value;
	}

}
