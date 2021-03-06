package org.alex.hw19.service;

import org.alex.hw19.domain.PhotoFile;
import org.alex.hw19.domain.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface PhotoFileService {

    PhotoFile storeFile(Student student, MultipartFile file) throws IOException;
    PhotoFile getFile(String fileId) throws FileNotFoundException;
}
