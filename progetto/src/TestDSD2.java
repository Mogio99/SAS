import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.job.Job;
import businesslogic.recipe.Recipe;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.kTaskManager;
import com.sun.jdi.ArrayReference;

import java.util.ArrayList;

public class TestDSD2 {
    public static void main (String [] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(6);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("ADD TASK");
        taskMgr.loadSS(s);
        int i = 0;
        int val_size = taskMgr.getCurrentSS().getTaskList().size();
        ArrayList<Task> arrayList = taskMgr.getCurrentSS().getTaskList();
        System.out.println("task presenti nella summary sheet 6:");
        for(i=0;i<val_size;i++){
            System.out.println(arrayList.get(i).getTaskName());
        }
        taskMgr.addTask(Recipe.loadRecipeById(20));
        taskMgr.addTask(Recipe.loadRecipeById(12));
        taskMgr.addTask(Recipe.loadRecipeById(2));
        val_size = taskMgr.getCurrentSS().getTaskList().size();
        arrayList = taskMgr.getCurrentSS().getTaskList();
        System.out.println("task presenti nella summary sheet 7 dopo aver aggiunto task:");
        for(i=0;i<val_size;i++){
            System.out.println(arrayList.get(i).getTaskName());
        }

    }
}
