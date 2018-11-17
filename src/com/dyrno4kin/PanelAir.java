package com.dyrno4kin;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelAir extends JPanel {
    public static Air air;
    public static boolean initialization = false;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(initialization) {
            air.DrawAir(g);
        }
    }
}
