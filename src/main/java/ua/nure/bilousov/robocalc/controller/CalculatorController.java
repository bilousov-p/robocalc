package ua.nure.bilousov.robocalc.controller;

import org.springframework.web.bind.annotation.*;
import ua.nure.bilousov.robocalc.dto.request.InputParamsRequest;

@RestController
@CrossOrigin
@RequestMapping("/calc")
public class CalculatorController {

    @PostMapping("/calculateParams")
    public String calculateParams(@RequestBody InputParamsRequest paramsRequest){
        System.out.println(paramsRequest);
        return "Hello";
    }
}
