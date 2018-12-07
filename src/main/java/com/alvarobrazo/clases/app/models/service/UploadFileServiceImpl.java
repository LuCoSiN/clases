package com.alvarobrazo.clases.app.models.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	@Value("${clases.ruta.uploads}")
	private String rutaUploads;

	@Override
	public String copy(MultipartFile file) {
		//extension
		String extension = file.getOriginalFilename().split("\\.")[1];
		//uuid para nombre de la foto
		UUID uuid = UUID.randomUUID();
        String nuevoNombre = uuid.toString()+"."+extension;
        Path rutaCompleta = Paths.get(rutaUploads+"//"+nuevoNombre);
		byte[] bytes;
		try {
			bytes = file.getBytes();
			Files.write(rutaCompleta, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nuevoNombre;
	}

	@Override
	public boolean delete(String filename) {
		boolean delete = false;
		Path rutaoldFile = Paths.get(rutaUploads+"//"+filename);
		File file = rutaoldFile.toFile();
		if(file.exists()&& file.canRead()) {
			delete= file.delete();
		}
		return delete;
	}
	
	public Path getPath(String filename) {
		return Paths.get(rutaUploads+"//"+filename);
	}


	@Override
	public void creafeIfNotExists() {
		File uploads = Paths.get(rutaUploads).toFile();
		if(!uploads.exists()) {
			try {
				Files.createDirectory(Paths.get(rutaUploads));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
