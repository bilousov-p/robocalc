package ua.nure.bilousov.robocalc.model.input;

import lombok.Data;

@Data
public class InputWeldParams {

    private Double voltage;

    private Double secondCoilVoltage;

    private Double amperage;

    private Double currentDensity;

    private Double coreSection;

    private Double coreWindowSection;
}
