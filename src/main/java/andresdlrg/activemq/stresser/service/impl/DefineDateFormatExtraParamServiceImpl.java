package andresdlrg.activemq.stresser.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import andresdlrg.activemq.stresser.exception.InvalidValueForDatePatternException;
import andresdlrg.activemq.stresser.service.ExtraParamService;

public class DefineDateFormatExtraParamServiceImpl implements ExtraParamService {

	private SimpleDateFormat sdf;
	private Date date;

	public DefineDateFormatExtraParamServiceImpl(String pattern, String value) {
		sdf = new SimpleDateFormat(pattern);
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			throw new InvalidValueForDatePatternException("Invalid value[" + value + "] for pattern[" + pattern + "]",
					e);
		}
	}

	@Override
	public Object getValue() {
		return date;
	}

}