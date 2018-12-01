package com.dyrno4kin;

import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelHangar extends JPanel{
    public  Hangar<IAir> hangar;

    public Hangar<IAir> getHangar() {
        return hangar;
    }

    public PanelHangar() {
        hangar = new Hangar<>(20, 615, 603);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(hangar != null) {
            hangar.Draw(g);
        }
    }
}