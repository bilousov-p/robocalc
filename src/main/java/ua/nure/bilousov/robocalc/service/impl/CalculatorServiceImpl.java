package ua.nure.bilousov.robocalc.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ua.nure.bilousov.robocalc.model.ShapeOfArea;
import ua.nure.bilousov.robocalc.model.calculated.CalculatedParams;
import ua.nure.bilousov.robocalc.model.calculated.EngineParams;
import ua.nure.bilousov.robocalc.model.calculated.ManipulatorParams;
import ua.nure.bilousov.robocalc.model.calculated.WeldParams;
import ua.nure.bilousov.robocalc.model.input.InputParams;
import ua.nure.bilousov.robocalc.model.input.InputWeldParams;
import ua.nure.bilousov.robocalc.repository.CalculatedParamsRepository;
import ua.nure.bilousov.robocalc.service.CalculatorService;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CalculatorServiceImpl implements CalculatorService {

    private final CalculatedParamsRepository calculatedParamsRepository;

    private final Double FIRST_PAIR_RATIO = 0.3;
    private final Double SECOND_PAIR_RATIO = 0.26;
    private final Double THIRD_PAIR_RATIO = 0.44;
    private final Integer DEFAULT_ENGINE_WEIGHT = 5;
    private final Integer DEFAULT_STEP_AMPERAGE_VALUE = 130;

    @Override
    public CalculatedParams calculateParameters(InputParams inputParams) {
        CalculatedParams calculatedParams = new CalculatedParams();
        calculatedParams.setInputParams(inputParams);

        calculatedParams.setManipulatorParams(calculateManipulatorParams(inputParams));
        calculatedParams.setNumberOfFreedoms(calculateNumberOfFreedoms(calculatedParams.getManipulatorParams()));
        calculatedParams.setEngineParams(calculateEngineParams(inputParams, calculatedParams.getManipulatorParams()));

        switch (inputParams.getFieldOfUse()){
            case WELD:
                calculatedParams.setWeldParams(calculateWeldParams(inputParams.getInputWeldParams()));
                break;
            case MOVE:
            case OTHER:
                break;
        }

        calculatedParamsRepository.save(calculatedParams);
        return calculatedParams;
    }

    @Override
    public List<CalculatedParams> getAllCalculations() {
        return (List<CalculatedParams>) calculatedParamsRepository.findAll();
    }

    @Override
    public CalculatedParams getCalculationsById(Long id) {
        return calculatedParamsRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void deleteCalculationById(Long id) {
        calculatedParamsRepository.deleteById(id);
    }

    private ManipulatorParams calculateManipulatorParams(InputParams inputParams) {
        ManipulatorParams manipulatorParams = new ManipulatorParams();

        if (inputParams.getShapeOfArea().equals(ShapeOfArea.CYLINDER)) {
            manipulatorParams.setNumberOfChains(2);
        } else {
            manipulatorParams.setNumberOfChains(3);
        }

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

    private EngineParams calculateEngineParams(InputParams inputParams, ManipulatorParams manipulatorParams){
        EngineParams engineParams = new EngineParams();

        if (inputParams.getShapeOfArea().equals(ShapeOfArea.CYLINDER)) {
            Double secondEngineTorque = (inputParams.getCargoCapacity() * manipulatorParams.getSecondChainSize()) / 10;
            Double firstEngineTorque = ((inputParams.getCargoCapacity() + DEFAULT_ENGINE_WEIGHT) * manipulatorParams.getFirstChainSize()) / 10;

            engineParams.setFirstLinkTorque(firstEngineTorque);
            engineParams.setSecondLinkTorque(secondEngineTorque);
        } else {
            Double thirdEngineTorque = (inputParams.getCargoCapacity() * manipulatorParams.getSecondChainSize()) / 10;
            Double secondEngineTorque = ((inputParams.getCargoCapacity() + DEFAULT_ENGINE_WEIGHT) * manipulatorParams.getFirstChainSize()) / 10;
            Double firstEngineTorque = ((inputParams.getCargoCapacity() + DEFAULT_ENGINE_WEIGHT + DEFAULT_ENGINE_WEIGHT) * manipulatorParams.getFirstChainSize()) / 10;

            engineParams.setFirstLinkTorque(firstEngineTorque);
            engineParams.setSecondLinkTorque(secondEngineTorque);
            engineParams.setThirdLinkTorque(thirdEngineTorque);
        }

        return engineParams;
    }

    private WeldParams calculateWeldParams(InputWeldParams inputWeldParams) {
        WeldParams weldParams = new WeldParams();

        Double power = 1.5 * inputWeldParams.getCoreSection() * inputWeldParams.getCoreWindowSection();
        weldParams.setTransformerPower(power);

        Double coilsPerVolt = 50 / inputWeldParams.getCoreSection();
        Double maxAmperage = power / inputWeldParams.getVoltage();

        Double secondNumOfCoils = inputWeldParams.getSecondCoilVoltage() / coilsPerVolt;
        weldParams.setSecondWireNumberOfCoils(secondNumOfCoils.intValue());

        Double stepVoltage = power / DEFAULT_STEP_AMPERAGE_VALUE;

        Double firstNumOfCoils = stepVoltage / coilsPerVolt;
        weldParams.setFirstWireNumberOfCoils(firstNumOfCoils.intValue());

        weldParams.setSecondWireSection(maxAmperage / inputWeldParams.getCurrentDensity());
        weldParams.setFirstWireSection(DEFAULT_STEP_AMPERAGE_VALUE / inputWeldParams.getCurrentDensity());

        return weldParams;
    }
}
