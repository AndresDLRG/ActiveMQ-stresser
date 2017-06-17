package andresdlrg.activemq.stresser.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import andresdlrg.activemq.stresser.service.ExtraParamService;

public class DefineDateFormatExtraParamServiceImpl implements ExtraParamService {

	private SimpleDateFormat sdf;
	private Date date;

	public DefineDateFormatExtraParamServiceImpl(String pattern, String value) throws ParseException {
		sdf = new SimpleDateFormat(pattern);
		date = sdf.parse(value);
	}

	@Override
	public Object getValue() {
		return date;
	}

}