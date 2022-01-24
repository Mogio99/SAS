package businesslogic;
import businesslogic.event.EventManager;
import businesslogic.menu.Menu;
import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.shift.TurnManager;
import businesslogic.task.kTaskManager;
import businesslogic.user.UserManager;
import persistence.MenuPersistence;
import persistence.PersistenceManager;
import persistence.PersistenceTaskManager;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private MenuManager menuMgr;
    private RecipeManager recipeMgr;
    private UserManager userMgr;
    private EventManager eventMgr;
    private TurnManager turnMgr;
    private kTaskManager taskMgr;


    private MenuPersistence menuPersistence;
    private PersistenceTaskManager taskPersistence;

    private CatERing() {
        menuMgr = new MenuManager();
        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
        turnMgr = new TurnManager();
        menuPersistence = new MenuPersistence();
        taskMgr = new kTaskManager();
        taskPersistence = new PersistenceTaskManager();
        menuMgr.addEventReceiver(menuPersistence);
        taskMgr.addEventReceiver(taskPersistence);
    }


    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public EventManager getEventManager() { return eventMgr; }

    public TurnManager getTurnManager(){ return turnMgr;}

    public kTaskManager getTaskManager(){return taskMgr;}
}
