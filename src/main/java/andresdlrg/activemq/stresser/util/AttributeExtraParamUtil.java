package andresdlrg.activemq.stresser.util;

import java.text.NumberFormat;
import java.text.ParseException;

import andresdlrg.activemq.stresser.model.AttributeMapping;
import andresdlrg.activemq.stresser.service.ExtraParamService;
import andresdlrg.activemq.stresser.service.impl.ArrayExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.ConsecutiveNumberExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.CurrentDateExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.DefineDateFormatExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.ListExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.NullExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomNumberExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomStringExtraParamServiceImpl;
import andresdlrg.activemq.stresser.service.impl.RandomStringListExtraParamServiceImpl;

public class AttributeExtraParamUtil {

	private static final String NULL_REGEX = "";
	private static final String CONSECUTIVE_NUMBER_REGEX = "consecutiveNumber\\(\\s*\\d+\\s*,\\s*[+-]\\s*\\)";
	private static final String RANDOM_NUMBER_REGEX = "randomNumber\\(\\s*\\d+\\s*,\\s*\\d+\\s*,\\s*(true|false)\\s*\\)";
	private static final String RANDOM_STRING_REGEX = "randomString(minLength, maxLength)";
	private static final String RANDOM_STRING_LIST_REGEX = "randomStringList\\(.+, .+ (,.+){0,}\\)";
	private static final String CURRENT_DATE_REGEX = "currentDate";
	private static final String DEFINE_DATE_FORMAT_REGEX = "defineDateFormat\\(.+\\)";
	private static final String ARRAY_REGEX = "array\\(.\\)";
	private static final String LIST_REGEX = "list\\(.\\)";

	public static ExtraParamService generateExtraParamService(AttributeMapping mapping, String extraParamString)
			throws Exception {

		String originalValue = mapping.getFieldValue();

		if (extraParamString.matches(NULL_REGEX)) {
			return generateNullExtraParam(originalValue);
		} else if (extraParamString.matches(CONSECUTIVE_NUMBER_REGEX)) {
			return generateConsecutiveNumberExtraParam(extractArguments(extraParamString));
		} else if (extraParamString.matches(RANDOM_NUMBER_REGEX)) {
			return generateRandomNumberExtraParam(extractArguments(extraParamString));
		} else if (extraParamString.matches(RANDOM_STRING_REGEX)) {
			return generateRandomStringExtraParam(extractArguments(extraParamString));
		} else if (extraParamString.matches(RANDOM_STRING_LIST_REGEX)) {
			return generateRandomStringListExtraParam(originalValue);
		} else if (extraParamString.matches(CURRENT_DATE_REGEX)) {
			return generateCurrentDateExtraParam();
		} else if (extraParamString.matches(DEFINE_DATE_FORMAT_REGEX)) {
			return generateDefineDateFormatExtraParam(originalValue, extractArguments(extraParamString));
		} else if (extraParamString.matches(ARRAY_REGEX)) {
			return generateArrayExtraParam(originalValue, extractArguments(extraParamString));
		} else if (extraParamString.matches(LIST_REGEX)) {
			return generateListExtraParam(originalValue, extractArguments(extraParamString));
		} else {
			return null;
		}

	}

	private static String[] extractArguments(String extraParamString) {
		String simple = extraParamString.substring(extraParamString.indexOf("(") + 1, extraParamString.indexOf("("));
		simple.replaceAll("\\s", "");
		return simple.split(",");
	}

	private static NullExtraParamServiceImpl generateNullExtraParam(String original) {
		return new NullExtraParamServiceImpl(original);
	}

	private static ConsecutiveNumberExtraParamServiceImpl generateConsecutiveNumberExtraParam(String[] args) {
		int toAdd;
		if (args[1].equals("+")) {
			toAdd = 1;
		} else {
			toAdd = -1;
		}
		return new ConsecutiveNumberExtraParamServiceImpl(Long.valueOf(args[0]), toAdd);
	}

	private static RandomNumberExtraParamServiceImpl generateRandomNumberExtraParam(String[] args)
			throws ParseException {
		return new RandomNumberExtraParamServiceImpl(NumberFormat.getInstance().parse(args[0]),
				NumberFormat.getInstance().parse(args[1]), Boolean.valueOf(args[2]));
	}

	private static RandomStringExtraParamServiceImpl generateRandomStringExtraParam(String[] args) {
		return new RandomStringExtraParamServiceImpl(Long.parseLong(args[0]), Long.parseLong(args[1]));
	}

	private static RandomStringListExtraParamServiceImpl generateRandomStringListExtraParam(String originalValue) {
		return new RandomStringListExtraParamServiceImpl(originalValue);
	}

	private static CurrentDateExtraParamServiceImpl generateCurrentDateExtraParam() {
		return new CurrentDateExtraParamServiceImpl();
	}

	private static DefineDateFormatExtraParamServiceImpl generateDefineDateFormatExtraParam(String originalValue,
			String[] args) throws ParseException {
		return new DefineDateFormatExtraParamServiceImpl(args[0], originalValue);
	}

	private static ArrayExtraParamServiceImpl generateArrayExtraParam(String originalValue, String[] args) {
		return new ArrayExtraParamServiceImpl(originalValue, args[0]);
	}

	private static ListExtraParamServiceImpl generateListExtraParam(String originalValue, String[] args) {
		return new ListExtraParamServiceImpl(originalValue, args[0]);
	}

}
