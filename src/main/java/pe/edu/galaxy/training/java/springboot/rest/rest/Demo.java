package pe.edu.galaxy.training.java.springboot.rest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/demo")
public class Demo {
	
	
	@GetMapping
	public String saludo() {	
		return "Spring Boot saludo";
	}
	
	@GetMapping
	@RequestMapping("/{nombre}")
	public String saludoNombre(@PathVariable("nombre") String nombre) {
		return "Bienvenido "+nombre;
	}
	
}
