package by.epam.kisel.bean;

import java.io.Serializable;

public class BankDepartment extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int number;
	private Bank bank;
	private String address;

	public BankDepartment(int bankDepartmentId, int number, String address) {
		this.id = bankDepartmentId;
		this.number = number;
		this.address = address;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getBankDepartmentId() {
		return id;
	}

	public Bank getBank() {
		return bank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + id;
		result = prime * result + number;
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
		BankDepartment other = (BankDepartment) obj;
		return id == other.id &&
				number == other.number &&
				bank.equals(other.bank) &&
				address.equals(other.address);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", bank=");
		builder.append(bank);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
