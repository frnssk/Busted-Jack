package resources;

public class LoginRequest {
	private String username;
	private char[] password;
	
	public LoginRequest(String username, char[] password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	
	public char[] getPassword() {
		return password;
	}

}
