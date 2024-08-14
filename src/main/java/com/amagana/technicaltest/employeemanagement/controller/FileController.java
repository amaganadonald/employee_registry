package com.amagana.technicaltest.employeemanagement.controller;

import com.amagana.technicaltest.employeemanagement.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Value("${file.upload-dir")
    private String path;
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestPart MultipartFile multipartFile) throws IOException {
        String filename = fileService.uploadFile(path, multipartFile);
        return ResponseEntity.ok("File uploaded successfully: " + filename);
    }

    @GetMapping("/{filename}")
    public void serveFileHandler(@PathVariable String filename, HttpServletResponse httpServletResponse) throws IOException {
        InputStream fileResource = fileService.getResourceFile(path, filename);
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(fileResource, httpServletResponse.getOutputStream());
    }
}
