public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String taskType() {
        return "[D]";
    }

    @Override
    public String others() {
        return " (by: " + by + ")";
    }

    public String extra() {
        return " by: " + by;
    }

    @Override
    public String saveTxt() {
        return ("D | " + super.getStatusIcon() + " | " + super.description + extra() + "\n");
    }
}
