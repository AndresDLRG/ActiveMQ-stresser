package andresdlrg.activemq.stresser.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import andresdlrg.activemq.stresser.exception.InvalidExtraParamException;
import andresdlrg.activemq.stresser.exception.UnsupportedFieldTypeException;
import andresdlrg.activemq.stresser.model.AttributeMapping;
import andresdlrg.activemq.stresser.service.ExtraParamService;
import andresdlrg.activemq.stresser.service.impl.ArrayExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.ConsecutiveNumberExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.CurrentDateExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.DefineDateFormatExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.DirectObjectExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.ListExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.NullExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomNumberExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomStringExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomStringFromListExtraParamServiceImpl;

public class AttributeExtraParamUtil {

	private static final String NULL_REGEX = "null";
	private static final String DIRECT_REGEX = "";
	private static final String CONSECUTIVE_NUMBER_REGEX = "consecutiveNumber\\(\\s*\\d+\\s*,\\s*[+-]\\s*\\)";
	private static final String RANDOM_NUMBER_REGEX = "randomNumber\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)";
	private static final String RANDOM_STRING_REGEX = "randomString\\(\\s*\\d+\\s*,\\s*\\d+\\s*\\)";
	private static final String RANDOM_STRING_LIST_REGEX = "randomStringList\\(.+, .+ (,.+){0,}\\)";
	private static final String CURRENT_DATE_REGEX = "currentDate";
	private static final String DEFINE_DATE_FORMAT_REGEX = "defineDateFormat\\(.+\\)";
	private static final String ARRAY_REGEX = "array\\(.\\)";
	private static final String LIST_REGEX = "list\\(.\\)";

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

	public static ExtraParamService generateExtraParamService(AttributeMapping mapping, String extraParamString) {
		log.debug("Generating ExtraParamService for fieldName=[{}], value=[{}], fieldType=[{}], extraParamString=[{}]",
				mapping.getFieldName(), mapping.getFieldValue(), mapping.getFieldType(), extraParamString);
		String originalValue = mapping.getFieldValue();
		String fieldType = mapping.getFieldType();

		ExtraParamService extraParamService = null;

		if (extraParamString.matches(NULL_REGEX)) {
			extraParamService = generateNullExtraParam(fieldType, originalValue);
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
		}
		if (extraParamService != null) {
			log.debug("ExtraParamService of class [{}] generated", extraParamService.getClass().getSimpleName());
			return extraParamService;
		} else {
			throw new InvalidExtraParamException();
		}
	}

	private static String[] extractArguments(String extraParamString) {
		String simple = extraParamString.substring(extraParamString.indexOf('(') + 1, extraParamString.indexOf(')'));
		simple = simple.replaceAll("\\s", "");
		return simple.split(",");
	}

	private static NullExtraParamServiceImpl generateNullExtraParam(String fieldType, String originalValue) {
		return new NullExtraParamServiceImpl();
	}

	private static DirectObjectExtraParamServiceImpl<?> generateDirectObjectExtraParam(String fieldType,
			String originalValue) {
		if (STRING_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(String.valueOf(originalValue));
		} else if (BOOLEAN_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Boolean.valueOf(originalValue));
		} else if (CHARACTER_TYPE.equalsIgnoreCase(fieldType) || CHAR_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Character.valueOf(originalValue.charAt(0)));
		} else if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Byte.valueOf(originalValue));
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Short.valueOf(originalValue));
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Integer.valueOf(originalValue));
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Long.valueOf(originalValue));
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Float.valueOf(originalValue));
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			return new DirectObjectExtraParamServiceImpl<>(Double.valueOf(originalValue));
		}
		throw new UnsupportedFieldTypeException();
	}

	private static ConsecutiveNumberExtraParamServiceImpl<Number> generateConsecutiveNumberExtraParam(String fieldType,
			String[] args) {
		int toAdd;
		if ("+".equals(args[1])) {
			toAdd = 1;
		} else {
			toAdd = -1;
		}
		if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<>(Byte.valueOf(args[0]), toAdd);
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<>(Short.valueOf(args[0]), toAdd);
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<>(Integer.valueOf(args[0]), toAdd);
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			return new ConsecutiveNumberExtraParamServiceImpl<>(Long.valueOf(args[0]), toAdd);
		}
		throw new UnsupportedFieldTypeException();
	}

	private static RandomNumberExtraParamServiceImpl<Number> generateRandomNumberExtraParam(String fieldType,
			String[] args) {

		if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<>(Byte.valueOf(args[0]), Byte.valueOf(args[0]));
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<>(Short.valueOf(args[0]), Short.valueOf(args[0]));
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<>(Integer.valueOf(args[0]), Integer.valueOf(args[0]));
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<>(Long.valueOf(args[0]), Long.valueOf(args[0]));
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<>(Float.valueOf(args[0]), Float.valueOf(args[0]));
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			return new RandomNumberExtraParamServiceImpl<>(Double.valueOf(args[0]), Double.valueOf(args[0]));
		}
		throw new UnsupportedFieldTypeException();
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
		try {
			return new DefineDateFormatExtraParamServiceImpl(args[0], originalValue);
		} catch (ParseException e) {
			return null;
		}
	}

	private static ArrayExtraParamServiceImpl<?> generateArrayExtraParam(String fieldType, String originalValue,
			String separator) {
		String[] values = originalValue.split(separator);
		if (STRING_TYPE.equalsIgnoreCase(fieldType)) {
			return new ArrayExtraParamServiceImpl<>(values);
		} else if (BOOLEAN_TYPE.equalsIgnoreCase(fieldType)) {
			Boolean[] booleans = new Boolean[values.length];
			for (int i = 0; i < values.length; i++) {
				booleans[i] = Boolean.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<>(booleans);
		} else if (CHARACTER_TYPE.equalsIgnoreCase(fieldType) || CHAR_TYPE.equalsIgnoreCase(fieldType)) {
			Character[] chars = new Character[values.length];
			for (int i = 0; i < values.length; i++) {
				chars[i] = Character.valueOf(values[i].charAt(0));
			}
			return new ArrayExtraParamServiceImpl<>(chars);
		} else if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			Byte[] bytes = new Byte[values.length];
			for (int i = 0; i < values.length; i++) {
				bytes[i] = Byte.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<>(bytes);
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			Short[] shorts = new Short[values.length];
			for (int i = 0; i < values.length; i++) {
				shorts[i] = Short.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<>(shorts);
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			Integer[] ints = new Integer[values.length];
			for (int i = 0; i < values.length; i++) {
				ints[i] = Integer.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<>(ints);
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			Long[] longs = new Long[values.length];
			for (int i = 0; i < values.length; i++) {
				longs[i] = Long.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<>(longs);
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			Float[] floats = new Float[values.length];
			for (int i = 0; i < values.length; i++) {
				floats[i] = Float.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<>(floats);
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			Double[] doubles = new Double[values.length];
			for (int i = 0; i < values.length; i++) {
				doubles[i] = Double.valueOf(values[i]);
			}
			return new ArrayExtraParamServiceImpl<>(doubles);
		}
		throw new UnsupportedFieldTypeException();
	}

	private static ListExtraParamServiceImpl<?> generateListExtraParam(String fieldType, String originalValue,
			String separator) {
		String[] values = originalValue.split(separator);
		if (STRING_TYPE.equalsIgnoreCase(fieldType)) {
			List<String> strings = Arrays.asList(values);
			return new ListExtraParamServiceImpl<>(strings);
		} else if (BOOLEAN_TYPE.equalsIgnoreCase(fieldType)) {
			List<Boolean> booleans = new ArrayList<>();
			for (String str : values) {
				booleans.add(Boolean.valueOf(str));
			}
			return new ListExtraParamServiceImpl<>(booleans);
		} else if (CHARACTER_TYPE.equalsIgnoreCase(fieldType) || CHAR_TYPE.equalsIgnoreCase(fieldType)) {
			List<Character> chars = new ArrayList<>();
			for (String str : values) {
				chars.add(Character.valueOf(str.charAt(0)));
			}
			return new ListExtraParamServiceImpl<>(chars);
		} else if (BYTE_TYPE.equalsIgnoreCase(fieldType)) {
			List<Byte> bytes = new ArrayList<>();
			for (String str : values) {
				bytes.add(Byte.valueOf(str));
			}
			return new ListExtraParamServiceImpl<>(bytes);
		} else if (SHORT_TYPE.equalsIgnoreCase(fieldType)) {
			List<Short> shorts = new ArrayList<>();
			for (String str : values) {
				shorts.add(Short.valueOf(str));
			}
			return new ListExtraParamServiceImpl<>(shorts);
		} else if (INTEGER_TYPE.equalsIgnoreCase(fieldType) || INT_TYPE.equalsIgnoreCase(fieldType)) {
			List<Integer> ints = new ArrayList<>();
			for (String str : values) {
				ints.add(Integer.valueOf(str));
			}
			return new ListExtraParamServiceImpl<>(ints);
		} else if (LONG_TYPE.equalsIgnoreCase(fieldType)) {
			List<Long> longs = new ArrayList<>();
			for (String str : values) {
				longs.add(Long.valueOf(str));
			}
			return new ListExtraParamServiceImpl<>(longs);
		} else if (FLOAT_TYPE.equalsIgnoreCase(fieldType)) {
			List<Float> floats = new ArrayList<>();
			for (String str : values) {
				floats.add(Float.valueOf(str));
			}
			return new ListExtraParamServiceImpl<>(floats);
		} else if (DOUBLE_TYPE.equalsIgnoreCase(fieldType)) {
			List<Double> doubles = new ArrayList<>();
			for (String str : values) {
				doubles.add(Double.valueOf(str));
			}
			return new ListExtraParamServiceImpl<>(doubles);
		}
		throw new UnsupportedFieldTypeException();
	}

}
