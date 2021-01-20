package org.alex.hw19.controller.impl;


import org.alex.hw19.controller.PhotoFileController;
import org.alex.hw19.domain.PhotoFile;
import org.alex.hw19.domain.Student;
import org.alex.hw19.dto.PhotoFileUploadResponse;
import org.alex.hw19.service.PhotoFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhotoFileControllerImpl implements PhotoFileController {

    @Autowired
    private PhotoFileService photoFileService;

    private PhotoFile photoFileThis;
    private Student student;
    private String fileDownloadUriThis;


    @Override
    @PostMapping("/uploadFile")
    public PhotoFileUploadResponse uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        PhotoFile photoFile = photoFileService.storeFile(student, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
                .path(photoFile.getId()).toUriString();
        fileDownloadUriThis = fileDownloadUri;

        return new PhotoFileUploadResponse(student.getFirstName() ,student.getLastName() ,student.getAge()
                ,photoFile.getFileName(), fileDownloadUri, file.getContentType(),
                file.getSize());
    }

    @Override
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws FileNotFoundException {
        PhotoFile photoFile = photoFileService.getFile(fileId);
        photoFileThis = photoFile;
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(photoFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + photoFile.getFileName() + "\"")
                .body(new ByteArrayResource(photoFile.getData()));
    }

    @Override
    @PostMapping("/uploadMultipleFiles")
    public List<PhotoFileUploadResponse> uploadMultipleFiles(MultipartFile[] files) {
        return Arrays.asList(files).stream().map(file -> {
            try {
                return uploadFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    @Override
    @GetMapping("/downloadFileThis")
    public PhotoFileUploadResponse downloadFileThis() {
        return new PhotoFileUploadResponse(photoFileThis.getStudent().getFirstName(),
                photoFileThis.getStudent().getLastName(), photoFileThis.getStudent().getAge(),
                photoFileThis.getFileName(), fileDownloadUriThis,
                photoFileThis.getFileType(), 0);
    }

    @Override
    @RequestMapping(value="/registration" , method = RequestMethod.POST)
    public @ResponseBody PhotoFileUploadResponse registration(@RequestBody Student student) {
        this.student = student;
        return null;

    }
}
