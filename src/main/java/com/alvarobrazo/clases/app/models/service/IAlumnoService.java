package com.alvarobrazo.clases.app.models.service;

import java.util.List;

import com.alvarobrazo.clases.app.models.entity.Alumno;

public interface IAlumnoService {

public List<Alumno> findAll();
	
	public void save(Alumno cliente);
	
	public Alumno findOne(Long id);
	
	public void delete(Long id);
	
}
