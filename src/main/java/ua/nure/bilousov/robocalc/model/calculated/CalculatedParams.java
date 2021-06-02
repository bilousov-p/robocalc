package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;

@Data
public class CalculatedParams {

    private Integer numberOfFreedoms;

    private ManipulatorParams manipulatorParams;

    private EngineParams engineParams;

    private WeldParams weldParams;
}
