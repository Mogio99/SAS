package businesslogic.task;
import businesslogic.SSException;
import businesslogic.disponibility.Cook;
import businesslogic.job.Job;
import businesslogic.shift.Turn;
import businesslogic.shift.TurnKitchen;

import java.sql.Time;
import java.util.ArrayList;

public class Task {
    private String quantity;
    private Time time;
    private boolean done;
    private Cook cook;
    private ArrayList<Turn> turnList;
    private Job consistingJob;

    public Task(Job rec) {
        this.consistingJob = rec;
        turnList = new ArrayList<>();
    }


    public void assigneTask(Task task, ArrayList<Turn> tlList, String portion, Time duration, Cook cook) throws SSException {
        if(portion!=null){
            this.quantity=portion;
        }
        if(duration!=null){
            this.time=duration;
        }
        if(cook!=null){
            if(cook.isAvaible(turnList)){
                this.cook=cook;
            }
        }else{
            throw new SSException();
        }
        //TODO:aggiungere if in caso

    }

    public void modifyTask(Task task, ArrayList<Turn> tlList, String portion, Time duration, Cook cook) throws SSException{
        if(portion!=null){
            this.quantity=portion;
        }
        if(duration!=null){
            this.time=duration;
        }
        if(cook!=null){
            if(cook.isAvaible(task.turnList)){
                this.cook=cook;
            }
        }else{
            throw new SSException();
        }
        if(tlList!=null){
            task.turnList.clear();
            for(int i=0;i<tlList.size();i++){
                task.turnList.add((TurnKitchen) tlList.get(i));
            }
        }
    }

    public void disassignTask() {
        this.quantity=null;
        this.time=null;
        this.cook=null;
        this.turnList.clear();
        this.done=false;
    }

    public void done() {
        this.done=true;
    }
}
