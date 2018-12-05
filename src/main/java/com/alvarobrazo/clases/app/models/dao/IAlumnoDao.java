package com.alvarobrazo.clases.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.alvarobrazo.clases.app.models.entity.Alumno;

public interface IAlumnoDao extends PagingAndSortingRepository<Alumno, Long> {
}
