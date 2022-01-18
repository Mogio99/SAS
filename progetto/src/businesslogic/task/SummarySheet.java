package businesslogic.task;
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
        ArrayList<recipe.Recipe> arrayListRecipe = menu.getAllRecipe;
        taskList = new ArrayList<Task>();
        int i = 0;
        for(i=0;i<arrayListRecipe.size();i++){
            Task task = new Task(arrayListRecipe.get(i));
        }
    }
}
