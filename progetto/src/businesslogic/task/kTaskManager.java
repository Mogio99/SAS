package businesslogic.task;
import businesslogic.service.Service;
import businesslogic.user.User;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import java.util.ArrayList;

public class kTaskManager {
    private ArrayList<KTaskEventReceiver> eventReceivers;
    private SummarySheet currentSS;

    public SummarySheet createSS(Service s) throws UseCaseLogicException{
        User user = userMngr.getCurrentUser();
        if(user.isChef()==false){
            throw new UseCaseLogicException();
        }

        Menu menu=s.getMenu();

        if(menu.isOwner(user)==false){
            throw new UseCaseLogicException();
        }
        SummarySheet ss = new SummarySheet(s,user,menu);
        this.setCurrent(ss);
        this.notifySSCreated(ss);
        return ss;
    }

    private void notifySSCreated(SummarySheet ss) {

    }

    public void setCurrent(SummarySheet ss) {
        this.currentSS = ss;
    }

    public


}
