package businesslogic.task;
import businesslogic.SSException;
import businesslogic.disponibility.Cook;
import businesslogic.event.ServiceInfo;
import businesslogic.job.Job;
import businesslogic.menu.Menu;
import businesslogic.shift.Turn;
import businesslogic.user.User;

import java.sql.Time;
import java.util.ArrayList;

public class SummarySheet {
    private ArrayList<Task> taskList;
    private User owner;
    private ServiceInfo serviceUsed;

    public SummarySheet(ServiceInfo s, User user, Menu menu) {
        this.owner = user;
        this.serviceUsed = s;
        ArrayList<Job> arrayListJob = menu.getAllJob;
        taskList = new ArrayList<Task>();
        int i = 0;
        for(i=0;i<arrayListJob.size();i++){
            Task task = new Task(arrayListJob.get(i));
            taskList.add(task);
        }
    }

    public Task addTask(Job job) {
        Task turn = new Task(job);
        taskList.add(turn);
        return turn;
    }

    public ArrayList<Task> sortTask(ArrayList<Task> newtl) {
        this.taskList= newtl;/*TODO:non so cosa devo fare*/
        return taskList;
    }

    public boolean contains(Task task){
        return taskList.contains(task);
    }

    public void assigneTask(Task task, ArrayList<Turn> tlList, String portion, Time duration, Cook cook)
    throws SSException {
        task.assigneTask(task,tlList,portion,duration,cook);

    }

    public void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    public void modifyTask(Task task, ArrayList<Turn> tlList, String portion, Time duration, Cook cook)     throws SSException {
        task.modifyTask(task,tlList,portion,duration,cook);

    }

    public void disassignTask(Task task) {
        task.disassignTask();
    }

    public void taskDone(Task task) {
        task.done();
    }
}
