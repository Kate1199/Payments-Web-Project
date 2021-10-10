package by.epam.kisel.bean;

import java.io.Serializable;

public class Card extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private long number;
	private String validityPeriod;
	private String type;
	
	public Card(String id, long number, String validityPeriod, String type) {
		this.id = id;
		this.number = number;
		this.validityPeriod = validityPeriod;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public long getNumber() {
		return number;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (int) (number ^ (number >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((validityPeriod == null) ? 0 : validityPeriod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return id.equals(other.id) &&
				number == other.number &&
				validityPeriod.equals(other.validityPeriod) &&
				type.equals(other.type);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", validityPeriod=");
		builder.append(validityPeriod);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
