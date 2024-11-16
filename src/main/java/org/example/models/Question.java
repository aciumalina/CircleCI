package org.example.models;

import lombok.Data;

@Data
public class Question {
    private final String content;
    private int upVotes;
    private int downVotes;

}
