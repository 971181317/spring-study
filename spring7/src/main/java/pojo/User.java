package pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//注册到spring容器中
@Component
public class User {
    @Value("dxy")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
