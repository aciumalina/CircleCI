package org.example.store;

import org.example.models.Question;

import java.util.HashMap;

public class QuestionsStore {
    public HashMap<Integer, Question> questions = new HashMap<>();

    public static int id = 0;

    public Question addQuestion(Question question){
        questions.put(++id,  question);
        return question;
    }

    public Question getQuestion(int id){
        Question question = questions.get(id);
        if (question != null)
            return question;
        throw new RuntimeException("Question id was not present in the store");
    }
}
