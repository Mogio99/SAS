package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.task.SummarySheet;
import businesslogic.task.kTaskManager;


public class TestDSD2a {
    public static void main (String [] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        SummarySheet s = SummarySheet.loadSSId(26);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        taskMgr.loadSS(s);
        System.out.println("DELETE TASK TEST");
        System.out.println("<<<task presenti nella summary sheet 26:>>>");
        s.stampTask();
        taskMgr.deleteTask(s.getTaskList().get(1));
        taskMgr.deleteTask(s.getTaskList().get(2));
        taskMgr.deleteTask(s.getTaskList().get(3));
        System.out.println("------------------------------------");
        System.out.println("<<task presenti nella summary sheet 26 dopo i delete:>>");
        s.stampTask();
        System.out.println("TEMINE TEST DSD2a");
    }
}
