import java.util.Scanner;

import classes.TaskManager;
import classes.Task;
import classes.Priority;
import classes.Status;
import classes.TaskManagerInterface;

public class App {
    public static void main(String[] args) {

        TaskManagerInterface manager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        int option;
        do {
            System.out.println("\n=== Menú de Gestión de Tareas ===");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Editar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Listar tareas por prioridad");
            System.out.println("5. Buscar tarea");
            System.out.println("6. Filtrar tareas por estado");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarTarea(manager, scanner);
                    break;
                case 2:
                    editarTarea(manager, scanner);
                    break;
                case 3:
                    eliminarTarea(manager, scanner);
                    break;
                case 4:
                    listarTareas(manager);
                    break;
                case 5:
                    buscarTareas(manager, scanner);
                    break;
                case 6:
                    filtrarTareas(manager, scanner);
                    break;
                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 7);

        scanner.close();
    }

    private static void agregarTarea(TaskManagerInterface manager, Scanner scanner) {
        System.out.print("Ingrese el ID de la tarea: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el título: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese la descripción: ");
        String description = scanner.nextLine();
        System.out.print("Ingrese la prioridad (ALTA, MEDIA, BAJA): ");
        Priority priority = Priority.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Ingrese el estado (PENDIENTE, EN_PROGRESO, COMPLETADA): ");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());

        Task task = new Task(id, title, description, priority, status);
        manager.addTask(task);
        System.out.println("Tarea agregada exitosamente.");
    }

    private static void editarTarea(TaskManagerInterface manager, Scanner scanner) {
        System.out.print("Ingrese el ID de la tarea a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nuevo título: ");
        String newTitle = scanner.nextLine();
        System.out.print("Ingrese la nueva descripción: ");
        String newDescription = scanner.nextLine();
        System.out.print("Ingrese la nueva prioridad (ALTA, MEDIA, BAJA): ");
        Priority newPriority = Priority.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Ingrese el nuevo estado (PENDIENTE, EN_PROGRESO, COMPLETADA): ");
        Status newStatus = Status.valueOf(scanner.nextLine().toUpperCase());

        manager.editTask(id, newTitle, newDescription, newPriority, newStatus);
        System.out.println("Tarea editada exitosamente.");
    }

    private static void eliminarTarea(TaskManagerInterface manager, Scanner scanner) {
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        int id = scanner.nextInt();
        manager.deleteTask(id);
        System.out.println("Tarea eliminada exitosamente.");
    }

    private static void listarTareas(TaskManagerInterface manager) {
        System.out.println("\n=== Lista de Tareas por Prioridad ===");
        for (Task task : manager.listTasksByPriority()) {
            System.out.println(task);
        }
    }

    private static void buscarTareas(TaskManagerInterface manager, Scanner scanner) {
        System.out.print("Ingrese el término de búsqueda (título o descripción): ");
        String query = scanner.nextLine();
        System.out.println("\n=== Resultados de la Búsqueda ===");
        for (Task task : manager.searchTasks(query)) {
            System.out.println(task);
        }
    }

    private static void filtrarTareas(TaskManagerInterface manager, Scanner scanner) {
        System.out.print("Ingrese el estado para filtrar (PENDIENTE, EN_PROGRESO, COMPLETADA): ");
        Status status = Status.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("\n=== Tareas Filtradas por Estado ===");
        for (Task task : manager.filterTasksByStatus(status)) {
            System.out.println(task);
        }
    }
}
