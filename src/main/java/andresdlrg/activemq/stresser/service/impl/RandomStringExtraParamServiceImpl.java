package andresdlrg.activemq.stresser.service.impl;

import java.util.Random;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class RandomStringExtraParamServiceImpl implements ExtraParamService {

	private long minLength;
	private long maxLength;
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	public RandomStringExtraParamServiceImpl(long minLength, long maxLength) {
		super();
		this.minLength = minLength;
		this.maxLength = maxLength;
	}

	@Override
	public Object getValue() {
		return getRandomString();
	}

	private String getRandomString() {
		StringBuilder sb = new StringBuilder();
		Random rnd = new Random();
		long currentLength = (long) (rnd.nextDouble() * (maxLength - minLength) + minLength);
		while (sb.length() < currentLength) {
			int index = (int) (rnd.nextFloat() * CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}
		return sb.toString();
	}

}