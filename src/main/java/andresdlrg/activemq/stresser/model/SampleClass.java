package andresdlrg.activemq.stresser.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SampleClass implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long consecutive;
	private String permaString;
	private String randomString;
	private Double permaNumber;
	private Integer randomNumber;
	private Date permaDate;
	private Date currentDate;
	private int[] integerArray;
	private List<String> stringList;

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

	public int[] getIntegerArray() {
		return integerArray;
	}

	public void setIntegerArray(int[] integerArray) {
		this.integerArray = integerArray;
	}

	public List<String> getStringList() {
		return stringList;
	}

	public void setStringList(List<String> stringList) {
		this.stringList = stringList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SampleClass [");
		if (consecutive != null) {
			builder.append("consecutive=");
			builder.append(consecutive);
			builder.append(", ");
		}
		if (permaString != null) {
			builder.append("permaString=");
			builder.append(permaString);
			builder.append(", ");
		}
		if (randomString != null) {
			builder.append("randomString=");
			builder.append(randomString);
			builder.append(", ");
		}
		if (permaNumber != null) {
			builder.append("permaNumber=");
			builder.append(permaNumber);
			builder.append(", ");
		}
		if (randomNumber != null) {
			builder.append("randomNumber=");
			builder.append(randomNumber);
			builder.append(", ");
		}
		if (permaDate != null) {
			builder.append("permaDate=");
			builder.append(permaDate);
			builder.append(", ");
		}
		if (currentDate != null) {
			builder.append("currentDate=");
			builder.append(currentDate);
			builder.append(", ");
		}
		if (integerArray != null) {
			builder.append("integerArray=");
			builder.append(Arrays.toString(integerArray));
			builder.append(", ");
		}
		if (stringList != null) {
			builder.append("stringList=");
			builder.append(stringList);
		}
		builder.append("]");
		return builder.toString();
	}

}
