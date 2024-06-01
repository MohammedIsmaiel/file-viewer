package com.techsavants.file_viewer.app.file;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techsavants.file_viewer.app.directory.FileInfo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/files")
class FileController {

    private FileService fileService;

    @GetMapping("/read")
    public FileInfo readFile(@RequestParam String path) {
        try {
            return fileService.readFile(path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file", e);
        }
    }

    @GetMapping("/tail")
    public FileInfo tailFile(@RequestParam String path, @RequestParam long startLine) {
        try {
            return fileService.readFileFromLine(path, startLine);
        } catch (IOException e) {
            throw new RuntimeException("Failed to tail file", e);
        }
    }

}
