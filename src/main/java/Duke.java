import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        final String SPLIT_LINE = "____________________________________________________________";
        System.out.println(SPLIT_LINE);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(SPLIT_LINE);
        line = in.nextLine();
        while (!line.equals("bye")){
            System.out.println(SPLIT_LINE);
            System.out.println(line);
            System.out.println(SPLIT_LINE);
            line = in.nextLine();
        }
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(SPLIT_LINE);
    }
}
