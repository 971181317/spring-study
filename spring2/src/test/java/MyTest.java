import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.Hello;

public class MyTest {
    public static void main(String[] args) {
        //获取spring上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Hello hello = (Hello) context.getBean("hello");
        hello.show();
        Hello hello2 = (Hello) context.getBean("hello2");
        hello2.show();
        Hello hello3 = (Hello) context.getBean("hello3");
        hello3.show();
    }
}
