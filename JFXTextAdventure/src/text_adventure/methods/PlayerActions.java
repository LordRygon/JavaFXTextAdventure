package text_adventure.methods;

import javafx.scene.paint.Color;
import text_adventure.*;
import text_adventure.entities.Entity;
import text_adventure.entities.Monster;
import text_adventure.entities.Player;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerActions {
    public static ArrayList<GameText> move(Location[][] locations, Player player, int xMove, int yMove, int mapsize) {

        int newX = player.getX_location() + xMove;
        int newY = player.getY_location() + yMove;
        if ((newX < 0) || (newX >= mapsize) || (newY < 0) || (newY >= mapsize)) {
            return new ArrayList<>(Collections.singletonList(new GameText("You can't go in that direction\n", Color.YELLOW)));

        } else if (player.isDelayed()) {
            return new ArrayList<>(Collections.singletonList(new GameText("You're too tired, rest a little bit longer.\n", Color.RED)));
        } else {
            player.setX_location(newX);
            player.setY_location(newY);
            player.setDelay(true, 4000);
            return PrintMethods.printLocation(player, locations);
        }
    }
    public static ArrayList<GameText> playerCombat(Player player, Location[][] locations, ArrayList<Monster> monsters) {
        if (!locations[player.getX_location()][player.getY_location()].getEntities().isEmpty()) {
            ArrayList<GameText> combatResults;
            if (!player.isDelayed()) {
                ArrayList<Entity> target = locations[player.getX_location()][player.getY_location()].getEntities();
                combatResults = player.damageResults(player, target.get(0), player.getWeaponStrength()); //Right now, just attacks first monster at location

                if (player.isTargetDefeated(target.get(0))) {
                    monsters.remove((Monster) target.get(0));
                    locations[player.getX_location()][player.getY_location()].removeEntities(target.get(0));
                }
                return combatResults;
            } else {
                return new ArrayList<>(Collections.singletonList(new GameText("You need to wait before you can attack again\n", Color.LIGHTGOLDENRODYELLOW)));
            }

        } else {
            return new ArrayList<>(Collections.singletonList(new GameText("There is no one to attack here\n", Color.LIGHTGOLDENRODYELLOW)));
        }

    }
}

