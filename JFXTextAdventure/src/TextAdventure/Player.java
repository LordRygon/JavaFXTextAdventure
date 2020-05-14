package TextAdventure;

public class Player {
    private String name;
    private int currentHealth;
    private int maxHealth;
    private String Weapon;

    public Player(String name, int Health, String weapon) {
        this.name = name;
        this.currentHealth = Health;
        this.maxHealth = Health;
        Weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public String getWeapon() {
        return Weapon;
    }
}
