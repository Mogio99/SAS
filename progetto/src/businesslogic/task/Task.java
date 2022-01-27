package businesslogic.task;
import businesslogic.SSException;
import businesslogic.disponibility.Cook;
import businesslogic.job.Job;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuItem;
import businesslogic.recipe.Recipe;
import businesslogic.shift.Turn;
import businesslogic.shift.TurnKitchen;
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
    private String quantity;
    private Time time;
    private boolean done;
    private Cook cook;
    private ArrayList<Turn> turnList;
    private Job consistingJob;

    public Task(Job rec) {
        this.consistingJob = rec;
        this.idRecipe = rec.getId();
        turnList = new ArrayList<>();
    }

    public int getIdRecipe(){
        return this.idRecipe;
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
    /*PERSISTANCE*/
    public void saveNewTaskInSS(Task task, int id) {
        String name_rec = task.consistingJob.toString();
        String query = "INSERT INTO task (id_recipe,id_summarysheet,name_rec) VALUES (?, ?,?);";
        int[] result = PersistenceManager.executeBatchUpdate(query, 1, new BatchUpdateHandler() {
            @Override
            public void handleBatchItem(PreparedStatement ps, int batchCount) throws SQLException {
                ps.setInt(1,task.getIdRecipe());
                ps.setInt(2, id);
                ps.setString(3,name_rec);
            }

            @Override
            public void handleGeneratedIds(ResultSet rs, int count) throws SQLException {
                if (count == 0) {
                    task.id = rs.getInt(1);
                }
            }
        });
    }
    public static Task loadTaskById(int id_rep) {

        String query="SELECT * FROM task WHERE id_recipe = "+id_rep;
        final boolean[] exists = {false};
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                exists[0] =true;
            }
        });
        return  new Task(Recipe.loadRecipeById(20));
    }

}
