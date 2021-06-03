package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;
import ua.nure.bilousov.robocalc.model.input.InputParams;

import javax.persistence.*;

@Entity
@Data
public class CalculatedParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private InputParams inputParams;

    private Integer numberOfFreedoms;

    @OneToOne(cascade = CascadeType.ALL)
    private ManipulatorParams manipulatorParams;

    @OneToOne(cascade = CascadeType.ALL)
    private EngineParams engineParams;

    @OneToOne(cascade = CascadeType.ALL)
    private WeldParams weldParams;
}
