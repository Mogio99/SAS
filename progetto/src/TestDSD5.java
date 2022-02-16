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
        ArrayList<TurnKitchen> tl1= new ArrayList<TurnKitchen>();
        tl1.add(TurnKitchen.loadKitchenTurnById(1));
        tl1.add(TurnKitchen.loadKitchenTurnById(2));
        tl1.add(TurnKitchen.loadKitchenTurnById(3));
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(25);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("ASSIGNE TASK");
        taskMgr.loadSS(s);
        Task t1=s.getTaskList().get(2);
        try {
            taskMgr.assigneTask(t1, tl1, User.loadUser("Paola"));
            System.out.println(t1);
        }catch(Exception e){
            System.out.println(e);
        }
    }


}
