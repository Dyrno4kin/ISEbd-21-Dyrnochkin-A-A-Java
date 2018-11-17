package com.dyrno4kin;
import java.awt.Color;
import java.awt.Graphics;
public class AirBus extends Air {
    private Color DopColor;
    public Color getDopColor(){
        return DopColor;
    }
    public void setDopColor(Color value) {
        DopColor = value;
    }
    public AirBus(int maxSpeed, float weight, Color mainColor, Color dopColor)
    {
        super(maxSpeed, weight, mainColor);
        DopColor = dopColor;
    }

    public void DrawAir(Graphics g) {
        super.DrawAir(g);
        g.setColor(MainColor);
        g.fillOval((int)_startPosX + 80, (int)_startPosY - 6, 60, 40);
        g.fillOval((int)_startPosX + 110, (int)_startPosY + 13, 40, 20);
        g.fillRect((int)_startPosX + 55, (int)_startPosY - 5, 60, 38);
        g.fillRect((int)_startPosX + 19, (int)_startPosY - 5, 60, 10);
        g.fillRect((int)_startPosX + 19, (int)_startPosY - 27, 15, 25);
        g.fillRect((int)_startPosX + 5, (int)_startPosY - 16, 40, 6);
        g.setColor(DopColor);
        g.fillOval((int)_startPosX + 120, (int)_startPosY + 3, 10, 10);
    }
}

