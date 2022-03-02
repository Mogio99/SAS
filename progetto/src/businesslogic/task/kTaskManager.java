package businesslogic.task;
import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.event.ServiceInfo;
import businesslogic.job.Job;
import businesslogic.shift.ShiftBoard;
import businesslogic.shift.TurnKitchen;
import businesslogic.user.User;
import businesslogic.UseCaseLogicException;
import businesslogic.menu.Menu;
import persistence.PersistenceTaskManager;

import java.sql.Time;
import java.util.ArrayList;

public class kTaskManager {
    private ArrayList<KTaskEventReceiver> eventReceivers;
    private SummarySheet currentSS;

    /*costruttore*/
    public kTaskManager(){
        eventReceivers = new ArrayList<>();
    }
    public void setCurrent(SummarySheet ss) {
        this.currentSS = ss;
    }

    /*DSD1 creazione del summary sheet*/
    public SummarySheet createSS(ServiceInfo s) throws UseCaseLogicException, SSException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        Menu menu=s.getMenu();
        if(!menu.isOwner(user)){
            throw new SSException();
        }
        SummarySheet ss = new SummarySheet(s,user,menu);

        this.setCurrent(ss);
        this.notifySSCreated(ss);
        return currentSS;
    }
    /*DSD1a*/
    public SummarySheet loadSS(SummarySheet ss) throws UseCaseLogicException,SSException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()) {
            throw new UseCaseLogicException();
        }
        boolean rightOwner=user.isOwner(user);
        if(!rightOwner){
            throw new SSException();
        }
        this.setCurrent(ss);
        return currentSS;
    }


    /*DSD2 aggiunta di lavori*/
    public Task addTask(Job job) throws UseCaseLogicException,SSException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSS==null){
            throw new SSException();
        }
        Task t = currentSS.addTask(job);

        this.notifyTaskAdded(t);
        return t;
    }
    /*DSD 2a*/
    public void deleteTask(Task task) throws UseCaseLogicException,SSException {
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if (!user.isChef()) {
            throw new UseCaseLogicException();
        }
        if (currentSS == null) {
            throw new SSException();
        }
        if(!currentSS.contains(task)){
            throw new SSException();
        }
        currentSS.deleteTask(task);
        this.notifyTaskRemoved(task);
    }



    /*DSD 3 ritorna una lista ordinata dei Task*/
    public ArrayList<Task> sortTask(ArrayList<Task> newtl) throws UseCaseLogicException,SSException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        ArrayList<Task> sort;
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSS == null){
            throw new SSException();
        }
        sort=currentSS.sortTask(newtl);
        notifyTaskSorted(sort);
        return sort;
    }
    /*DSD 4 getShiftboard deve ritornare una tabella
        dei turni
    */
    public ShiftBoard getShiftBoard() throws UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        ShiftBoard shiftBoard  = CatERing.getInstance().getTurnManager().getShiftBoard();
        return shiftBoard;
    }

    /*DSD 5 assigneTask per aggiunger dei lavori al foglio riepilogativo */

    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList, int portion, Time duration, User cook)
            throws UseCaseLogicException,SSException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();

        if(!user.isChef()){
            throw new UseCaseLogicException();
        }

        if(currentSS == null){
            throw new SSException();
        }

        for(int i=0;i< tlList.size();i++){
            if(tlList.get(i).isSaturated()){
                System.out.println("Turn "+tlList.get(i).getId()+
                        " è saturo non si possono eseguire operazioni");
                throw new SSException();
            }
        }
        if(!currentSS.contains(task)){
            System.out.println("task da aggiungere presente, impossibile aggiungere");
            throw new SSException();
        }
        currentSS.assigneTask(task,tlList,portion,duration,cook);
        this.notifyTaskAssigned(task);
    }
    /*DSD5a modifica*/
    public void modifyTask(Task task, ArrayList<TurnKitchen> tlList, int portion, Time duration, User cook)
            throws UseCaseLogicException,SSException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        int i = 0;
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSS == null){
            throw new SSException();
        }
        if(tlList!=null) {
            for (i = 0; i < tlList.size(); i++) {
                if (tlList.get(i).isSaturated()) {
                    throw new SSException();
                }
            }
        }
        if(!currentSS.contains(task)){
            throw new SSException();
        }
        currentSS.modifyTask(task,tlList,portion,duration,cook);
        this.notifyTaskModify(task);
    }
    /*DSD5b estensione*/
    public void disassignTask(Task task) throws SSException,UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSS == null){
            throw new SSException();
        }
        if(!currentSS.contains(task)){
            throw new SSException();
        }
        currentSS.disassignTask(task);
        this.notifyDisassignTask(task);

    }
    /*DSD5c taskDone*/
    public void taskDone(Task task) throws SSException,UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        if(currentSS == null){
            throw new SSException();
        }
        if(!currentSS.contains(task)){
            throw new SSException();
        }
        currentSS.taskDone(task);
        this.notifyTaskDone(task);
    }

    /*DSD 6 per settare il valore di saturazione del turno di */
    public void setSaturation(TurnKitchen kitchenTurn,boolean val)
        throws SSException,UseCaseLogicException{
        User user = CatERing.getInstance().getUserManager().getCurrentUser();
        if(!user.isChef()){
            throw new UseCaseLogicException();
        }
        kitchenTurn.setSaturation(val);
        this.notifyKitTurnSat(kitchenTurn);
    }
    public SummarySheet getCurrentSS(){return this.currentSS;}

    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList) throws UseCaseLogicException, SSException {
        assigneTask(task,tlList,0,null,null);
    }
    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList,User cook) throws UseCaseLogicException, SSException {

        assigneTask(task,tlList,0,null,cook);
    }
    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList,int portion) throws UseCaseLogicException, SSException {
        assigneTask(task,tlList,portion,null,null);
    }
    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList,Time duration) throws UseCaseLogicException, SSException {
        assigneTask(task,tlList,0,duration,null);
    }
    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList,Time duration,int portion) throws UseCaseLogicException, SSException {
        assigneTask(task,tlList,portion,duration,null);
    }
    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList,Time duration,User cook) throws UseCaseLogicException, SSException {
        assigneTask(task,tlList,0,duration,cook);
    }
    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList,User cook,int portion) throws UseCaseLogicException, SSException {
        assigneTask(task,tlList,portion,null,cook);
    }

    public void modifyTask(Task task, ArrayList<TurnKitchen> tlList) throws UseCaseLogicException, SSException {
        modifyTask(task,tlList,0,null,null);
    }
    public void modifyTask(Task task,int portion) throws UseCaseLogicException, SSException {
        modifyTask(task,null,portion,null,null);
    }
    public void modifyTask(Task task, Time duration, Time time3, int i) throws UseCaseLogicException, SSException {
        modifyTask(task,null,0,duration,null);
    }
    public void modifyTask(Task task, User cook) throws UseCaseLogicException, SSException {
        modifyTask(task,null,0,null,cook);
    }
    public void modifyTask(Task task, ArrayList<TurnKitchen> tlList,User cook,Time duration) throws UseCaseLogicException, SSException {
        modifyTask(task,tlList,0,duration,cook);
    }
    public void modifyTask(Task task, ArrayList<TurnKitchen> tlList,User cook,int portion) throws UseCaseLogicException, SSException {
        modifyTask(task,tlList,portion,null,cook);
    }
    public void modifyTask(Task task, ArrayList<TurnKitchen> tlList,Time duration,int portion) throws UseCaseLogicException, SSException {
        modifyTask(task,tlList,portion,duration,null);
    }
    public void modifyTask(Task task,User cook,Time duration,int portion) throws UseCaseLogicException, SSException {
        modifyTask(task,null,portion,duration,null);
    }



    private void notifySSCreated(SummarySheet ss) {
        for(KTaskEventReceiver kitchenTaskER : eventReceivers){
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
    private void notifyTaskAssigned(Task task) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateTaskAssigned(task);
        }
    }
    private void notifyKitTurnSat(TurnKitchen kitchenTurn) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateKitTurnSat(kitchenTurn);
        }
    }
    private void notifyTaskRemoved(Task task) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateTaskRemoved(task);
        }
    }

    private void notifyTaskModify(Task task) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateTaskModify(task);
        }
    }

    private void notifyDisassignTask(Task task) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateTaskDisassigned(task);
        }
    }

    private void notifyTaskDone(Task task) {
        for(KTaskEventReceiver kitchenTaskER: this.eventReceivers){
            kitchenTaskER.updateTaskDone(task);
        }
    }

    public void addEventReceiver(PersistenceTaskManager taskPersistence) {
        eventReceivers.add(taskPersistence);
    }
}
