import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.ServiceInfo;
import businesslogic.task.kTaskManager;

public class TestCatERing {
    public static void main(String [] args) throws UseCaseLogicException {
        ServiceInfo s = new ServiceInfo("bello");
        kTaskManager task= new kTaskManager();
        task.createSS(s);

    }
}
