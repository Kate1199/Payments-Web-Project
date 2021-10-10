package by.epam.kisel.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Payment extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String reciever;
	private String paymentDetails;
	private String description;
	private int fixedAmount;
	private int procentFee;
	
	public Payment() {
		
	}

	public Payment(int id, String name, String reciever, String paymentDetails, String description, int fixedAmount,
			int procentFee) {
		this.id = id;
		this.name = name;
		this.reciever = reciever;
		this.paymentDetails = paymentDetails;
		this.description = description;
		this.fixedAmount = fixedAmount;
		this.procentFee = procentFee;
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

	@Override
	public int hashCode() {
		return Objects.hash(description, fixedAmount, id, name, paymentDetails, procentFee, reciever);
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
		return Objects.equals(description, other.description) && fixedAmount == other.fixedAmount
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(paymentDetails, other.paymentDetails) && procentFee == other.procentFee
				&& Objects.equals(reciever, other.reciever);
	}
	
	
	
	
	
	
}
