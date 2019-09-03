public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String taskType() {
        return "[T]";
    }

    @Override
    public String saveTxt() {
        return ("T | " + super.getStatusIcon() + " | " + super.description + "\n");
    }
}