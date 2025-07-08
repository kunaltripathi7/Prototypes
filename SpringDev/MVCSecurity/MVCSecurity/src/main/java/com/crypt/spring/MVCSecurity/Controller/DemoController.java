package com.crypt.spring.MVCSecurity.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/")
    public String getHome() {
        return "home";
    }


    @GetMapping("/leaders")
    public String getLeaders() {
        return "invite-page";
    }
}
