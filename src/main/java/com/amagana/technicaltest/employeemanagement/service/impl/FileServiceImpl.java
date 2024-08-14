package com.amagana.technicaltest.employeemanagement.service.impl;

import com.amagana.technicaltest.employeemanagement.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String filePath = path+ File.separator+filename;
        File dest = new File(path);
        if(!dest.exists())
            dest.mkdir();
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filename;
    }

    @Override
    public InputStream getResourceFile(String path, String name) throws FileNotFoundException {
        String filename = path+ File.separator+name;
        return new FileInputStream(filename);
    }
}
