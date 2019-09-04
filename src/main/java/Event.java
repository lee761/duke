public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String taskType() {
        return "[E]";
    }

    @Override
    public String others() {
        return " (at: " + at + ")";
    }

    public String extra() {
        return " at: " + at;
    }

    @Override
    public String saveTxt() {
        return ("E | " + super.getStatusIcon() + " | " + super.description + extra() + "\n");
    }
}