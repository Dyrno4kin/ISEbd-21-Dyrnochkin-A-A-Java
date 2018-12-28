package com.dyrno4kin;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MultiLevelHangar {
    ArrayList<Hangar<IAir>> parkingStages;
    private  int countPlaces = 20;
    private int pictureWidth;
    private int pictureHeight;
    public MultiLevelHangar(int countStages, int pictureWidth, int pictureHeight)
    {
        parkingStages = new ArrayList<Hangar<IAir>>();
        for(int i =0; i< countStages; ++i)
        {
            parkingStages.add(new Hangar<IAir>(countPlaces, pictureWidth, pictureHeight));
        }
    }


    public Hangar<IAir> getHangar(int ind){
        if((ind>-1) && (ind < parkingStages.size()))
        {
            return parkingStages.get(ind);
        }
        return null;
    }

    public boolean saveData(String filename) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            writeToFile("HangarStages:" + parkingStages.size() + System.lineSeparator(), bw);
            for (Hangar<IAir> level : parkingStages) {
                writeToFile("Level" + System.lineSeparator(), bw);
                for (int i = 0; i < countPlaces; i++) {
                    IAir air = level.getAir(i);
                    if (air != null) {
                        if (air.getClass().getSimpleName().equals("Air")) {
                            writeToFile(i + ":Air:" + air.getInfo(), bw);
                        }
                        if (air.getClass().getSimpleName().equals("AirBus")) {
                            writeToFile(i + ":AirBus:" + air.getInfo(), bw);
                        }
                        writeToFile(System.lineSeparator(), bw);
                    }
                }
            }
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    private void writeToFile(String text, BufferedWriter writer) {
        try {
            char[] info = text.toCharArray();
            writer.write(info, 0, info.length);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean loadData(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        }
        String bufferTextFromFile = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                bufferTextFromFile += line + "\n";
            }
            String[] strs = bufferTextFromFile.split("\n");
            if (strs[0].contains("HangarStages")) {
                int count = Integer.parseInt(strs[0].split(":")[1]);
                if (parkingStages != null) {
                    parkingStages.clear();
                }
                parkingStages = new ArrayList<Hangar<IAir>>(count);
            } else {
                return false;
            }
            int counter = -1;
            IAir air = null;
            for (int i = 1; i < strs.length; ++i) {
                if (strs[i].equals("Level")) {
                    counter++;
                    parkingStages.add(new Hangar<IAir>(countPlaces, pictureWidth, pictureHeight));
                    continue;
                }
                if (strs[i].isEmpty() || strs[i] == null) {
                    continue;
                }
                if (strs[i].split(":")[1].equals("Air")) {
                    air = new Air(strs[i].split(":")[2]);
                } else if (strs[i].split(":")[1].equals("AirBus")) {
                    air = new AirBus(strs[i].split(":")[2]);
                }
                parkingStages.get(counter).setAir(Integer.parseInt(strs[i].split(":")[0]), air);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
