package com.dyrno4kin;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.Set;



public class Air {
    private float _startPosX;
    private float _startPosY;
    private int _pictureWidth;
    private int _pictureHeight;
    private int airWidth = 100;
    private int airHeight = 60;
    private int MaxSpeed;
    public int getMaxSpeed(){
        return MaxSpeed;
    }
    private void setMaxSpeed(int value){
        MaxSpeed = value;
    }

    private float Weight;
    public float getWeight(){
        return Weight;
    }
    private void setWeight(float value){
        Weight = value;
    }

    private Color MainColor;
    public Color getMainColor(){
        return MainColor;
    }
    private void setMainColor(Color value){
        MainColor = value;
    }

    private Color DopColor;
    public Color getDopColor(){
        return DopColor;
    }
    private void setDopColor(Color value){
        DopColor = value;
    }

    public Air(int maxSpeed, float weight, Color mainColor, Color dopColor) {
        setMaxSpeed (maxSpeed);
        setWeight(weight);
        setMainColor(mainColor);
        setDopColor (dopColor);
    }

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
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
        g.setColor(MainColor);
        g.fillOval((int)_startPosX + 80, (int)_startPosY - 6, 60, 40);
        g.fillOval((int)_startPosX + 110, (int)_startPosY + 13, 40, 20);
        g.fillRect((int)_startPosX + 55, (int)_startPosY - 5, 60, 38);
        g.fillRect((int)_startPosX + 19, (int)_startPosY - 5, 60, 10);
        g.fillRect((int)_startPosX + 19, (int)_startPosY - 27, 15, 25);
        g.fillRect((int)_startPosX + 5, (int)_startPosY - 16, 40, 6);
        g.fillRect((int)_startPosX + 45, (int)_startPosY - 5, 10, 30);
        g.setColor(MainColor);
        g.drawLine((int)_startPosX + 60, (int)_startPosY - 5, (int)_startPosX + 20, (int)_startPosY - 5);
        int yPos = 32;
        while (yPos > 4)
        {
            g.drawLine((int)_startPosX + 55, (int)_startPosY + yPos, (int)_startPosX + 20, (int)_startPosY + 4);
            yPos--;
        }
        g.setColor(DopColor);
        g.fillOval((int)_startPosX + 120, (int)_startPosY + 3, 10, 10);
    }
}
