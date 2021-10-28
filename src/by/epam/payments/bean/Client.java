package by.epam.payments.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String identificationNumber;
	private String lastName;
	private String firstName;
	private String patronymic;
	private String phoneNumber;
	private String registrationAddress;
	private String realAddress;
	private int userId;
	private List<Account> accounts = new ArrayList<Account>();
	
	public Client() {
	}
	
	public Client(String identificationNumber, String lastName, String firstName, String patronymic, 
			String phoneNumber, String registrationAdress, String realAdress, int userId) {
		this.identificationNumber = identificationNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.phoneNumber = phoneNumber;
		this.registrationAddress = registrationAdress;
		this.realAddress = realAdress;
		this.userId = userId;
	}
	
	public Client(int id, String identificationNumber, String lastName, String firstName, String patronymic,
			String phoneNumber, String registrationAdress, String realAdress, int userId) {
		this.id = id;
		this.identificationNumber = identificationNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.phoneNumber = phoneNumber;
		this.registrationAddress = registrationAdress;
		this.realAddress = realAdress;
		this.userId = userId;
	}
	
	
	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegistrationAddress() {
		return registrationAddress;
	}

	public void setRegistrationAddress(String registrationAdress) {
		this.registrationAddress = registrationAdress;
	}

	public String getRealAddress() {
		return realAddress;
	}

	public void setRealAddress(String realAdress) {
		this.realAddress = realAdress;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((identificationNumber == null) ? 0 : identificationNumber.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((patronymic == null) ? 0 : patronymic.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((realAddress == null) ? 0 : realAddress.hashCode());
		result = prime * result + ((registrationAddress == null) ? 0 : registrationAddress.hashCode());
		result = prime * result + userId;
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
		Client other = (Client) obj;
		return identificationNumber.equals(other.identificationNumber) &&
				firstName.equals(other.firstName) &&
				lastName.equals(other.lastName) &&
				patronymic.equals(other.patronymic) &&
				phoneNumber.equals(phoneNumber) &&
				registrationAddress.equals(other.registrationAddress) &&
				realAddress.equals(other.realAddress) &&
				userId == other.userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [id=");
		builder.append(id);
		builder.append(", identificationNumber=");
		builder.append(identificationNumber);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", patronymic=");
		builder.append(patronymic);
		builder.append(", phoneNumber=");
		builder.append(phoneNumber);
		builder.append(", registrationAddress=");
		builder.append(registrationAddress);
		builder.append(", realAddress=");
		builder.append(realAddress);
		builder.append(", userId=");
		builder.append(userId);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
