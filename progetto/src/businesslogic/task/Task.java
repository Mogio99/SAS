package businesslogic.task;
import businesslogic.CatERing;
import businesslogic.SSException;
import businesslogic.job.Job;
import businesslogic.recipe.Recipe;
import businesslogic.shift.TurnKitchen;
import businesslogic.user.User;
import persistence.BatchUpdateHandler;
import persistence.PersistenceManager;
import persistence.ResultHandler;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

public class Task {
    private int idRecipe;
    private int id;
    private int quantity;
    private Time time;
    private boolean done;
    private User cook;
    private ArrayList<TurnKitchen> turnList;
    private Job consistingJob;

    public Task(Job rec) {
        this.consistingJob = rec;
        this.idRecipe = rec.getId();
        turnList = new ArrayList<>();
    }

    public Task() {
        turnList = new ArrayList<>();
    }
    public void setId(int id){this.id=id;}
    public int getId(){return this.id;}
    public int getIdRecipe(){
        return this.idRecipe;
    }

    public void assigneTask(Task task, ArrayList<TurnKitchen> tlList, int portion, Time duration, User cook){
        if(portion!=0) {
            this.quantity = portion;
        }
        if(duration!=null){
            this.time=duration;
        }
        if(cook!=null){
            if(cook.isAvailable(turnList)){
                this.cook=cook;
            }
        }
        if(tlList!=null){
            this.turnList = tlList;
            saveListOnTask(task,tlList);
        }
    }


    public void modifyTask(Task task, ArrayList<TurnKitchen> tlList, int  portion, Time duration, User cook) throws SSException{
        if(portion!=0) {
            this.quantity = portion;
        }
        if(duration!=null){
            this.time=duration;
        }
        if(cook!=null){
            if(cook.isAvailable(task.turnList)){
                this.cook=cook;
            }else{
                throw new SSException();
            }
        }
        if(tlList!=null){
            task.turnList.clear();
            for(int i=0;i<tlList.size();i++){
                task.turnList.add((TurnKitchen) tlList.get(i));
            }
            saveListOnTask(task,tlList);
        }

    }

    public void disassignTask() {
        this.quantity=0;
        this.time=null;
        this.cook=null;
        this.turnList.clear();
        this.done=false;
        try {
            modifyTask(this, null, 0, null, null);
        }catch (SSException e) {
            e.printStackTrace();
        }
    }


    public String toString(){
        return "Task_id= "+this.id+"\n"+
                "Recipe= "+consistingJob.toString()+"\n"+
                "Cook= "+this.cook +"\n"+
                "Quantity= "+this.quantity+"\n"+
                "Time = "+ this.time +"\n"+
                "Done ="+ this.done + "\n";
    }
    public void done(){
        this.done=true;
    }
    /*PERSISTANCE*/
    public void saveDone() {
        String query = "UPDATE task SET done=" + this.done +" WHERE id=" + this.getId() + ";";
        PersistenceManager.executeUpdate(query);

    }
    public static void saveNewTaskInSS(Task task) {
        String name_rec = task.consistingJob.toString();
        String query = "INSERT INTO task (id_recipe,id_summarysheet,name_rec,quantity,cook_id,time,done) VALUES (?,?,?,?,?,?,?);";
        int[] result = PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1,task.getIdRecipe());
                ps.setInt(2, CatERing.getInstance().getTaskManager().getCurrentSS().getId());
                ps.setString(3,name_rec);
                ps.setInt(4,task.quantity);
                if (task.cook != null) {
                    ps.setInt(5,task.cook.getId());
                }else{
                    ps.setInt(5,0);
                }
                ps.setTime(6,task.time);
                ps.setBoolean(7,task.done);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                if (count == 0) {
                    task.id = rs.getInt(1);
                }
            }
        });
    }
    public static Task loadTaskById(int id){
        String query ="SELECT * from task WHERE id="+id+";";
        ArrayList<Integer> list= new ArrayList<Integer>();
        Task t=new Task();
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                t.id=rs.getInt("id");
                t.idRecipe=rs.getInt("id_recipe");
                t.consistingJob=Recipe.loadRecipeById(rs.getInt("id_recipe"));
                t.quantity=rs.getInt("quantity");
                t.cook=User.loadUserById(rs.getInt("cook_id"));
                t.time=rs.getTime("time");
                t.done=rs.getBoolean("done");

                String query="SELECT * FROM catering.turn_list WHERE task_id="+t.id;
                PersistenceManager.executeQuery(query, new ResultHandler() {
                    @Override
                    public void handle(ResultSet rs) throws SQLException {
                        list.add(rs.getInt("turn_id"));
                    }
                });

            }
        });
        for(int i=0;i<list.size();i++) {
            t.turnList.add(TurnKitchen.loadKitchenTurnById(list.get(i)));
        }

        return t;
    }

    public void remove() {
        String query = "DELETE FROM task WHERE id = "+this.id;
        PersistenceManager.executeUpdate(query);
    }
    public void removeTurn() {
        String query = "DELETE FROM turn_list WHERE task_id = "+this.id;
        PersistenceManager.executeUpdate(query);
    }

    private void saveListOnTask(Task task, ArrayList<TurnKitchen> tlList) {
        String paramQuery = "INSERT INTO turn_list (turn_id, task_id) values (?, ?)";
        PersistenceManager.executeBatchUpdate(paramQuery, tlList.size(), new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1, tlList.get(batchCount).getId());
                ps.setInt(2, task.getId());
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
            }
        });
    }

    public void saveTaskModified(Task task) {
        String time = task.time == null ? null : "'" + task.time + "'";
        String query;
        if(this.cook!=null) {
            query = "UPDATE task SET quantity=" + task.quantity +
                    ", time=" + time +
                    ", cook_id=" + task.cook.getId() +
                    ", quantity="+ task.quantity+
                    ", id_recipe=" + task.consistingJob.getId() +
                    " WHERE id=" + task.getId() + "; ";
        }else{
            query = "UPDATE task SET quantity=" + task.quantity +
                    ", time=" + time +
                    ", cook_id=" + null +
                    ", quantity="+ task.quantity+
                    ", id_recipe=" + task.consistingJob.getId() +
                    " WHERE id=" + task.getId() + "; ";
        }
        PersistenceManager.executeUpdate(query);
    }
}
