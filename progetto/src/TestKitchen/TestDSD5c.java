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
        ArrayList<TurnKitchen> tl1 = new ArrayList<TurnKitchen>();
        tl1.add(TurnKitchen.loadKitchenTurnById(1));
        tl1.add(TurnKitchen.loadKitchenTurnById(2));
        tl1.add(TurnKitchen.loadKitchenTurnById(3));
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(23);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("DONE TASK");
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
    }
}
