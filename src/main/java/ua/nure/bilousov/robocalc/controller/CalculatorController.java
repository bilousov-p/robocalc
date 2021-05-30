package ua.nure.bilousov.robocalc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class CalculatorController {

    @GetMapping("/get")
    public String getParams(){
        return "Hello";
    }
}
