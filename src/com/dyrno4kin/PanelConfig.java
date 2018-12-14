package com.dyrno4kin;

import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelConfig extends JPanel {
    private IAir air;

    void setAir(IAir transport) {
        air = transport;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (air != null) {
            air.DrawAir(g);
        }
    }
}
