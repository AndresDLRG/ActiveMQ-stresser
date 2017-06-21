package andresdlrg.activemq.stresser.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {
	
	private static Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

	public static void set(Object object, String fieldName, Object fieldValue)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {
		log.debug("Setting field [] to value [] using reflection", fieldName, fieldValue);
		Class<?> clazz = object.getClass();

		Field field;
		field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, fieldValue);
	}

	public static Object createObjectFromClassname(String className)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> clz = Class.forName(className);
		log.debug("Generating instance of class {} using default constructor", className);
		return clz.newInstance();
	}

}
