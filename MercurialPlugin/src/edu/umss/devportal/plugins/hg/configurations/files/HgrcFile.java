/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.configurations.files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Edits the repository Hgrc file
 * @author Arminda Yovana Soto
 */
public class HgrcFile {

    private final Map<String, Map<String, String>> sections;

    /**
     * Constructor initialize the map sections
     */
    public HgrcFile() {
        sections = new HashMap<String, Map<String, String>>();
    }

    /**
     * Loads the hgrc file
     * @param filename hgrc
     * @throws FileNotFoundException
     */
    public HgrcFile(String filename) throws FileNotFoundException {
        this();
        load(filename);
    }

    /**
     * Sets the name
     * @param section represent the [ui]
     * @param key sample username
     * @param value sample Yovana
     */
    public void setKeyValue(String section, String key, String value) {
        Map<String, String> section2 = getSection(section);
        if (section2 != null) {
            section2.put(key.toLowerCase(), value);
        }
    }

    /**
     * Returns all sections
     * @return
     */
    public Map<String, Map<String, String>> getSections() {
        return sections;
    }

    /**
     * Returns name and values of one section
     * @param section
     * @return
     */
    public Map<String, String> getSection(String section) {
        if (section == null) {
            return sections.get(null);
        }
        return sections.get(section.toLowerCase());
    }

    /**
     * Verifies that section value is null or empthy
     * @param section
     * @param key
     * @return
     */
    public boolean isNullOrEmpty(String section, String key) {
        String value = getKeyValue(section, key);
        return value == null || value.length() == 0;
    }

    /**
     * Gives a section s name value
     * @param section
     * @param key the name
     * @return
     */
    public String getKeyValue(String section, String key) {
        Map<String, String> section2 = getSection(section);
        if (section2 != null) {
            return section2.get(key.toLowerCase());
        }
        return null;
    }

    /**
     * Returns the
     * @param section
     * @param key
     * @return
     */
    public int getKeyIntValue(String section, String key) {
        return getKeyIntValue(section, key, 0);
    }

    /**
     * Returns the section
     * @param section
     * @param key
     * @param defaultValue
     * @return
     */
    public int getKeyIntValue(String section, String key, int defaultValue) {
        String value = getKeyValue(section, key.toLowerCase());
        if (value == null) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Loads the hgrc  file
     * @param filename
     * @throws FileNotFoundException
     */
    public void load(String filename) throws FileNotFoundException {
        FileInputStream in = new FileInputStream(filename);
        try {
            load(in);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                Logger.getLogger(HgrcFile.class.getName()).log(Level.SEVERE, "the file not found");
            }
        }
    }

    /**
     * Loads the hgrc file
     * @param in datas
     */
    private void load(InputStream in) {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(in));
            try {
                Map<String, String> section = null;
                String sectionName;
                for (String read = null; (read = input.readLine()) != null;) {
                    if (read.startsWith(";") || read.startsWith("#")) {
                        continue;
                    } else if (read.startsWith("[")) {
                        // new section
                        sectionName = read.substring(1, read.indexOf("]")).toLowerCase();
                        section = sections.get(sectionName);
                        if (section == null) {
                            section = new HashMap<String, String>();
                            sections.put(sectionName, section);
                        }
                    } else if (read.indexOf("=") != -1) {
                        if (section == null) {
                            // create the null-section entry
                            section = new HashMap<String, String>();
                            sections.put(null, section);
                        }
                        // new key
                        String key = read.substring(0, read.indexOf("=")).trim().toLowerCase();
                        String value = read.substring(read.indexOf("=") + 1).trim();
                        section.put(key, value);
                    }
                }
            } finally {
                input.close();
            }
        } catch (IOException e) {
            Logger.getLogger(HgrcFile.class.getName()).log(Level.SEVERE, "the file not found");
        }
    }

    /**
     * Save the file 
     * @param filename new file name
     * @throws IOException
     */
    public void save(String filename) throws IOException {
        FileOutputStream out = new FileOutputStream(filename);
        try {
            save(out);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    /**
     * Saves the file
     * @param out datas outlines
     */
    private void save(OutputStream out) {
        try {
            PrintWriter output = new PrintWriter(out);

            try {
                for (String section : sections.keySet()) {
                    output.println("[" + section + "]");
                    for (Map.Entry<String, String> entry : getSection(section).entrySet()) {
                        output.println(entry.getKey() + "=" + entry.getValue());
                    }
                }
            } finally {
                output.close();
            }
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
            }
        }
    }

    /**
     * Adds sections and its values
     * @param section
     * @param name
     * @param value
     */
    public void addSectionAndValue(String section, String name, String value) {
        HashMap mapValues = new HashMap<String, String>();
        mapValues.put(name, value);
        sections.put(section.toLowerCase(), mapValues);
    }

    /**
     * Adds Values into section
     * @param section
     * @param name
     * @param value
     */
    public void addValuesToSection(String section, String name, String value) {
        HashMap mapValues = new HashMap<String, String>();
        String sect = section.toLowerCase();
        if (sections.containsKey(sect)) {
            Map<String, String> get = sections.get(sect);
            String put = get.put(name, value);
            sections.put(sect, get);

        } else {
            Logger.getLogger(HgrcFile.class.getName()).log(Level.SEVERE, "section not exist");
        }
    }

    /**
     * Remove the section include its datas
     * @param section
     */
    public void removeSection(String section) {
        if (sections.containsKey(section.toLowerCase())) {
            sections.remove(section.toLowerCase());
        }
    }
}
