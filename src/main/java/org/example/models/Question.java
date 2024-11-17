package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class Question {
    private final String content;

    private UUID userId;
    @JsonCreator
    public Question(
            @JsonProperty("content") String content,
            @JsonProperty("userId") UUID userId
    ) {
        this.content = content;
        this.userId = userId;
    }
}
