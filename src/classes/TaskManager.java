package classes;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager implements TaskManagerInterface {
    private LinkedList<Task> tasks;

    public TaskManager() {
        this.tasks = new LinkedList<>();
    }

    @Override
    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public void editTask(int id, String newTitle, String newDescription, Priority newPriority, Status newStatus) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setTitle(newTitle);
                task.setDescription(newDescription);
                task.setPriority(newPriority);
                task.setStatus(newStatus);
                return;
            }
        }
    }

    @Override
    public void deleteTask(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    @Override
    public List<Task> listTasksByPriority() {
        return tasks.stream()
                .sorted((t1, t2) -> t1.getPriority().compareTo(t2.getPriority()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> searchTasks(String query) {
        return tasks.stream()
                .filter(task -> task.getTitle().contains(query) || task.getDescription().contains(query))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> filterTasksByStatus(Status status) {
        return tasks.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }
}
