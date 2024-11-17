package org.example.store;

import org.example.models.Question;

import java.util.HashMap;

public class QuestionsStore {
    public HashMap<Integer, Question> questions;

    public static int id = 0;

    private static QuestionsStore single_instance = null;

    private QuestionsStore(HashMap<Integer, Question> questions) {
        this.questions = questions;
    }

    public static synchronized QuestionsStore getInstance()
    {
        if (single_instance == null)
            single_instance = new QuestionsStore(new HashMap<>());

        return single_instance;
    }

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
