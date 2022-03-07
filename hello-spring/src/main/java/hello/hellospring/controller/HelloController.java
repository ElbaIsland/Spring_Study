package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 1) 스프링 웹 개발 기초 : 정적 컨텐츠
    @GetMapping("hello")    //  1. web에서 /hello로 들어올시 자동으로 매핑해서 아래 메소드를 호출
    public String hello(Model model){
        model.addAttribute("data", "hello spring");    //  hello.html의 data
        return "hello"; // 2. resources > templates > hello.html을 찾아 랜더링한다.
    }

    // 2) 스프링 웹 개발 기초 : mvc & 템플릿 엔진()
    // * model을 통해 view 랜더링한다는 것 기억하자!
    // ** RequestParam을 통해, 값을 받아야지만 제대로된 화면을 보여준다. url에 ?var='변수명' 입력 기억하자.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("var") String variable, Model model){
        model.addAttribute("var", variable);
        return "hello-mvc process";
    }

    // 3-1) 스프링 웹 개발 기초 : api(객체 반환)
    @GetMapping("hello-string")
    @ResponseBody() //  이전까지 html영역에서 직접 넣어주었던 body 영역을 "직접" 넣어주겠다는 뜻!
    public String helloString(@RequestParam("name") String name){
        return "Hello" + name;  //  웹상에서 Hello + name 조합된 문장이 뜰 것!
    }

    // 3-2) 스프링 웹 개발 기초 : api(객체 반환)
    // JSON 방식, KET인 NAME과 VALUE인 사용자 입력값으로 URL에서 출력된다.
    @GetMapping("hello-api")
    @ResponseBody() //  이전까지 html영역에서 직접 넣어주었던 body 영역을 "직접" 넣어주겠다는 뜻!
    public Hello helloAPI(@RequestParam("name") String name){
        Hello hello = new Hello();  //  아래 static class인 hello를 가져온다.
        hello.setName(name);    //  위의 name을 받아서 set
        return hello;
    }

    static class Hello{
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

}
