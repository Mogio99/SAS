import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.task.SummarySheet;
import businesslogic.task.kTaskManager;

public class TestDSD1 {
    public static void main(String[] args) throws UseCaseLogicException {
        /* System.out.println("TEST DATABASE CONNECTION");
        PersistenceManager.testSQLConnection();*/
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        System.out.println("TEST CREATESS");
        ServiceInfo s = ServiceInfo.loadServiceById(2);
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        SummarySheet ss = taskMgr.createSS(s);
        System.out.println("TEST END");
    }
}
