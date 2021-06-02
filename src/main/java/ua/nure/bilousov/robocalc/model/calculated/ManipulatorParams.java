package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;

@Data
public class ManipulatorParams {

    private Integer numberOfChains;

    private Integer numberOfSteadyLinks;

    private Integer numberOfAngleLinks;

    private Double firstChainSize;

    private Double secondChainSize;

    private Double thirdChainSize;
}
