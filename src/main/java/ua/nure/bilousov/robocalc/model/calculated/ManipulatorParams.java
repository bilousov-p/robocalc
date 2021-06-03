package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ManipulatorParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    private CalculatedParams calculatedParams;

    private Integer numberOfChains;

    private Integer numberOfSteadyLinks;

    private Integer numberOfAngleLinks;

    private Double firstChainSize;

    private Double secondChainSize;

    private Double thirdChainSize;
}
