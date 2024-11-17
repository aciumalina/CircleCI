import org.example.models.Question;
import org.example.store.QuestionsStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;


public class QuestionStoreTests {

    private QuestionsStore sut;
    @Before
    public void initialize(){
        sut = QuestionsStore.getInstance();
        sut.getQuestions().put(0, new Question("some question", UUID.randomUUID()));
        QuestionsStore.setId(1);
    }
    @Test
    public void ShouldAddQuestion(){
        Question question = createQuestion("mockMessage");
        Question result = sut.addQuestion(question);
        Assert.assertSame(result.getContent(), "mockMessage");
        Assert.assertSame(sut.getQuestions().size(), 2);
    }

    @Test
    public void WhenQuestionIdDoesNotExist_ShouldThrow(){
        Exception exception = Assert.assertThrows(RuntimeException.class, () -> sut.getQuestion(2));
        Assert.assertTrue(exception.getMessage().contains("Question ID was not present in the store"));
    }

    private Question createQuestion(String message){
        return new Question(message, UUID.randomUUID());
    }
}
