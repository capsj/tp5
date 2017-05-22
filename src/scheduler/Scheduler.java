package scheduler;

import events.Event;
import processes.Process;
import processes.Resource;
import schedulingalgorithms.Run;
import schedulingalgorithms.SchedulingAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * tp5
 * Created by jeronimocarlos on 5/22/17.
 */
public class Scheduler {

    List<Process> ready;
    List<Process> pending;
    List<Process> blocked;
    Run running;
    SchedulingAlgorithm schedulingAlgorithm;
    Process[] processes;
    List<Run> history;
    private int clock;

    public void start(){
        history = new ArrayList<>();
//        schedulingAlgorithm = new Priority();
        Resource[] resources = {new Resource(ResourceType.CPU, 5), new Resource(ResourceType.IO, 10), new Resource(ResourceType.CPU, 3)};
        processes = new Process[]{
                new Process(1).arrivalTime(10).priority(10).resource(new ArrayList<>(Arrays.asList(resources))),
                new Process(2).arrivalTime(10).priority(4).resource(new ArrayList<>(Arrays.asList(resources)))
        };
        ready = new ArrayList<>();
        pending = new ArrayList<>();
        blocked = new ArrayList<>();
        pending = new ArrayList<>(Arrays.asList(processes));
        clock = 1;
        schedule();
    }

    public List<Event> schedule(){
        clock = 0;
        List<Event> events = new LinkedList<>();
        addPWithATimeZero(new ArrayList<>(Arrays.asList(processes)), schedulingAlgorithm);

        while (schedulingAlgorithm.hasNextProcess()){
            addPWithATimeZero(new ArrayList<>(Arrays.asList(processes)),schedulingAlgorithm);

            Run run = schedulingAlgorithm.nextProcess();

            clock += run.getTime();
        }
        return events;
    }

    public void addPWithATimeZero(ArrayList<Process> processes, SchedulingAlgorithm alg){
        for (Process process : processes) {
            if (process.getArrivalTime()==0){
                alg.addProcess(process);
                process.setArrivalTime(process.getArrivalTime()-1);
            }
        }
    }

}
