import java.text.SimpleDateFormat;
import java.util.Date;

public class DukeException extends Exception {
    public DukeException(String error) {
        super(error);
    }
    /*
     * To catch invalid input or empty input
     */
    static void checkInput(String in) throws DukeException {
        String[] inputs = {"todo", "deadline", "event", "list", "done", "delete", "find"};
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

    /*
     * To catch invalid/completed task done
     */
    static void checkTask(Task[] Ta, int x, int size) throws DukeException {
        if (x >= size || x < 0) {
            throw new DukeException("☹ OOPS!!! There is no such task to be done. Please enter a valid task number.");
        }
        else {
            Task checkTa = Ta[x];
            if (checkTa.status()) {
                throw new DukeException("This task has been done.");
            }
        }
    }

    // To catch invalid task
    static void checkCmd(String[] t) throws DukeException {
        if (t.length < 2 || t[1].isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
    }
}
