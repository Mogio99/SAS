import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.task.SummarySheet;
import businesslogic.task.kTaskManager;

public class TestDSD1a {
    public static void main(String[] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        SummarySheet ss = SummarySheet.loadSSId(24);
        taskMgr.loadSS(ss);
        System.out.println(taskMgr.getCurrentSS().getServiceName());
        System.out.println(taskMgr.getCurrentSS().getId());
    }
}
