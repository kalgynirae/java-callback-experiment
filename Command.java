import java.util.HashMap;
import java.util.Map;

public enum Command {
    ECHO("/echo") {
        public boolean execute(String[] args) {
            for (String s : args) {
                System.out.print(s + " ");
            }
            System.out.println();
            return true;
        }
    },
    ME("/me") {
        public boolean execute(String[] args) {
            System.out.print("[username] ");
            if (args.length < 1) {
                System.out.println("Specify at least one argument.");
                return false;
            }
            for (String s : args) {
                System.out.print(s + " ");
            }
            System.out.println();
            return true;
        }
    },
    DELETE("/delete") {
        public boolean execute(String[] args) {
            if (args.length != 1) {
                System.out.println("Specify exactly one argument.");
                return false;
            }
            System.out.println(args[0] + " DELETED!");
            return true;
        }
    },
    EXIT("/exit") {
        public boolean execute(String[] args) {
            throw new ServerStopException();
        }
    };

    public abstract boolean execute(String[] args);

    String name;
    static Map<String, Command> commands = new HashMap<String, Command>();

    Command(String name) {
        this.name = name;
    }

    public static Command getByName(String name) {
        return commands.get(name);
    }

    static {
        // Save name -> Command pairs in HashMap for access by name later
        for (Command c : Command.values()) {
            commands.put(c.name, c);
        }
    }
}
