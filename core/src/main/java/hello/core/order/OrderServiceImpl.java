package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  //  고정할인
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();  //  변동할인
//  ====> 위 세개 다 DIP 위반(현재 클라이언트가 인터페이스 & 구현체 둘 다 의존) + OCP 위반(변경하지 않고 확장 불가, 기능변경시 현재 클라에 영항)

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
     03.15 수정 => 위의 (주석 처리된) 잘못된 방식을 개선하여, 생정자 주입을 통해 DIP 개선
     */
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 1) 먼저 주문한 사용자정보를 불러온다
        Member member = memberRepository.findById(memberId);

        // 2) 할인정책이 들어간 최종 가격을 불러온다.
        // **이부분을 통해, 설계가 잘된 로직이라는 것을 이해할 수 있다. 단일체계원칙을 잘 지킨 상태.
        // **할인정책이 어떻게 들어가는지 알 필요 없이, 그저 가격을 설정해주기만 하면 된다!
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice); // 3) 최종생성된 주문 반환
    }
}
