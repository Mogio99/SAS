package businesslogic.task;
import businesslogic.job.Job;
import businesslogic.menu.Menu;
import businesslogic.service.Service;
import businesslogic.user.User;
import java.util.ArrayList;

public class SummarySheet {
    private ArrayList<Task> taskList;
    private User owner;
    private Service serviceUsed;

    public SummarySheet(Service s, User user, Menu menu) {
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
        this.taskList= /*TODO:non so cosa devo fare*/
        return taskList;
    }
}
