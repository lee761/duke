import java.io.FileNotFoundException;

public class Parser {
    private Storage save;
    private Ui ui;
    private TaskList tasklist;

    public Parser(TaskList tasklist, Ui ui) {
        this.tasklist = tasklist;
        this.ui = ui;
    }
    /*
     * To understand what they input command is
     */
    public void parse(String input, int y) {
        ui.line();
        if (input.equals("list")) {
            tasklist.listTask(y);
        } else if (input.contains("done")) {
            tasklist.doneTask(input, y);
        } else if (input.contains("delete")) {
            tasklist.deleteTask(input, y);
        } else if (input.contains("find")) {
            tasklist.findTask(input, y);
        } else if (input.contains("todo")) {
            tasklist.addTodo(input, y);
        } else if (input.contains("deadline")) {
            tasklist.addDeadline(input, y);
        } else if (input.contains("event")) {
            tasklist.addEvent(input, y);
        }
    }
}
