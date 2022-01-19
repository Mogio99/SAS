package businesslogic.task;
import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.job.Job;
import businesslogic.service.Service;
import businesslogic.user.User;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import java.util.ArrayList;

public class kTaskManager {
    private ArrayList<KTaskEventReceiver> eventReceivers;
    private SummarySheet currentSS;

    /*costruttore*/
    public kTaskManager(){
        eventReceivers = new ArrayList<>();
    }
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
    private void notifyTaskSorted(ArrayList<Task> newtl) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateTaskSorted(newtl);
        }
    }
    /*creazione del summary sheet*/
    public SummarySheet createSS(Service s) throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
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
    /*aggiunta di lavori*/
    public Task addTask(Job job) throws UseCaseLogicException,SSException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(user.isChef()==false){
            throw new UseCaseLogicException();
        }
        if(currentSS==null){
            throw new SSException();
        }
        Task t = currentSS.addTask(job);
        this.notifyTaskAdded(t);
        return t;
    }

    public ArrayList<Task> sortTask(ArrayList<Task> newtl) throws UseCaseLogicException,SSException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        ArrayList<Task> sort;
        if(user.isChef()==false){
            throw new UseCaseLogicException();
        }
        if(currentSS == null){
            throw new SSException();
        }
        sort=currentSS.sortTask(newtl);
        notifyTaskSorted(sort);
        return sort;
    }

    public sheet


}
