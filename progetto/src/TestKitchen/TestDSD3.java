package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.kTaskManager;

import java.util.ArrayList;
import java.util.Collections;

public class TestDSD3 {
    public static void main (String [] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(26);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("TEST SORT TASK");
        taskMgr.loadSS(s);
        System.out.println("PRIMA DI ESSERE ORDINATA");
        s.stampTask();
        ArrayList<Task> newtl=(ArrayList<Task>) s.getTaskList().clone();
        Collections.shuffle(newtl);
        taskMgr.sortTask(newtl);
        System.out.println("\n");
        System.out.println("DOPO  ESSERE ORDINATA");
        s.stampTask();
        System.out.println("TEMINE TEST DSD3");
    }
}
