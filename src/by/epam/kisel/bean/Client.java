package by.epam.kisel.bean;

import java.io.Serializable;

public class Client extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String identificationNumber;
	private String lastName;
	private String firstName;
	private String patronymic;
	private String registrationAdress;
	private String realAdress;
	private boolean residentUSA;
	
	public Client() {
		
	}
	
	public Client(String identificationNumber, String lastName, String firstName, String patronymic,
			String registrationAdress, String realAdress, boolean residentUSA) {
		this.identificationNumber = identificationNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.registrationAdress = registrationAdress;
		this.realAdress = realAdress;
		this.residentUSA = residentUSA;
	}
	
	public Client(int id, String identificationNumber, String lastName, String firstName, String patronymic,
			String registrationAdress, String realAdress, boolean residentUSA) {
		this.id = id;
		this.identificationNumber = identificationNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.registrationAdress = registrationAdress;
		this.realAdress = realAdress;
		this.residentUSA = residentUSA;
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

	public String getRegistrationAdress() {
		return registrationAdress;
	}

	public void setRegistrationAdress(String registrationAdress) {
		this.registrationAdress = registrationAdress;
	}

	public String getRealAdress() {
		return realAdress;
	}

	public void setRealAdress(String realAdress) {
		this.realAdress = realAdress;
	}

	public boolean isResidentUSA() {
		return residentUSA;
	}

	public void setResidentUSA(boolean residentUSA) {
		this.residentUSA = residentUSA;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
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
		result = prime * result + ((realAdress == null) ? 0 : realAdress.hashCode());
		result = prime * result + ((registrationAdress == null) ? 0 : registrationAdress.hashCode());
		result = prime * result + (residentUSA ? 1231 : 1237);
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
				registrationAdress.equals(other.registrationAdress) &&
				realAdress.equals(other.realAdress) &&
				residentUSA == other.residentUSA;
	}
	
}
