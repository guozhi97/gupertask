import org.junit.Test;

import static org.junit.Assert.*;

public class TeacherTest {
    @Test
    public void test(){
        GPer gPer = GPer.getInstance();

        Question question = new Question("tony","能讲的慢一点吗？");

        Teacher teacher = new Teacher("tom");
        Teacher teacher2 = new Teacher("rsa");

        gPer.addObserver(teacher);
        gPer.addObserver(teacher2);

        gPer.send(question);

    }

}