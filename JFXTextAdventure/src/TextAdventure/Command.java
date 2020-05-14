package TextAdventure;

public class Command {
    private String name;
    private String alias;
    private String description;
    private Runnable action;

    public Command(String name, String alias, String description, Runnable action) {
        this.name = name;
        this.alias = alias;
        this.description = description;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {
        action.run();
    }
}
