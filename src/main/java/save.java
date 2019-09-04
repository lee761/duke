import java.io.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class save {
    public static int y;

    public save() {
    }

    void saveToFile(Task[] tasks, int x) {
        try {
            File file = new File("D:\\data\\duke.txt");
            PrintWriter output = new PrintWriter(file);
            for (int t = 0; t < x; t++) {
                output.append(tasks[t].saveTxt());
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Task[] readFile() throws FileNotFoundException {
        Scanner in = new Scanner(new File("D:\\data\\duke.txt"));
        Task[] tasks = new Task[100];
        y = 0;
        try {
            while (in.hasNextLine()) {
                String a = in.nextLine();
                String[] arr = a.split(Pattern.quote(" | "), 3);
//                System.out.println(arr[0]);
//                System.out.println(arr[2]);
                if (arr[0].contains("T")) {
                    ToDo todo = new ToDo(arr[2]);
                    tasks[y] = todo;
                    if (arr[1].contains("✘")) {
                        todo.isDone = false;
                    } else {
                        todo.markAsDone();
                    }
                }
                else if (arr[0].contains("D")) {
                    String[] temp_arr = arr[2].split(Pattern.quote("by:"));
//                    System.out.println(temp_arr[0]);
//                    System.out.println(temp_arr[1]);
                    Deadline Dl = new Deadline(temp_arr[0], temp_arr[1]);
                    tasks[y] = Dl;
                    if (arr[1].contains("✘")) {
                        Dl.isDone = false;
                    } else {
                        Dl.markAsDone();
                    }
                }
                else {
                    String[] temp_arr = arr[2].split(Pattern.quote("at:"));
                    Event Ev = new Event(temp_arr[0], temp_arr[1]);
                    tasks[y] = Ev;
                    if (arr[1].contains("✘")) {
                        Ev.isDone = false;
                    } else {
                        Ev.markAsDone();
                    }
                }
                System.out.println(++y + ". " + a);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
        }
        return tasks;
    }
}


