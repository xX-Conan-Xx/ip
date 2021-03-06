package duke.task;
public class Deadline extends Task {

    protected String by;

    public Deadline(String line) {
        super(line.substring(9,(line.indexOf("/")-1)));
        this.by = line.substring((line.indexOf("/")+4));
    }

    public Deadline(String line, boolean done) {
        super(line.substring(9,(line.indexOf("/")-1)),done);
        this.by = line.substring((line.indexOf("/")+4));
    }

    public Task completeTask(){
        this.setDone(true);
        return this;
    }
    @Override
    public String toString(){
        return "[" + "D" + "]" + super.toString() + " (by: "+ by +")";
    }
}