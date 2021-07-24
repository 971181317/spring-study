package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import pojo.User;

//@Configuration也会被注册到spring容器中，也是一个@Component
//等价于xml
@Configuration
//扫描包
@ComponentScan("pojo")
//导入其他的配置
@Import(MyConfig.class)
public class MyConfig2 {
    //注册一个bean，相当于<bean></bean>
    //方法的名字相当于id
    //返回值相当于class
    @Bean
    public User getUser() {
        return new User();
    }
}
