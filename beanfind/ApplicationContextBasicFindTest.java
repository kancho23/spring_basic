package hello.core.beanfind;

/*public class로 안해도됨 (junit이므로)*/

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    /* 주목하자. 인터페이스로 구현하는 방식이다.
     * 이렇게되면 인터페이스 구현체가 대상이된다.
     *
     * 그 결과는 이렇게 나옴
     * memberService = hello.core.member.MemberServiceImpl@52d645b1
     * memberService.getClass() = class hello.core.member.MemberServiceImpl
     */

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        /*검증할때는 Assertion 사용함*/
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    /* 결과는 이렇게 나옴
     * memberService = hello.core.member.MemberServiceImpl@52d645b1
     * memberService.getClass() = class hello.core.member.MemberServiceImpl
     */
    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        /*검증할때는 Assertion 사용함*/
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

        /*이렇게 타입으로만 할 경우, '같은 타입'일 경우 문제가 좀 복잡해짐짐*/
    }

    /* 주목하자. 이건 '인터페이스'가아닌 '구체타입으로 조회'하는 방식이다.*/
    /* 우리는 '역할'과 '구현'에서, '역할'에 의존해야한다. 하지만, 이 코든느 구현에 의존하고 있다. 즉, 좋은코드는 아님*/
    @Test
    @DisplayName("구체타입으로 조회")
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());

        /*검증할때는 Assertion 사용함*/
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    /*실패 테스트를 확인해보자*/
    @Test
    @DisplayName("빈 이름으로 조회, 실패테스트")
    void findBeanByNameX(){
        /*이 로직은 예외가 터져서, 실패가 떠야하는 케이스*/
        // MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);

        /* assertThrow + NosuchBeanDefinitionException을 쓰면, 항상 '예외가 터져야'되는 케이스를 말함
        * 예외가 터져야 성공함
        * */
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
    }

}
