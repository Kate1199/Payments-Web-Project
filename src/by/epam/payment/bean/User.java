package by.epam.payment.bean;

import java.util.Arrays;

public class User extends Entity {
	
	private int id;
	private String login;
	private String email;
	private byte[] password;
	private byte[] salt;
	private Role role;
	
	public User() {
	}
	
	public User(String login, String email, byte[] password, byte[] salt, Role role) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.role = role;
	}

	
	public User(int id, String login, String email, byte[] password, byte[] salt, Role role) {
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.salt = salt;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPassword() {
		return password;
	}
	
	public void setPassword(byte[] password) {
		this.password = password;
	}
	
	public byte[] getSalt() {
		return salt;
	}

	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + Arrays.hashCode(password);
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + Arrays.hashCode(salt);
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
		User other = (User) obj;
		return id == other.id
				&& login.equals(other.login)
				&& email.equals(other.email)
				&& Arrays.equals(password, other.password)
				&& Arrays.equals(salt, other.salt)
				&& role.equals(other.role);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getSimpleName());
		builder.append(" [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(Arrays.toString(password));
		builder.append(", salt=");
		builder.append(Arrays.toString(salt));
		builder.append(", role=");
		builder.append(role);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
