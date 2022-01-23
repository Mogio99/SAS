import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.CatERing;
import businesslogic.UseCaseLogicException;
import businesslogic.event.EventInfo;
import businesslogic.event.ServiceInfo;
import businesslogic.menu.Menu;
import businesslogic.menu.Section;
import businesslogic.recipe.Recipe;
import javafx.collections.ObservableList;

public class TestMenu {
    public static void main(String[] args) throws UseCaseLogicException {
        /* System.out.println("TEST DATABASE CONNECTION");
        PersistenceManager.testSQLConnection();*/
        System.out.println("TEST FAKE LOGIN");
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        System.out.println("TEST GETMENU");
        ServiceInfo s = ServiceInfo.loadServiceById("Coffee break pomeriggio",4);
        CatERing.getInstance().getTaskManager().createSS(s);
    }
}
