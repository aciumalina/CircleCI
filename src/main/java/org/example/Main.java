package org.example;
import org.example.deserializers.QuestionsDeserializer;
import org.example.models.Question;
import org.example.store.QuestionsStore;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;
public class Main {

    public static void main(String[] args) {
        Path watchDir = Paths.get("input");
        try {
            Files.createDirectories(watchDir);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            watchDir.register(watchService, ENTRY_CREATE);

            System.out.println("Watching directory: " + watchDir.toAbsolutePath());

            while (true) {
                WatchKey key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() != ENTRY_CREATE)
                        continue;

                    Path fileName = (Path) event.context();
                    Path filePath = watchDir.resolve(fileName);
                    System.out.println("New file detected: " + fileName);
                    processFile(filePath);

                }

                if (!key.reset()) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processFile(Path filePath) {
        System.out.println("Processing file..");
        List<Question> questions = QuestionsDeserializer.readQuestions(filePath);
        if (questions == null)
            return;
        QuestionsStore questionsStore = QuestionsStore.getInstance();
        questions.forEach(questionsStore::addQuestion);
    }
}