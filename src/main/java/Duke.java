import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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
        UI.greeting(SPLIT_LINE);//print hello
        line = UI.readFile(tasks, in, sc);//read file
        UI.mainSystem(line, tasks, in, SPLIT_LINE);//executing main system
        UI.write(tasks,file);//write to file
        UI.bye(SPLIT_LINE);// print bye
    }
}
