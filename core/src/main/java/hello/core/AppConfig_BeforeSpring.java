package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 실제 애플리케이션의 동작에 필요한 "구현 객체를 생성" 한다!
// AppConfig 는 생성한 객체 인스턴스의 참조(레퍼런스)를 "생성자를 통해서 주입(연결)" 한다.
// 객체의 생성과 연결은 AppConfig가 담당
// DIP 완성!
public class AppConfig_BeforeSpring {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
