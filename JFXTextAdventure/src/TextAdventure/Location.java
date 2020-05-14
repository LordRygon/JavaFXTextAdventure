package TextAdventure;

public class Location {

    private int x;
    private int y;
    private String description;

    public Location(int x, int y, String description) {
        this.x = x;
        this.y = y;
        this.description = description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDescription() {
        return description;
    }
}
