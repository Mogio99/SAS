package businesslogic.task;

public interface KTaskEventReceiver {
    /*in un'intefaccia i metodi sono dichiatrati senza codice*/
    public void updateSSCreated(SummarySheet ss);

    public void updateTaskAdded(Task t);
}
