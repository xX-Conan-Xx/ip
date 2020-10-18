package duke;
public class Parser {
    public static Command parseCommand(String inputMessage) {
        String[] inputWords = inputMessage.split(Ui.INPUT_SPACE);
        String commandType = inputWords[Ui.INPUT_COMMAND];
        boolean isDescriptionEmpty = false;
        if(inputWords.length==1){
            isDescriptionEmpty = true;
        }

        return new Command(commandType, inputMessage,isDescriptionEmpty);
    }
}
