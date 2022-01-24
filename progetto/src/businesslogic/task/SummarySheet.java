package businesslogic.task;
import businesslogic.SSException;
import businesslogic.disponibility.Cook;
import businesslogic.event.ServiceInfo;
import businesslogic.job.Job;
import businesslogic.menu.Menu;
import businesslogic.recipe.Recipe;
import businesslogic.shift.Turn;
import businesslogic.user.User;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class SummarySheet {
    private ArrayList<Task> taskList;
    private User owner;
    private ServiceInfo serviceUsed;
    private int id;

    public SummarySheet(ServiceInfo s, User user, Menu menu) {
        this.owner = user;
        this.serviceUsed = s;
        ArrayList<Recipe> arrayListRecipe = menu.getAllRecipe();
        taskList = new ArrayList<Task>();
        int i = 0;
        for(i=0;i<arrayListRecipe.size();i++){
            System.out.println(arrayListRecipe.get(i));
            Task task = new Task(arrayListRecipe.get(i));
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

    public static void saveNewSS(SummarySheet ss) {
            String ssInsert = "INSERT INTO catering.SummarySheet(user, service_id,service_name) VALUES (?, ?,?);";
            int[] result = PersistenceManager.executeBatchUpdate(ssInsert, 1, new BatchUpdateHandler() {
                @Override
                public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                    ps.setInt(1,(ss.owner.getId()));
                    ps.setInt(2, ss.serviceUsed.getId());
                    ps.setString(3, ss.serviceUsed.getName());
                }

                @Override
                public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                    if (count == 0) {
                        ss.id = rs.getInt(1);
                    }
                }
            });

    }
}


