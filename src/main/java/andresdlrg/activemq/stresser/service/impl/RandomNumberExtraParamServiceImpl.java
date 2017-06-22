package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class RandomNumberExtraParamServiceImpl<T> implements ExtraParamService {

	private T min;
	private T max;

	public RandomNumberExtraParamServiceImpl(T min, T max) {
		this.min = min;
		this.max = max;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValue() {
		Double minDouble = Double.parseDouble(String.valueOf(min));
		Double maxDouble = Double.parseDouble(String.valueOf(max));
		Double value = (Math.random() * (maxDouble - minDouble) + minDouble);
		T result;
		if (min instanceof Byte && max instanceof Byte) {
			result = (T)Byte.valueOf((byte)value.longValue());
		} else if(min instanceof Short && max instanceof Short) {
			result = (T)Short.valueOf((short)value.longValue());
		} else if(min instanceof Integer && max instanceof Integer) {
			result = (T)Integer.valueOf((int)value.longValue());
		} else if(min instanceof Long && max instanceof Long) {
			result = (T)Long.valueOf((long)value.longValue());
		} else if(min instanceof Float && max instanceof Float) {
			result = (T)(Float)value.floatValue();
		} else {
			result = (T)value;
		}

		return result;
	}

}