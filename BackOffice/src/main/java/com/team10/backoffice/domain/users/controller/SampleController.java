package com.team10.backoffice.domain.users.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleController {

    @GetMapping("/member/login")
    public String login() {
        return "member/login";
    }

}
