package com.techsavants.file_viewer.app.directory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DirectoryService {
    public List<File> getDirectoryContents(String path) {
        log.info("path is : {}", path);
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                return Arrays.stream(files)
                        .sorted((f1, f2) -> {
                            if (f1.isDirectory() && f2.isFile()) {
                                return -1;
                            } else if (f1.isFile() && f2.isDirectory()) {
                                return 1;
                            }
                            return f1.getName().compareToIgnoreCase(f2.getName());
                        })
                        .collect(Collectors.toList());
            }
        }
        return List.of();
    }

    public List<FileInfo> listFiles(String path) {
        File directory = new File(path);
        if (directory.isDirectory()) {
            return Arrays.stream(directory.listFiles())
                    .map(file -> new FileInfo(file.getName(), file.isDirectory(), file.getAbsolutePath()))
                    .sorted((f1, f2) -> Boolean.compare(f2.isDirectory(), f1.isDirectory()))
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Path is not a directory");
        }
    }
}
