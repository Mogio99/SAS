package TestKitchen;

import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.UseCaseLogicException;
import businesslogic.shift.TurnKitchen;
import businesslogic.task.SummarySheet;
import businesslogic.task.Task;
import businesslogic.task.kTaskManager;
import com.sun.nio.sctp.SctpStandardSocketOptions;

import java.io.InputStream;
import java.util.ArrayList;

public class TestDSD6 {
    public static void main(String [] args) {
        CatERing.getInstance().getUserManager().fakeLogin("Lidia");
        System.out.println(CatERing.getInstance().getUserManager().getCurrentUser());
        kTaskManager taskMgr = CatERing.getInstance().getTaskManager();
        SummarySheet s = SummarySheet.loadSSId(23);
        System.out.println("SET SATURATION KITCHEN TURN");
        try{
            taskMgr.loadSS(s);
            TurnKitchen t= TurnKitchen.loadKitchenTurnById(2);
            taskMgr.setSaturation(t,true);
        } catch (UseCaseLogicException e) {
            e.printStackTrace();
        } catch (SSException e) {
            e.printStackTrace();
        }

    }

}
