package pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

public class Person {
    /*
    自动装配@Autowired
    也可以写在get方法上，写在变量上时可以不书写set方法
    使用Autowired可以不写set，前提是属性在IOC容器中存在，且符合名字（byName）

    如果显示定义required = false，说明这个对象属性可以为null,否则不允许为空

    如果有多个类型，可以使用@Qualifier(value = "cat1")，显式指定对象

    可以使用@Resource自动装配，当bean不唯一时，可以使用name属性

    @Resource和 @Autowired的区别
        都是放在属性上的
        @Autowired使用byName实现
        @Resource 默认byName，找不到变byType
    */
    @Autowired(required = false)
    @Qualifier(value = "cat1")
    private Cat cat;
    @Resource
    private Dog dog;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "cat=" + cat +
                ", dog=" + dog +
                ", name='" + name + '\'' +
                '}';
    }
}
