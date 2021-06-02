package ua.nure.bilousov.robocalc.controller;

import org.springframework.web.bind.annotation.*;
import ua.nure.bilousov.robocalc.model.InputParams;

@RestController
@CrossOrigin
@RequestMapping("/calc")
public class CalculatorController {

    @PostMapping("/calculateParams")
    public String calculateParams(@RequestBody InputParams paramsRequest){
        System.out.println(paramsRequest);
        return "Hello";
    }
}
