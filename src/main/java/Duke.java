import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        final String SPLIT_LINE = "____________________________________________________________";
        System.out.println(SPLIT_LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(SPLIT_LINE);
        line = in.nextLine();
        while (!line.equals("bye")){
            if(line.equals("list")){
                System.out.println(SPLIT_LINE);
                for (int i = 0; i < tasks.size(); i++) {

                    System.out.println((i+1)+":"+"["+tasks.get(i).getStatusIcon()+"] "+tasks.get(i).description);
                }
                System.out.println(SPLIT_LINE);
            }else if(line.startsWith("done")){
                System.out.println(SPLIT_LINE);
                int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+",""))-1;
                tasks.set(taskNumberCompleted,tasks.get(taskNumberCompleted).completeTask());
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" "+":"+"["+tasks.get(taskNumberCompleted).getStatusIcon()+"] "+tasks.get(taskNumberCompleted).description);
                System.out.println(SPLIT_LINE);
            } else{
                System.out.println(SPLIT_LINE);
                System.out.println("added: "+line);
                tasks.add(new Task(line));
                System.out.println(SPLIT_LINE);
            }
            line = in.nextLine();
        }
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(SPLIT_LINE);
    }
}
