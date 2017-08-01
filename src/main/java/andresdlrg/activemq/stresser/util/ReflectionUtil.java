package andresdlrg.activemq.stresser.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.exception.NumberOfArgsMismatchException;
import andresdlrg.activemq.stresser.exception.UnsupportedFieldTypeException;
import andresdlrg.activemq.stresser.service.impl.DirectObjectExtraParamServiceImpl;

public class ReflectionUtil {

	private static final String STRING_TYPE = "string";
	private static final String BOOLEAN_TYPE = "boolean";
	private static final String CHARACTER_TYPE = "character";
	private static final String CHAR_TYPE = "char";
	private static final String BYTE_TYPE = "byte";
	private static final String SHORT_TYPE = "short";
	private static final String INTEGER_TYPE = "integer";
	private static final String INT_TYPE = "int";
	private static final String LONG_TYPE = "long";
	private static final String FLOAT_TYPE = "float";
	private static final String DOUBLE_TYPE = "double";

	private static Logger log = LoggerFactory.getLogger(ReflectionUtil.class);

	private ReflectionUtil() {
	}

	public static void set(Object object, String fieldName, Object fieldValue)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		log.debug("Setting field [{}] to value [{}] using reflection", fieldName, fieldValue);

		Object obj;
		Field field;
		if (!fieldName.contains(".")) {
			field = object.getClass().getDeclaredField(fieldName);
			obj = object;
		} else {
			String[] fields = fieldName.split("\\.");
			Object object2 = object;
			for (int i = 0; i < fields.length - 1; i++) {
				Field f = object2.getClass().getDeclaredField(fields[i]);
				f.setAccessible(true);
				object2 = f.get(object2);
			}
			field = object2.getClass().getDeclaredField(fields[fields.length - 1]);
			obj = object2;
		}
		set(obj, field, fieldValue);
	}

	public static Object createInstance(String fullQualifiedClassName, String[] constructorArgTypes,
			String[] constructorArgValues)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
		if (constructorArgTypes.length != constructorArgValues.length) {
			throw new NumberOfArgsMismatchException("constructorArgTypes length [" + constructorArgTypes.length
					+ "] should be equal to constructorArgValues length [" + constructorArgValues.length + "]");
		}
		if (constructorArgTypes.length == 0) {
			return Class.forName(fullQualifiedClassName).newInstance();
		}

		int argsLength = constructorArgTypes.length;
		Class<?>[] argTypes = new Class[argsLength];
		Object[] argValues = new Object[argsLength];

		for (int i = 0; i < argsLength; i++) {
			if (STRING_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = String.class;
				argValues[i] = constructorArgValues[i];
			} else if (BOOLEAN_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Boolean.class;
				argValues[i] = Boolean.valueOf(constructorArgValues[i]);
			} else if (CHARACTER_TYPE.equalsIgnoreCase(constructorArgTypes[i])
					|| CHAR_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Character.class;
				argValues[i] = Character.valueOf(constructorArgValues[i].charAt(0));
			} else if (BYTE_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Byte.class;
				argValues[i] = Byte.valueOf(constructorArgValues[i]);
			} else if (SHORT_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Short.class;
				argValues[i] = Short.valueOf(constructorArgValues[i]);
			} else if (INTEGER_TYPE.equalsIgnoreCase(constructorArgTypes[i])
					|| INT_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Integer.class;
				argValues[i] = Integer.valueOf(constructorArgValues[i]);
			} else if (LONG_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Long.class;
				argValues[i] = Long.valueOf(constructorArgValues[i]);
			} else if (FLOAT_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Float.class;
				argValues[i] = Float.valueOf(constructorArgValues[i]);
			} else if (DOUBLE_TYPE.equalsIgnoreCase(constructorArgTypes[i])) {
				argTypes[i] = Double.class;
				argValues[i] = Double.valueOf(constructorArgValues[i]);
			} else {
				throw new UnsupportedFieldTypeException("[" + constructorArgTypes[i] + "] fieldType is not supported");
			}
		}
		Class<?> classHandle = Class.forName(fullQualifiedClassName);

		Constructor<?> constructor = classHandle.getConstructor(argTypes);
		return constructor.newInstance(argValues);

	}

	private static void set(Object object, Field field, Object fieldValue)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
		field.setAccessible(true);
		field.set(object, fieldValue);
	}

}
