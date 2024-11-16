package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.example.models.Question;
import org.example.store.QuestionsStore;

@RequiredArgsConstructor
@Log
public class QuestionService {
    private final QuestionsStore questionsStore;

    public Question addQuestion(String content){
        Question question = new Question(content);
        return questionsStore.addQuestion(question);
    }
    public Question getQuestion(int id){
        try
        {
            return questionsStore.getQuestion(id);
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
}
