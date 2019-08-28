public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String taskType() {
        return " [D]";
    }

    @Override
    public String others() {
        return " (by:" + by + ")";
    }
}
