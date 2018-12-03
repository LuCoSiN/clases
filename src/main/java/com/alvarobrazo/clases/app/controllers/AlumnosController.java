package com.alvarobrazo.clases.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.alvarobrazo.clases.app.models.entity.Alumno;
import com.alvarobrazo.clases.app.models.service.IAlumnoService;

@Controller
@SessionAttributes("alumno")
public class AlumnosController {
	
	@Autowired
	private IAlumnoService clienteSercice;
	
	@RequestMapping(value="/alumnos",method=RequestMethod.GET)
	public String alumnos(Model model) {
		model.addAttribute("titulo", "Listado de alumnos");
		model.addAttribute("alumnos", clienteSercice.findAll());
		return "alumnos";
		
	}
	
	@RequestMapping(value="/alumno")
	public String crear(Map<String,Object> model) {
		Alumno cliente = new Alumno();
		model.put("alumno", cliente);
		model.put("titulo", "Formulario de Alumno");
		return "alumno";
	}
	
	@RequestMapping(value="/alumno/{id}")
	public String editar(@PathVariable(value="id")Long id, Map<String,Object> model) {
		Alumno cliente = null;
		if(id>0) {
			cliente = clienteSercice.findOne(id);
		}
		else {
			return "redirect:/alumnos";
		}
		model.put("alumno", cliente);
		model.put("titulo", "Editar alumno");
		return "alumno";
	}
	
	@RequestMapping(value="/alumno", method=RequestMethod.POST)
	public String guardar(@Valid Alumno cliente, BindingResult result, Model model, SessionStatus status) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Alumno");
			return "alumno";
		}
		clienteSercice.save(cliente);
		status.setComplete();
		return "redirect:alumnos";
	}
	
	@RequestMapping(value="/delete/alumno/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			clienteSercice.delete(id);
		}
		return "redirect:/alumnos";
	}

}
