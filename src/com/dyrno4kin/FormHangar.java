package com.dyrno4kin;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.awt.EventQueue;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
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
    FileHandler fh;
    private static Logger logger= Logger.getLogger(FormHangar.class.getName());
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

        try {
            fh = new FileHandler("C:\\Users\\adres\\Desktop//log.txt");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException ex){
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuFile = new JMenu("File");
        menuBar.add(menuFile);

        JMenuItem menuSave = new JMenuItem("Save");
        menuSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser filesave = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt");
                filesave.setFileFilter(filter);
                if (filesave.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = filesave.getSelectedFile();
                    String path = file.getAbsolutePath();
                    try{
                        hangar.saveData(path);
                        JOptionPane.showMessageDialog(null, "Saved");
                        logger.info("Сохранено в файл " + file.getName());
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Save failed", "", 0, null);
                    }
                }
            }
        });
        menuFile.add(menuSave);

        JMenuItem menuLoad = new JMenuItem("Load");
        menuLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("txt file", "txt");
                fileChooser.setFileFilter(filter);
                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        hangar.loadData(file.getAbsolutePath());
                        JOptionPane.showMessageDialog(null, "Loaded");
                        logger.info("Загружено из файла " + file.getName());
                    } catch (HangarOccupiedPlaceException ex) {
                        JOptionPane.showMessageDialog(null, "Занято место", ex.getMessage(), JOptionPane.ERROR_MESSAGE);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Load failed", 0, null);
                    }
                    panelHangar.repaint();
                }
            }
        });
        menuFile.add(menuLoad);


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
                    try{
                        DialogConfig dConfig = new DialogConfig(frame);
                        if (dConfig.isSuccessful()) {
                            PanelTakeHangar.air = dConfig.getAir();
                            int i = hangar.getHangar(listBoxLevels.getSelectedIndex()).Plus(PanelTakeHangar.air);
                            logger.info("Добавлен самолет " + PanelTakeHangar.air.getInfo() + " на место " + i);
                            panelHangar.repaint();
                        }
                    }catch(HangarOverflowException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Переполнение", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
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
                    try{
                        IAir air = hangar.getHangar(listBoxLevels.getSelectedIndex()).Minus(Integer.parseInt(maskedTextBox1.getText()));
                        air.SetPosition(5, 5, pictureBoxTakeAir.getWidth(), pictureBoxTakeAir.getHeight());
                        pictureBoxTakeAir.setAir(air);
                        pictureBoxTakeAir.repaint();
                        panelHangar.repaint();
                        logger.info("Изъят самолет " + air.getInfo() + " с места " + maskedTextBox1.getText());
                    } catch(HangarNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Не найдено", JOptionPane.ERROR_MESSAGE);
                    }
                    panelHangar.repaint();
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
