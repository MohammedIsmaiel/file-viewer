package com.techsavants.file_viewer.app.directory;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@AllArgsConstructor
@Log4j2
@RequestMapping("/api/directories")
class DirectoryController {

    private DirectoryService directoryService;

    @GetMapping
    public List<FileInfo> getDirectory(@RequestParam String path) {
        try {
            return directoryService.listFiles(path);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException("Failed to list directory contents", e);
        }
    }

}
