package org.alex.hw19.dto;


public class PhotoFileUploadResponse {

    private String firstName;
    private String lastName;
    private Integer age;

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public PhotoFileUploadResponse(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public PhotoFileUploadResponse(String firstName, String lastName, Integer age, String fileName,
                                   String fileDownloadUri, String fileType, long size) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public PhotoFileUploadResponse() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
