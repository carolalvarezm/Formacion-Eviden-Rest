package es.a926666.APIconJWT.Authenticator;


import es.a926666.APIconJWT.User.User;
import es.a926666.APIconJWT.User.UserService;
import es.a926666.APIconJWT.JWT.JWTService;
import es.a926666.APIconJWT.User.Role;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
	
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JWTService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public AuthService(PasswordEncoder passwordEncoder, JWTService jwtService,UserService userService, AuthenticationManager authenticationManager) {
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.userService= userService;
		this.authenticationManager= authenticationManager;
	}
	
	public ResponseEntity<?> login(LoginRequest request) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			UserDetails user = userService.findByUsername(request.getUsername());
			String token = jwtService.getToken(user);
			
			if(token!=null) {
				return ResponseEntity.ok(new AuthResponse(token));
			}
			else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe o la contraseña es incorrecta");
			}
			}
		catch(Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
		

		
	}
	
	public ResponseEntity<?> register(RegisterRequest request) {
		User user = new User(request.getUsername(),passwordEncoder.encode(request.getPassword()),request.getName(),request.getLastname(),request.getEmail(),Role.User);
		if (userService.addUser(user)==null) {
			return ResponseEntity.ok(new AuthResponse(jwtService.getToken(user)));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya se encuentra registrado");
	}

}
