package ua.nure.bilousov.robocalc.service;

import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;
import ua.nure.bilousov.robocalc.model.input.InputParams;


public interface CalculatorService {

    CalculatedParams calculateParameters(InputParams inputParams);
}
