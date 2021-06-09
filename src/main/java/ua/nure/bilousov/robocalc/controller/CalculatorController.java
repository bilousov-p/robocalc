package ua.nure.bilousov.robocalc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;
import ua.nure.bilousov.robocalc.model.input.InputParams;
import ua.nure.bilousov.robocalc.service.CalculatorService;

import java.util.List;

import static ua.nure.bilousov.robocalc.constant.ControllerConstants.*;


@RestController
@CrossOrigin
@RequestMapping(CALC_CONTROLLER_URL)
@RequiredArgsConstructor
public class CalculatorController {

    private final CalculatorService calculatorService;

    // TODO: Do not create InputWeldParams if null
    @PostMapping(CALCULATE_PARAMS)
    public CalculatedParams calculateParams(@RequestBody InputParams paramsRequest){
       return calculatorService.calculateParameters(paramsRequest);
    }

    @GetMapping(GET_ALL)
    public List<CalculatedParams> getAllCalculations(){
        return calculatorService.getAllCalculations();
    }

    @GetMapping(GET_BY_ID)
    public CalculatedParams getCalculationById(@PathVariable Long id){
        return calculatorService.getCalculationsById(id);
    }

    @DeleteMapping(DELETE_BY_ID)
    public void deleteCalculationById(@PathVariable Long id){
        calculatorService.deleteCalculationById(id);
    }
}
