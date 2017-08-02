package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class ConsecutiveNumberExtraParamServiceImpl<T extends Number> implements ExtraParamService {

	private T currentValue;
	private Integer toAdd;
	private boolean asString;

	public ConsecutiveNumberExtraParamServiceImpl(T initValue, Integer toAdd, boolean asString) {
		this.currentValue = initValue;
		this.toAdd = toAdd;
		this.asString = asString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getValue() {
		T toReturn = currentValue;
		if (toReturn instanceof Byte) {
			currentValue = (T) Byte.valueOf((byte) (currentValue.byteValue() + toAdd));
		} else if (toReturn instanceof Short) {
			currentValue = (T) Short.valueOf((short) (currentValue.shortValue() + toAdd));
		} else if (toReturn instanceof Integer) {
			currentValue = (T) Integer.valueOf(currentValue.intValue() + toAdd);
		} else {
			currentValue = (T) Long.valueOf(currentValue.longValue() + toAdd);
		}
		if (asString) {
			return String.valueOf(toReturn);
		}

		return toReturn;
	}

}