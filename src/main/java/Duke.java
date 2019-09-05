import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parsing;
    private Task[] list;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
    }

    public void run() throws FileNotFoundException {
        ui.showStartup();
        ui.line();
        list = storage.readFile();
        tasks = new TaskList(storage, list);
        parsing = new Parser(tasks, ui);
        int y = Storage.y;
        ui.line();
        String input = ui.readInput();
        while (!input.equals("bye")) {
            try {
                DukeException.checkInput(input);
            }
            catch (DukeException e) {
                System.out.println(e);
            }
            parsing.parse(input, y);
            System.out.println("__________________________________________");
            input = ui.readInput();
        }
        System.out.println("__________________________________________");
        ui.exit();
    }
    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }
}