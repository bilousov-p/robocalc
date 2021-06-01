package ua.nure.bilousov.robocalc.dto.request;

import lombok.Data;
import ua.nure.bilousov.robocalc.model.FieldOfUse;
import ua.nure.bilousov.robocalc.model.ShapeOfArea;

@Data
public class InputParamsRequest {

    private ShapeOfArea shapeOfArea;

    private FieldOfUse fieldOfUse;

    private Double reachZone;

    private Double cargoCapacity;

    private WeldParamsRequest weldParams;
}
