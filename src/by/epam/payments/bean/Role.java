package by.epam.payments.bean;

public enum Role {
	USER("user"),
	ADMIN("admin"),
	BANK("bank");

	private String roleName;
	
	Role(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		return roleName;
	}
}
