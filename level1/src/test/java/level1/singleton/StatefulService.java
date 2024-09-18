package level1.singleton;

public class StatefulService {

    // 이와 같이 상태를 유지하는 필드가 있으면 안됨.
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name);
        System.out.println("price = " + price);
        // 상태를 저장하면 안됨
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
