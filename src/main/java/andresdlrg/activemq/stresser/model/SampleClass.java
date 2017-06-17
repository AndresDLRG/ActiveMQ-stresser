package andresdlrg.activemq.stresser.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SampleClass implements Serializable{

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

}
