package com.dyrno4kin;
import java.awt.Color;
import java.awt.Graphics;
abstract public class Vehicle implements IAir {
    protected float _startPosX;
    protected float _startPosY;
    protected int _pictureWidth;
    protected int _pictureHeight;
    protected int MaxSpeed;
    public int getMaxSpeed(){
        return MaxSpeed;
    }
    protected void setMaxSpeed(int MaxSpeed){
        this.MaxSpeed = MaxSpeed;
    }
    protected float Weight;
    public float getWeight(){
        return Weight;
    }
    protected void setWeight(int Weight){
        this.Weight = Weight;
    }
    protected Color mainColor;
    public Color getMainColor(){
        return mainColor;
    }
    protected void setMainColor(Color mainColor){ this.mainColor = mainColor; }
    @Override
    public void setMainColor(String colorName){
        switch (colorName) {
            case "yellow":
                mainColor = Color.YELLOW;
                break;
            case "blue":
                mainColor = Color.BLUE;
                break;
            case "pink":
                mainColor = Color.PINK;
                break;
            case "green":
                mainColor = Color.GREEN;
                break;
            case "black":
                mainColor = Color.BLACK;
                break;
            case "orange":
                mainColor = Color.ORANGE;
                break;
            case "cyan":
                mainColor = Color.CYAN;
                break;
            case "magenta":
                mainColor = Color.MAGENTA;
                break;
        }

    }
    public void SetPosition(int x, int y, int width, int height)
    {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public abstract void DrawAir(Graphics g);

    public abstract void MoveTransport(Direction direction);
}
