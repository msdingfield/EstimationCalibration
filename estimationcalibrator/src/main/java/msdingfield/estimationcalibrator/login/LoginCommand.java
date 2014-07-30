package msdingfield.estimationcalibrator.login;

public class LoginCommand {
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "LoginCommand [username=" + username + ", password=" + password
				+ "]";
	}
}
