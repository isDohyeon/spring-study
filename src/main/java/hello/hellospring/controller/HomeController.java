package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈 화면 URL 요청에 대한 home.html 반환
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
