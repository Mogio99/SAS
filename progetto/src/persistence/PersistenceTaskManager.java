package persistence;

import businesslogic.shift.TurnKitchen;
import businesslogic.task.KTaskEventReceiver;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;

import java.util.ArrayList;

public class PersistenceTaskManager implements KTaskEventReceiver {

    @Override
    public void updateSSCreated(SummarySheet ss) {
        SummarySheet.saveNewSS(ss);
    }

    @Override
    public void updateTaskAdded(Task t) {

    }

    @Override
    public void updateTaskSorted(ArrayList<Task> newtl) {

    }

    @Override
    public void updateTaskAssigned(Task task) {

    }

    @Override
    public void updateKitTurnSat(TurnKitchen kitchenTurn) {

    }

    @Override
    public void updateTaskRemoved(Task task) {

    }

    @Override
    public void updateTaskModify(Task task) {

    }

    @Override
    public void updateTaskDisassigned(Task task) {

    }

    @Override
    public void updateTaskDone(Task task) {

    }
}
