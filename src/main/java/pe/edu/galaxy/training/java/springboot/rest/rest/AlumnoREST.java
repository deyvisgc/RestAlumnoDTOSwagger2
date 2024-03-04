package pe.edu.galaxy.training.java.springboot.rest.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.springboot.rest.dto.AlumnoDTO;
import pe.edu.galaxy.training.java.springboot.rest.service.exception.ServiceException;
import pe.edu.galaxy.training.java.springboot.rest.service.service.AlumnoService;

@Slf4j
@RestController
@RequestMapping("/alumnos")
@Api(value = "API REST Alumno")
public class AlumnoREST {
	
	@Autowired
	private AlumnoService alumnoService;

	@ApiOperation(value = "Listar Alumnos", response = AlumnoDTO.class, tags = "alumnos.findAll")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not Authorized!"), 
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not Found!") })
	@GetMapping
	public ResponseEntity<List<AlumnoDTO>> findAll() {	
		try {
			List<AlumnoDTO> lstAlumno=alumnoService.findLike(new AlumnoDTO());
			if (lstAlumno.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstAlumno);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	

	@ApiOperation(value = "Listar Alumnos por Nombre", response = AlumnoDTO.class, tags = "alumnos.findLikeNombre()")
	@ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "Not Authorized!"), 
            @ApiResponse(code = 403, message = "Forbidden!"),
            @ApiResponse(code = 404, message = "Not Found!") })
	@ApiParam(name ="nombre del alumno", example = "Juan" )
	@GetMapping("/by-nombre")
	public ResponseEntity<List<AlumnoDTO>> findLikeNombre( @RequestParam String nombre) {	
		try {
			List<AlumnoDTO> lstAlumno=alumnoService.findLike(new AlumnoDTO(nombre));
			if (lstAlumno.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstAlumno);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<AlumnoDTO> findById(@PathVariable Long id) {	
		try {
			AlumnoDTO alumnoDTO=alumnoService.findById(new AlumnoDTO(id));
			if (alumnoDTO==null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(alumnoDTO);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/by-nombre-post")
	public ResponseEntity<List<AlumnoDTO>> findLikeNombrePost(@RequestBody AlmnoRequest alumnoRequest) {	
		try {
			
			List<AlumnoDTO> lstAlumno=alumnoService.findLike(new AlumnoDTO(alumnoRequest.getNombre()));
			if (lstAlumno.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(lstAlumno);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<AlumnoDTO> save(@RequestBody @Validated AlumnoDTO alumnoDTO) {
		try {
			AlumnoDTO oAlumno=alumnoService.insert(alumnoDTO);
			if (oAlumno==null) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(oAlumno);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	
	@PutMapping("/{id}")
	public AlumnoDTO update(@PathVariable Long id,  @RequestBody AlumnoDTO alumnoDTO) {
		try {
			alumnoDTO.setId(id);
			return alumnoService.update(alumnoDTO);
		} catch (ServiceException e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		try {
			 alumnoService.delete(new AlumnoDTO(id));
		} catch (ServiceException e) {
			log.error(e.getMessage());
		}	
	}
	
}
