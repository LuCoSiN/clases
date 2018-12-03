package com.alvarobrazo.clases.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.alvarobrazo.clases.app.models.entity.Alumno;

public interface IAlumnoDao extends CrudRepository<Alumno, Long> {
}
