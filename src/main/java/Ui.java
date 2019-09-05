import java.util.Scanner;

public class Ui {

    public Ui() {
    }

     public void showStartup() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("Here are your current list of tasks:");
    }

    public void line() {
        System.out.println("__________________________________________");
    }

    String readInput() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
