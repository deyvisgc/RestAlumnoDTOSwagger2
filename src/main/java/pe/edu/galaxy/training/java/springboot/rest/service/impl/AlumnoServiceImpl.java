package pe.edu.galaxy.training.java.springboot.rest.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.training.java.springboot.rest.dto.AlumnoDTO;
import pe.edu.galaxy.training.java.springboot.rest.entity.AlumnoEntity;
import pe.edu.galaxy.training.java.springboot.rest.repository.AlumnoRepository;
import pe.edu.galaxy.training.java.springboot.rest.service.exception.ServiceException;
import pe.edu.galaxy.training.java.springboot.rest.service.service.AlumnoService;

@Slf4j
@Data
@EqualsAndHashCode(callSuper=false)
@Service
public class AlumnoServiceImpl extends GenericServiceImpl implements AlumnoService{

	
	@Autowired
	private AlumnoRepository alumnoRepository;

	@Override
	public List<AlumnoDTO> findLike(AlumnoDTO alumno) throws ServiceException {
		try {
			String nombre="";
			if (alumno!=null) {
				nombre= alumno.getNombre();
			}
			nombre=(nombre==null)?"":nombre;
			nombre="%"+nombre+"%";
			
			List<AlumnoEntity> lstAlumnoEntity=this.getAlumnoRepository().findByName(nombre.toUpperCase());
			/*
			List<AlumnoDTO> lstAlumnoBean= new ArrayList<>();
			
			for (AlumnoEntity alumnoEntity : lstAlumnoEntity) {
				//lstAlumnoBean.add(getAlumnoBean(alumnoEntity));
				AlumnoDTO alumnoBean= new AlumnoDTO();
				BeanUtils.copyProperties(alumnoEntity,alumnoBean);
				lstAlumnoBean.add(alumnoBean);
			}
			
			return lstAlumnoBean;
			
			*/
			return lstAlumnoEntity.stream()
					.map(alumnoEntity -> getAlumnoBean(alumnoEntity)).collect(Collectors.toList());
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}
	
	private AlumnoDTO getAlumnoBean(AlumnoEntity alumnoEntity) {
		AlumnoDTO alumnoDTO= new AlumnoDTO();
		BeanUtils.copyProperties(alumnoEntity,alumnoDTO);
		return alumnoDTO;
	}
	
	private AlumnoEntity getAlumnoEntity(AlumnoDTO alumnoDTO) {
		AlumnoEntity alumnoEntity= new AlumnoEntity();
		BeanUtils.copyProperties(alumnoDTO,alumnoEntity);
		return alumnoEntity;
	}

	@Override
	public AlumnoDTO findById(AlumnoDTO alumnoDTO) throws ServiceException {
		try {
			
			Optional<AlumnoEntity> opt=this.getAlumnoRepository().findById(alumnoDTO.getId());
			if (opt.isPresent()) {
				
				return this.getAlumnoBean(opt.get());
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public AlumnoDTO insert(AlumnoDTO alumnoDTO) throws ServiceException {
		try {
			alumnoDTO.setFechaRegistro(new Date());
			
			AlumnoEntity alumnoEntity =this.getAlumnoRepository().save(this.getAlumnoEntity(alumnoDTO));
			
			return this.getAlumnoBean(alumnoEntity);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}
	}

	@Override
	public AlumnoDTO update(AlumnoDTO alumnoDTO) throws ServiceException {
		Optional<AlumnoEntity> opt= this.getAlumnoRepository().findById(alumnoDTO.getId());
		if (opt==null) {
			return null;	
		}
		if (opt.isPresent()) {
			AlumnoEntity oAlumnoEntity= opt.get();
			BeanUtils.copyProperties(alumnoDTO,oAlumnoEntity);
			AlumnoEntity alumnoEntity= alumnoRepository.save(oAlumnoEntity);
			return this.getAlumnoBean(alumnoEntity);
		}
		return null;	
	}

	@Override
	public boolean delete(AlumnoDTO alumnoDTO) throws ServiceException {
		Optional<AlumnoEntity> opt= this.getAlumnoRepository().findById(alumnoDTO.getId());
		if (opt==null) {
			return false;	
		}
		if (opt.isPresent()) {
			AlumnoEntity oAlumnoEntity= opt.get();
			oAlumnoEntity.setEstado("0");
			AlumnoEntity alumnoEntity= alumnoRepository.save(oAlumnoEntity);
			if (alumnoEntity!=null) {
				return true;
			}
			
		}
		return false;	
	}

	@Override
	public AlumnoDTO findByCodigo(String codigo) throws ServiceException {
		return null;
		/*
		try {
			
			Optional<Alumno> opt=this.getAlumnoRepository().findById(codigo);
			if (opt.isPresent()) {
				return opt.get();
			}
			return null;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ServiceException(e);
		}*/
	}

}
