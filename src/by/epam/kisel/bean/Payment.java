package by.epam.kisel.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Payment extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private byte[] image;
	private String reciever;
	private String paymentDetails;
	private String description;
	private int fixedAmount;
	private int procentFee;
	
	public Payment() {
		
	}

	public Payment(int id, String name, byte[] image, String reciever, String paymentDetails, String description, int fixedAmount,
			int procentFee) {
		this.id = id;
		this.name = name;
		this.image = image;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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
		return Objects.hash(description, fixedAmount, id, name, image, paymentDetails, procentFee, reciever);
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
				&& Arrays.equals(image, other.image)
				&& reciever.equals(other.reciever)
				&& paymentDetails.equals(other.paymentDetails)
				&& description.equals(other.description)
				&& fixedAmount == other.fixedAmount
				&& procentFee == other.procentFee;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", image=");
		builder.append(Arrays.toString(image));
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
		builder.append("]");
		return builder.toString();
	}
	
}
