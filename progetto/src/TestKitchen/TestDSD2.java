package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.recipe.Recipe;
import businesslogic.task.SummarySheet;

import businesslogic.task.kTaskManager;


import java.util.ArrayList;

public class TestDSD2 {
    public static void main (String [] args) throws UseCaseLogicException, SSException {
        System.out.println("TEST ADD TASK");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(26);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        taskMgr.loadSS(s);
        System.out.println(s.toString());

        System.out.println("task presenti nella summary sheet 26:");
        s.stampTask();
        taskMgr.addTask(Recipe.loadRecipeById(20));
        taskMgr.addTask(Recipe.loadRecipeById(12));
        taskMgr.addTask(Recipe.loadRecipeById(2));

        System.out.println("\n Task presenti nella summary sheet 26 dopo aver aggiunto task:");
        s.stampTask();
        System.out.println("TEMINE TEST DSD2");
    }
}
