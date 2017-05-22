package processes;

import scheduler.ResourceType;

/**
 * tp5
 * Created by jeronimocarlos on 5/22/17.
 */
public class Resource {
    private ResourceType type;
    private int time;

    public Resource(ResourceType type, int time) {
        this.time = time;
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
