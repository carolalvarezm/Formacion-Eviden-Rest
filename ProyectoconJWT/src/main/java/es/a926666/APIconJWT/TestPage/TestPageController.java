package es.a926666.APIconJWT.TestPage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class TestPageController {
	
	public TestPageController() {
		super();
	}

	@GetMapping(value = "Test")
	public String hello() {
		return "Hola desde un endpoint protegido";
	}
}
