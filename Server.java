import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        // Read and execute commands indefinitely
        Scanner scanner = new Scanner(System.in);
        String prompt = "> ";

        System.out.println("Welcome! Type /exit to quit.");
        while (true) {
            System.out.print(prompt);
            try {
                do_command(scanner.nextLine());
            }
            catch (InvalidCommandException e) {
                System.out.println("Invalid command!");
            }
            catch (ServerStopException e) {
                break;
            }
        }
    }

    static void do_command(String command) throws InvalidCommandException {
        Scanner scanner = new Scanner(command);
        String name;
        // Grab command name
        try {
            name = scanner.next();
        }
        catch (NoSuchElementException e) {
            name = "";
        }
        // Grab arguments
        List<String> arguments = new ArrayList<String>();
        while (scanner.hasNext()) {
            arguments.add(scanner.next());
        }
        String[] args = arguments.toArray(new String[0]);
        // Execute command
        Command c = Command.getByName(name);
        if (c != null) {
            c.execute(args);
        }
        else {
            throw new InvalidCommandException();
        }
    }
}
