package andresdlrg.activemq.stresser.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

	public static void set(Object object, String fieldName, Object fieldValue)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Class<?> clazz = object.getClass();

		Field field;
		field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, fieldValue);
	}

	public static Object createObjectFromClassname(String className)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clz = Class.forName(className);
		return clz.newInstance();
	}

}
