package robotService.models.procedures.interfaces;

import robotService.models.robots.interfaces.Robot;

import static robotService.common.ExceptionMessages.ALREADY_REPAIRED;
import static robotService.common.ExceptionMessages.INSUFFICIENT_PROCEDURE_TIME;

public class Repair extends BaseProcedure {


    @Override
    public void doService(Robot robot, int procedureTime) {
        if(robot.getProcedureTime() < procedureTime) {
            throw new IllegalArgumentException(INSUFFICIENT_PROCEDURE_TIME);
        }

        if(robot.isRepaired()) {
            throw new IllegalArgumentException(String.format(ALREADY_REPAIRED, robot.getName()));
        }
        robot.setProcedureTime(robot.getProcedureTime() - procedureTime);
        robot.setHappiness(robot.getHappiness() - 5);
        robot.setRepaired(true);
        this.robots.add(robot);

    }
}
