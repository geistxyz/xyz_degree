/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arminda Yovana Soto
 */
public class FileManager {

    private final String SEP = StringUtilities.FILE_SEPARATOR.getValue();

    public FileManager() {
    }

    /**
     * Writes new lines to end of the file
     * @param newLine lines that will be wrotes into the file
     */
    public void write(String filePath, String line) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                PrintWriter printer = new PrintWriter(writer);
                printer.write(line);
                writer.newLine();
                printer.close();
            } else {
                System.out.println("the file not exist, can not be wrote");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE,
                    "FileWriter cann't be instanced for the write", ex.getClass());
        }
    }

    /**
     * Reads a file and shows the lines by means of commnad line
     */
    public void read(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);
                while (reader.ready()) {
                    System.out.println(reader.readLine());
                }
                reader.close();
            } else {
                System.out.println("the file not exist, can not be readed");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE,
                    "File can not be readed", ex.getClass());
        }

    }

    /**
     * Reads a file and shows the lines by means of commnad line
     */
    public String[] readLine(File file) {
        List<String> list = new ArrayList<String>();
        try {
            if (file.exists()) {
                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);
                while (file.canRead()) {
                    list.add(reader.readLine());
                }
                reader.close();
            } else {
                System.out.println("the file not exist, can not be readed");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE,
                    "File can not be readed", ex.getClass());
        }
        return list.toArray(new String[0]);
    }

    /**
     * Writes new lines to end of the file
     * @param newLine lines that will be wrotes into the file
     */
    public void write(String filePath, String[] lines) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);
                PrintWriter printer = new PrintWriter(writer);
                for (String line : lines) {
                    printer.write(line);
                    writer.newLine();
                }
                printer.close();
            } else {
                System.out.println("the file not exist, can not be wrote");
            }
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE,
                    "FileWriter cann't be instanced for the write", ex.getClass());
        }
    }

    /**
     * Creates a new file if not exist
     * @param path the file path
     * @param fileName a name of this file
     * @return a new file
     */
    public File notExistFileCreate(String filePath, String fileName) {
        File file = new File(filePath.concat(SEP).concat(fileName));
        File resultFile = null;
        if (file.exists() && file.isFile()) {
            resultFile = file;
        } else {
            try {
                file.createNewFile();
                resultFile = file;
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "file not had created");
            }
        }
        return resultFile;
    }

    /**
     * Deletes the file
     * @param file
     * @return
     */
    public File clearFile(File file) {
        String filePath = file.getAbsolutePath();
        File result = null;
        if (file.exists() && file.isFile()) {
            try {
                file.delete();
                result = new File(filePath);
                result.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "");
            }
        }
        return result;
    }
}
