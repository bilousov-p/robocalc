package ua.nure.bilousov.robocalc.service.impl;

import org.springframework.stereotype.Service;
import ua.nure.bilousov.robocalc.model.ShapeOfArea;
import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;
import ua.nure.bilousov.robocalc.model.calculated.ManipulatorParams;
import ua.nure.bilousov.robocalc.model.input.InputParams;
import ua.nure.bilousov.robocalc.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public CalculatedParams calculateParameters(InputParams inputParams) {
        CalculatedParams calculatedParams = new CalculatedParams();

        calculatedParams.setManipulatorParams(calculateManipulatorParams(inputParams));

        return calculatedParams;
    }

    public ManipulatorParams calculateManipulatorParams(InputParams inputParams) {
        ManipulatorParams manipulatorParams = new ManipulatorParams();

        if (inputParams.getShapeOfArea().equals(ShapeOfArea.CYLINDER)) {
            manipulatorParams.setNumberOfChains(2);
        }

        manipulatorParams.setNumberOfChains(3);

        switch (inputParams.getShapeOfArea()) {
            case CYLINDER:
                manipulatorParams.setNumberOfAngleLinks(1);
                manipulatorParams.setNumberOfSteadyLinks(1);
                break;
            case PARALLELP:
                manipulatorParams.setNumberOfAngleLinks(0);
                manipulatorParams.setNumberOfSteadyLinks(3);
                break;
            case COMPLEX_SPHERE:
                manipulatorParams.setNumberOfAngleLinks(3);
                manipulatorParams.setNumberOfSteadyLinks(0);
                break;
            case PART_OF_SPHERE:
                manipulatorParams.setNumberOfAngleLinks(2);
                manipulatorParams.setNumberOfSteadyLinks(1);
                break;
            default:
                manipulatorParams.setNumberOfAngleLinks(0);
                manipulatorParams.setNumberOfSteadyLinks(0);
        }

        return manipulatorParams;
    }
}
