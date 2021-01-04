package com.everyportfolio.api_gateway.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class ViewController {

    @GetMapping("/")
    @ResponseBody
    public HashMap<String, Object> mainView() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Hi", "This's for main View. it will be added html file later");

        return result;
    }
}
