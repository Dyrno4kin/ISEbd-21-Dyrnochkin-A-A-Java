package com.dyrno4kin;

import java.awt.*;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class FormHangar {

    private JFrame frame;
    private JPanel panel;
    private JTextField maskedTextBox1;
    Hangar<IAir> hangar;
    private PanelAir pictureBoxTakeAir;
    private PanelHangar panelHangar;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormHangar window = new FormHangar();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public FormHangar() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1050, 503);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        panelHangar= new PanelHangar();
        panelHangar .setBounds(10, 11, 768, 432);
        frame.getContentPane().add(panelHangar);
        hangar = panelHangar.getHangar();
        JPanel pictureBoxHangar = new JPanel();
        pictureBoxHangar.setBounds(0, 0, 778, 466);
        frame.getContentPane().add(pictureBoxHangar);

        JButton buttonSetAir = new JButton("Cамолет");
        buttonSetAir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
                Air air = new Air(100, 1000, mainColor);
                int place = hangar.Plus(air);
                PanelAir.initialization = true;
                panelHangar.repaint();
            }
        });
        buttonSetAir.setBounds(790, 13, 118, 40);
        frame.getContentPane().add(buttonSetAir);

        JButton buttonSetAirBus = new JButton("Аэробус");
        buttonSetAirBus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color mainColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
                Color dopColor = JColorChooser.showDialog(null, "Choose a color", Color.GRAY);
                AirBus air = new AirBus(100, 1000, mainColor, dopColor);
                int place = hangar.Plus(air);
                PanelAir.initialization = true;
                panelHangar.repaint();
            }
        });
        buttonSetAirBus.setBounds(790, 104, 118, 40);
        frame.getContentPane().add(buttonSetAirBus);

        JPanel panel = new JPanel();
        panel.setBounds(779, 226, 250, 230);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        pictureBoxTakeAir = new PanelAir();
        pictureBoxTakeAir.setBounds(12, 102, 250, 180);
        panel.add(pictureBoxTakeAir);

        JLabel label = new JLabel("Забрать самолет");
        label.setBounds(12, 0, 118, 16);
        panel.add(label);

        maskedTextBox1 = new JTextField();
        maskedTextBox1.setBounds(68, 29, 70, 22);
        panel.add(maskedTextBox1);
        maskedTextBox1.setColumns(10);

        JLabel label_1 = new JLabel("Место:");
        label_1.setBounds(12, 32, 56, 16);
        panel.add(label_1);

        JButton buttonTakeAir = new JButton("Забрать");
        buttonTakeAir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!maskedTextBox1.getText().equals("")) {
                    IAir air = hangar.Minus(Integer.parseInt(maskedTextBox1.getText()));
                    if (air != null) {
                        air.SetPosition(5,50, pictureBoxTakeAir.getWidth(), pictureBoxTakeAir.getHeight());
                        pictureBoxTakeAir.setAir(air);
                        pictureBoxTakeAir.repaint();
                        panelHangar.repaint();
                    } else {
                        pictureBoxTakeAir.setAir(null);
                        pictureBoxTakeAir.repaint();
                    }
                }
            }
        });
        buttonTakeAir.setBounds(22, 64, 97, 25);
        panel.add(buttonTakeAir);
    }
}
