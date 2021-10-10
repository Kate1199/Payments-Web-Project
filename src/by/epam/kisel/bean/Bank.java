package by.epam.kisel.bean;

import java.io.Serializable;

public class Bank extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private boolean residentRB;
	private int mainDepartment;
	
	public Bank(int id, String name, boolean residentRB, int mainDepartment) {
		this.id = id;
		this.name = name;
		this.residentRB = residentRB;
		this.mainDepartment = mainDepartment;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public boolean isResidentRB() {
		return residentRB;
	}
	
	public int getMainDepartment() {
		return mainDepartment;
	}
	public void setMainDepartment(int mainDepartment) {
		this.mainDepartment = mainDepartment;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (residentRB ? 1231 : 1237);
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
		Bank other = (Bank) obj;
		return id == other.id &&
				name.equals(other.id) &&
				residentRB == other.residentRB;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName() + " [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", residentRB=");
		builder.append(residentRB);
		builder.append("]");
		return builder.toString();
	}
}
