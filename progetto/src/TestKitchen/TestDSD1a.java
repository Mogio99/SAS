package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.task.SummarySheet;
import businesslogic.task.kTaskManager;

public class TestDSD1a {
    public static void main(String[] args) throws UseCaseLogicException, SSException {
        System.out.println("TEST DI LOAD SUMMRY SHEET ESISTENTE");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        SummarySheet ss = SummarySheet.loadSSId(26);
        taskMgr.loadSS(ss);
        System.out.println(ss.toString());
        ss.stampTask();

        System.out.println("TEMINE TEST DSD1a");

    }
}
