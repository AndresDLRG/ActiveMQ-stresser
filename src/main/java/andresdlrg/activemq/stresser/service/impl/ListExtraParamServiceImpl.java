package andresdlrg.activemq.stresser.service.impl;

import java.util.List;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class ListExtraParamServiceImpl<T> implements ExtraParamService {

	private List<T> stringList;

	public ListExtraParamServiceImpl(List<T> stringList) {
		this.stringList = stringList;
	}

	@Override
	public Object getValue() {
		return stringList;
	}

}