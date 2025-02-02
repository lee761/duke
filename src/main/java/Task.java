public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "✔" : "✘"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String taskType() {
        return (" ");
    }

    public String others() {
        return (" ");
    }

    public String saveTxt() {
        return (" ");
    }

    public boolean status() {
        return isDone;
    }
}