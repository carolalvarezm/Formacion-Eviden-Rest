package es.a926666.APIconJWT.Authenticator;


public class RegisterRequest {

	String username;
	String password;
	String name;
	String lastname;
	String email;
	
	public RegisterRequest(String username, String password, String name, String lastname, String email) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
