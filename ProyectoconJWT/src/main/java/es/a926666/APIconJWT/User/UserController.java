package es.a926666.APIconJWT.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;

	//Devolvemos la respuesta con los usuarios
	@GetMapping(value = "/")
	public  List<User> user() {
		return userService.getAllUsers() ;
	}
}
