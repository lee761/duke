import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TaskList {
    private Storage save;
    private Task[] tasks;
    private int x = 0;

    public TaskList(Storage save, Task[] list) throws FileNotFoundException {
        this.tasks = list;
        this.save = save;
    }

    public void listTask(int y) {
        int count = 0;
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < (x + y); i++) {
            Task cmd = tasks[i];
            System.out.println(++count + ". " + cmd.taskType() + "[" + cmd.getStatusIcon() + "] " + cmd.description + cmd.others());
        }
    }

    public void doneTask(String input, int y) {
        String[] taskNum = input.split(Pattern.quote(" "));
        int num = Integer.parseInt(taskNum[1]) - 1;
        try {
            DukeException.checkTask(tasks, num, x+y);
            Task t = tasks[num];
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + t.taskType() + "[" + t.getStatusIcon() + "] " + t.description + t.others());
            save.saveToFile(tasks, (x + y));
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public void deleteTask(String input, int y) {
        String[] taskNum = input.split(Pattern.quote(" "));
        int num = Integer.parseInt(taskNum[1]) - 1;
        Task t = tasks[num];
        ArrayList<Task> list = new ArrayList<>();
        for (int k = 0; k < x + y; k++) {
            list.add(tasks[k]);
        }
        list.remove(num);
        for (int i = 0; i < list.size(); i++) {
            tasks[i] = list.get(i);
        }
        x = x - 1;
        System.out.println("Noted. I've removed this task: \n" + t.taskType() + "[" + t.getStatusIcon() + "] " + t.description + t.others() + "\nNow you have " + (x + y) + " tasks in the list.");
        save.saveToFile(tasks, (x + y));
    }

    public void findTask(String input, int y) {
        int count  = 0;
        String[] findTask = input.split(" ");
        System.out.println("Here are the matching tasks in your list: \n");
        for (int i = 0; i < x + y; i++) {
            if (tasks[i].description.contains(findTask[1])) {
                System.out.print(++count + ". " + tasks[i].taskType() + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description + tasks[i].others() + "\n");
            }
        }
        if (count == 0) {
            System.out.println("There are no such task in the list.");
        }
    }

    public void addTodo(String input, int y) {
        try {
            String[] taskOne = input.split(" ", 2);
            DukeException.checkCmd(taskOne);
            ToDo newTodo = new ToDo(taskOne[1]);
            tasks[x+y] = newTodo;
            x = x + 1;
            System.out.println("Got it. I've added this task: \n" + newTodo.taskType() + "[" + newTodo.getStatusIcon() + "] " + newTodo.description + "\nNow you have " + (x + y) + " tasks in the list.");
            save.saveToFile(tasks, (x + y));
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public void addDeadline(String input, int y) {
        try {
            String[] c = input.split(" ", 2);
            DukeException.checkCmd(c);
            String[] taskTwo = c[1].split(" /by ");
            Deadline newDl = new Deadline(taskTwo[0], taskTwo[1]);
            tasks[x+y] = newDl;

            dateTime newFormat = new dateTime();
            newDl.by = newFormat.convert(taskTwo[1]);
            if (dateTime.isValidFormat(taskTwo[1])) {
                x = x + 1;
                System.out.println("Got it. I've added this task: \n" + newDl.taskType() + "[" + newDl.getStatusIcon() + "] " + newDl.description + " (by: " + newDl.by + ")" + "\nNow you have " + (x + y) + " tasks in the list.");
                save.saveToFile(tasks, (x + y));
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
        catch (ParseException e) {
            System.out.println("Wrong Date/Time format");
        }
    }

    public void addEvent(String input, int y) {
        try {
            String[] a = input.split(" ", 2);
            DukeException.checkCmd(a);
            String[] taskThree = a[1].split(" /at");
            Event newEv = new Event(taskThree[0], taskThree[1]);
            tasks[x+y] = newEv;
            x = x + 1;
            System.out.println("Got it. I've added this task: \n" + newEv.taskType() + "[" + newEv.getStatusIcon() + "] " + newEv.description + " (at: " + newEv.at + ")" + "\nNow you have " + (x + y) + " tasks in the list.");
            save.saveToFile(tasks, (x + y));
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
