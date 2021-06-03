package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class WeldParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    private CalculatedParams calculatedParams;

    private Double transformerPower;

    private Integer firstWireNumberOfCoils;

    private Integer secondWireNumberOfCoils;

    private Double firstWireSection;

    private Double secondWireSection;
}
