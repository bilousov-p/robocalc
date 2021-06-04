package ua.nure.bilousov.robocalc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;
import ua.nure.bilousov.robocalc.model.input.InputParams;
import ua.nure.bilousov.robocalc.service.CalculatorService;

import java.util.List;

// TODO: Move ulrs to ControllerConstants
@RestController
@CrossOrigin
@RequestMapping("/calc")
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    // TODO: Do not create InputWeldParams if null
    @PostMapping("/calculateParams")
    public CalculatedParams calculateParams(@RequestBody InputParams paramsRequest){
       return calculatorService.calculateParameters(paramsRequest);
    }

    @GetMapping("/getAll")
    public List<CalculatedParams> getAllCalculations(){
        return calculatorService.getAllCalculations();
    }

    @GetMapping("/getById/{id}")
    public CalculatedParams getCalculationById(@PathVariable Long id){
        return calculatorService.getCalculationsById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCalculationById(@PathVariable Long id){
        calculatorService.deleteCalculationById(id);
    }
}
