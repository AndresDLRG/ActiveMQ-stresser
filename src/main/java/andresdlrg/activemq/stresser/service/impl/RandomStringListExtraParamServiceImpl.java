package andresdlrg.activemq.stresser.service.impl;

import java.util.Arrays;
import java.util.List;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class RandomStringListExtraParamServiceImpl implements ExtraParamService {

	private List<String> stringList;

	public RandomStringListExtraParamServiceImpl(String originalString) {
		stringList = Arrays.asList(originalString.split(","));
	}

	@Override
	public Object getValue() {
		int random = (int) (Math.random() * (stringList.size()));
		return stringList.get(random);
	}

}