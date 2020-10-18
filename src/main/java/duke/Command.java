package duke;
public class Command {
    private String commandType;
    private String inputMessage;
    private boolean isDescriptionEmpty;

    public Command(String commandType, String inputMessage, boolean isDescriptionEmpty) {
        this.commandType = commandType;
        this.inputMessage = inputMessage;
        this.isDescriptionEmpty = isDescriptionEmpty;
    }

    public String getCommandType() {
        return commandType;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public boolean isDescriptionEmpty() {
        return isDescriptionEmpty;
    }
}
