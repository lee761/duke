import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    /**
     * To print the word Duke.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Level 4: ToDos, Events, Deadlines
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("__________________________________________");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Task[] tasks = new Task[100];
        int x = 0;
        while (!input.equals("bye")) {
            System.out.println("__________________________________________");
            String type, add;
            if (input.equals("list") && x != 0) {
                int count = 0;
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < x; i++) {
                    Task cmd = tasks[i];
                    type = cmd.taskType();
                    add = cmd.others();
                    System.out.println(++count + ". " + type + "[" + cmd.getStatusIcon() + "] " + cmd.description + add);
                }
            } else if (input.contains("done")) {
                String[] tasknum = input.split(Pattern.quote(" "));
                int num = Integer.parseInt(tasknum[1]) - 1;
                Task t = tasks[num];
                t.markAsDone();
                type = t.taskType();
                add = t.others();
                System.out.println("Nice! I've marked this task as done: \n" + type + "[" + t.getStatusIcon() + "] " + t.description + add);
            } else {
                if (input.contains("todo")) {
                    String[] taskOne = input.split(" ", 2);
                    ToDo newTodo = new ToDo(taskOne[1]);
                    tasks[x] = newTodo;
                    x = x + 1;
                    System.out.println("Got it. I've added this task: \n" + newTodo.taskType() + "[" + newTodo.getStatusIcon() + "] " + newTodo.description + "\nNow you have " + x + " tasks in the list.");
                }
                else if (input.contains("deadline")) {
                    String[] c = input.split(" ", 2);
                    String[] taskTwo = c[1].split(" /by");
                    Deadline newDl = new Deadline(taskTwo[0], taskTwo[1]);
                    tasks[x] = newDl;
                    x = x + 1;
                    System.out.println("Got it. I've added this task: \n" + newDl.taskType() + "[" + newDl.getStatusIcon() + "] " + newDl.description + " (by:" + newDl.by + ")" + "\nNow you have " + x + " tasks in the list.");
                }
                else if (input.contains("event")) {
                    String[] e = input.split(" ", 2);
                    String[] taskThree = e[1].split(" /at");
                    Event newEv = new Event(taskThree[0], taskThree[1]);
                    tasks[x] = newEv;
                    x = x + 1;
                    System.out.println("Got it. I've added this task: \n" + newEv.taskType() + "[" + newEv.getStatusIcon() + "] " + newEv.description + " (at:" + newEv.at + ")" + "\nNow you have " + x + " tasks in the list.");
                }
            }
            System.out.println("__________________________________________");
            input = scan.nextLine();
        }
        System.out.println("__________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
    }
}
