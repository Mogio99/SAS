package businesslogic.shift;

public class TurnService extends Turn {
    private boolean saturated=false;

    public boolean isSaturated() {
        return this.saturated;
    }
}
