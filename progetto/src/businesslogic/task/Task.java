package businesslogic.task;
import businesslogic.disponibility.Cook;
import businesslogic.turn.TurnKitchen;
import recipe.Recipe;

import java.sql.Time;
import java.util.ArrayList;

public class Task {
    private String quantity;
    private Time time;
    private boolean done;
    private Cook cook;
    private ArrayList<TurnKitchen>  turnList;
    private Job consistingJob;

    public Task(Recipe rec) {
        this.consistingJob(rec);
    }

    private void consistingJob(recipe.Recipe rec) {
    }
}
