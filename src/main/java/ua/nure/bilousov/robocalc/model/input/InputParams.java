package ua.nure.bilousov.robocalc.model.input;

import lombok.Data;
import ua.nure.bilousov.robocalc.model.FieldOfUse;
import ua.nure.bilousov.robocalc.model.ShapeOfArea;

import javax.persistence.*;

@Entity
@Data
public class InputParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ShapeOfArea shapeOfArea;

    @Enumerated(value = EnumType.STRING)
    private FieldOfUse fieldOfUse;

    private Double reachZone;

    private Double cargoCapacity;

    @OneToOne(cascade = CascadeType.ALL)
    private InputWeldParams inputWeldParams;
}
