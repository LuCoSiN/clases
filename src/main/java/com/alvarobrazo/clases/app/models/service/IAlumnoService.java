package com.alvarobrazo.clases.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.alvarobrazo.clases.app.models.entity.Alumno;

public interface IAlumnoService {

	public List<Alumno> findAll();
	
	public Page<Alumno> findAll(Pageable pageable);
	
	public void save(Alumno cliente);
	
	public Alumno findOne(Long id);
	
	public void delete(Long id);
	
}
