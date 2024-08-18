package com.movieflix.movieFlix.service;

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

        //file name
        String fileName = file.getOriginalFilename();

        //file location
        String filePath = path + File.separator + fileName;

        //create file object
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }

        //copy file or upload existing
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public InputStream getResourceFile(String path, String filename) throws FileNotFoundException {

        String filePath = path + File.separator + filename;

        return new FileInputStream(filePath);
    }
}
