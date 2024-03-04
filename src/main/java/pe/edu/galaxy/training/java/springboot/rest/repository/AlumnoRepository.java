package pe.edu.galaxy.training.java.springboot.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.galaxy.training.java.springboot.rest.entity.AlumnoEntity;

@Repository
public interface AlumnoRepository extends JpaRepository<AlumnoEntity, Long>{

	@Query("select p from AlumnoEntity p where estado='1'")
	List<AlumnoEntity> findAllCustom();
	
	@Query("select p from AlumnoEntity p where upper(nombre) like :nombre and estado='1'")
	List<AlumnoEntity> findByName(@Param("nombre") String nombre);
	
	
}
