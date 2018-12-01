package com.dyrno4kin;
import javax.swing.JList;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelHangar extends JPanel{
    private  MultiLevelHangar hangar;
    private JList listBoxLevels;
    public final int countLevel = 5;
    public MultiLevelHangar getHangar() {
        return hangar;
    }
    public PanelHangar() {
        hangar = new MultiLevelHangar(countLevel, 615, 603);
    }

    public void setListLevels(JList listBoxLevels) {
        this.listBoxLevels = listBoxLevels;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int selectedLevel = listBoxLevels.getSelectedIndex();
        hangar.getHangar(selectedLevel).Draw(g);
        if(selectedLevel != -1){
            if(hangar != null) {
                hangar.getHangar(selectedLevel).Draw(g);
            }
        }
    }
}