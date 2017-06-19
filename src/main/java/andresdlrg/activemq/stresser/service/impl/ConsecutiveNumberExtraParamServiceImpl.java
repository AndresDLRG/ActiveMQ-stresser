package andresdlrg.activemq.stresser.service.impl;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class ConsecutiveNumberExtraParamServiceImpl<T extends Number> implements ExtraParamService{

	private T currentValue;
	private Integer toAdd;
	
	public ConsecutiveNumberExtraParamServiceImpl(T initValue, Integer toAdd) {
		this.currentValue = initValue;
		this.toAdd = toAdd;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		T toReturn = currentValue;
		if (toReturn instanceof Byte) {
			currentValue = (T)Byte.valueOf((byte)(currentValue.byteValue() + toAdd));
		} else if(toReturn instanceof Short) {
			currentValue = (T)Short.valueOf((short)(currentValue.shortValue() + toAdd));
		} else if (toReturn instanceof Integer) {
			currentValue = (T)Integer.valueOf(currentValue.intValue() + toAdd);
		} else {
			currentValue = (T)Long.valueOf(currentValue.longValue() + toAdd);
		}
		
		return toReturn ;
	}

}