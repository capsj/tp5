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
import java.util.stream.Collectors;

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
        while(!(pending.isEmpty() && ready.isEmpty() && blocked.isEmpty()) || running != null){
            System.out.println(clock++);
            decreaseArrivalTimes();
            decreaseBlocked();
            addToReady();
            schedulingAlgorithm.addProcesses(ready);
            if (running == null) {
                running = schedulingAlgorithm.nextProcess();
                if (running != null)
                    history.add(new Run(running.getP(), running.getRunTime()));
            }
            else if (running == null && pending.isEmpty()) break;
            else {
                ready.remove(running.getP());
                decreaseRunning();
            }
        }
        System.out.println("Finished");
        ScheduleGraphic.draw(Arrays.asList(processes), history);
    }

    private void addToReady(){
        ready.addAll(pending.stream().filter(process -> process.getArrivalTime() == 0).collect(Collectors.toList()));
        System.out.println("Ready size: " + ready.size());
    }

    private void decreaseArrivalTimes(){
        List<Process> futureRemoved = new ArrayList<>();
        for (Process process : pending){
            if (process.getArrivalTime() == 0) futureRemoved.add(process);
            else process.arrivalTime(process.getArrivalTime()-1);
        }
        for (Process process : futureRemoved){
            pending.remove(process);
        }
    }

    private void decreaseBlocked(){
        List<Process> futureRemoved = new ArrayList<>();
        for(Process process : blocked){
            Resource res = process.getResources().get(0);
            if(res.getTime() == 0) {
                process.removeResource();
                if (!process.getResources().isEmpty()) ready.add(process);
                futureRemoved.add(process);
            }
            else res.setTime(res.getTime()-1);
        }
        for (Process process : futureRemoved){
            blocked.remove(process);
        }
    }

    private void decreaseRunning(){
        Resource currentResource = running.getP().getResources().get(0);
        running.setRunTime(running.getRunTime()-1);
        currentResource.setTime(currentResource.getTime()-1);

        if (currentResource.getTime() > 0 && running.getRunTime() == 0) {
            ready.add(running.getP());
            running = null;
        }
        else if (currentResource.getTime() == 0 || running.getRunTime() == 0){
            running.getP().removeResource();
            if(!running.getP().getResources().isEmpty()){
                blocked.add(running.getP());
            }
            running = null;
        }
    }
}
