package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

//for login endpoint

public class  AuthRequest {
	
	public String username;
	public String password;

	public AuthRequest() {
	}

	public AuthRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthRequest{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}