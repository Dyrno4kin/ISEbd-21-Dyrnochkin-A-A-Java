package com.dyrno4kin;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelTakeHangar extends JPanel {
    public static IAir air;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (air != null)
        {
            air.SetPosition(5, 5, this.getWidth(), this.getHeight());
            air.DrawAir(g);
        }
    }
}