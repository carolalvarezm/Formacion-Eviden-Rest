package es.a926666.APIconJWT.Authenticator;


import java.util.Collection;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.a926666.APIconJWT.User.User;
import es.a926666.APIconJWT.User.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	private UserService userService;
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		User user = this.userService.findByUsername(username);
		
		if(user==null || !(this.passwordEncoder.matches(password, user.getPassword()))) {
			throw new BadCredentialsException("Invalid username or Password");
			
		}
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(username, password,authorities); 
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	public void setUserService(UserService userService) {
		this.userService=userService;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
		
	}

	

}
