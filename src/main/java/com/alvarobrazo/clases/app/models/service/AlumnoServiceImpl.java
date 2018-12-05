package com.alvarobrazo.clases.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alvarobrazo.clases.app.models.dao.IAlumnoDao;
import com.alvarobrazo.clases.app.models.entity.Alumno;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	
	@Autowired
	private IAlumnoDao alumnoDao;

	@Override
	@Transactional(readOnly=true)
	public List<Alumno> findAll() {
		return (List<Alumno>) alumnoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Alumno cliente) {
		alumnoDao.save(cliente);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Alumno findOne(Long id) {
		return alumnoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		alumnoDao.deleteById(id);
	}

	@Override
	public Page<Alumno> findAll(Pageable pageable) {
		return alumnoDao.findAll(pageable);
	}

}
