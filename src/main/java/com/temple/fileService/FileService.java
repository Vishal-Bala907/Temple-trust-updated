package com.temple.fileService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.temple.Entities.Products;

@Service
public class FileService {

	public Products fileUpload(String path, Products pro, MultipartFile file) {

		// File Name
		String name = file.getOriginalFilename();

		// Full Path
		String filepath = path + File.separator + name;

		// Create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		// file copy
		pro.setImageUrl("products/" + name);

		try {
			Files.copy(file.getInputStream(), Paths.get(filepath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pro;
	}

	
}
