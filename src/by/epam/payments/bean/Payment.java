package by.epam.payments.bean;

import java.io.Serializable;

public class Payment extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String reciever;
	private String paymentDetails;
	private String description;
	private int fixedAmount;
	private int procentFee;
	private int accountId;
	
	public Payment() {
	}

	public Payment(int id, String name, String reciever, String paymentDetails, String description, int fixedAmount,
			int procentFee, int accountId) {
		this.id = id;
		this.name = name;
		this.reciever = reciever;
		this.paymentDetails = paymentDetails;
		this.description = description;
		this.fixedAmount = fixedAmount;
		this.procentFee = procentFee;
		this.accountId = accountId;
	}
	
	public Payment(String name, String reciever, String paymentDetails, String description, int fixedAmount,
			int procentFee, int accountId) {
		this.name = name;
		this.reciever = reciever;
		this.paymentDetails = paymentDetails;
		this.description = description;
		this.fixedAmount = fixedAmount;
		this.procentFee = procentFee;
		this.accountId = accountId;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFixedAmount() {
		return fixedAmount;
	}

	public void setFixedAmount(int fixedAmount) {
		this.fixedAmount = fixedAmount;
	}

	public int getProcentFee() {
		return procentFee;
	}

	public void setProcentFee(int procentFee) {
		this.procentFee = procentFee;
	}

	public int getAccountId() {
		return accountId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + fixedAmount;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((paymentDetails == null) ? 0 : paymentDetails.hashCode());
		result = prime * result + procentFee;
		result = prime * result + ((reciever == null) ? 0 : reciever.hashCode());
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
		Payment other = (Payment) obj;
		return id == other.id
				&& name.equals(other.name)
				&& reciever.equals(other.reciever)
				&& paymentDetails.equals(other.paymentDetails)
				&& description.equals(other.description)
				&& fixedAmount == other.fixedAmount
				&& procentFee == other.procentFee
				&& accountId == other.accountId;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", reciever=");
		builder.append(reciever);
		builder.append(", paymentDetails=");
		builder.append(paymentDetails);
		builder.append(", description=");
		builder.append(description);
		builder.append(", fixedAmount=");
		builder.append(fixedAmount);
		builder.append(", procentFee=");
		builder.append(procentFee);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append("]");
		return builder.toString();
	}
	
}
