package text_adventure;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import text_adventure.entities.Entity;
import text_adventure.entities.Monster;
import text_adventure.entities.Player;
import text_adventure.methods.MonsterCombat;
import text_adventure.methods.PlayerActions;
import text_adventure.methods.PrintMethods;

import java.util.ArrayList;
import java.util.Collections;

public class TextAdventure extends Application {

    //Game variables (Some could/should be public)
    private ScrollPane gameOutput = new ScrollPane();
    private TextField playerInput = new TextField();
    private TextFlow textFlow = new TextFlow();

    private int mapsize = 5;
    private Location[][] locations = new Location[mapsize][mapsize];
    private Timeline timeline;
    private int gamespeed = 1000;

    private Player player = new Player("Yourname", 100, 2, 2, "Sword");
    private ArrayList<Monster> monsters = new ArrayList<Monster>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("JavaFX Text Adventure");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
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
        GameTimerEngine();
        return content;
    }

    private void initGame() {

        printOut(PrintMethods.printIntro());
        initializeLocations();
        initializeNPCs();
        printOut(PrintMethods.printLocation(player, locations));
    }

    private void initializeLocations() {
        for (int x = 0; x <mapsize; x++) {
            for (int y = 0; y <mapsize; y++ ) {
                locations[x][y] = new Location(x,y, "This is a empty room" );
            }
        }
    }

    private void initializeNPCs() {
        for (int i = 1; i<=10; i++) {
            monsters.add(new Monster("Giant Rat", (int) (10+Math.random()*20), (int) (Math.random()*mapsize), (int) (Math.random()*mapsize), "Rat"));
        }
        //Assign monsters to each location
        for (Monster mon: monsters) {
            locations[mon.getX_location()][mon.getY_location()].addEntities(mon);
        }
    }
    //This is the core method call for displaying text, uses GameText Class
    public void printOut(ArrayList<GameText> arrayGameText) {
        if (!arrayGameText.isEmpty()) {
            for (GameText txt: arrayGameText) {
                Text textLine = new Text(" " + txt.getText());
                textLine.setFill(txt.getColor());
                textLine.setFont(Font.font(txt.getFont(), txt.getFontSize()));
                textLine.setStyle(txt.getFontStyle());
                textFlow.getChildren().add(textLine);
                gameOutput.setContent(textFlow);
                //This sets the scroll bar to the last position, the two calls to layout() are necessary, but there is
                //likely a more precise method to perform this action.
                textFlow.layout();
                gameOutput.layout();
                gameOutput.setVvalue(1.0f);
            }
        }
    }
    //Processes player input
    private void runInput(String text) {
        switch(text.toLowerCase())
        {
            case "help": case "h": printOut(PrintMethods.printHelp());break;
            case "north": case "n":
            printOut(PlayerActions.move(locations, player, 0, -1, mapsize)); break;
            case "south": case "s":
            printOut(PlayerActions.move(locations, player, 0, 1, mapsize)); break;
            case "east": case "e":
            printOut(PlayerActions.move(locations, player, 1, 0, mapsize)); break;
            case "west": case "w":
            printOut(PlayerActions.move(locations, player, -1, 0, mapsize)); break;
            case "look": case "l": case "": printOut(PrintMethods.printLocation(player, locations));break;
            case "stats": case "st": printOut(PrintMethods.printStats(player));break;
            case "pause": case "p": pauseGame();break;
            case "attack": case "a": printOut(PlayerActions.playerCombat(player, locations, monsters));break;
            case "exit": case "x": timeline.stop();Platform.exit();break;
            default: printOut(PrintMethods.commandNotFound(text));
        }
    }

    private void pauseGame() {
        if (timeline.getCurrentRate()>0) {
            timeline.pause();
            printOut(new ArrayList<>(Collections.singletonList(new GameText("Game is paused\n", Color.ORANGERED))));
        } else {
            printOut(new ArrayList<>(Collections.singletonList(new GameText("Game is unpaused\n", Color.ORANGERED))));
            timeline.play();
        }
    }

    //GameTimerEngine handles time-based actions, mostly passive
    private void GameTimerEngine() {
        this.timeline = new Timeline(new KeyFrame(
                Duration.millis(gamespeed),
                ae -> {
                    //This is where we call commands to update player, monster, and environmental variables at set intervals;
                    if (player.isDelayed())   {
                        if (!player.updateActionTimer(gamespeed))
                            printOut(new ArrayList<>(Collections.singletonList (new GameText("You are ready\n", Color.SILVER))));
                    }
                    player.autoheal();
                    //Monster attacking check
                    ArrayList<Entity> entitiesLocation =  locations[player.getX_location()][player.getY_location()].getEntities();
                    if (!entitiesLocation.isEmpty()) {
                        for (Entity e: entitiesLocation) {
                            if ((e instanceof Monster) && (!e.isDelayed())) { //check if the Entity is a monster and can do an action
                                printOut(MonsterCombat.attackPlayer((Monster) e,player));
                                if (player.getHealth()<=0) {
                                    printOut(new ArrayList<>(Collections.singletonList (new GameText("You have been killed.\n", Color.PALEVIOLETRED))));
                                    pauseGame(); //Just pauses the game if player dies

                                }
                            }
                        }
                    }
                    for (Monster mon: monsters) {
                        if (mon.isDelayed()) {
                            mon.updateActionTimer(gamespeed);
                        }
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
