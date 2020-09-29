public class Todo extends Task {

    public Todo(String description) {
        super(description.substring(5));
    }
    public Todo(String description, boolean done){
        super(description.substring(5),done);
    }
    @Override
    public String toString() {
        return "["+"T"+"]"+ super.toString();
    }
}