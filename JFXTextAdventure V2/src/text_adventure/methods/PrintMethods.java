package text_adventure.methods;

import javafx.scene.paint.Color;
import text_adventure.entities.Entity;
import text_adventure.GameText;
import text_adventure.Location;
import text_adventure.entities.Player;

import java.util.ArrayList;
import java.util.Collections;

public class PrintMethods {
    public static ArrayList<GameText> printIntro() {
        ArrayList<GameText> gameText = new ArrayList<>();
        Color color = Color.SILVER;
        String font = "Monospaced";
        int fontSize = 10;
        String fontStyle = "-fx-font-weight: bold";
        gameText.add(new GameText("     ____.                   _______________  ___      _____       .___                    __                        \n", color, font, fontSize, fontStyle));
        gameText.add(new GameText("    |    |____ ___  _______  \\_   _____/\\   \\/  /     /  _  \\    __| _/__  __ ____   _____/  |_ __ _________   ____  \n", color, font, fontSize, fontStyle));
        gameText.add(new GameText("    |    \\__  \\\\  \\/ /\\__  \\  |    __)   \\     /     /  /_\\  \\  / __ |\\  \\/ // __ \\ /    \\   __\\  |  \\_  __ \\_/ __ \\ \n", color, font, fontSize, fontStyle));
        gameText.add(new GameText("/\\__|    |/ __ \\\\   /  / __ \\_|     \\    /     \\    /    |    \\/ /_/ | \\   /\\  ___/|   |  \\  | |  |  /|  | \\/\\  ___/ \n", color, font, fontSize, fontStyle));
        gameText.add(new GameText("\\________(____  /\\_/  (____  /\\___  /   /___/\\  \\   \\____|__  /\\____ |  \\_/  \\___  >___|  /__| |____/ |__|    \\___  >\n", color, font, fontSize, fontStyle));
        gameText.add(new GameText("              \\/           \\/     \\/          \\_/           \\/      \\/           \\/     \\/                        \\/ \n\n", color, font, fontSize, fontStyle));
        gameText.add(new GameText("Type 'help' for commands\n"));
        return gameText;
    }
    public static ArrayList<GameText> commandNotFound(String text) {
        return new ArrayList<>(Collections.singletonList (new GameText("Command " + text + " not found\n", Color.RED)));
    }
    public static ArrayList<GameText> printHelp() {
        ArrayList<GameText> gameText = new ArrayList<>();
        gameText.add(new GameText("Help\t (h) \t: Commands\n"));
        gameText.add(new GameText("North\t (n) \t: Move North\n"));
        gameText.add(new GameText("South\t (s) \t: Move South\n"));
        gameText.add(new GameText("East\t (e) \t: Move East\n"));
        gameText.add(new GameText("West\t (w) \t: Move West\n"));
        gameText.add(new GameText("Look\t (l) \t: Look Around\n"));
        gameText.add(new GameText("Stats\t (st) \t: Character Information\n"));
        gameText.add(new GameText("Pause\t (p) \t: Pause/UnPause Game\n"));
        gameText.add(new GameText("Exit\t (x) \t: Exit game\n"));
        return gameText;
    }
    public static ArrayList<GameText> printStats(Player player)  {
        ArrayList<GameText> gameText = new ArrayList<>();
        gameText.add(new GameText("Name:\t\t", Color.LIGHTBLUE));
        gameText.add(new GameText(player.getName() + "\n", Color.GOLD));
        gameText.add(new GameText("Health:\t", Color.LIGHTBLUE));
        gameText.add(new GameText((int) player.getHealth() + "/" + (int) player.getMaxHealth()+ "\n", Color.GOLD));
        gameText.add(new GameText("Experience:\t", Color.LIGHTBLUE));
        gameText.add(new GameText(player.getExperience() + "\n", Color.GOLD));
        gameText.add(new GameText("Weapon:\t", Color.LIGHTBLUE));
        gameText.add(new GameText(player.getWeapon()+ "\n", Color.GOLD));
        return gameText;
    }
    public static ArrayList<GameText> printLocation(Player player, Location[][] locations) {
        ArrayList<GameText> gameText = new ArrayList<>();
        gameText.add(new GameText(locations[player.getX_location()][player.getY_location()].getDescription() +
                " (" + player.getX_location() + "," + player.getY_location() + ")" + "\n", Color.GREEN));
        ArrayList<Entity> entitiesLocation =  locations[player.getX_location()][player.getY_location()].getEntities();
        if (!entitiesLocation.isEmpty()) {
            for (Entity e: entitiesLocation) {
                gameText.add(new GameText("You see a"));
                gameText.add(new GameText(e.getName() + "\n", Color.RED));
            }
        }
        return gameText;
    }
}
