package andresdlrg.activemq.stresser.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SampleClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long consecutive;
	private String permaString;
	private String randomString;
	private Double permaNumber;
	private Integer randomNumber;
	private Date permaDate;
	private Date currentDate;
	private Integer[] integerArray;
	private List<String> stringList;
	private Map<String, String> mapParams;
	private InsideClass insideClass;
	private MyEnum someEnum;
	
	public SampleClass() {
	}
	
	public SampleClass(String permaString) {
		this.permaString = permaString;
	}

	public Long getConsecutive() {
		return consecutive;
	}

	public void setConsecutive(Long consecutive) {
		this.consecutive = consecutive;
	}

	public String getPermaString() {
		return permaString;
	}

	public void setPermaString(String permaString) {
		this.permaString = permaString;
	}

	public String getRandomString() {
		return randomString;
	}

	public void setRandomString(String randomString) {
		this.randomString = randomString;
	}

	public Double getPermaNumber() {
		return permaNumber;
	}

	public void setPermaNumber(Double permaNumber) {
		this.permaNumber = permaNumber;
	}

	public Integer getRandomNumber() {
		return randomNumber;
	}

	public void setRandomNumber(Integer randomNumber) {
		this.randomNumber = randomNumber;
	}

	public Date getPermaDate() {
		return permaDate;
	}

	public void setPermaDate(Date permaDate) {
		this.permaDate = permaDate;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Integer[] getIntegerArray() {
		return integerArray;
	}

	public void setIntegerArray(Integer[] integerArray) {
		this.integerArray = integerArray;
	}

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	public Map<String, String> getMapParams() {
		return mapParams;
	}

	public void setMapParams(Map<String, String> mapParams) {
		this.mapParams = mapParams;
	}

	public InsideClass getInsideClass() {
		return insideClass;
	}

	public void setInsideClass(InsideClass insideClass) {
		this.insideClass = insideClass;
	}

	public MyEnum getSomeEnum() {
		return someEnum;
	}

	public void setSomeEnum(MyEnum someEnum) {
		this.someEnum = someEnum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SampleClass [consecutive=");
		builder.append(consecutive);
		builder.append(", permaString=");
		builder.append(permaString);
		builder.append(", randomString=");
		builder.append(randomString);
		builder.append(", permaNumber=");
		builder.append(permaNumber);
		builder.append(", randomNumber=");
		builder.append(randomNumber);
		builder.append(", permaDate=");
		builder.append(permaDate);
		builder.append(", currentDate=");
		builder.append(currentDate);
		builder.append(", integerArray=");
		builder.append(Arrays.toString(integerArray));
		builder.append(", stringList=");
		builder.append(stringList);
		builder.append(", mapParams=");
		builder.append(mapParams);
		builder.append(", insideClass=");
		builder.append(insideClass);
		builder.append(", someEnum=");
		builder.append(someEnum);
		builder.append("]");
		return builder.toString();
	}

}
