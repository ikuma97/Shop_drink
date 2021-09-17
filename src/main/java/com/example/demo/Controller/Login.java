package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Login {
    @GetMapping("/dang-nhap")
    public String login(HttpServletRequest req){
        return "ff";
    }
    @GetMapping("/acess-deny")
    public String accessDeny(HttpServletRequest req){
        return "/acess-deny";
    }
}
