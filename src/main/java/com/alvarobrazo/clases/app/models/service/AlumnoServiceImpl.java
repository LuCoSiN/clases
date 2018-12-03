package com.alvarobrazo.clases.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvarobrazo.clases.app.models.dao.IAlumnoDao;
import com.alvarobrazo.clases.app.models.entity.Alumno;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	
	@Autowired
	private IAlumnoDao clienteDao;

	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findAll() {
		return (List<Alumno>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public void save(Alumno cliente) {
		clienteDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Alumno findOne(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);;
		
	}

}
