package duke;
import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String INPUT_SPACE = " ";
    public static final int INPUT_COMMAND = 0;

    public static void bye(String SPLIT_LINE) {//print bye
        System.out.println(SPLIT_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPLIT_LINE);
    }

    public static void greeting(String SPLIT_LINE) {//print greeting
        System.out.println(SPLIT_LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(SPLIT_LINE);
    }

    public static void mainSystem(String line, ArrayList<Task> tasks, Scanner in, String SPLIT_LINE) {
        while (!Parser.parseCommand(line).getCommandType().equals("bye")){
            try{
                String commandType = Parser.parseCommand(line).getCommandType();
                switch (commandType){
                    case "list":
                        System.out.println(SPLIT_LINE);
                        list(tasks);
                        System.out.println(SPLIT_LINE);
                        break;
                    case "done":
                        System.out.println(SPLIT_LINE);
                        done(line, tasks);
                        System.out.println(SPLIT_LINE);
                        break;
                    case "delete":
                        System.out.println(SPLIT_LINE);
                        delete(line, tasks);
                        System.out.println(SPLIT_LINE);
                        break;
                    case "find":
                        System.out.println(SPLIT_LINE);
                        find(line, tasks);
                        System.out.println(SPLIT_LINE);
                        break;
                    case "todo":
                        System.out.println(SPLIT_LINE);
                        todo(line, tasks);
                        System.out.println(SPLIT_LINE);
                        break;
                    case "deadline":
                        System.out.println(SPLIT_LINE);
                        deadline(line, tasks);
                        System.out.println(SPLIT_LINE);
                        break;
                    case "event":
                        System.out.println(SPLIT_LINE);
                        event(line, tasks);
                        System.out.println(SPLIT_LINE);
                        break;
                    default:
                        System.out.println(SPLIT_LINE);
                        System.out.println("☹ OOPS!!!Wrong input. Please refer to the instruction.");
                        System.out.println(SPLIT_LINE);
                }
            }catch (Exception e){
                System.out.println(SPLIT_LINE);
                System.out.println("☹ OOPS!!!Wrong input. Please refer to the instruction.");
                System.out.println(SPLIT_LINE);
            }
            line = in.nextLine();
        }
    }

    private static void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ":" + tasks.get(i).toString());
        }
    }

    private static void find(String line, ArrayList<Task> tasks) {
        System.out.println("  Here are the matching tasks in your list:\n");
        String keyword = line.substring(5);
        int count = 1;
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getDescription().contains(keyword)){

                System.out.println(count + ": " + tasks.get(i));
                count ++;
            }

        }
    }

    private static void delete(String line, ArrayList<Task> tasks) {
        try{
            if (Parser.parseCommand(line).isDescriptionEmpty()) {
                throw new DException();
            }
            int taskNumberDeleted = Integer.parseInt(line.replaceAll("\\D+","")) - 1;
            if(taskNumberDeleted> tasks.size()||taskNumberDeleted<0){
                throw new IndexDoNotExistException();
            }
            System.out.println(" Noted. I've removed this task: ");
            System.out.println("  " + tasks.get(taskNumberDeleted));
            tasks.remove(taskNumberDeleted);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }catch (DException e){
            System.out.println("☹ OOPS!!! The description of a delete cannot be empty.");
        }catch (IndexDoNotExistException e){
            System.out.println("☹ OOPS!!! The description of a delete is out of range.");
        }
    }

    private static void event(String line, ArrayList<Task> tasks) {
        try{
            if (Parser.parseCommand(line).isDescriptionEmpty()) {
                throw new DException();
            }
            System.out.println(" Got it. I've added this task:");
            tasks.add(new Event(line));
            System.out.println("  "+new Event(Parser.parseCommand(line).getInputMessage()));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }catch(DException e){
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        }
    }

    private static void deadline(String line, ArrayList<Task> tasks) {
        try{
            if (Parser.parseCommand(line).isDescriptionEmpty()) {
                throw new DException();
            }
            System.out.println(" Got it. I've added this task:");
            tasks.add(new Deadline(line));
            System.out.println("  "+new Deadline(Parser.parseCommand(line).getInputMessage()));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }catch(DException e){
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    private static void todo(String line, ArrayList<Task> tasks) {
        try{
            if (Parser.parseCommand(line).isDescriptionEmpty()) {
                throw new DException();
            }
            System.out.println(" Got it. I've added this task:");
            tasks.add(new Todo(line));
            System.out.println("  "+new Todo(Parser.parseCommand(line).getInputMessage()));
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }catch(DException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void done(String line, ArrayList<Task> tasks) {
        try{
            if (Parser.parseCommand(line).isDescriptionEmpty()) {
                throw new DException();
            }
            int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+","")) - 1;
            if(taskNumberCompleted> tasks.size()||taskNumberCompleted<0){
                throw new IndexDoNotExistException();
            }
            tasks.set(taskNumberCompleted, tasks.get(taskNumberCompleted).completeTask());
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println(" " + tasks.get(taskNumberCompleted));
        }catch (DException e){
            System.out.println("☹ OOPS!!! The description of a done cannot be empty.");
        }catch (IndexDoNotExistException e){
            System.out.println("☹ OOPS!!! The description of a done is out of range.");
        }
    }

    public static String readFile(ArrayList<Task> tasks, Scanner in, Scanner sc) {//read tasks from txt file
        String line;
        while(true){
            if(sc.hasNextLine()){
                line= sc.nextLine();
                if(line.startsWith("[T]")){
                    if(line.substring(3,6).equals("[\u2713]")){
                        line = "todo " + line.substring(7);
                        tasks.add(new Todo(line, true));
                    }else{
                        line = "todo " + line.substring(7);
                        tasks.add(new Todo(line));
                    }

                }
                if(line.startsWith("[D]")){
                    if(line.substring(3,6).equals("[\u2713]")){
                        line = "deadline " + line.substring(7,(line.indexOf("(") )) + "/ by" + line.substring(line.indexOf(":")+2,line.indexOf(")"));
                        tasks.add(new Deadline(line, true));
                    }else{
                        line = "deadline " + line.substring(7,(line.indexOf("(") )) + "/ by" + line.substring(line.indexOf(":")+2,line.indexOf(")"));
                        tasks.add(new Deadline(line));
                    }
                }
                if(line.startsWith("[E]")){
                    if(line.substring(3,6).equals("[\u2713]")){
                        line = "event " + line.substring(7,(line.indexOf("(") )) + "/ at" + line.substring(line.indexOf(":")+2,line.indexOf(")"));
                        tasks.add(new Event(line, true));
                    }else{
                        line = "event " + line.substring(7,(line.indexOf("(") )) + "/ at" + line.substring(line.indexOf(":")+2,line.indexOf(")"));
                        tasks.add(new Event(line));
                    }
                }
            }
            else {
                line= in.nextLine();
                break;
            }
        }
        return line;
    }

    public static void write(ArrayList<Task> tasks, File data) throws IOException {//write tasks to txt file
        FileWriter writer = new FileWriter(data,false);
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i) instanceof Deadline){
                writer.write((tasks.get(i)).toString() + "\n");}
            if(tasks.get(i) instanceof Event){
                writer.write((tasks.get(i)).toString() + "\n");}
            if(tasks.get(i) instanceof Todo){
                writer.write((tasks.get(i)).toString() + "\n");}
        }
        writer.close();
    }
}
