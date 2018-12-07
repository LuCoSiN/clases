package com.alvarobrazo.clases.app.models.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public String copy(MultipartFile file);
	
	public boolean delete(String filename);
	
	public void creafeIfNotExists();


}
