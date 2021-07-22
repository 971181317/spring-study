import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Person;

public class MyTest {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person);
        person.getCat().shout();
        person.getDog().shout();
    }
}
