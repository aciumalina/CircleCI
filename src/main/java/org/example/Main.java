package org.example;

import org.example.deserializers.QuestionsDeserializer;
import org.example.models.Question;
import org.example.store.QuestionsStore;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

/**
 * The main class that watches for new files and processes them.
 */
public final class Main {

    private Main() {
        //private constructor to avoid initialization
    }

    /**
     * The main method that starts the file-watching service.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(final String[] args) {
        Path watchDir = Paths.get("input");
        try {
            Files.createDirectories(watchDir);
            WatchService watchService = FileSystems.getDefault()
                    .newWatchService();
            watchDir.register(watchService, ENTRY_CREATE);

            System.out.println("Watching directory: "
                    + watchDir.toAbsolutePath());

            while (true) {
                WatchKey key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() != ENTRY_CREATE) {
                        continue;
                    }

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
            System.err.println("Error: "
                    + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Processes a file by reading and storing questions.
     *
     * @param filePath the path of the file to be processed
     */
    private static void processFile(final Path filePath) {
        System.out.println("Processing file..");
        List<Question> questions = QuestionsDeserializer
                .readQuestions(filePath);
        if (questions == null) {
            return;
        }
        QuestionsStore questionsStore = QuestionsStore.getInstance();
        questions.forEach(questionsStore::addQuestion);
    }
}
