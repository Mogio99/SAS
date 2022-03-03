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
        Task.saveNewTaskInSS(t);
    }

    @Override
    public void updateTaskSorted(ArrayList<Task> newtl) {
        for(int i=0;i< newtl.size();i++){
            newtl.get(i).remove();
        }
        for(int i=0;i< newtl.size();i++){
            newtl.get(i).saveNewTaskInSS(newtl.get(i));
        }
    }

    @Override
    public void updateTaskAssigned(Task task) {
        task.saveTaskModified(task);
    }

    @Override
    public void updateKitTurnSat(TurnKitchen kitchenTurn) {
        kitchenTurn.saveKitchenTurnSat();
    }

    @Override
    public void updateTaskRemoved(Task task) {
        task.remove();
    }

    @Override
    public void updateTaskModify(Task task) {
        task.saveTaskModified(task);
    }

    @Override
    public void updateTaskDisassigned(Task task) {
        task.saveTaskModified(task);
        task.removeTurn();
    }

    @Override
    public void updateTaskDone(Task task) {
        task.saveDone();
    }
}
