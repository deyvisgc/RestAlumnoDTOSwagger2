package pe.edu.galaxy.training.java.springboot.rest.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="AlumnoEntity")
@Table(name="ALUMNO")
@NoArgsConstructor
public class AlumnoEntity {
	
	@ApiModelProperty(notes = "Codigo de alumno",name="id",required=true,value="1")
	@Id
	@Column(name="ID_ALUMNO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAlumno")
	@SequenceGenerator(name = "seqAlumno", allocationSize = 1, sequenceName = "SEQ_ALUMNO")
	private Long id;
	
	@ApiModelProperty(notes = "Apellido Paterno (debe ser mayor de 5 y menor que 60 caracteres)",name="apellidoPaterno",required=true,value="Perez")
	@Size (min=5, max=60, message="El apellido paterno debe ser mayor de 5 y menor que 60 caracteres")
	@Column(name="APELLIDO_PATERNO")
	private String apellidoPaterno;
	
	@Column(name="APELLIDO_MATERNO")
	private String apellidoMaterno;
	
	@Size (min=5, max=60, message="El nombre ser mayor de 3  y menor que 10 caracteres")
	@Column(name="NOMBRE")
	private String nombre;
	
	@Positive(message="El tipo de documento deber ser mayor que 0")
	@Column(name="ID_TIPO_DOCUMENTO")
	private Integer idTipoDoumento;
	
	@Column(name="NRO_DOCUMENTO")
	private String nroDocumento;

	@Email(message="Formato de correo no valido, ejemplo correo@dominio")
	@Column(name="CORREO")
	private String correo;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	@Column(name="FECHA_REGISTRO")
	private Date fechaRegistro;
	
	@Column(name="ESTADO")
	private String estado="1";
	

	public AlumnoEntity(String nombre) {
		super();
		this.nombre = nombre;
	}

	public AlumnoEntity(Long id) {
		super();
		this.id = id;
	}
	
	

}
