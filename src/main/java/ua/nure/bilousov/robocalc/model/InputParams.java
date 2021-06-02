package ua.nure.bilousov.robocalc.model;

import lombok.Data;

@Data
public class InputParams {

    private ShapeOfArea shapeOfArea;

    private FieldOfUse fieldOfUse;

    private Double reachZone;

    private Double cargoCapacity;

    private InputWeldParams inputWeldParams;
}
