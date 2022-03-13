package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();



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
