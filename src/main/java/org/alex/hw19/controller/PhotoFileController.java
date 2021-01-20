package org.alex.hw19.controller;

import org.alex.hw19.domain.Student;
import org.alex.hw19.dto.PhotoFileUploadResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PhotoFileController {

    PhotoFileUploadResponse uploadFile(MultipartFile file) throws IOException;
    ResponseEntity<Resource> downloadFile(String fileId) throws FileNotFoundException;
    List<PhotoFileUploadResponse> uploadMultipleFiles(MultipartFile[] files);
    PhotoFileUploadResponse downloadFileThis();
    PhotoFileUploadResponse registration(Student students);
}
