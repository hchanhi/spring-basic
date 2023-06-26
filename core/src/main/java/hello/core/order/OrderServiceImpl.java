package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    // 할인 정책을 변경 하려면 FixDiscountPolict 를 RateDiscountPolicy로 변경 해야함
    // OrderServiceImpl 이 DiscountPolicy interface 만 의존하지 않고 구현체 클래스 까지 의존하고 있는 문제점 DIP 위반!
    // 구현체 클래스를 변경하는 순간 orderServiceImpl의 소스코드도 변경되므로 OCP 위반!
    // ==>>> 추상(인터페이스)에만 의존하도록 변경해야 한다!!

    // As is
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // To be
    private DiscountPolicy discountPolicy;


    // 철저하게 인터페이스에만 의존
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
