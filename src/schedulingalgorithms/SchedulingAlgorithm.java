package schedulingalgorithms;

import processes.Process;

/**
 * Created by sebi on 24/04/17.
 */
public interface SchedulingAlgorithm {

    void addProcess(Process p);
    Run nextProcess();
    boolean hasNextProcess();
    
    //// TODO: 24/04/17 First Come, First Serve algorithm
    //                  Shortest Job First
    //                  Shortest Remaining time next
    //                  Round-Robin Scheduling
    //                  Priority scheduling
    //                  Fair-Share Scheduling
    //                  Lottery Scheduling
    //                  Guaranteed Scheduling
    //                  Priority scheduling with multiple queues

}
