package com.techsavants.file_viewer.app.directory;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileInfo {
    private String name;
    private boolean isDirectory;
    private String extension;
    private long size;
    private long lastModified;
    private String content;
    private long lastLine;

    public FileInfo(Path path, BasicFileAttributes attrs) {
        this.name = path.getFileName().toString();
        this.isDirectory = attrs.isDirectory();
        this.extension = isDirectory ? "" : getFileExtension(name);
        this.size = attrs.size();
        this.lastModified = attrs.lastModifiedTime().toMillis();
    }

    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        return index > 0 ? fileName.substring(index + 1) : "";
    }
}
