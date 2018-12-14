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
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FormHangar {

    private JFrame frame;
    private JPanel panel;
    private JList listBoxLevels;
    private DefaultListModel model;
    private JPanel panelTake;
    private int countLevel = 5;
    private JTextField maskedTextBox1;
    MultiLevelHangar hangar;
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
        frame.setBounds(100, 100, 980, 503);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        panelHangar= new PanelHangar();
        panelHangar .setBounds(0, 11, 777, 443);
        frame.getContentPane().add(panelHangar);
        hangar = panelHangar.getHangar();
        JPanel pictureBoxHangar = new JPanel();
        pictureBoxHangar.setBounds(0, 0, 778, 466);
        frame.getContentPane().add(pictureBoxHangar);

        JButton buttonSetAir = new JButton("Заказать самолет");
        buttonSetAir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                if (listBoxLevels.getSelectedIndex() > -1) {
                    DialogConfig dConfig = new DialogConfig(frame);
                    if (dConfig.isSuccessful()) {
                        PanelTakeHangar.air = dConfig.getAir();
                        int i = hangar.getHangar(listBoxLevels.getSelectedIndex()).Plus(PanelTakeHangar.air);
                        panelHangar.repaint();
                    }
                }
            }
        });
        buttonSetAir.setBounds(790, 141,  140, 41);
        frame.getContentPane().add(buttonSetAir);

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
                    IAir air = hangar.getHangar(listBoxLevels.getSelectedIndex()).Minus(Integer.parseInt(maskedTextBox1.getText()));
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

        listBoxLevels = new JList();
        listBoxLevels.setBounds(790, 11, 118, 118);
        frame.getContentPane().add(listBoxLevels);
        model = new DefaultListModel();
        for(int i = 0; i < 5; i++)
        {
            model.addElement("Уровень " + (i + 1));
        }
        listBoxLevels.setModel(model);
        listBoxLevels.setSelectedIndex(0);
        panelHangar.setListLevels(listBoxLevels);
        listBoxLevels.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                panelHangar.repaint();
            }
        });

    }
}
