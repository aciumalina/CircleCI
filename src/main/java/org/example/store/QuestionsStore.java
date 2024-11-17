package org.example.store;

import org.example.models.Question;

import java.util.HashMap;

/**
 * Singleton class that stores questions.
 */

public final class QuestionsStore {

    /**
     * Sets the unique ID for questions.
     *
     * @param givenId the ID to set
     */

    public static void setId(final int givenId) {
        QuestionsStore.id = givenId;
    }

    /**
     * Stores the questions mapped by their unique IDs.
     */
    private final HashMap<Integer, Question> questions;

    /**
     * The unique ID generator for questions.
     */
    private static int id = 0;

    /**
     * The singleton instance of the QuestionsStore.
     */
    private static QuestionsStore singleInstance = null;

    /**
     * Private constructor to enforce singleton pattern.
     *
     * @param questionsAsked the initial map of questions
     */
    private QuestionsStore(final HashMap<Integer, Question> questionsAsked) {
        this.questions = questionsAsked;
    }

    /**
     * Returns the singleton instance of the QuestionsStore.
     *
     * @return the singleton instance
     */
    public static synchronized QuestionsStore getInstance() {
        if (singleInstance == null) {
            singleInstance = new QuestionsStore(new HashMap<>());
        }
        return singleInstance;
    }

    /**
     * Adds a new question to the store.
     *
     * @param question the question to be added
     * @return the added question
     */
    public Question addQuestion(final Question question) {
        questions.put(++id, question);
        return question;
    }

    /**
     * Retrieves a question by its ID.
     *
     * @param questionId the ID of the question to retrieve
     * @return the question associated with the given ID
     * @throws RuntimeException if the question is not found
     */
    public Question getQuestion(final int questionId) {
        Question question = questions.get(questionId);
        if (question != null) {
            return question;
        }
        throw new RuntimeException("Question ID was not present in the store");
    }

    /**
     * Gets the list of all questions in the store.
     *
     * @return the list of questions
     */
    public HashMap<Integer, Question> getQuestions() {
        return questions;
    }

    /**
     * Gets the current unique ID.
     *
     * @return the current unique ID
     */
    public static int getId() {
        return id;
    }
}
