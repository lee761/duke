import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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
        File file = new File("D:\\data");
        File data = new File("D:\\data\\duke.txt");
        int x = 0;
        Task[] tasks = new Task[100];
        if (data.exists()) {
            read r = new read();
            r.openFile();
            tasks = r.readFile(tasks, x);
            r.closeFile();
        } else {
            System.out.println("No task currently");
            file.mkdir();
            FileWriter newTxt = new FileWriter("D:\\data\\duke.txt");
        }
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("__________________________________________");
        String d, b;
        System.out.println("Here are your current tasks:");
        print(tasks, x);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while(!input.equals("bye")) {
            try {
                checkInput(input);
                System.out.println("__________________________________________");
                String type, add;
                if (input.equals("list") && x != 0) {
                    int count = 0;
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < x; i++) {
                        print(tasks, x);
                    }
                } else if (input.contains("done")) {
                    String[] tasknum = input.split(Pattern.quote(" "));
                    int num = Integer.parseInt(tasknum[1]) - 1;
                    try {
                        checkTask(tasks, num, x);
                        Task t = tasks[num];
                        t.markAsDone();
                        type = t.taskType();
                        add = t.others();
                        write done = new write();
                        done.doneTask(type, t.description);
                        System.out.println("Nice! I've marked this task as done: \n" + type + "[" + t.getStatusIcon() + "] " + t.description + add);
                    } catch (DukeException | IOException e) {
                        System.out.println(e);
                    }
                } else {
                    write newData = new write();
                    if (input.contains("todo")) {
                        try {
                            String[] taskOne = input.split(" ", 2);
                            checkCmd(taskOne);
                            ToDo newTodo = new ToDo(taskOne[1]);
                            tasks[x] = newTodo;
                            newData.undoneTodo(input.trim());
                            x = x + 1;
                            System.out.println("Got it. I've added this task: \n" + newTodo.taskType() + "[" + newTodo.getStatusIcon() + "] " + newTodo.description + "\nNow you have " + x + " tasks in the list.");
                        } catch (DukeException | IOException e) {
                            System.out.println(e);
                        }
                    } else if (input.contains("deadline")) {
                        try {
                            String[] c = input.split(" ", 2);
                            checkCmd(c);
                            String[] taskTwo = c[1].split(" /by");
                            Deadline newDl = new Deadline(taskTwo[0], taskTwo[1]);
                            tasks[x] = newDl;
                            taskTwo[1] = convertDate(taskTwo[1]);
                            newData.undoneDl(c[0].trim(), c[1].trim());
                            x = x + 1;
                            System.out.println("Got it. I've added this task: \n" + newDl.taskType() + "[" + newDl.getStatusIcon() + "] " + newDl.description + " (by:" + newDl.by + ")" + "\nNow you have " + x + " tasks in the list.");
                        } catch (DukeException | IOException | ParseException e) {
                            System.out.println(e);
                        }
                    } else if (input.contains("event")) {
                        try {
                            String[] a = input.split(" ", 2);
                            checkCmd(a);
                            String[] taskThree = a[1].split(" /at");
                            Event newEv = new Event(taskThree[0], taskThree[1]);
                            tasks[x] = newEv;
                            taskThree[1] = convertDate(taskThree[1]);
                            newData.undoneEvent(a[0].trim(), a[1].trim());
                            x = x + 1;
                            System.out.println("Got it. I've added this task: \n" + newEv.taskType() + "[" + newEv.getStatusIcon() + "] " + newEv.description + " (at:" + newEv.at + ")" + "\nNow you have " + x + " tasks in the list.");
                        } catch (DukeException | IOException | ParseException e) {
                            System.out.println(e);
                        }
                    }
                }
            } catch (DukeException e) {
                System.out.println("__________________________________________");
                System.out.println(e);
            }
            System.out.println("__________________________________________");
            input = scan.nextLine();
        }
            System.out.println("__________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
    }


        // To catch invalid inputs
        private static void checkInput(String in) throws DukeException {
            String[] inputs = {"todo", "deadline", "event", "list", "done"};
            boolean checker = false;
            for (int i = 0; i < inputs.length; i++) {
                if (in.contains(inputs[i])) {
                    checker = true;
                }
            }
            if (in.isEmpty()) {
                throw new DukeException("Task cannot be empty. Please type a task.");
            }
            else if (!checker) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        // To catch invalid "done" task
        private static void checkTask(Task[] Ta, int x, int size) throws DukeException {
            if (x >= size || x < 0) {
                throw new DukeException("☹ OOPS!!! There is no such task to be done. Please enter a valid task number.");
            }
            else {
                Task checkTa = Ta[x];
                if (checkTa.status()) {
                    throw new DukeException("This task has been done. Please enter an incomplete task.");
                }
            }
        }

        // To catch invalid task
        private static void checkCmd(String[] t) throws DukeException {
            if (t.length < 2 || t[1].isEmpty()) {
                throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
            }
        }

        public static void print(Task[] Ta, int x) {
            int count = 0;
            String type, add;
            for (int i = 0; i < x; i++) {
                Task cmd = Ta[i];
                type = cmd.taskType();
                add = cmd.others();
                System.out.println(++count + ". " + type + "[" + cmd.getStatusIcon() + "] " + cmd.description + add);
            }
        }

    static String convertDate(String s) throws ParseException {
        String[] tokens = s.split(Pattern.quote("/"));
        SimpleDateFormat sourceFormat = new SimpleDateFormat("d/MM/yyyy HHmm");
        SimpleDateFormat targetFormatDate = new SimpleDateFormat("d");
        SimpleDateFormat targetFormat = new SimpleDateFormat("' of' MMMM yyyy, hh:mm aa");
        Date sourceDate = sourceFormat.parse(s);
        String converted = targetFormatDate.format(sourceDate) + getSuffix(Integer.parseInt(tokens[0])) + targetFormat.format(sourceDate);
        return converted;
    }

    //function to get the suffix for the date format
    static String getSuffix(int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }
}


