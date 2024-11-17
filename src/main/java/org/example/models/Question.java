package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

/**
 * Represents a question with content and a user ID.
 */
@Data
public class Question {

    /**
     * The content of the question.
     */
    private final String content;

    /**
     * The ID of the user who asked the question.
     */
    private UUID userId;

    /**
     * Creates a new Question instance.
     *
     * @param questionContent the content of the question
     * @param questionUserId the ID of the user who asked the question
     */
    @JsonCreator
    public Question(
            @JsonProperty("content") final String questionContent,
            @JsonProperty("userId") final UUID questionUserId
    ) {
        this.content = questionContent;
        this.userId = questionUserId;
    }
}
