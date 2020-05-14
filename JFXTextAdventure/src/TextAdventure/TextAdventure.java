package TextAdventure;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class TextAdventure extends Application {

    //Game variables
    private ScrollPane gameOutput = new ScrollPane();
    private TextField playerInput = new TextField();
    private TextFlow textFlow = new TextFlow();
    private Map<String, Command> commands = new HashMap<>();

    private int mapsize = 5;
    private Location[][] locations = new Location[mapsize][mapsize];
    private int currentX = 2;
    private int currentY = 2;

    private Player player = new Player("Yourname", 10, "Sword");


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("JavaFX Text Adventure");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    private Parent createContent() {
        playerInput.setOnAction(e -> {
            String inputText = playerInput.getText();
            playerInput.clear();
            runInput(inputText);
        });

        gameOutput.setPrefHeight(700 - 80);
        gameOutput.setFocusTraversable(false);

        VBox content = new VBox(10, gameOutput, playerInput);
        content.setPrefSize(900, 500);
        content.setPadding(new Insets(10));
        //The application.css sheet changes the background of the gameOutput Node. It is currently set to black.
        content.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        initGame();
        return content;
    }
    private void initGame() {
        printOutput("Welcome to JavaFX Text Adventure", Color.SILVER);
        initializeCommands();
        initializeLocations();
        printOutput(locations[currentX][currentY].getDescription() + " ("+currentX+","+currentY+")", Color.GREEN);
    }

    private void initializeCommands() {
        //Todo: link alias shortcut to command
        commands.put("help", new Command("help", "h", "Print all commands", this::printHelp));
        commands.put("north", new Command("go north", "n", "Move north", () -> move(0, -1)));
        commands.put("south", new Command("go south","s","Move south", () -> move(0, 1)));
        commands.put("east", new Command("go east", "e", "Move east", () -> move(1, 0)));
        commands.put("west", new Command("go west", "w", "Move west", () -> move(-1, 0)));
        commands.put("stats", new Command("stats", "st", "Display Stats", this::printStats));
        commands.put("exit", new Command("exit", "x", "Exit the game", Platform::exit));
    }

    private void initializeLocations() {
        for (int x = 0; x <mapsize; x++) {
            for (int y = 0; y <mapsize; y++ ) {
                locations[x][y] = new Location(x,y, "This is a empty room" );
            }
        }
    }

    public void printOutput(String text, Color color) {
        //Performs all the printing for the gameOutput box. Converts the input into a single line with the
        //desired color. This could be modified to print out different colors on the same line, change font, size, etc.
        Text textLine = new Text(" " + text + "\n");
        textLine.setFill(color);
        textLine.setFont(Font.font("monospaced", 16));
        textFlow.getChildren().add(textLine);
        gameOutput.setContent(textFlow);

        //This sets the scroll bar to the last position, the two calls to layout() are necessary, but there is
        //likely a more precise method to perform this action.
        textFlow.layout();
        gameOutput.layout();
        gameOutput.setVvalue(1.0f);
    }

    private void runInput(String text) {
        if (!commands.containsKey(text)) {
            printOutput("Command " + text + " not found", Color.RED);
        } else {
            commands.get(text).execute();
        }
    }

    private void printHelp() {
        commands.forEach((name, cmd) -> {
            printOutput(name + "\t" + cmd.getDescription(), Color.WHITE);
        });
    }

    private void move(int xMove, int yMove) {
        int newX = currentX + xMove;
        int newY = currentY + yMove;
        if ((newX<0)||(newX>=mapsize)||(newY<0)||(newY>=mapsize)) {
            printOutput("You can't go in that direction", Color.YELLOW);
        } else {
            currentX +=xMove;
            currentY +=yMove;

            printOutput(locations[currentX][currentY].getDescription() + " ("+currentX+","+currentY+")", Color.GREEN);

        }
    }

    private void printStats() {
        printOutput("Name:\t\t" + player.getName(), Color.GOLD);
        printOutput("Health:\t" + player.getCurrentHealth() + "/" + player.getMaxHealth(), Color.GOLD);
        printOutput("Weapon:\t"+ player.getWeapon(), Color.GOLD);
    }
}
