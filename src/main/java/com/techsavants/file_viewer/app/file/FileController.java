package com.techsavants.file_viewer.app.file;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/files")
class FileController {

    private FileService fileService;

    @GetMapping("/view")
    public ResponseEntity<List<String>> viewFile(@RequestParam String path) {
        try {
            List<String> content = fileService.viewFile(path);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(List.of("Error reading file: " + e.getMessage()));
        }
    }

    @GetMapping("/tail")
    public ResponseEntity<List<String>> tailFile(@RequestParam String path, @RequestParam int lines) {
        try {
            List<String> content = fileService.tailFile(path, lines);
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(List.of("Error tailing file: " + e.getMessage()));
        }
    }

}
