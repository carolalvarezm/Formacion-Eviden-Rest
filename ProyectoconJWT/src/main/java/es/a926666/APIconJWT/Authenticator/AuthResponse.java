package es.a926666.APIconJWT.Authenticator;

public class AuthResponse {
	String Token;

	public AuthResponse(String token) {
		Token = token;
	}

	public AuthResponse() {
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}
	
}
