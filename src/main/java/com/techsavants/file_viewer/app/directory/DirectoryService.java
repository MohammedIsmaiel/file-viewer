package com.techsavants.file_viewer.app.directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class DirectoryService {
    public List<FileInfo> listFiles(String path) throws IOException {
        log.debug("reading path: {}", path);
        Path directory = Paths.get(path);
        if (Files.isDirectory(directory)) {
            return Files.list(directory)
                    .map(this::getFileInfo)
                    .sorted((f1, f2) -> {
                        if (f1.isDirectory() && !f2.isDirectory())
                            return -1;
                        if (!f1.isDirectory() && f2.isDirectory())
                            return 1;
                        return f1.getName().compareTo(f2.getName());
                    })
                    .collect(Collectors.toList());
        } else {
            throw new IllegalArgumentException("Path is not a directory");
        }
    }

    private FileInfo getFileInfo(Path path) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            return new FileInfo(path, attrs);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file attributes", e);
        }
    }
}
