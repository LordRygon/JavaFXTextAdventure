package text_adventure.entities;

import javafx.scene.paint.Color;
import text_adventure.GameText;

import java.util.ArrayList;

public class Player extends Entity implements Combat {
    private String weapon;
    private double weaponStrength;
    private double healingRate;
    private long experience;

    public Player(String name, int maxHealth, int x_location, int y_location, String weapon) {
        super(name, maxHealth, x_location, y_location);
        this.weapon = weapon;
        this.weaponStrength = 10;
        this.healingRate = 0.2;
        this.experience = 0;
    }

    public Player(String name, int currentHealth, int maxHealth, boolean actionDelay, int actionDelayTime, int x_location, int y_location, String weapon) {
        super(name, currentHealth, maxHealth, actionDelay, actionDelayTime, x_location, y_location);
        this.weapon = weapon;
        this.weaponStrength = 10;
    }

    public String getWeapon() {
        return weapon;
    }

    public long getExperience() {
        return experience;
    }

    public double getWeaponStrength() {
        return weaponStrength;
    }
    public void autoheal() {
        if (this.getHealth()<this.getMaxHealth()) {
            modHealth(healingRate);
        }
    }

    @Override
    public ArrayList<GameText> damageResults(Entity attacker, Entity target, double attackStrength) {
        double randomDamage = attackStrength/2+ Math.random()*(attackStrength);
        ArrayList<GameText> combatMessage = new ArrayList<GameText>();
                combatMessage.add(new GameText("You attack the " +target.getName() + " with your " + getWeapon() + " for " + (int) randomDamage + "\n", Color.AQUA));
        target.modHealth(-1*( (int) randomDamage));
        attacker.setDelay(true,actionDelayTime(attacker));
        combatMessage.add(new GameText("Target health is " + (int) target.getHealth()+ "\n"));
        if (this.isTargetDefeated(target)) {
            combatMessage.add(new GameText("You have defeated the " + target.getName() + "\n", Color.PINK));
            this.experience += target.getMaxHealth(); //Gain experience based on targets max health
            combatMessage.add(new GameText("You gain " + (int) target.getMaxHealth() + " experience!\n", Color.LIMEGREEN));
        }
        return combatMessage;
    }

    @Override
    public boolean isTargetDefeated(Entity target) {
        if (target.getHealth()<=0) {
            return true;
        }
        return false;
    }

    @Override
    public int actionDelayTime(Entity attacker) {
        //Determine delay based on type of attack/attacker. Default 4000 for now
        return 4000;
    }
}
