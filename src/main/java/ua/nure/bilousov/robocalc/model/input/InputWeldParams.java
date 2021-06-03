package ua.nure.bilousov.robocalc.model.input;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class InputWeldParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private InputParams inputParams;

    private Double voltage;

    private Double secondCoilVoltage;

    private Double amperage;

    private Double currentDensity;

    private Double coreSection;

    private Double coreWindowSection;
}
