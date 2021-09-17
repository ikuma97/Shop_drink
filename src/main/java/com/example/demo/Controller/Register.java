package com.example.demo.Controller;

import com.example.demo.Entity.PerSon;
import com.example.demo.PerSonDto.PersonDTO;
import com.example.demo.Service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class Register {
    @Autowired
    UserSevice userSevice;
    @GetMapping("/register")
    public String register( Model model){
        model.addAttribute("register",new PerSon());
        return "register";
    }
    @PostMapping("/register")
    public String saveRegister(PersonDTO perSon){
        userSevice.save(perSon);
        return "thanhcong";
    }
}
