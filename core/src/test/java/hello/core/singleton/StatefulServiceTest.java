package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("잘못된 방식")
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // 쓰레드 A : A유저가 만원 주문
        statefulService1.worngWayOrder("userA", 10000);
        // 쓰레드 B : B유저가 삼만원 주문
        statefulService2.worngWayOrder("userA", 30000);

        // 쓰레드 A : A유저가 주문금액 조회....어라?
        int priceofA = statefulService1.getPrice();
        System.out.println("priceofA = " + priceofA); // 3만원 나옴...
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(30000);

    }

    @Test
    @DisplayName("제대로 된 방식")
    void statelessServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // 쓰레드 A : A유저가 만원 주문
        int priceofA = statefulService1.rightWayOrder("userA", 10000);
        // 쓰레드 B : B유저가 삼만원 주문
        int priceofB = statefulService2.rightWayOrder("userB", 30000);

        System.out.println("priceofA = " + priceofA); // 제대로 나옴...
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}