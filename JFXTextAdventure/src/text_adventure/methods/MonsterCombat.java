package text_adventure.methods;

import text_adventure.entities.Entity;
import text_adventure.GameText;
import text_adventure.entities.Monster;

import java.util.ArrayList;

public class MonsterCombat {

    public static ArrayList<GameText> attackPlayer(Monster attacker, Entity player) {
        return attacker.damageResults(attacker, player, attacker.getAttackStrength());
        //return new ArrayList<>(Collections.singletonList (new GameText("Monster has attacked\n", Color.OLDLACE)));

    }
}
