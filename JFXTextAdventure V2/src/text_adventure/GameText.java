package text_adventure;

import javafx.scene.paint.Color;

public class GameText {
    private String text;
    private Color color = Color.WHITE;
    private String font = "Monospaced";
    private int fontSize = 12;
    private String fontStyle = "-fx-font-weight: bold";

    public GameText(String text) {
        this.text = text;
    }

    public GameText(String text, Color color) {
        this.text = text;
        this.color = color;
    }

    public GameText(String text, Color color, String font, int fontSize, String fontStyle) {
        this.text = text;
        this.color = color;
        this.font = font;
        this.fontSize = fontSize;
        this.fontStyle = fontStyle;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }

    public String getFont() {
        return font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }
}

