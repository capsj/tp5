package schedulingalgorithms;

import processes.Process;

/**
 * Created by sebi on 24/04/17.
 */
public class Run {
    private Process p;
    private int runTime;

    public Run(Process p, int runTime) {
        this.p = p;
        this.runTime = runTime;
    }

    public Process getP() {
        return p;
    }

    public void setP(Process p) {
        this.p = p;
    }

    public int getRunTime() {
        return runTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }
}
