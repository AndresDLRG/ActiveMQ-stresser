package andresdlrg.activemq.stresser.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

	public static void set(Object object, String attributeType, String fieldName, Object fieldValue)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clazz = object.getClass();
		Class<?> asd = Class.forName(attributeType);
		Object o = asd.newInstance();

		Field field;
		try {
			field = clazz.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, fieldValue);
		} catch (Exception e) {

		}
	}

}
