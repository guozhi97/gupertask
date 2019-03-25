import org.junit.Test;

import static org.junit.Assert.*;

public class JDKMeipoTest {
    @Test
    public void testMeipo(){
        Person son = (Person)new JDKMeipo().getInstance(new Son("xiaoming",20));
        son.findLove();

    }
}