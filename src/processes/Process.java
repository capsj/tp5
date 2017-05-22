package processes;

import java.util.List;
import java.util.Queue;

/**
 * tp5
 * Created by jeronimocarlos on 5/22/17.
 */
public class Process {

    private int id;
    private int priority;
    private int arrivalTime;
    private List<Resource> resources;
    private State state;

    public Process(int id) {
        this.id = id;
        this.state = State.PENDING;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public List<Resource> getResources(){
        return resources;
    }
    public void removeResource(){
        resources.remove(0);
    }

//    public boolean isLocked(){
//        return resources;
//    }

}
