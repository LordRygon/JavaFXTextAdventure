package text_adventure;

import text_adventure.entities.Entity;

import java.util.ArrayList;

public class Location {

    private int x;
    private int y;
    private String description;
    private ArrayList<Entity> entities = new ArrayList<>();

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

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void addEntities(Entity entity) {
        entities.add(entity);
    }
    public void removeEntities(Entity entity) {
        entities.remove(entity);
    }
    public int numberEntities() {
        return entities.size();
    }
}
