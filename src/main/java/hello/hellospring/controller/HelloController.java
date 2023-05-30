package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 1. 정적 컨텐트 : 파일을 그대로 내려줌
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "test/hello"; // templates에 있는 html 이름
    }

    // 2. mvc와 템플릿 엔진 : 템플릿엔진을 모델/뷰/컨트롤러로 쪼개서 뷰를 렌더링된 html로 내려준다
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * api 방식에는 ResponseBody 어노테이션 사용
     * HTTP BODY에 문자 내용을 직접 반환
     * viewResolver 대신 HttpMessageConverter가 동작
     */
    // 3.1 api - string
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 3.2 api - object : json 반환이 default , 얫날 프로젝트 같은 경우는 XML로 되어있긴함
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
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
