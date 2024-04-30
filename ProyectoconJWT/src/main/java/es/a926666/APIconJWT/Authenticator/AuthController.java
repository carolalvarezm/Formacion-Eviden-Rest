package es.a926666.APIconJWT.Authenticator;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {
	private final AuthService authService;
	public AuthController(AuthService authService) {
		this.authService = authService;
		
	}

	//Devolvemos la respuesta del Login
	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {
		return authService.login(request);
	}

	//Devolvemos la respuesta del Register
	@PostMapping(value = "register")
	public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
		return authService.register(request);
	}
}
