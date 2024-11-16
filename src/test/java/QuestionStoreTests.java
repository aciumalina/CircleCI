import org.example.models.Question;
import org.example.store.QuestionsStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class QuestionStoreTests {

    private QuestionsStore sut;
    @Before
    public void initialize(){
        sut = new QuestionsStore();
        sut.questions = new HashMap<>();
    }
    @Test
    public void ShouldAddQuestion(){
        Question question = createQuestion("mockMessage");
        Question result = sut.addQuestion(question);
        Assert.assertSame(result.getContent(), "mockMessage");
        Assert.assertSame(sut.questions.size(), 1);
    }

    private Question createQuestion(String message){
        return new Question(message);
    }
}
