package com.techsavants.file_viewer.app.directory;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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
@RequestMapping("/api/directories")
class DirectoryController {

    private DirectoryService directoryService;

    @GetMapping("list")
    public ResponseEntity<List<String>> listDirectory(@RequestParam String path) {
        log.info("path is : {}", path);
        List<File> files = directoryService.getDirectoryContents(path);
        List<String> fileNames = files.stream()
                .map(File::getName)
                .collect(Collectors.toList());
        return ResponseEntity.ok(fileNames);
    }

    @GetMapping
    public List<FileInfo> getDirectory(@RequestParam String path) {
        return directoryService.listFiles(path);
    }

}
