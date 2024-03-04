package pe.edu.galaxy.training.java.springboot.rest.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlumnoDTO {
	
	private Long id;
	
	@Builder.Default
	private String apellidoPaterno="";
	
	@Builder.Default
	private String apellidoMaterno="";
	
	@Builder.Default
	private String nombre="";
	
	private Integer idTipoDoumento;
	
	private String nroDocumento;

	private String correo;
	
	private String telefono;
	
	private Date fechaRegistro;
	
	@Builder.Default
	private String estado="1";

	public AlumnoDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public AlumnoDTO(Long id) {
		super();
		this.id = id;
	}
	
	public String getNombreCompleto() {
		return this.getApellidoPaterno().toUpperCase()+ " "
				+ this.getApellidoMaterno().toUpperCase()+ ", "
				+ this.getNombre();
	}
	
}
