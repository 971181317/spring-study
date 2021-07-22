package pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//等价于<bean id="" class=""></bean>
//@Component 组件，放在类上，表明该类被spring管理，就是bean
@Component
public class User {

    //@Value赋值，可以注解在set方法上
    @Value("dxy")
    public String name;
}
