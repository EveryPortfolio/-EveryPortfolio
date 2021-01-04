package com.everyportfolio.user.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class MainController {
    @GetMapping("/hello")
    public HashMap<String, Object> hello() {
        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("user-name", "jyj");

        return parameter;
    }
}
