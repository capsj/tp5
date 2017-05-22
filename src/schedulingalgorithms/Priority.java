package schedulingalgorithms;

import java.util.List;
import processes.Process;

/**
 * tp5
 * Created by jeronimocarlos on 5/22/17.
 */
public class Priority implements SchedulingAlgorithm {
    List<Process> ready;
    @Override
    public void addProcesses(List<Process> p) {
        ready = p;
        ready.sort((o1, o2) -> {
            if(o1.getPriority() < o2.getPriority()) return 1;
            else if (o1.getPriority() == o2.getPriority()) return 0;
            return -1;
        });
    }

    @Override
    public Run nextProcess() {
        if (!ready.isEmpty()) {
            Run run = new Run(ready.get(0), 10);
            ready.remove(0);
            System.out.println("Process id to run: " + run.getP().getId());
            return run;
        }
        return null;
    }

    @Override
    public boolean hasNextProcess() {
        return false;
    }
}
