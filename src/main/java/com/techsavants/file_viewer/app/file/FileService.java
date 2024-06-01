package com.techsavants.file_viewer.app.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
class FileService {

    public List<String> viewFile(String path) throws IOException {
        Path filePath = Paths.get(path);
        return Files.readAllLines(filePath);
    }

    public List<String> tailFile(String path, int lines) throws IOException {
        Path filePath = Paths.get(path);
        try (Stream<String> stream = Files.lines(filePath)) {
            return stream.skip(Math.max(0, Files.lines(filePath).count() - lines))
                    .collect(Collectors.toList());
        }
    }
}
