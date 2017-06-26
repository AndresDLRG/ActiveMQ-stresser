package andresdlrg.activemq.stresser.util;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReflectionUtil {

	private static Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

	private ReflectionUtil() {
	}

	public static void set(Object object, String fieldName, Object fieldValue) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, NoSuchFieldException {
		log.debug("Setting field [] to value [] using reflection", fieldName, fieldValue);
		Class<?> clazz = object.getClass();

		Field field;
		field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(object, fieldValue);
	}

}
