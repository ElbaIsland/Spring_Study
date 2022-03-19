package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 03.19 : 스프링 컨테이너에 등록된 빈 조회방법
public class ApplicationContextInfoTest {

    // 1) 스프링 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 단축키 1) iter : for문 자동 생성
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);

            //  단축키 2) soutv : 변수명까지 sysout | soutm : 메소드명까지 sysout
            System.out.println("name = " + beanDefinitionName + ", obj = " + bean);
        }
    }

    // 단축키 3) ctrl + d : 내가 영역 잡은 부분 전체 복사
    @Test
    @DisplayName("(내가만든)애플리케이션의 빈만 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);    //  bean의 "정보"를 출력하는 명령어

            // Role ROLE_APPLICATION : 스프링 내부에서 만드는 bean이 아닌, 내가 애플리케이션 개발을 위해 등록한 빈 or 외부 라이브러리
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", obj = " + bean);
            }

        }
    }

}
