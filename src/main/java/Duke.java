import java.util.Scanner;
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

        // Level 1: Greet, Echo, Exit
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scan = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            input = scan.nextLine();
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
