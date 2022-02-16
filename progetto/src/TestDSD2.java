import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.recipe.Recipe;
import businesslogic.task.SummarySheet;

import businesslogic.task.kTaskManager;


import java.util.ArrayList;

public class TestDSD2 {
    public static void main (String [] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(24);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        System.out.println("ADD TASK");
        System.out.println("task presenti nella summary sheet 6:");
        taskMgr.loadSS(s);

        taskMgr.addTask(Recipe.loadRecipeById(20));
        taskMgr.addTask(Recipe.loadRecipeById(12));
        taskMgr.addTask(Recipe.loadRecipeById(2));


        System.out.println("\n Task presenti nella summary sheet 6 dopo aver aggiunto task:");
        SummarySheet.loadSSId(24);
    }
}
