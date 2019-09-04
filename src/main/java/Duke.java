import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

public class Duke {
    /**
     * To print the word Duke.
     */
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Level 7: Save
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("__________________________________________");
        Scanner scan = new Scanner(System.in);
        int x = 0;
//        Task[] tasks = new Task[100];
        System.out.println("Here are your current list of tasks:");
        save Save = new save();
//        Save.readFile();
        Task[] tasks = Save.readFile();
        System.out.println("__________________________________________");
        String input = scan.nextLine();
//        save saveFile = new save();
        int y = save.y;
        while (!input.equals("bye")) {
            try {
                DukeException.checkInput(input);
                System.out.println("__________________________________________");
                String type, add;
                if (input.equals("list")) {
                    int count = 0;
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < (x + y); i++) {
                        Task cmd = tasks[i];
                        type = cmd.taskType();
                        add = cmd.others();
                        System.out.println(++count + ". " + type + "[" + cmd.getStatusIcon() + "] " + cmd.description + add);
                    }
                } else if (input.contains("done")) {
                    String[] taskNum = input.split(Pattern.quote(" "));
                    int num = Integer.parseInt(taskNum[1]) - 1;
                    try {
                        DukeException.checkTask(tasks, num, x+y);
                        Task t = tasks[num];
                        t.markAsDone();
                        type = t.taskType();
                        add = t.others();
                        System.out.println("Nice! I've marked this task as done: \n" + type + "[" + t.getStatusIcon() + "] " + t.description + add);
                        Save.saveToFile(tasks, (x + y));
                    } catch (DukeException e) {
                        System.out.println(e);
                    }
                } else {
                    if (input.contains("todo")) {
                        try {
                            String[] taskOne = input.split(" ", 2);
                            DukeException.checkCmd(taskOne);
                            ToDo newTodo = new ToDo(taskOne[1]);
                            tasks[x+y] = newTodo;
                            x = x + 1;
                            System.out.println("Got it. I've added this task: \n" + newTodo.taskType() + "[" + newTodo.getStatusIcon() + "] " + newTodo.description + "\nNow you have " + (x + y) + " tasks in the list.");
                            Save.saveToFile(tasks, (x + y));
                        } catch (DukeException e) {
                            System.out.println(e);
                        }
                    } else if (input.contains("deadline")) {
                        try {
                            String[] c = input.split(" ", 2);
                            DukeException.checkCmd(c);
                            String[] taskTwo = c[1].split(" /by ");
                            Deadline newDl = new Deadline(taskTwo[0], taskTwo[1]);
                            tasks[x+y] = newDl;
                            x = x + 1;
                            System.out.println("Got it. I've added this task: \n" + newDl.taskType() + "[" + newDl.getStatusIcon() + "] " + newDl.description + " (by: " + newDl.by + ")" + "\nNow you have " + (x + y) + " tasks in the list.");
                            Save.saveToFile(tasks, (x + y));
                        } catch (DukeException e) {
                            System.out.println(e);
                        }
                    } else if (input.contains("event")) {
                        try {
                            String[] a = input.split(" ", 2);
                            DukeException.checkCmd(a);
                            String[] taskThree = a[1].split(" /at ");
                            Event newEv = new Event(taskThree[0], taskThree[1]);
                            tasks[x+y] = newEv;
                            x = x + 1;
                            System.out.println("Got it. I've added this task: \n" + newEv.taskType() + "[" + newEv.getStatusIcon() + "] " + newEv.description + " (at: " + newEv.at + ")" + "\nNow you have " + (x + y) + " tasks in the list.");
                            Save.saveToFile(tasks, (x + y));
                        } catch (DukeException e) {
                            System.out.println(e);
                        }
                    }
                }
            }
            catch (DukeException e) {
                System.out.println("__________________________________________");
                System.out.println(e);
            }
            System.out.println("__________________________________________");
            input = scan.nextLine();
        }
        System.out.println("__________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
    }
}



