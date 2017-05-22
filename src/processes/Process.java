package processes;

import java.util.Queue;

/**
 * tp5
 * Created by jeronimocarlos on 5/22/17.
 */
public class Process {

    private String name;
    private int priority;
    private int arrivalTime;
    private Queue<Resource> resources;
    private State state;

    public Process(String name, int priority, int arrivalTime, Queue<Resource> resources) {
        this.name = name;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.resources = resources;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public Queue<Resource> getResources() {
        return resources;
    }

    public boolean isLocked(){
        return resources.peek().isIO();
    }

    public int consumeResource(int time){
        if (resources.peek().getRemainingTime()-time <= 0){
            return resources.poll().getRemainingTime();
        }
        resources.peek().consume(time);
        return resources.peek().getRemainingTime();
    }
}
