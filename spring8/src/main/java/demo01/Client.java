package demo01;

public class Client {
    /*

    代理模式好处
        1. 可以让正式角色ao啊哦做更加纯粹，不用关注一个公共的业务
        2. 公共业务交给了代理角色，实现了业务的分工
        3. 公共业务发生扩展的时候，方便集中管理
    */
    public static void main(String[] args) {
        Host host = new Host();
        //代理租房，中介帮房东租房，但是会有附加操作
        Proxy proxy = new Proxy(host);
        //不同面对房东，直接找中介
        proxy.rent();
    }
}
