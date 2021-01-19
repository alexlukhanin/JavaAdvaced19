package org.alex.hw19.service.impl;


import org.alex.hw19.domain.PhotoFile;
import org.alex.hw19.domain.Student;
import org.alex.hw19.repository.PhotoFileRepository;
import org.alex.hw19.service.PhotoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;


@Service
public class PhotoFileServiceImpl implements PhotoFileService {

    @Autowired
    private PhotoFileRepository photoFileRepository;


    @Override
    public PhotoFile storeFile(Student student, MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        PhotoFile multipart = null;

        if (!fileName.contains("..")) {
            multipart = new PhotoFile(fileName, file.getContentType(), file.getBytes());
        }
        if(student != null) {
            multipart.setStudent(student);
            return photoFileRepository.save(multipart);
        }
        return null;
    }

    @Override
    public PhotoFile getFile(String id) throws FileNotFoundException {
        return photoFileRepository.findById(id)
                .orElseThrow(() -> new FileNotFoundException("File not found with Id =" + id));

    }
}
