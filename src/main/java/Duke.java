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

        // Level 3: Mark as Done
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Task[] tasks = new Task[100];
        int x = 0;
        while (!input.equals("bye")) {
            if (input.equals("list") && x != 0) {
                int count = 0;
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < x; i++) {
                    Task cmd = tasks[i];
                    System.out.println(++count + ". [ " + cmd.getStatusIcon() + " ] " + cmd.description);
                }
            } else if (input.contains("done ")) {
                String[] tasknum = input.split(Pattern.quote(" "));
                int num = Integer.parseInt(tasknum[1]) - 1;
                Task t = tasks[num];
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done: \n[ " + t.getStatusIcon() + " ] " + t.description);
            } else {
                Task newTask = new Task(input);
                System.out.println("added: " + input);
                tasks[x] = newTask;
                x = x + 1;
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}