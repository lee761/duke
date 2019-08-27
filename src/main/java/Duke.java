import java.util.Scanner;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

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

        // Level 2: Add, List
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String[] tasks = new String[100];
        int x = 0;
        while (!input.equals("bye")) {
            if (input.equals("list") && tasks.length != 0) {
                int count = 0;
                for (int i = 0; i < x; i++) {
                    System.out.println(++count + ". " + tasks[i]);
                }
            }
            else {
                tasks[x] = input;
                System.out.println("added: " + input);
                x = x + 1;
            }
            input = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
