package businesslogic.task;
import businesslogic.job.Job;
import businesslogic.service.Service;
import businesslogic.user.User;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import java.util.ArrayList;

public class kTaskManager {
    private ArrayList<KTaskEventReceiver> eventReceivers;
    private SummarySheet currentSS;

    private void notifySSCreated(SummarySheet ss) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateSSCreated(ss);
        }
    }
    private void notifyTaskAdded(Task t) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateTaskAdded(t);
        }
    }
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
        return currentSS;
    }



    public void setCurrent(SummarySheet ss) {
        this.currentSS = ss;
    }

    public Task addTask(Job job) throws UseCaseLogicException{
        User user = userMngr.getCurrentUser();
        if(user.isChef()==false){
            throw new UseCaseLogicException();
        }
        if(currentSS==null){
            throw new UseCaseLogicException();
        }
        Task t = currentSS.addTask(job);
        this.notifyTaskAdded(t);
    }


}
