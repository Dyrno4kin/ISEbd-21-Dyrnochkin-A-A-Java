package com.dyrno4kin;
import java.awt.Color;
import java.awt.Graphics;

public class Air extends Vehicle {
    private int airWidth = 100;
    private int airHeight = 60;

    public Air(int maxSpeed, int weight, Color mainColor) {
        setMaxSpeed(maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
    }

    public Air(String info) {
        String[] str = info.split(";");
        if(str.length == 5) {
            MaxSpeed = Integer.parseInt(str[0]);
            Weight = Float.parseFloat(str[1]);
            mainColor = new Color(Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]));
        }
    }

    public void MoveTransport(Direction direction) {

        float step = getMaxSpeed() * 100 / getWeight();
        switch (direction) {
            case Right:
                if (_startPosX + step < _pictureWidth - airWidth) {
                    _startPosX += step;
                }
                break;
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX -= step;
                }
                break;
            case Up:
                if (_startPosY - step - 10 > 0) {
                    _startPosY -= step;
                }
                break;
            case Down:
                if (_startPosY + step < _pictureHeight - airHeight) {
                    _startPosY += step;
                }
                break;
        }
    }

    public void DrawAir(Graphics g) {
        g.setColor(mainColor);
        g.fillOval((int)_startPosX + 110, (int)_startPosY + 13, 40, 20);
        g.fillRect((int)_startPosX + 55, (int)_startPosY + 5, 69, 28);
        g.fillRect((int)_startPosX + 21, (int)_startPosY - 25, 15, 31);
        g.fillRect((int)_startPosX + 7, (int)_startPosY - 16, 40, 6);
        g.fillOval((int)_startPosX + 120, (int)_startPosY + 4, 15, 10);
        g.fillOval((int)_startPosX + 125, (int)_startPosY + 11, 15, 10);
        g.fillOval((int)_startPosX + 123, (int)_startPosY + 10, 15, 10);
        int yPos = 32;
        while (yPos > 4)
        {
            g.drawLine((int)_startPosX + 55, (int)_startPosY + yPos, (int)_startPosX + 20, (int)_startPosY + 4);
            yPos--;
        }
        g.setColor(Color.blue);
        g.fillOval((int)_startPosX + 120, (int)_startPosY + 9, 12, 6);
    }

    @Override
    public String getInfo() {
        return MaxSpeed + ";" + Weight + ";"  + mainColor.getRed() + ";"
                + mainColor.getGreen() + ";" + mainColor.getBlue();
    }
}
