package ua.nure.bilousov.robocalc.model.input;

import lombok.Data;
import ua.nure.bilousov.robocalc.model.FieldOfUse;
import ua.nure.bilousov.robocalc.model.ShapeOfArea;

@Data
public class InputParams {

    private ShapeOfArea shapeOfArea;

    private FieldOfUse fieldOfUse;

    private Double reachZone;

    private Double cargoCapacity;

    private InputWeldParams inputWeldParams;
}
