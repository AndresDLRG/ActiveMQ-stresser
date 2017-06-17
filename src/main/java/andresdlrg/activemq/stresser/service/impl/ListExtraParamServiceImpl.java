package andresdlrg.activemq.stresser.service.impl;

import java.util.Arrays;
import java.util.List;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class ListExtraParamServiceImpl implements ExtraParamService {

	private List<String> stringList;

	public ListExtraParamServiceImpl(String originalString, String separator) {
		stringList = Arrays.asList(originalString.split(separator));
	}

	@Override
	public Object getValue() {
		return stringList;
	}

}