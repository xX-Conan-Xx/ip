import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) throws IOException {
        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        FileInputStream fis=new FileInputStream("C:\\Users\\23535\\Documents\\GitHub\\ip\\src\\data\\duke.txt");
        Scanner sc=new Scanner(fis);
        FileWriter writer = new FileWriter("C:\\Users\\23535\\Documents\\GitHub\\ip\\src\\data\\duke.txt");
        final String SPLIT_LINE = "____________________________________________________________";
        System.out.println(SPLIT_LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(SPLIT_LINE);
        if(sc.hasNextLine()){
            line=sc.nextLine();}
        else {
            line=in.nextLine();
        }
        while (!line.equals("bye")){
            try{
                if(line.equals("list")){
                    System.out.println(SPLIT_LINE);
                    for (int i = 0; i < tasks.size(); i++) {

                        System.out.println((i+1)+":"+tasks.get(i).toString());
                    }
                    System.out.println(SPLIT_LINE);
                }else if(line.startsWith("done")){
                    System.out.println(SPLIT_LINE);
                    int taskNumberCompleted = Integer.parseInt(line.replaceAll("\\D+",""))-1;
                    tasks.set(taskNumberCompleted,tasks.get(taskNumberCompleted).completeTask());
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(" "+":"+tasks.get(taskNumberCompleted));
                    System.out.println(SPLIT_LINE);
                } else if(line.startsWith("todo")){
                    if(line.equals("todo")){
                        throw new Exception();
                    }else{
                        System.out.println(SPLIT_LINE);
                        System.out.println(" Got it. I've added this task:");
                        tasks.add(new Todo(line));
                        System.out.println("  "+new Todo(line));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(SPLIT_LINE);
                    }
//                System.out.println(SPLIT_LINE);
//                System.out.println("added: "+line);
//                tasks.add(new Task(line));
//                System.out.println(SPLIT_LINE);
                } else if(line.startsWith("deadline")){
                    if(line.equals("deadline")){
                        throw new Exception();
                    }else{
                        System.out.println(SPLIT_LINE);
                        System.out.println(" Got it. I've added this task:");
                        tasks.add(new Deadline(line));
                        System.out.println("  "+new Deadline(line));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(SPLIT_LINE);
                    }
                }
                else if(line.startsWith("event")){
                    if(line.equals("event")){
                        throw new Exception();
                    }else{
                        System.out.println(SPLIT_LINE);
                        System.out.println(" Got it. I've added this task:");
                        tasks.add(new Event(line));
                        System.out.println("  "+new Event(line));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(SPLIT_LINE);
                    }
                }
                else if(line.startsWith("delete")){
                    System.out.println(SPLIT_LINE);
                    int taskNumberDeleted = Integer.parseInt(line.replaceAll("\\D+",""))-1;
                    tasks.set(taskNumberDeleted,tasks.get(taskNumberDeleted).completeTask());
                    System.out.println(" Noted. I've removed this task: ");
                    System.out.println("  "+tasks.get(taskNumberDeleted));
                    tasks.remove(taskNumberDeleted);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(SPLIT_LINE);
                }
                else if (line.startsWith("find")){
                    System.out.println(SPLIT_LINE);
                    System.out.println("  Here are the matching tasks in your list:\n");
                    String keyword = line.substring(4);
                    int count = 1;
                    for(int i=0;i< tasks.size();i++){
                        if(tasks.get(i).description.contains(keyword)){

                            System.out.println(count +": " + tasks.get(i));
                            count ++;
                        }

                    }

                    System.out.println(SPLIT_LINE);
                }
                else {
                    System.out.println(SPLIT_LINE);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(SPLIT_LINE);
                }
            }catch (Exception e){
                if(line.equals("todo"))
                {   System.out.println(SPLIT_LINE);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(SPLIT_LINE);
                }
                if(line.equals("deadline"))
                {   System.out.println(SPLIT_LINE);
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(SPLIT_LINE);
                }
                if(line.equals("event"))
                {   System.out.println(SPLIT_LINE);
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(SPLIT_LINE);
                }
            }
            if(sc.hasNextLine()){
                line=sc.nextLine();}
            else {
                line=in.nextLine();
            }
        }
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i) instanceof Deadline){
                writer.write("deadline " +tasks.get(i).description);}
            if(tasks.get(i) instanceof Event){
                writer.write("event " +tasks.get(i).description);}
            if(tasks.get(i) instanceof Todo){
                writer.write("todo " +tasks.get(i).description);}

        }
        writer.close();
        System.out.println(SPLIT_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SPLIT_LINE);
    }
}
