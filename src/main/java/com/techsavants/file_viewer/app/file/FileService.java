package com.techsavants.file_viewer.app.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.techsavants.file_viewer.app.directory.FileInfo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
class FileService {

    public FileInfo readFile(String filePath) throws IOException {
        return readFileFromLine(filePath, 0);
    }

    public FileInfo readFileFromLine(String filePath, long startLine) throws IOException {
        log.info("reading file: {} ", filePath);
        log.info("reading from line : {} ", startLine);
        Path path = Paths.get(filePath);
        if (Files.isRegularFile(path)) {
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
            FileInfo fileInfo = new FileInfo(path, attrs);

            try (Stream<String> lines = Files.lines(path)) {
                List<String> contentLines = lines.skip(startLine).collect(Collectors.toList());
                fileInfo.setContent(String.join("\n", contentLines));
                fileInfo.setLastLine(startLine + contentLines.size());
            }

            return fileInfo;
        } else {
            throw new IllegalArgumentException("Path is not a regular file");
        }
    }
}
