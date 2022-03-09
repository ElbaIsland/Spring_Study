package hello.hellospring.aop;

// 03.10 AOP : 메소드별 특정 로직 집어넣어야 하는 상황 가정...

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component  //  2-2) Component로 연계
public class TimeTraceAop {

    // 1) 시간 측정용 공통 메소드 Make, 이것을 공통 Spring Bean에 등록하러 ㄱㄱㄱ >> (SpringConfig)
    @Around("execution(* hello.hellospring..*(..)) ") // 어느 메소드에 이 메소드를 적용할건지 @Around로 세팅
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());

        try {
            Object result = joinPoint.proceed(); //  다음메소드로 진행하는 명령어
            return result;
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString()+ ", " + timeMs + "ms");
        }
    }
}
