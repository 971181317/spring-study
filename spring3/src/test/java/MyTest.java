import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Student;
import pojo.User;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        User user = context.getBean("user2", User.class);
        System.out.println(user);
    }

    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        User userSingleton1 = context.getBean("userSingleton", User.class);
        User userSingleton2 = context.getBean("userSingleton", User.class);
        System.out.println(userSingleton1 == userSingleton2);
        User userPrototype1 = context.getBean("userPrototype", User.class);
        User userPrototype2 = context.getBean("userPrototype", User.class);
        System.out.println(userPrototype1 == userPrototype2);
    }
}
