// package com.techsavants.file_viewer.app.file;

// import java.io.File;
// import java.util.concurrent.ConcurrentHashMap;

// import
// org.springframework.integration.file.tail.ApacheCommonsFileTailingMessageProducer;
// import org.springframework.messaging.MessageChannel;
// import org.springframework.stereotype.Service;

// @Service
// public class FileTailingService {

// private final MessageChannel fileTailChannel;
// private final ConcurrentHashMap<String,
// ApacheCommonsFileTailingMessageProducer> producers = new
// ConcurrentHashMap<>();

// public FileTailingService(MessageChannel fileTailChannel) {
// this.fileTailChannel = fileTailChannel;
// }

// public void startTailing(String filePath) {
// producers.computeIfAbsent(filePath, path -> {
// ApacheCommonsFileTailingMessageProducer producer = new
// ApacheCommonsFileTailingMessageProducer();
// producer.setFile(new File(path));
// producer.setOutputChannel(fileTailChannel);
// producer.start();
// return producer;
// });
// }

// public void stopTailing(String filePath) {
// ApacheCommonsFileTailingMessageProducer producer =
// producers.remove(filePath);
// if (producer != null) {
// producer.stop();
// }
// }
// }
