package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.shift.TurnKitchen;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.kTaskManager;

import java.util.ArrayList;

public class TestDSD5c {
    public static void main(String [] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(26);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("TEST DONE TASK");
        try{
            Task t1 = s.getTaskList().get(1);
            System.out.println("PRIMA DI DONE \n"+t1.toString());
            taskMgr.loadSS(s);
            taskMgr.taskDone(t1);
            System.out.println("DOPO DONE \n"+t1.toString());
        } catch (UseCaseLogicException e) {
            e.printStackTrace();
        } catch (SSException e) {
            e.printStackTrace();
        }
        System.out.println("TEMINE TEST DSD5.c");
    }
}
