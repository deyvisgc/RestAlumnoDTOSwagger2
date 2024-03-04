package pe.edu.galaxy.training.java.springboot.rest.service.service;

import java.util.List;

import pe.edu.galaxy.training.java.springboot.rest.service.exception.ServiceException;

public interface GenericService <T> {

	List<T> findLike(T t) throws ServiceException;
	
	T findById(T t) throws ServiceException;

	T insert(T t) throws ServiceException;
	
	T update(T t) throws ServiceException;
	
	boolean delete(T t) throws ServiceException;
	
}
