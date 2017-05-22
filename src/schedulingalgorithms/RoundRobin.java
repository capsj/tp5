package schedulingalgorithms;

import processes.Process;

import java.util.List;

/**
 * tp5
 * Created by jeronimocarlos on 24/04/17.
 */
public class RoundRobin implements SchedulingAlgorithm {

    List<Process> ready;
    @Override
    public void addProcesses(List<Process> p) {
        ready = p;
    }

    @Override
    public Run nextProcess() {
        if (!ready.isEmpty()) {
            Run run = new Run(ready.get(0), 10);
            ready.remove(0);
            return run;
        }
        return null;
    }

    @Override
    public boolean hasNextProcess() {
        return false;
    }
}
