# spring学习

## 1. spring

### 1.1 配置

* xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!--import 导入其他的xml-->
    <import resource="bean.xml"/>
    <!--配置注解支持-->
    <context:annotation-config/>
    <!--指定要扫描的包，这个包下的注解就会生效，空格分隔-->
    <context:component-scan base-package="pojo service dao controller"/>
</beans>
```

* JavaConfig

```java
//@Configuration也会被注册到spring容器中，也是一个@Component
//等价于xml
@Configuration
//扫描包
@Component("pojo")
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
```

### 1.2 获取上下文

* xml

```java
//xml获取
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
```

* 注解

```java
ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
```

### 1.3 获取对象

> 通过唯一的id值获取

```java
//直接获取
Hello hello = (Hello) context.getBean("hello");
//通过字节码反射获取，不需要强转
Hello hello = context.getBean("hello", Hello.class);
```

## 2. IOC(控制反转)

### 2.1 DI(依赖注入)

#### 2.1.1 xml

`<bean>`标签

* `id`:唯一标识符
* `class`:对应的类
* `name`：别名，比alias标签好用，可以起多个别名

```xml
<bean id="hello" class="pojo.Hello" name="hello2, hello3"></bean>
```

1. 参数赋值

   **默认使用空参构造，如果有带参构造方法，则必须书写空参构造方法**

   **必须写set方法，底层使用set方法注入**

   `<property>`：对应类的属性

   * `name`：属性名称
   * `value`：注入属性值
   * `ref`：注入bean，值为其他bean的id

   > 普通值 | bean | arr | list | map | set | null | properties

   ```xml
   <bean id="student" class="pojo.Student">
           <!--普通值注入-->
           <property name="name" value="dxy"/>
           <!--bean注入-->
           <property name="address" ref="address"/>
           <!--数组注入-->
           <property name="books">
               <array>
                   <value>书1</value>
                   <value>书2</value>
                   <value>书3</value>
               </array>
           </property>
           <!--list注入-->
           <property name="hobbys">
               <list>
                   <value>hobby1</value>
                   <value>hobby2</value>
                   <value>hobby3</value>
               </list>
           </property>
           <!--map注入-->
           <property name="card">
               <map>
                   <entry key="k1" value="v1"/>
                   <entry key="k2" value="v2"/>
               </map>
           </property>
           <!--set注入-->
           <property name="game">
               <set>
                   <value>game1</value>
                   <value>game2</value>
               </set>
           </property>
           <!--null注入-->
           <property name="wife">
               <null/>
           </property>
           <!--Properties-->
           <property name="info">
               <props>
                   <prop key="p1">v1</prop>
                   <prop key="p2">v2</prop>
               </props>
           </property>
       </bean>
   ```

   ```java
   public class Student {
   
       private String name;
       private Address address;
       private String[] books;
       private List<String> hobbys;
       private Map<String, String> card;
       private Set<String> game;
       private String wife;
       private Properties info;
   
       ......
   }
   ```

2. 构造器赋值

   `<constructor-arg>`：会匹配构造方法

   ```java
   public class Hello {
       private String name;
       ......
   }
   ```

   > 类型赋值，不建议使用，相同属性会出错

   * `type`：构造参数的类型
   * `value`：参数的值

    ```xml
    <bean id="hello" class="pojo.Hello">
        <constructor-arg type="java.lang.String" value="类型赋值"/>
    </bean>
    ```

   > 下标赋值

   * `index`：构造参数的索引

   ```xml
   <bean id="hello" class="pojo.Hello">
       <constructor-arg index="0" value="下标赋值"/>
   </bean>
   ```

   > 直接通过参数名赋值

   * `name`：参数名称

   ```xml
   <bean id="hello" class="pojo.Hello">
       <constructor-arg name="name" value="参数名赋值"/>
   </bean>
   ```

3. 自动装配`autowire`

   > byName

   **会在容器上下文中查找，和自己对象set方法后面的值对应的bean id**

   **需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法相同**

   ```xml
   <bean class="pojo.Dog"/>
   <bean class="pojo.Cat"/>
   <bean id="person" class="pojo.Person" autowire="byType">
       <property name="name" value="名字"/>
   </bean>
   ```

   ```java
   public class Person {
       private Cat cat;
       private Dog dog;
       private String name;
       ......
   }
   ```

   > byType

   **通过类型寻找，但只允许上下文有唯一的一个类型bean，bean可以省略id**

   **需要保证所有bean的class唯一，并且这个bean需要和自动注入的属性的类型相同**

   ```xml
   <bean id="dog" class="pojo.Dog"/>
   <bean id="cat" class="pojo.Cat"/>
   <bean id="person" class="pojo.Person" autowire="byName">
       <property name="name" value="名字"/>
   </bean>
   ```

4. 作用域`scope`

   `singleton`:单例，默认，获取的bean永远是一个

   `prototype`:原形模式，每次获取的bean为新创建的

   ```xml
   <bean id="userSingleton" class="pojo.User" scope="singleton"/>
   <bean id="userPrototype" class="pojo.User" scope="prototype"/>
   ```

5. 命名空间注入

   导入依赖

   ```xml
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:p="http://www.springframework.org/schema/p"
          xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
   </beans>
   ```

   > p命名空间注入，可以直接注入属性

   ```xml
   <bean id="user" class="pojo.User" p:name="名字" p:age="19"/>
   ```

   > c命名空间注入，通过构造器注入

   ```xml
   <bean id="user2" class="pojo.User" c:name="参数构造" c:age="15"/>
   ```

#### 2.1.2 注解

注解配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
    <!--配置注解支持-->
    <context:annotation-config/>
    <!--指定要扫描的包，这个包下的注解就会生效-->
    <context:component-scan base-package="pojo service dao controller"/>
</beans>
```

1. ``@Autowired``自动装配

   可以写在get方法上，写在变量上时可以不书写set方法

   使用`Autowired`可以不写set，前提是属性在IOC容器中存在，且符合名字（byName）

   如果显示定义`required = false`，说明这个对象属性可以为`null`,否则不允许为空

   如果有多个类型，可以使用`@Qualifier(value = "cat1")`，显式指定对象

   可以使用`@Resource`自动装配，当bean不唯一时，可以使用name属性

   * @Resource和 @Autowired的区别
     * 都是放在属性上的
     * `@Autowired`使用byName实现
     * `@Resource` 默认byName，找不到变byType

   ```java
   public class Person {
      @Autowired(required = false)
      @Qualifier(value = "cat1")
      private Cat cat;
      @Resource
      private Dog dog;
      private String name;
      ......
   }
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           http://www.springframework.org/schema/context/spring-context.xsd">
   <!--    配置注解支持-->
       <context:annotation-config/>
   
       <bean id="dog" class="pojo.Dog"/>
       <bean id="cat1" class="pojo.Cat"/>
       <bean id="cat2" class="pojo.Cat"/>
       <bean id="person" class="pojo.Person">
       </bean>
   </beans>
   ```

2. bean定义

   `@Component`

   * 等价于`<bean id="" class=""></bean>`
   * `@Component`---组件，放在类上，表明该类被spring管理，就是bean
   * `@Controller`: `controller`层的Component
   * `@Repository`: `dao`层的Component
   * `@Service`: `service`层的Component

   `@Scope`

   * 作用域，单例：singleton，原形：prototype

   ``@Value``

   * 赋值，可以注解在set方法上

   ```java
   @Component
   @Scope("singleton")
   public class User {
       @Value("dxy")
       public String name;
   }
   
   @Controller
   public class UserController {
   }
   
   @Repository
   public class UserDao {
   }
   
   @Service
   public class UserService {
   }
   ```
