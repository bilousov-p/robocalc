package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;

@Data
public class WeldParams {

    private Double transformerPower;

    private Integer numberOfCoils;

    private Double firstWireSection;

    private Double secondWireSection;
}
