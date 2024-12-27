package test;

import classes.TaskManager;
import classes.Task;
import classes.Priority;
import classes.Status;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    @Test
    public void testAddTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task(1, "Test Task", "Description", Priority.ALTA, Status.PENDIENTE);
        manager.addTask(task);

        List<Task> tasks = manager.listTasksByPriority();
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }

    @Test
    public void testEditTask() {
        TaskManager manager = new TaskManager();
        Task task = new Task(1, "Old Task", "Old Description", Priority.MEDIA, Status.PENDIENTE);
        manager.addTask(task);

        manager.editTask(1, "New Task", "New Description", Priority.ALTA, Status.EN_PROGRESO);

        List<Task> tasks = manager.listTasksByPriority();
        assertEquals("New Task", tasks.get(0).getTitle());
        assertEquals(Status.EN_PROGRESO, tasks.get(0).getStatus());
    }

    @Test
    public void testDeleteTask() {
        TaskManager manager = new TaskManager();
        Task task1 = new Task(1, "Task 1", "Description 1", Priority.ALTA, Status.PENDIENTE);
        Task task2 = new Task(2, "Task 2", "Description 2", Priority.MEDIA, Status.EN_PROGRESO);
        manager.addTask(task1);
        manager.addTask(task2);

        manager.deleteTask(1);

        List<Task> tasks = manager.listTasksByPriority();
        assertEquals(1, tasks.size());
        assertEquals("Task 2", tasks.get(0).getTitle());
    }
}
