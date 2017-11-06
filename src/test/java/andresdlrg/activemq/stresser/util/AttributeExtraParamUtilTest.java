package andresdlrg.activemq.stresser.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import andresdlrg.activemq.stresser.model.AttributeMapping;
import andresdlrg.activemq.stresser.model.MyEnum;
import andresdlrg.activemq.stresser.model.SampleClass;
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

public class AttributeExtraParamUtilTest {

	@Test
	public void nullExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("cadena|string||null");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof NullExtraParamServiceImpl);
		assertNull(service.getValue());
	}

	@Test
	public void directObjectExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|string|someValue|");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof DirectObjectExtraParamServiceImpl<?>);
		assertEquals("someValue", service.getValue());
	}

	@Test
	public void directObjectExtraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|double|3.1416|");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof DirectObjectExtraParamServiceImpl<?>);
		assertEquals(3.1416, service.getValue());
	}

	@Test(expected = NumberFormatException.class)
	public void directObjectExtraParam3() {
		// Setting test data
		new AttributeMapping("attribute|double|someString|");
	}

	@Test
	public void consecutiveNumberextraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|long||consecutiveNumber(3,+)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof ConsecutiveNumberExtraParamServiceImpl<?>);
		assertEquals(3L, service.getValue());
		assertEquals(4L, service.getValue());
	}

	@Test
	public void consecutiveNumberextraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|int||consecutiveNumber(5,-)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof ConsecutiveNumberExtraParamServiceImpl<?>);
		assertEquals(5, service.getValue());
		assertEquals(4, service.getValue());
	}

	@Test
	public void consecutiveNumberextraParam3() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|int||consecutiveNumber(5,-, true)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof ConsecutiveNumberExtraParamServiceImpl<?>);
		assertEquals("5", service.getValue());
		assertEquals("4", service.getValue());
	}

	@Test
	public void randomNumberExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|long||randomNumber(5,16)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof RandomNumberExtraParamServiceImpl);
		Long value = (Long) service.getValue();
		assertTrue(value < 16L && value >= 5L);
	}

	@Test
	public void randomNumberExtraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|byte||randomNumber(5,16)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof RandomNumberExtraParamServiceImpl);
		Byte value = (Byte) service.getValue();
		assertTrue(value < (byte) 16 && value >= (byte) 5);
	}

	@Test
	public void randomNumberExtraParam3() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|float||randomNumber(5,16)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof RandomNumberExtraParamServiceImpl);
		Float value = (Float) service.getValue();
		assertTrue(value < 16F && value >= 5F);
	}

	@Test
	public void randomNumberExtraParam4() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|int||randomNumber(5, 5, true)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof RandomNumberExtraParamServiceImpl);
		assertEquals("5", service.getValue());
	}

	@Test
	public void randomStringExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|string||randomString(5,16)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof RandomStringExtraParamServiceImpl);
		String value = (String) service.getValue();
		assertTrue(value.length() < 16 && value.length() >= 5);
	}

	@Test
	public void currentDateExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|date||currentDate");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof CurrentDateExtraParamServiceImpl);
	}

	@Test
	public void defineDateFormatExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|date|2017/10/15|defineDateFormat(yyyy/MM/dd)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof DefineDateFormatExtraParamServiceImpl);
		Date date = (Date) service.getValue();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		assertEquals(2017, cal.get(Calendar.YEAR));
		assertEquals(9, cal.get(Calendar.MONTH));
		assertEquals(15, cal.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void defineDateFormatExtraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|date|20-05-2018|defineDateFormat(dd-MM-yyyy)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof DefineDateFormatExtraParamServiceImpl);
		Date date = (Date) service.getValue();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		assertEquals(2018, cal.get(Calendar.YEAR));
		assertEquals(4, cal.get(Calendar.MONTH));
		assertEquals(20, cal.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void arrayExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|string|ab,cd,ef|array(,)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof ArrayExtraParamServiceImpl<?>);
		assertTrue(service.getValue() instanceof String[]);
		assertTrue(((String[]) service.getValue()).length == 3);
	}

	@Test
	public void arrayExtraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|double|1.0,3.6,4.6|array(,)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof ArrayExtraParamServiceImpl<?>);
		assertTrue(service.getValue() instanceof Double[]);
		assertTrue(((Double[]) service.getValue()).length == 3);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|string|ab,cd,ef|list(,)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof ListExtraParamServiceImpl<?>);
		assertTrue(service.getValue() instanceof List<?>);
		assertTrue(((List<String>) service.getValue()).size() == 3);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listExtraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|float|4.5,8.6,9.456|list(,)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof ListExtraParamServiceImpl<?>);
		assertTrue(service.getValue() instanceof List<?>);
		assertTrue(((List<Float>) service.getValue()).size() == 3);
	}

	@Test
	public void mapExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|map|key1:value1,key2:value2|map(string,string)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof MapExtraParamServiceImpl<?, ?>);
		assertTrue(service.getValue() instanceof Map<?, ?>);
		assertTrue(((Map<?, ?>) service.getValue()).size() == 2);
	}

	@Test
	public void mapExtraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|map|key1:null,null:value2|map(string,string)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof MapExtraParamServiceImpl<?, ?>);
		assertTrue(service.getValue() instanceof Map<?, ?>);
		assertTrue(((Map<?, ?>) service.getValue()).size() == 2);
		assertTrue(((Map<?, ?>) service.getValue()).get(null).equals("value2"));
		assertTrue(((Map<?, ?>) service.getValue()).get("key1") == null);
	}
	
	@Test
	public void mapExtraParam3() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|map|key1:null,null:value2|map(string,string,false)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof MapExtraParamServiceImpl<?, ?>);
		assertTrue(service.getValue() instanceof Map<?, ?>);
		assertTrue(((Map<?, ?>) service.getValue()).size() == 2);
		assertTrue(((Map<?, ?>) service.getValue()).get("null").equals("value2"));
		assertTrue(((Map<?, ?>) service.getValue()).get("key1").equals("null"));
	}
	
	@Test
	public void mapExtraParam4() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping("attribute|map|  key1  :  null  ,  null  :  value2  |map(string,string)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof MapExtraParamServiceImpl<?, ?>);
		assertTrue(service.getValue() instanceof Map<?, ?>);
		assertTrue(((Map<?, ?>) service.getValue()).size() == 2);
		assertTrue(((Map<?, ?>) service.getValue()).get(null).equals("value2"));
		assertTrue(((Map<?, ?>) service.getValue()).get("key1") == null);
	}

	@Test
	public void customClassExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping(
				"attribute|andresdlrg.activemq.stresser.model.SampleClass|| class()");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof DirectObjectExtraParamServiceImpl);
		assertTrue(service.getValue() instanceof SampleClass);
	}

	@Test
	public void customClassExtraParam2() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping(
				"attribute|andresdlrg.activemq.stresser.model.SampleClass|something| class(string)");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof DirectObjectExtraParamServiceImpl);
		assertTrue(service.getValue() instanceof SampleClass);
	}

	@Test
	public void enumExtraParam1() {
		// Setting test data
		AttributeMapping mapping = new AttributeMapping(
				"attribute|andresdlrg.activemq.stresser.model.MyEnum| ENUM1| enum");

		// test
		ExtraParamService service = mapping.getExtraParam();
		assertTrue(service instanceof DirectObjectExtraParamServiceImpl);
		assertTrue(service.getValue() instanceof MyEnum);
	}

}
