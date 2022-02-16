import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.task.SummarySheet;
import businesslogic.task.kTaskManager;

public class TestDSD1 {
    public static void main(String[] args) throws UseCaseLogicException, SSException {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        System.out.println("TEST CREATESS");
        ServiceInfo s = ServiceInfo.loadServiceById(4);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        SummarySheet ss = taskMgr.createSS(s);
        System.out.println("TEST END");
    }
}
