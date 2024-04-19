package es.a926666.APIconJWT.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private Map<String,User> users = new HashMap<>();
	private final PasswordEncoder passwordEncoder;
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		User user= new User("user",this.passwordEncoder.encode("1234"),"usuario","user","user@gmail.com",Role.User);
		users.put(user.getUsername(),user);
	}

	
	public User addUser(User user) {
		if(users.get(user.getUsername())==null) {
			users.put(user.getUsername(), user);
			return user;
		}
		return null;
	}
	public List<User> getAllUsers(){
		return new ArrayList<>(users.values());
	}
	public User findByUsername(String username) {
	    return  users.get(username);

	}
}
