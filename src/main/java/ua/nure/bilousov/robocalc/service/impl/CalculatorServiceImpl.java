package ua.nure.bilousov.robocalc.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.bilousov.robocalc.model.ShapeOfArea;
import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;
import ua.nure.bilousov.robocalc.model.calculated.ManipulatorParams;
import ua.nure.bilousov.robocalc.model.input.InputParams;
import ua.nure.bilousov.robocalc.service.CalculatorService;


@Service
public class CalculatorServiceImpl implements CalculatorService {

    private final Double FIRST_PAIR_RATIO = 0.3;
    private final Double SECOND_PAIR_RATIO = 0.26;
    private final Double THIRD_PAIR_RATIO = 0.44;

    @Override
    public CalculatedParams calculateParameters(InputParams inputParams) {
        CalculatedParams calculatedParams = new CalculatedParams();

        calculatedParams.setManipulatorParams(calculateManipulatorParams(inputParams));
        calculatedParams.setNumberOfFreedoms(calculateNumberOfFreedoms(calculatedParams.getManipulatorParams()));

        return calculatedParams;
    }

    private ManipulatorParams calculateManipulatorParams(InputParams inputParams) {
        ManipulatorParams manipulatorParams = new ManipulatorParams();

        if (inputParams.getShapeOfArea().equals(ShapeOfArea.CYLINDER)) {
            manipulatorParams.setNumberOfChains(2);
        }

        manipulatorParams.setNumberOfChains(3);

        switch (inputParams.getShapeOfArea()) {
            case CYLINDER:
                manipulatorParams.setNumberOfAngleLinks(1);
                manipulatorParams.setNumberOfSteadyLinks(1);
                manipulatorParams.setFirstChainSize(inputParams.getReachZone());
                manipulatorParams.setSecondChainSize(inputParams.getReachZone());
                break;
            case PARALLELP:
                manipulatorParams.setNumberOfAngleLinks(0);
                manipulatorParams.setNumberOfSteadyLinks(3);
                manipulatorParams.setFirstChainSize(inputParams.getReachZone());
                manipulatorParams.setSecondChainSize(inputParams.getReachZone());
                manipulatorParams.setThirdChainSize(inputParams.getReachZone());
                break;
            case COMPLEX_SPHERE:
                manipulatorParams.setNumberOfAngleLinks(3);
                manipulatorParams.setNumberOfSteadyLinks(0);
                manipulatorParams.setFirstChainSize(inputParams.getReachZone()*FIRST_PAIR_RATIO);
                manipulatorParams.setSecondChainSize(inputParams.getReachZone()*SECOND_PAIR_RATIO);
                manipulatorParams.setThirdChainSize(inputParams.getReachZone()*THIRD_PAIR_RATIO);
                break;
            case PART_OF_SPHERE:
                manipulatorParams.setNumberOfAngleLinks(2);
                manipulatorParams.setNumberOfSteadyLinks(1);
                manipulatorParams.setFirstChainSize(inputParams.getReachZone());
                manipulatorParams.setSecondChainSize(inputParams.getReachZone());
                manipulatorParams.setThirdChainSize(inputParams.getReachZone());
                break;
            default:
                manipulatorParams.setNumberOfAngleLinks(0);
                manipulatorParams.setNumberOfSteadyLinks(0);
        }

        return manipulatorParams;
    }

    private Integer calculateNumberOfFreedoms(ManipulatorParams manipulatorParams) {
        return 3*manipulatorParams.getNumberOfChains() - 2*manipulatorParams.getNumberOfSteadyLinks() - manipulatorParams.getNumberOfAngleLinks();
    }
}
