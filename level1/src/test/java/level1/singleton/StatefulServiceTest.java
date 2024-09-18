package level1.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        // statefulService1 과 statefulService2 는 모두
        // 스프링 빈으로 등록된 StatefulService 의 같은 객체를 참조하게 됨
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // 참조값 확인 -> 같은 주소값이 출력된
        System.out.println("statefulService1 = " + statefulService1);
        System.out.println("statefulService2 = " + statefulService2);

        // 다른 객체를 생성해서 다른 주문을 했다고 생각하지만
        // 같은 객체를 참조하기 때문에 주문이 덮어씌워짐
        statefulService1.order("userA", 10000);
        statefulService2.order("userB", 20000);

        // statefulService1의 10000이 반환되었을 것으로 예상
        // 하지만 20000이 반환됨
        int price = statefulService1.getPrice();

        // 10000을 기대했지만 실제 값 = 20000으로 테스트 실패
        Assertions.assertThat(price).isEqualTo(10000);
    }

    private static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}