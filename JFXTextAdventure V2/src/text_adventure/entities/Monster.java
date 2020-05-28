package text_adventure.entities;

import javafx.scene.paint.Color;
import text_adventure.GameText;

import java.util.ArrayList;

public class Monster extends Entity implements Combat {
    private String type;
    private int attackStrength;

    public Monster(String name, int maxHealth, int x_location, int y_location, String type) {
        super(name, maxHealth, x_location, y_location);
        this.type = type;
        attackStrength = 5;
    }

    public Monster(String name, int currentHealth, int maxHealth, boolean actionDelay, int actionDelayTime, int x_location, int y_location, String type) {
        super(name, currentHealth, maxHealth, actionDelay, actionDelayTime, x_location, y_location);
        this.type = type;
    }

    public int getAttackStrength() {
        return attackStrength;
    }

    @Override
    public ArrayList<GameText> damageResults(Entity attacker, Entity target, double attackStrength) {
        double randomDamage = attackStrength/2+ Math.random()*(attackStrength);
        ArrayList<GameText> combatMessage = new ArrayList<GameText>();
        combatMessage.add(new GameText("The " + attacker.getName() + " bites you for " + (int) randomDamage + "!\n", Color.DARKRED));
        target.modHealth(-1*( (int) randomDamage));
        attacker.setDelay(true,actionDelayTime(attacker));
        return combatMessage;
    }

    @Override
    public boolean isTargetDefeated(Entity target) {
        return false;
    }

    @Override
    public int actionDelayTime(Entity attacker) {
        //Determine delay time based on attacker, set default 3000 for now
        return 3000;
    }
}
