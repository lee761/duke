import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Formatter;
import java.util.regex.Pattern;

public class read {
    private Scanner scan;

    public void openFile() throws FileNotFoundException {
        scan = new Scanner(new File("D:\\data\\duke.txt"));
    }

    public Task[] readFile(Task[] tasks, int x) {
        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            String[] t = input.split(Pattern.quote(" | "));
            if (t.length == 3) {
                ToDo newTodo = new ToDo(t[2]);
                if (t[1].equals("\u2713")) {
                    newTodo.isDone = true;
                } else {
                    newTodo.isDone = false;
                }
                tasks[x] = newTodo;
            } else {
                if (t[0].equals("deadline")) {
                    Deadline newDl = new Deadline(t[2], t[3]);
                    if (t[1].equals("\u2713")) {
                        newDl.isDone = true;
                    } else {
                        newDl.isDone = false;
                    }
                    tasks[x] = newDl;
                } else {
                    Event newEv = new Event(t[2], t[3]);
                    if (t[1].equals("\u2713")) {
                        newEv.isDone = true;
                    } else {
                        newEv.isDone = false;
                    }
                    tasks[x] = newEv;
                }
            }
        }
        return tasks;
    }

    public void closeFile(){
        scan.close();
    }
}