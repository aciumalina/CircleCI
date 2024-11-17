package org.example.deserializers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.Question;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class QuestionsDeserializer {
    public static List<Question> readQuestions(Path filePath){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    filePath.toFile(),
                    new TypeReference<>() {
                    }
            );

        } catch (IOException e) {
            System.err.println("Error reading or parsing the JSON file: " + e.getMessage());
        }
        return null;
    }
}
