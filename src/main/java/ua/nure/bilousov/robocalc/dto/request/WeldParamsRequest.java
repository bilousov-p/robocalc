package ua.nure.bilousov.robocalc.dto.request;

import lombok.Data;

@Data
public class WeldParamsRequest {

    private Double voltage;

    private Double secondCoilVoltage;

    private Double amperage;

    private Double currentDensity;
}
