package demo01;

public class Proxy implements Rent {
    private Host host;

    public Proxy() {
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        heTong();
        fare();
    }

    public void seeHouse() {
        System.out.println("中介带用户看房");
    }

    //收中介费
    public void fare() {
        System.out.println("收中介费");
    }

    //签租赁合同
    public void heTong() {
        System.out.println("签合同");
    }
}
