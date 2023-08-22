package com.durgesh.blog.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.durgesh.blog.services.FileService;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
//		FILE NAME
		String name = file.getOriginalFilename();
		
//		RANDOM NAME GENERATE FILE
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
//		FULL PATH
		String filePath = path + File.separator + fileName1;
		
//		CREATE FOLDER IF NOT CREATED
		File f = new File(path);
		if(!f.exists())
		{
			f.mkdir();
		}
		
//		FILE COPY
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String fullPath = path + File.separator + fileName;
		InputStream is = new FileInputStream(fullPath);
//		DB LOGIC TO RETURN INPUT STREAM
		return is;
	}

}
