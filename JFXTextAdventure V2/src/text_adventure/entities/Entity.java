package text_adventure.entities;

public class Entity {
    private String name;
    private double health;
    private double maxHealth;
    private boolean actionDelay;
    private int actionDelayTime;
    private int x_location;
    private int y_location;

    public Entity(String name, int maxHealth, int x_location, int y_location) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.x_location = x_location;
        this.y_location = y_location;
    }

    public Entity(String name, int currentHealth, int maxHealth, boolean actionDelay, int actionDelayTime, int x_location, int y_location) {
        this.name = name;
        this.health = currentHealth;
        this.maxHealth = maxHealth;
        this.actionDelay = actionDelay;
        this.actionDelayTime = actionDelayTime;
        this.x_location = x_location;
        this.y_location = y_location;
    }

    public void setDelay(boolean tired, int waitTime) {
        this.actionDelay = tired;
        this.actionDelayTime = waitTime;
    }

    public boolean isDelayed() {
        return actionDelay;
    }
    public boolean updateActionTimer(int increment) {
        if (this.actionDelayTime <= 0) {
            this.actionDelay= false;
            return false;
        } else {
            this.actionDelayTime -=increment;
            return true;
        }
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public int getX_location() {
        return x_location;
    }

    public int getY_location() {
        return y_location;
    }

    public void setX_location(int x_location) {
        this.x_location = x_location;
    }

    public void setY_location(int y_location) {
        this.y_location = y_location;
    }

    public void modHealth(double modHealth) {
        this.health += modHealth;
    }
}
