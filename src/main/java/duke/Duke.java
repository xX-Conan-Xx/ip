package duke;
import duke.task.Task;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a command to be executed. A <code>Command</code> object corresponds to
 * the command's type and description along with whether it is the bye command
 */
public class Duke {
    public static void main(String[] args) throws IOException {
        String line;
        ArrayList<Task> tasks = new ArrayList<>();//create a ArrayList to store the tasks
        Scanner in = new Scanner(System.in);
        File file = new File("duke.txt");//create a file object
        if(file.exists()==false){//if the file does not exit then create one
            file.createNewFile();
        }
        FileInputStream fis=new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(file,true);
        Scanner sc=new Scanner(fis);
        final String SPLIT_LINE = "____________________________________________________________";
        Ui.greeting(SPLIT_LINE);//print hello
        line = Ui.readFile(tasks, in, sc);//read file
        Ui.mainSystem(line, tasks, in, SPLIT_LINE);//executing main system
        Ui.write(tasks,file);//write to file
        Ui.bye(SPLIT_LINE);// print bye
    }
}
