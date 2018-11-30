package com.dyrno4kin;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelAir extends JPanel {
    public static IAir air;
    public static boolean initialization = false;

    void setAir(IAir transport){
        air = transport;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(air != null) {
            air.DrawAir(g);
        }
    }
}
