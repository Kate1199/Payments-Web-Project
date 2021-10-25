package by.epam.payments.bean;

import java.io.Serializable;
import java.util.Arrays;

public class Card extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private byte[] number;
	private int startDigits;
	private int endDigits;
	private byte[] salt;
	private String validityPeriod;
	private int accountId;
	
	public Card() {
	}
	
	public Card(int id, byte[] number, int startDigits, int endDigits, byte[] salt, String validityPeriod,
			int accountId) {
		this.id = id;
		this.number = number;
		this.startDigits = startDigits;
		this.endDigits = endDigits;
		this.salt = salt;
		this.validityPeriod = validityPeriod;
		this.accountId = accountId;
	}

	public Card(byte[] number, int startDigits, int endDigits, byte[] salt, String validityPeriod,
			int accountId) {
		this.number = number;
		this.startDigits = startDigits;
		this.endDigits = endDigits;
		this.salt = salt;
		this.validityPeriod = validityPeriod;
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public byte[] getNumber() {
		return number;
	}

	public int getStartDigits() {
		return startDigits;
	}

	public int getEndDigits() {
		return endDigits;
	}

	public byte[] getSalt() {
		return salt;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public int getAccountId() {
		return accountId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + endDigits;
		result = prime * result + id;
		result = prime * result + Arrays.hashCode(number);
		result = prime * result + Arrays.hashCode(salt);
		result = prime * result + startDigits;
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
		return id == other.id &&
				Arrays.equals(number, other.number) &&
				startDigits == other.startDigits &&
				endDigits == other.endDigits &&
				Arrays.equals(salt, other.salt) &&
				validityPeriod.equals(other.validityPeriod) &&
				accountId == other.accountId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(Arrays.toString(number));
		builder.append(", startDigits=");
		builder.append(startDigits);
		builder.append(", endDigits=");
		builder.append(endDigits);
		builder.append(", salt=");
		builder.append(Arrays.toString(salt));
		builder.append(", validityPeriod=");
		builder.append(validityPeriod.toString());
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append("]");
		return builder.toString();
	}
	
}
