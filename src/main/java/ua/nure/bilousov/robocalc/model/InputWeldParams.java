package ua.nure.bilousov.robocalc.model;

import lombok.Data;

@Data
public class InputWeldParams {

    private Double voltage;

    private Double secondCoilVoltage;

    private Double amperage;

    private Double currentDensity;
}
