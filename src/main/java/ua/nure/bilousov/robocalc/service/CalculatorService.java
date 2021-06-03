package ua.nure.bilousov.robocalc.service;

import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;
import ua.nure.bilousov.robocalc.model.input.InputParams;

import java.util.List;


public interface CalculatorService {

    CalculatedParams calculateParameters(InputParams inputParams);

    List<CalculatedParams> getAllCalculations();

    CalculatedParams getCalculationsById(Long id);
}
