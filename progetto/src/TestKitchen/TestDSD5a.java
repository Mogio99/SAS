package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.shift.TurnKitchen;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.kTaskManager;
import businesslogic.user.User;

import java.sql.Time;
import java.util.ArrayList;

public class TestDSD5a {
    public static void main(String [] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        ArrayList<TurnKitchen> tl1 = new ArrayList<TurnKitchen>();
        tl1.add(TurnKitchen.loadKitchenTurnById(1));
        tl1.add(TurnKitchen.loadKitchenTurnById(2));
        tl1.add(TurnKitchen.loadKitchenTurnById(3));
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(26);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("MODIFY TASK");
        Task t1 = s.getTaskList().get(2);
        Task t2 = s.getTaskList().get(1);
        long time = 200000;
        Time time3 = new Time(time);
        try{
            taskMgr.loadSS(s);
            taskMgr.modifyTask(t1,tl1, User.loadUser("Antonietta"), 5);
            taskMgr.modifyTask(t2,User.loadUser("Paola"),time3,30);
        } catch (UseCaseLogicException e) {
            e.printStackTrace();
        } catch (SSException e) {
            e.printStackTrace();
        }
        System.out.println(t1.toString());
        System.out.println(t2.toString());
        System.out.println("TEMINE TEST DSD5.a ");
    }

}
