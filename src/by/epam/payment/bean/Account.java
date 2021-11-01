package by.epam.payment.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String numberIBAN;
	private String currency;
	private long balance;
	private String status;
	private int clientId;
	private int bankDepartmentId;
	private List<Card> cards = new ArrayList<Card>();
	private Client client;
	private double balanceMultiplier = 0.01;
	
	public Account() {
		
	}
	
	public Account(int id, String numberIBAN, String currency, long balance, String status, int clientId, 
			int bankDepartmentId) {
		this.id = id;
		this.numberIBAN = numberIBAN;
		this.currency = currency;
		this.balance = balance;
		this.status = status;
		this.clientId = clientId;
		this.bankDepartmentId = bankDepartmentId;
	}
	
	public Account(String numberIBAN, String currency, long balance, String status, int clientId, int bankDepartmentId) {
		this.numberIBAN = numberIBAN;
		this.currency = currency;
		this.balance = balance;
		this.status = status;
		this.clientId = clientId;
		this.bankDepartmentId = bankDepartmentId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNumberIBAN() {
		return numberIBAN;
	}
	
	public String getCurrency() {
		return currency;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getClientId() {
		return clientId;
	}

	public int getBankDepartmentId() {
		return bankDepartmentId;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getBalanceMultiplier() {
		return balanceMultiplier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (balance ^ (balance >>> 32));
		result = prime * result + bankDepartmentId;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result + clientId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + id;
		result = prime * result + ((numberIBAN == null) ? 0 : numberIBAN.hashCode());
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
		return  id == other.id &&
				numberIBAN.equals(other.numberIBAN) &&
				currency.equals(other.currency) &&
				balance == other.balance &&
				status.equals(other.status) &&
				clientId == other.clientId &&
				bankDepartmentId == other.bankDepartmentId &&
				cards.equals(cards);
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
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", status=");
		builder.append(status);
		builder.append(", clientId=");
		builder.append(clientId);
		builder.append(", bankDepartmentId=");
		builder.append(bankDepartmentId);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
