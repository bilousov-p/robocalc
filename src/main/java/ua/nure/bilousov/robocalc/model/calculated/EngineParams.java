package ua.nure.bilousov.robocalc.model.calculated;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class EngineParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne
//    private CalculatedParams calculatedParams;

    private Double firstLinkTorque;

    private Double secondLinkTorque;

    private Double thirdLinkTorque;
}
