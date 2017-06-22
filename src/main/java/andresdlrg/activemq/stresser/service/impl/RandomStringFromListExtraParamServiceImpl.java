package andresdlrg.activemq.stresser.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class RandomStringFromListExtraParamServiceImpl implements ExtraParamService {

	private List<String> stringList;
	private Random rnd;

	public RandomStringFromListExtraParamServiceImpl(String[] args) {
		stringList = Arrays.asList(args);
		rnd = new Random();
	}

	@Override
	public Object getValue() {
		int random = rnd.nextInt(stringList.size());
		return stringList.get(random);
	}

}