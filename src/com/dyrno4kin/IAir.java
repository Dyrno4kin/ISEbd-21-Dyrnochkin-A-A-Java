package com.dyrno4kin;

import java.awt.Graphics;

public interface IAir {
    void SetPosition(int x, int y, int width, int height);
    void MoveTransport(Direction direction);
    void DrawAir(Graphics g);
    void setMainColor(String colorName);
    String getInfo();
}