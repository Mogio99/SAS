package businesslogic.task;

import businesslogic.shift.TurnKitchen;

import java.util.ArrayList;

public interface KTaskEventReceiver {
    /*in un'intefaccia i metodi sono dichiatrati senza codice*/
    public void updateSSCreated(SummarySheet ss);

    public void updateTaskAdded(Task t);

    public void updateTaskSorted(ArrayList<Task> newtl);

    public void updateTaskAssigned(Task task);

    public void updateKitTurnSat(TurnKitchen kitchenTurn);

    public void updateTaskRemoved(Task task);

    public void updateTaskModify(Task task);

    public void updateTaskDisassigned(Task task);

    public void updateTaskDone(Task task);
}
