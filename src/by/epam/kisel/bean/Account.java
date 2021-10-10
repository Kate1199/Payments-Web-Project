package by.epam.kisel.bean;

import java.io.Serializable;

public class Account extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String numberIBAN;
	private String currency;
	private int procent;
	private int balance;
	
	public Account(String id, String numberIBAN, String currency, int procent, int balance) {
		this.id = id;
		this.numberIBAN = numberIBAN;
		this.currency = currency;
		this.procent = procent;
		this.balance = balance;
	}

	public int getProcent() {
		return procent;
	}

	public void setProcent(int procent) {
		this.procent = procent;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public String getNumberIBAN() {
		return numberIBAN;
	}

	public String getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + balance;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numberIBAN == null) ? 0 : numberIBAN.hashCode());
		result = prime * result + procent;
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
		Account other = (Account) obj;
		return id == other.id &&
				numberIBAN.equals(other.numberIBAN) &&
				currency.equals(other.currency) &&
				procent == other.procent &&
				balance == other.balance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [id=");
		builder.append(id);
		builder.append(", numberIBAN=");
		builder.append(numberIBAN);
		builder.append(", currency=");
		builder.append(currency);
		builder.append(", procent=");
		builder.append(procent);
		builder.append(", balance=");
		builder.append(balance);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
