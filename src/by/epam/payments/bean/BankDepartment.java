package by.epam.payments.bean;

import java.io.Serializable;

public class BankDepartment extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int number;
	private String name;
	private String address;
	
	public BankDepartment() {
	}

	public BankDepartment(int bankDepartmentId, int number, String name, String address) {
		this.id = bankDepartmentId;
		this.number = number;
		this.name = name;
		this.address = address;
	}
	
	public BankDepartment(int number, String name, String address) {
		this.number = number;
		this.name = name;
		this.address = address;
	}
	
	public int getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + number;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
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
				name.equals(other.name) &&
				address.equals(other.address);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [id=");
		builder.append(id);
		builder.append(", number=");
		builder.append(number);
		builder.append(", name=");
		builder.append(name);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
