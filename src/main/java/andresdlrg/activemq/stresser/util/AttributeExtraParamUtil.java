package andresdlrg.activemq.stresser.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.exception.InvalidExtraParamException;
import andresdlrg.activemq.stresser.exception.InvalidValueForMapException;
import andresdlrg.activemq.stresser.exception.UnsupportedFieldTypeException;
import andresdlrg.activemq.stresser.model.AttributeMapping;
import andresdlrg.activemq.stresser.service.ExtraParamService;
import andresdlrg.activemq.stresser.service.impl.ArrayExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.ConsecutiveNumberExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.CurrentDateExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.DefineDateFormatExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.DirectObjectExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.ListExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.MapExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.NullExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomNumberExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomStringExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomStringFromListExtraParamServiceImpl;

public class AttributeExtraParamUtil {

	private static final String NULL_REGEX = "null";
	private static final String DIRECT_REGEX = "";
	private static final String CLASS_REGEX = "class\\(\\s*(.*|(.+|(.+(,.+){0,})))\\s*\\)";
	private static final String ENUM_REGEX = "enum";
	private static final String CONSECUTIVE_NUMBER_REGEX = "consecutiveNumber\\(\\s*\\d+\\s*,\\s*[+-]\\s*(,\\s*(true|false))?\\s*\\)";
	private static final String RANDOM_NUMBER_REGEX = "randomNumber\\(\\s*\\d+\\s*,\\s*\\d+\\s*(,\\s*(true|false))?\\s*\\)";
	private static final String RANDOM_STRING_REGEX = "randomString\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)";
	private static final String RANDOM_STRING_LIST_REGEX = "randomStringList\\(.+,.+(,.+){0,}\\)";
	private static final String CURRENT_DATE_REGEX = "currentDate";
	private static final String DEFINE_DATE_FORMAT_REGEX = "defineDateFormat\\(.+\\)";
	private static final String ARRAY_REGEX = "array\\(.\\)";
	private static final String LIST_REGEX = "list\\(.\\)";
	private static final String MAP_REGEX = "map\\(.+,.+(,\\s*(true|false)\\s*,\\s*(true|false))?\\s*\\)";

	private static final String MAP_ORIGINAL_VALUE_REGEX = ".+:.+(,.+:.+){0,}";

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

	private static Logger log = LoggerFactory.getLogger(AttributeExtraParamUtil.class);

	private AttributeExtraParamUtil() {
	}

	public static ExtraParamService generateExtraParamService(AttributeMapping mapping, String extraParamString) {
		log.debug("Generating ExtraParamService for fieldName=[{}], value=[{}], fieldType=[{}], extraParamString=[{}]",
				mapping.getFieldName(), mapping.getFieldValue(), mapping.getFieldType(), extraParamString);
		String originalValue = mapping.getFieldValue();
		String fieldType = mapping.getFieldType();

		ExtraParamService extraParamService = null;

		if (extraParamString.matches(NULL_REGEX)) {
			extraParamService = generateNullExtraParam();
		} else if (extraParamString.matches(DIRECT_REGEX)) {
			extraParamService = generateDirectObjectExtraParam(fieldType, originalValue);
		} else if (extraParamString.matches(CONSECUTIVE_NUMBER_REGEX)) {
			extraParamService = generateConsecutiveNumberExtraParam(fieldType, extractArguments(extraParamString));
		} else if (extraParamString.matches(RANDOM_NUMBER_REGEX)) {
			extraParamService = generateRandomNumberExtraParam(fieldType, extractArguments(extraParamString));
		} else if (extraParamString.matches(RANDOM_STRING_REGEX)) {
			extraParamService = generateRandomStringExtraParam(extractArguments(extraParamString));
		} else if (extraParamString.matches(RANDOM_STRING_LIST_REGEX)) {
			extraParamService = generateRandomStringFromListExtraParam(extractArguments(extraParamString));
		} else if (extraParamString.matches(CURRENT_DATE_REGEX)) {
			extraParamService = generateCurrentDateExtraParam();
		} else if (extraParamString.matches(DEFINE_DATE_FORMAT_REGEX)) {
			extraParamService = generateDefineDateFormatExtraParam(originalValue, extractArguments(extraParamString));
		} else if (extraParamString.matches(ARRAY_REGEX)) {
			String simple = extraParamString.substring(extraParamString.indexOf('(') + 1,
					extraParamString.indexOf(')'));
			extraParamService = generateArrayExtraParam(fieldType, originalValue, simple);
		} else if (extraParamString.matches(LIST_REGEX)) {
			String simple = extraParamString.substring(extraParamString.indexOf('(') + 1,
					extraParamString.indexOf(')'));
			extraParamService = generateListExtraParam(fieldType, originalValue, simple);
		} else if (extraParamString.matches(MAP_REGEX)) {
			String[] args = extractArguments(extraParamString);

			boolean acceptNull = true;
			boolean trimStrings = true;
			if (args.length >= 3) {
				acceptNull = Boolean.valueOf(args[2]);
			}
			if (args.length >= 4) {
				trimStrings = Boolean.valueOf(args[3]);
			}

			extraParamService = generateMapExtraParam(args[0], args[1], originalValue, acceptNull, trimStrings);
		} else if (extraParamString.matches(CLASS_REGEX)) {
			String[] argsTypes = extractArguments(extraParamString);
			String[] argsValues = originalValue.trim().equals("") ? new String[0] : originalValue.split(",");
			extraParamService = generateCustomClassExtraParam(fieldType, argsValues, argsTypes);
		} else if (extraParamString.matches(ENUM_REGEX)) {
			extraParamService = generateEnumExtraParam(fieldType, originalValue);
		}

		if (extraParamService != null) {
			log.info("ExtraParamService of class [{}] generated for fieldName [{}]",
					extraParamService.getClass().getSimpleName(), mapping.getFieldName());
			return extraParamService;
		} else {
			throw new InvalidExtraParamException("[" + extraParamString + "] is not a valid extraParam");
		}
	}

	private static String[] extractArguments(String extraParamString) {
		String simple = extraParamString.substring(extraParamString.indexOf('(') + 1, extraParamString.indexOf(')'));
		simple = simple.replaceAll("\\s", "");
		return simple.trim().equals("") ? new String[0] : simple.split(",");
	}

	private static NullExtraParamServiceImpl generateNullExtraParam() {
		return new NullExtraParamServiceImpl();
	}

	private static DirectObjectExtraParamServiceImpl<?> generateDirectObjectExtraParam(String fieldType,
			String originalValue) {
		if (STRING_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<String>(String.valueOf(originalValue));
		} else if (BOOLEAN_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Boolean>(Boolean.valueOf(originalValue));
		} else if (CHARACTER_TYPE.equalsIgnoreCase(fieldType) || CHAR_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Character>(Character.valueOf(originalValue.charAt(0)));
		} else if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Byte>(Byte.valueOf(originalValue));
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Short>(Short.valueOf(originalValue));
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Integer>(Integer.valueOf(originalValue));
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Long>(Long.valueOf(originalValue));
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Float>(Float.valueOf(originalValue));
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<Double>(Double.valueOf(originalValue));
		}
		throw new UnsupportedFieldTypeException("[" + fieldType + "] fieldType is not supported");
	}

	private static ConsecutiveNumberExtraParamServiceImpl<Number> generateConsecutiveNumberExtraParam(String fieldType,
			String[] args) {
		int toAdd;
		if ("+".equals(args[1])) {
			toAdd = 1;
		} else {
			toAdd = -1;
		}
		boolean asString = false;
		if (args.length == 3 && args[2].equals("true")) {
			asString = true;
		}

		if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<Number>(Byte.valueOf(args[0]), toAdd, asString);
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<Number>(Short.valueOf(args[0]), toAdd, asString);
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<Number>(Integer.valueOf(args[0]), toAdd, asString);
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<Number>(Long.valueOf(args[0]), toAdd, asString);
		}
		throw new UnsupportedFieldTypeException("[" + fieldType + "] fieldType is not supported");
	}

	private static RandomNumberExtraParamServiceImpl<Number> generateRandomNumberExtraParam(String fieldType,
			String[] args) {
		boolean asString = false;
		if (args.length == 3 && args[2].equals("true")) {
			asString = true;
		}
		if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<Number>(Byte.valueOf(args[0]), Byte.valueOf(args[1]),
					asString);
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<Number>(Short.valueOf(args[0]), Short.valueOf(args[1]),
					asString);
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<Number>(Integer.valueOf(args[0]), Integer.valueOf(args[1]),
					asString);
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<Number>(Long.valueOf(args[0]), Long.valueOf(args[1]),
					asString);
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<Number>(Float.valueOf(args[0]), Float.valueOf(args[1]),
					asString);
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<Number>(Double.valueOf(args[0]), Double.valueOf(args[1]),
					asString);
		}
		throw new UnsupportedFieldTypeException("[" + fieldType + "] fieldType is not supported");
	}

	private static RandomStringExtraParamServiceImpl generateRandomStringExtraParam(String[] args) {
		return new RandomStringExtraParamServiceImpl(Long.parseLong(args[0]), Long.parseLong(args[1]));
	}

	private static RandomStringFromListExtraParamServiceImpl generateRandomStringFromListExtraParam(String[] args) {
		return new RandomStringFromListExtraParamServiceImpl(args);
	}

	private static CurrentDateExtraParamServiceImpl generateCurrentDateExtraParam() {
		return new CurrentDateExtraParamServiceImpl();
	}

	private static DefineDateFormatExtraParamServiceImpl generateDefineDateFormatExtraParam(String originalValue,
			String[] args) {
		return new DefineDateFormatExtraParamServiceImpl(args[0], originalValue);
	}

	private static ArrayExtraParamServiceImpl<?> generateArrayExtraParam(String fieldType, String originalValue,
			String separator) {
		String[] values = originalValue.split(separator);
		if (STRING_TYPE.equalsIgnoreCase(fieldType)) {
			return new ArrayExtraParamServiceImpl<String>(values);
		} else if (BOOLEAN_TYPE.equalsIgnoreCase(fieldType)) {
			Boolean[] booleans = new Boolean[values.length];
			for (int i = 0; i < values.length; i++) {
				booleans[i] = Boolean.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<Boolean>(booleans);
		} else if (CHARACTER_TYPE.equalsIgnoreCase(fieldType) || CHAR_TYPE.equalsIgnoreCase(fieldType)) {
			Character[] chars = new Character[values.length];
			for (int i = 0; i < values.length; i++) {
				chars[i] = Character.valueOf(values[i].charAt(0));
			}
			return new ArrayExtraParamServiceImpl<Character>(chars);
		} else if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			Byte[] bytes = new Byte[values.length];
			for (int i = 0; i < values.length; i++) {
				bytes[i] = Byte.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<Byte>(bytes);
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			Short[] shorts = new Short[values.length];
			for (int i = 0; i < values.length; i++) {
				shorts[i] = Short.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<Short>(shorts);
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			Integer[] ints = new Integer[values.length];
			for (int i = 0; i < values.length; i++) {
				ints[i] = Integer.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<Integer>(ints);
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			Long[] longs = new Long[values.length];
			for (int i = 0; i < values.length; i++) {
				longs[i] = Long.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<Long>(longs);
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			Float[] floats = new Float[values.length];
			for (int i = 0; i < values.length; i++) {
				floats[i] = Float.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<Float>(floats);
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			Double[] doubles = new Double[values.length];
			for (int i = 0; i < values.length; i++) {
				doubles[i] = Double.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<Double>(doubles);
		}
		throw new UnsupportedFieldTypeException("[" + fieldType + "] fieldType is not supported");
	}

	private static ListExtraParamServiceImpl<?> generateListExtraParam(String fieldType, String originalValue,
			String separator) {
		String[] values = originalValue.split(separator);
		if (STRING_TYPE.equalsIgnoreCase(fieldType)) {
			List<String> strings = Arrays.asList(values);
			return new ListExtraParamServiceImpl<String>(strings);
		} else if (BOOLEAN_TYPE.equalsIgnoreCase(fieldType)) {
			List<Boolean> booleans = new ArrayList<Boolean>();
			for (String str : values) {
				booleans.add(Boolean.valueOf(str));
			}
			return new ListExtraParamServiceImpl<Boolean>(booleans);
		} else if (CHARACTER_TYPE.equalsIgnoreCase(fieldType) || CHAR_TYPE.equalsIgnoreCase(fieldType)) {
			List<Character> chars = new ArrayList<Character>();
			for (String str : values) {
				chars.add(Character.valueOf(str.charAt(0)));
			}
			return new ListExtraParamServiceImpl<Character>(chars);
		} else if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			List<Byte> bytes = new ArrayList<Byte>();
			for (String str : values) {
				bytes.add(Byte.valueOf(str));
			}
			return new ListExtraParamServiceImpl<Byte>(bytes);
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			List<Short> shorts = new ArrayList<Short>();
			for (String str : values) {
				shorts.add(Short.valueOf(str));
			}
			return new ListExtraParamServiceImpl<Short>(shorts);
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			List<Integer> ints = new ArrayList<Integer>();
			for (String str : values) {
				ints.add(Integer.valueOf(str));
			}
			return new ListExtraParamServiceImpl<Integer>(ints);
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			List<Long> longs = new ArrayList<Long>();
			for (String str : values) {
				longs.add(Long.valueOf(str));
			}
			return new ListExtraParamServiceImpl<Long>(longs);
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			List<Float> floats = new ArrayList<Float>();
			for (String str : values) {
				floats.add(Float.valueOf(str));
			}
			return new ListExtraParamServiceImpl<Float>(floats);
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			List<Double> doubles = new ArrayList<Double>();
			for (String str : values) {
				doubles.add(Double.valueOf(str));
			}
			return new ListExtraParamServiceImpl<Double>(doubles);
		}
		throw new UnsupportedFieldTypeException("[" + fieldType + "] fieldType is not supported");
	}

	private static MapExtraParamServiceImpl<?, ?> generateMapExtraParam(String keyType, String valueType,
			String originalValue, boolean acceptNull, boolean trimStrings) {
		if (!originalValue.matches(MAP_ORIGINAL_VALUE_REGEX)) {
			throw new InvalidValueForMapException(
					originalValue + " can not match with regex " + MAP_ORIGINAL_VALUE_REGEX);
		}
		String[] mapits = originalValue.split(",");
		List<String[]> mapAlls = new ArrayList<String[]>();

		for (String mapit : mapits) {
			String[] insidies = mapit.split(":");
			mapAlls.add(insidies);
		}

		if (STRING_TYPE.equalsIgnoreCase(keyType) && STRING_TYPE.equalsIgnoreCase(valueType)) {
			Map<String, String> mapp = new HashMap<String, String>();
			for (String[] strings : mapAlls) {
				if (trimStrings && strings[0] != null) {
					strings[0] = strings[0].trim();
				}
				if (trimStrings && strings[1] != null) {
					strings[1] = strings[1].trim();
				}
				if (acceptNull && strings[0].equalsIgnoreCase(NULL_REGEX)) {
					strings[0] = null;
				}
				if (acceptNull && strings[1].equalsIgnoreCase(NULL_REGEX)) {
					strings[1] = null;
				}
				mapp.put(strings[0], strings[1]);

			}
			return new MapExtraParamServiceImpl<String, String>(mapp);
		}

		throw new UnsupportedFieldTypeException("[" + keyType + "] or [" + valueType + "] fieldType is not supported");

	}

	private static DirectObjectExtraParamServiceImpl<Object> generateCustomClassExtraParam(String fieldType,
			String[] originalValues, String[] argsTypes) {
		try {
			Object obj = ReflectionUtil.createInstance(fieldType, argsTypes, originalValues);
			return new DirectObjectExtraParamServiceImpl<Object>(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static DirectObjectExtraParamServiceImpl<Enum<?>> generateEnumExtraParam(String fieldType,
			String originalValue) {
		try {
			Class<?> classHandle = Class.forName(fieldType);
			Enum<?> enumerator = Enum.valueOf((Class<Enum>) classHandle, originalValue);
			return new DirectObjectExtraParamServiceImpl<Enum<?>>(enumerator);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
