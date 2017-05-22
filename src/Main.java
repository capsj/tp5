import processes.Resource;
import scheduler.ResourceType;
import processes.Process;
import scheduler.Scheduler;
import schedulingalgorithms.RoundRobin;
import schedulingalgorithms.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * tp5
 * Created by jeronimocarlos on 5/22/17.
 */
public class Main {

    public static void main(String[] args) {
        Resource[] resources1 = {new Resource(ResourceType.CPU, 5), new Resource(ResourceType.IO, 10), new Resource(ResourceType.CPU, 3)};
        Resource[] resources2 = {new Resource(ResourceType.CPU, 5), new Resource(ResourceType.IO, 10), new Resource(ResourceType.CPU, 3)};
        Process [] processes = new Process[]{
            new Process(1).arrivalTime(10).priority(10).resource(new ArrayList<>(Arrays.asList(resources1))),
            new Process(2).arrivalTime(10).priority(4).resource(new ArrayList<>(Arrays.asList(resources2)))
        };
        SchedulingAlgorithm algorithm = new RoundRobin();

        Scheduler scheduler = new Scheduler();
        scheduler.start(algorithm, processes);
    }
}
