package org.example.deserializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Question;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Utility class for deserializing questions from JSON files.
 */
public final class QuestionsDeserializer {

    // Private constructor to prevent instantiation
    private QuestionsDeserializer() {
        // Utility class; prevent instantiation.
    }

    /**
     * Reads a list of questions from a JSON file located at the specified path.
     *
     * @param filePath the path to the JSON file containing the questions
     * @return a list of deserialized questions, or null if an error occurs
     */
    public static List<Question> readQuestions(final Path filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    filePath.toFile(),
                    new TypeReference<>() {
                    }
            );
        } catch (IOException e) {
            System.err.println("Error reading or parsing the JSON file: "
                    + e.getMessage());
        }
        return null;
    }
}
