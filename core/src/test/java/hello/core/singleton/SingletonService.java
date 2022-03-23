package hello.core.singleton;

// 03.23 : 싱글톤 패턴
public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성해둔다.
    // JVM이 뜨는 시점에, static + 우측 new 명령어를 보고 객체를 바로 생성한다.
    private static final SingletonService instanse = new SingletonService();

    // 2. public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    // (+ getInstance를 통해 instanse 변수에 자기자신을 넣어놓는다.)
    public static SingletonService getInstance(){
        return instanse;
    }

    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 로직 객체호출");
    }

}
