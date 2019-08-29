import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.regex.Pattern;

public class write {

    private Formatter print;
    private Scanner scan;
    private Writer output;

    public void openFile() throws FileNotFoundException {
        print = new Formatter(new File("D:\\data\\duke.txt"));
    }

    public void undoneTodo(String s) throws IOException {
        output = new BufferedWriter((new FileWriter("D:\\data\\duke.txt", true)));
        output.append("T | \u2718 | " + s + "\n");
        output.close();
    }

    public void undoneDl(String s1, String s2) throws IOException {
        output = new BufferedWriter((new FileWriter("D:\\data\\duke.txt", true)));
        output.append("D | \u2718 | " + s1 + " | " + s2 + "\n");
        output.close();
    }

    public void undoneEvent(String s1, String s2) throws IOException {
        output = new BufferedWriter((new FileWriter("D:\\data\\duke.txt", true)));
        output.append("E | \u2718 | " + s1 + " | " + s2 + "\n");
        output.close();
    }

    public void doneTask(String type, String description) throws IOException {
        File oldF = new File("D:\\data\\duke.txt");
        File newF = new File("D:\\data\\temp.txt");

        FileWriter file1 = new FileWriter("D:\\data\\temp.txt", true);
        BufferedWriter file2 = new BufferedWriter(file1);
        PrintWriter file3 = new PrintWriter(file2);
        scan = new Scanner(new File("D:\\data\\duke.txt"));
        while (scan.hasNextLine()) {
            String userInput = scan.nextLine();
            String[] tokens = userInput.split(Pattern.quote(" | "));
            if (tokens[0].equals(type) && tokens[2].equals(description)) {
                if (tokens.length == 3) {
                    file3.println(tokens[0] + " | \u2713 | " + tokens[2]);
                } else {
                    file3.println(tokens[0] + " | \u2713 | " + tokens[2] + " | " + tokens[3]);
                }
            } else {
                file3.println(userInput);
            }
        }
        scan.close();
        file3.flush();
        file3.close();
        oldF.delete();
        newF.renameTo(new File("D:\\data\\duke.txt"));
    }

    public void closeFile(){
        print.close();
    }
}