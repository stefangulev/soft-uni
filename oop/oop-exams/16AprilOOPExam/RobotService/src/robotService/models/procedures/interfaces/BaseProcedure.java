package robotService.models.procedures.interfaces;

import robotService.models.robots.interfaces.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseProcedure implements Procedure {
    protected List<Robot> robots;

    protected BaseProcedure() {
        this.robots = new ArrayList<>();
    }


    @Override
    public String history() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(System.lineSeparator());
        sb.append(this.robots.stream().map(Robot::toString).collect(Collectors.joining(System.lineSeparator())));
        return sb.toString().trim();
    }

    @Override
    public void doService(Robot robot, int procedureTime) {

    }

}
