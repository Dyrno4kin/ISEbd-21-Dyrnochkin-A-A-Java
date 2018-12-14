package com.dyrno4kin;
import java.awt.Color;
import java.awt.Graphics;
public class AirBus extends Air {
    private Color dopColor;
    public Color getDopColor(){
        return dopColor;
    }
    public void setDopColor(Color dopColor) {
        this.dopColor = dopColor;
    }
    public void setDopColor(String colorName) {
        switch (colorName) {
            case "yellow":
                dopColor = Color.YELLOW;
                break;
            case "blue":
                dopColor = Color.BLUE;
                break;
            case "red":
                dopColor = Color.RED;
                break;
            case "green":
                dopColor = Color.GREEN;
                break;
            case "black":
                dopColor = Color.BLACK;
                break;
            case "orange":
                dopColor = Color.ORANGE;
                break;
            case "grey":
                dopColor = Color.GRAY;
                break;
            case "white":
                dopColor = Color.WHITE;
                break;
        }

    }
    public AirBus(int maxSpeed, int weight, Color mainColor, Color dopColor)
    {
        super(maxSpeed, weight, mainColor);
        setDopColor(dopColor);
    }

    public void DrawAir(Graphics g) {
        super.DrawAir(g);
        g.setColor(mainColor);
        g.fillOval((int)_startPosX + 80, (int)_startPosY - 6, 60, 40);
        g.fillOval((int)_startPosX + 110, (int)_startPosY + 13, 40, 20);
        g.fillRect((int)_startPosX + 55, (int)_startPosY - 5, 60, 38);
        g.fillRect((int)_startPosX + 19, (int)_startPosY - 5, 60, 10);
        g.fillRect((int)_startPosX + 19, (int)_startPosY - 27, 15, 25);
        g.fillRect((int)_startPosX + 5, (int)_startPosY - 16, 40, 6);
        g.setColor(dopColor);
        g.fillOval((int)_startPosX + 120, (int)_startPosY + 3, 10, 10);
    }
}

