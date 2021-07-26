package demo02;

public class Client {
    public static void main(String[] args) {
        UserService userService = new UserService();
        UserServiceProxy proxy = new UserServiceProxy();
        proxy.setUserService(userService);

        proxy.add();
    }
}
