package pe.edu.galaxy.training.java.springboot.rest.service.service;

import pe.edu.galaxy.training.java.springboot.rest.dto.AlumnoDTO;
import pe.edu.galaxy.training.java.springboot.rest.service.exception.ServiceException;

public interface AlumnoService extends GenericService<AlumnoDTO>{

	AlumnoDTO findByCodigo(String codigo) throws ServiceException;
		
}
