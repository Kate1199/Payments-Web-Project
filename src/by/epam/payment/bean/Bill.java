package by.epam.payment.bean;

import java.io.Serializable;
import java.util.Calendar;

public class Bill extends Entity implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Calendar dateTime;
	private long amount;
	private int fee;
	private int cardId;
	private int paymentId;
	
	public Bill() {
	}
	
	public Bill(Calendar dateTime, long amount) {
		this.dateTime = dateTime;
		this.amount = amount;
	}
	
	public Bill(Calendar dateTime, long amount, int fee, int cardId, int paymentId) {
		this.dateTime = dateTime;
		this.amount = amount;
		this.fee = fee;
		this.cardId = cardId;
		this.paymentId = paymentId;
	}

	public Bill(int id, Calendar dateTime, long amount, int fee, int cardId, int paymentId) {
		this.id = id;
		this.dateTime = dateTime;
		this.amount = amount;
		this.fee = fee;
		this.cardId = cardId;
		this.paymentId = paymentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDateTime() {
		return dateTime;
	}

	public void setDateTime(Calendar dateTime) {
		this.dateTime = dateTime;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getCardId() {
		return cardId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + (int) (amount ^ (amount >>> 32));
		result = prime * result + fee;
		result = prime * result + cardId;
		result = prime * result + paymentId;
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
		Bill other = (Bill) obj;
		return id == other.id &&
				dateTime.equals(other.dateTime) &&
				amount == other.amount &&
				fee == other.fee &&
				cardId == other.cardId &&
				paymentId == other.paymentId;
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
		builder.append(", cardId=");
		builder.append(cardId);
		builder.append(", paymentId=");
		builder.append(paymentId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
