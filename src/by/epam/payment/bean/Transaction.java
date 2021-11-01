package by.epam.payment.bean;

public class Transaction {
	
	private String senderAccount;
	private String recieverAccount;
	
	public Transaction() {
	}

	public Transaction(String senderAccount, String recieverAccount) {
		this.senderAccount = senderAccount;
		this.recieverAccount = recieverAccount;
	}

	public String getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(String senderAccount) {
		this.senderAccount = senderAccount;
	}

	public String getRecieverAccount() {
		return recieverAccount;
	}

	public void setRecieverAccount(String recieverAccount) {
		this.recieverAccount = recieverAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recieverAccount == null) ? 0 : recieverAccount.hashCode());
		result = prime * result + ((senderAccount == null) ? 0 : senderAccount.hashCode());
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
		Transaction other = (Transaction) obj;
		return senderAccount.equals(other.senderAccount) &&
				recieverAccount.equals(other.recieverAccount);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [senderAccount=");
		builder.append(senderAccount);
		builder.append(", recieverAccount=");
		builder.append(recieverAccount);
		builder.append("]");
		return builder.toString();
	}
	
	
}
