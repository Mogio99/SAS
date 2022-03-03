package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.shift.TurnKitchen;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.kTaskManager;
import businesslogic.user.User;
import com.sun.javafx.tk.Toolkit;

import java.util.ArrayList;

public class TestDSD5 {
    public static void main(String [] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");

        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(26);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("ASSIGNE TASK");
        taskMgr.loadSS(s);

        ArrayList<TurnKitchen> tl1= new ArrayList<TurnKitchen>();
        tl1.add(TurnKitchen.loadKitchenTurnById(1));
        tl1.add(TurnKitchen.loadKitchenTurnById(2));
        tl1.add(TurnKitchen.loadKitchenTurnById(3));

        Task t1=s.getTaskList().get(2);
        Task t2=s.getTaskList().get(1);
        try {
            taskMgr.assigneTask(t1, tl1, User.loadUser("Paola"));
            System.out.println(t1);
            taskMgr.assigneTask(t2, tl1 ,User.loadUser("Antonietta"),20);
            System.out.println(t2);

        }catch(Exception e){
            System.out.println("Errore nel test "+e);
        }
        System.out.println("TEMINE TEST DSD5");
    }


}
