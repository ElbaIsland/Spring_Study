package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

// 03.23 BeanDefinition 살펴보기
public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefName);

            // 다 출력하면 양이 너무 많기 때문에, ROLE_APPLICATION만 출력하도록 할 것
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName" + beanDefName +
                        ", beanDefinition = " + beanDefinition);
            }
        }
        
    }

}
