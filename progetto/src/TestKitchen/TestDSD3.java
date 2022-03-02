package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.kTaskManager;

import java.util.ArrayList;

public class TestDSD3 {
    public static void main (String [] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(26);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("SORT TASK");
        taskMgr.loadSS(s);
        ArrayList<Task> newtl= new ArrayList<>();
        ArrayList<Task> extl = s.getTaskList();
        newtl.add(extl.get(0));
        newtl.add(extl.get(1));
        newtl.add(extl.get(2));
        newtl.add(extl.get(4));
        newtl.add(extl.get(6));
        newtl.add(extl.get(3));
        newtl.add(extl.get(5));
        taskMgr.sortTask(newtl);
        SummarySheet.loadSSId(26);
        System.out.println("TEMINE TEST DSD3");
    }
}
