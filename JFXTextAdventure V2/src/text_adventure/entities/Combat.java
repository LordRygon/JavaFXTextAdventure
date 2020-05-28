package text_adventure.entities;

import text_adventure.GameText;
import text_adventure.entities.Entity;

import java.util.ArrayList;

public interface Combat {
    ArrayList<GameText> damageResults(Entity attacker, Entity target, double attackStrength);
    //damage results calls the next 3 methods, which are private to the class.
    boolean isTargetDefeated(Entity target);
    int actionDelayTime(Entity attacker);

}
