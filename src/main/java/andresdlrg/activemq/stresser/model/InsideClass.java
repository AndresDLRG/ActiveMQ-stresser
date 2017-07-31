
package andresdlrg.activemq.stresser.model;

import java.io.Serializable;

public class InsideClass implements Serializable {

	private static final long serialVersionUID = 1L;

	public InsideClass() {
	}

	public InsideClass(String insideProperty) {
		this.insideProperty = insideProperty;
	}

	private String insideProperty;

	public String getInsideProperty() {
		return insideProperty;
	}

	public void setInsideProperty(String insideProperty) {
		this.insideProperty = insideProperty;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InsideClass [insideProperty=");
		builder.append(insideProperty);
		builder.append("]");
		return builder.toString();
	}

}
