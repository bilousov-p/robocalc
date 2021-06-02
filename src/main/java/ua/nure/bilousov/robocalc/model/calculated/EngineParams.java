package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;

@Data
public class EngineParams {

    private Double firstLinkTorque;

    private Double secondLinkTorque;

    private Double thirdLinkTorque;
}
