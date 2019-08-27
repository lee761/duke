import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.ArrayList;

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

        // Level 3: Mark as Done
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ArrayList<Task> tasks = new ArrayList<>(100);
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int count = 0;
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    System.out.println(++count + ". [ " + t.getStatusIcon() + " ] " + t.description);
                }
            } else if (input.contains("done ")) {
                String[] taskNum = input.split(Pattern.quote(" "));
                int num = Integer.parseInt(taskNum[1]) - 1;
                Task doneTask = tasks.get(num);
                doneTask.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n[ " + doneTask.getStatusIcon() + " ] " + doneTask.description);
            } else {
                Task todo = new Task(input);
                System.out.println("added: " + input);
                tasks.add(todo);
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}