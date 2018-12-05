package com.alvarobrazo.clases.app.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alvarobrazo.clases.app.models.entity.Alumno;
import com.alvarobrazo.clases.app.models.service.IAlumnoService;
import com.alvarobrazo.clases.app.models.service.IUploadFileService;
import com.alvarobrazo.clases.app.util.paginator.PageRender;

@Controller
@SessionAttributes("alumno")
public class AlumnosController {

	@Autowired
	private IAlumnoService alumnoSercice;
	
	@Autowired
	private IUploadFileService uploadSercice;
	
	@Value("${clases.max.upload.size}")
	private int maxUploadSize;

	@RequestMapping(value = "/alumnos", method = RequestMethod.GET)
	public String alumnos(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Alumno> alumnos = alumnoSercice.findAll(pageRequest);
		PageRender<Alumno> pageRender = new PageRender<Alumno>("/alumnos", alumnos);
		model.addAttribute("titulo", "Listado de alumnos");
		model.addAttribute("alumnos", alumnos);
		model.addAttribute("page", pageRender);
		return "alumnos";

	}
	
	

	@RequestMapping(value = "/alumno/alterar")
	public String crear(Map<String, Object> model) {
		Alumno cliente = new Alumno();
		model.put("alumno", cliente);
		model.put("titulo", "Formulario de Alumno");
		return "alumno/alterar";
	}
	
	@RequestMapping(value = "/alumno/ver/{id}")
	public String verAlumno(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Alumno alumno = alumnoSercice.findOne(id);
		if (alumno==null) {
			flash.addFlashAttribute("danger", "El alumno debe existir");
			return "redirect:/alumnos";
		}
		model.put("alumno", alumno);
		model.put("titulo", "Detalle alumno");
		return "alumno/ver";
	}

	@RequestMapping(value = "/alumno/alterar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Alumno alumno = null;
		if (id > 0) {
			alumno = alumnoSercice.findOne(id);
			if (alumno == null) {
				flash.addFlashAttribute("danger", "El alumno debe existir");
				return "redirect:/alumnos";
			}
		} else {
			flash.addFlashAttribute("danger", "El id del alumno no puede ser 0");
			return "redirect:/alumnos";
		}
		model.put("alumno", alumno);
		model.put("titulo", "Editar alumno");
		return "alumno/alterar";
	}

	@RequestMapping(value = "/alumno/alterar", method = RequestMethod.POST)
	public String guardar(@Valid Alumno alumno, BindingResult result, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Alumno");
			return "alumno/alterar";
		}
		
		if(!foto.isEmpty()) {
			//extension original
			String extension = foto.getOriginalFilename().split("\\.")[1];
			//controlamos tamaño del fichero
			long fileSizeInBytes = foto.getSize();
			// Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
			double fileSizeInKB = fileSizeInBytes / 1024;
			// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
			double fileSizeInMB = fileSizeInKB / 1024;
			if(fileSizeInMB>maxUploadSize) {
				model.addAttribute("titulo", "Formulario de Alumno");
				model.addAttribute("alumno", alumno);
				model.addAttribute("danger","Solo se permiten imágenes de máximo "+maxUploadSize+"MB");
				return "/alumno/alterar";
			}
			//controlamos extensión del fichero
			if(!extension.equals("png")&&!extension.equals("jpg")) {
				model.addAttribute("titulo", "Formulario de Alumno");
				model.addAttribute("alumno", alumno);
				model.addAttribute("danger","Solo se permiten imágenes jpg y png");
				return "/alumno/alterar";
			}
			//si el alumno tenía foto antes, la borramos
			if(alumno.getId()!=null && alumno.getId()>0) {
				Alumno old = alumnoSercice.findOne(alumno.getId());
				if( !Strings.isEmpty(old.getFoto())) {
					uploadSercice.delete(old.getFoto());
				}
			}
			//creamos la foto
			String nuevoNombre = uploadSercice.copy(foto);
			flash.addFlashAttribute("info","Has subido correctamente "+foto.getOriginalFilename());
			alumno.setFoto(nuevoNombre);
		}
		
		String messageFlash = (alumno.getId() != null) ? "Alumno modificado con éxito" : "Alumno creado con éxito";
		alumnoSercice.save(alumno);
		status.setComplete();
		flash.addFlashAttribute("sucess", messageFlash);
		return "redirect:/alumnos";
	}

	@RequestMapping(value = "/alumno/delete/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Alumno old = alumnoSercice.findOne(id);
			if( !Strings.isEmpty(old.getFoto())) {
				uploadSercice.delete(old.getFoto());
			}
			alumnoSercice.delete(id);
			flash.addFlashAttribute("sucess", "El alumno se ha eliminado con éxito");
		}
		return "redirect:/alumnos";
	}

}
