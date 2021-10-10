package by.epam.kisel.bean;

import java.util.Date;
import java.util.Objects;

public class Bill extends Entity {
	
	private String id;
	private Date dateTime;
	private int amount;
	private int fee;
	
	public Bill() {
		
	}

	public Bill(String id, Date dateTime, int amount, int fee) {
		this.id = id;
		this.dateTime = dateTime;
		this.amount = amount;
		this.fee = fee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, dateTime, fee, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bill other = (Bill) obj;
		return id.equals(other.id)
				&& dateTime.equals(other.dateTime)
				&& amount == other.amount
				&& fee == other.fee;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [id=");
		builder.append(id);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", fee=");
		builder.append(fee);
		builder.append("]");
		return builder.toString();
	}
	
	
}
