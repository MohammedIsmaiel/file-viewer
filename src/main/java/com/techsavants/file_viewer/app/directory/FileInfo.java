package com.techsavants.file_viewer.app.directory;

import lombok.Data;

@Data
public class FileInfo {
    private String name;
    private boolean isDirectory;
    private String path;

    public FileInfo(String name, boolean isDirectory, String path) {
        this.name = name;
        this.isDirectory = isDirectory;
        this.path = path;
    }
}
