package classes;

import java.util.List;

public interface TaskManagerInterface {
    void addTask(Task task);

    void editTask(int id, String newTitle, String newDescription, Priority newPriority, Status newStatus);

    void deleteTask(int id);

    List<Task> listTasksByPriority();

    List<Task> searchTasks(String query);

    List<Task> filterTasksByStatus(Status status);
}
