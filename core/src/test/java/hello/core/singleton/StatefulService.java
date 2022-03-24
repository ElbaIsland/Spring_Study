package hello.core.singleton;

// 03.24 : 싱글톤 방식의 문제점 => 상태유지(statuful)(=공유 값, 특정 클라의 값 변경 등..) 조심해야!
public class StatefulService {

    private int price;

    // 스프링빈 잘못사용
    public void worngWayOrder(String name, int price){
        System.out.println("name " + name + ", price : " + price);
        this.price = price; // 여기가 문제 (의도 : 주문했을때 값을 price변수에 넣고 나중에 꺼내려고 코딩)
    }
    public int getPrice(){
        return price;
    }

    // 스프링빈 올바르게 사용 by 지역변수!
    public int rightWayOrder(String name, int prices){
        System.out.println("name " + name + ", price : " + prices);
    //    this.price = price;
        return prices;
    }
    


}
